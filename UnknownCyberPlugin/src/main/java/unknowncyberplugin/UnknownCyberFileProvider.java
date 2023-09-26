/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unknowncyberplugin;

import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import docking.ActionContext;
import docking.WindowPosition;
import docking.action.*;
import docking.widgets.EmptyBorderButton;

import ghidra.framework.model.DomainFile;
import ghidra.framework.plugintool.ComponentProviderAdapter;
import ghidra.framework.plugintool.PluginTool;
import ghidra.program.model.block.BasicBlockModel;
import ghidra.program.model.block.CodeBlock;
import ghidra.program.model.block.CodeBlockIterator;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.InstructionIterator;
import ghidra.program.model.listing.Program;
import ghidra.util.HelpLocation;
import ghidra.util.Msg;
import ghidra.util.task.TaskMonitor;
import resources.ResourceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.lingala.zip4j.ZipFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.model.EnvelopedFileList200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;
import io.swagger.client.ApiClient;


public class UnknownCyberFileProvider extends ComponentProviderAdapter {
	private final static String PREV_IMAGE = "/images/check_icon.jpg";
	private final static HelpLocation HELP = new HelpLocation("SampleHelpTopic", "SampleHelpTopic_Anchor_Name");
	private MyButton activeButtonObj;
	private JPanel mainPanel;
	private DockingAction action;
	
	// Set component references ahead of time so they can be accessed at a global level
	// as opposed to being passed into functions as a local reference.
	private JLabel idLabel, nameLabel, changedLabel, accessLabel;
	private JTable matchTable;
	private JButton submitButton, submitDisassembledButton;
	private JScrollPane matchScroller;
	private ApiClient apiClient;
	private FilesApi filesApi;

  private Program program;
	private FunctionIterator fIterator;

	public void setProgram(Program programIn) {
		program = programIn;
		if (program != null) {
			fIterator = program.getFunctionManager().getFunctions(true);
		}
	}

	
	public UnknownCyberFileProvider(PluginTool tool, String name) {
		super(tool, name, name);
		buildMainPanel();
		// TODO: Fix image loading
		setIcon(ResourceManager.loadImage("/images/x_icon.jpg"));
		// No idea what the help thing actually does
		setHelpLocation(HELP);
		setDefaultWindowPosition(WindowPosition.WINDOW);
		setTitle("Unknown Cyber File Interface");
		setVisible(true);
		// createActions();
		apiClient = new ApiClient();
		filesApi = new FilesApi(apiClient);
	}
	
	private void checkFileAccess(String hash) {
		// TODO: ping API for hash, check if user is owner of hash, set visible indicator for user
		// Used for easy display to user
	}
	
	private void submitFile() {		
		// TODO: disable submit/disassembly buttons at start
		// Re-enable buttons on completion

		File myFile = new File(program.getExecutablePath());
		List<File> files = Arrays.asList(myFile);
		try {
			EnvelopedFileUploadResponseList200 response = filesApi.uploadFile(files, "", Arrays.asList(), Arrays.asList(), "json", false, false, "", true, false, false, false, false, false, false);
			Msg.info(this, response);
		} catch (Exception e) {
			Msg.error(this, e);
		}
		// announce("Success or Failure");
		
		// Edit file access on success
	}
	
	private void submitDisassembly() {
		// Declare important paths here so they can be deleted in the finally clause
		Path procDirectory = null;
		Path fileJson = null;
		ZipFile zip = null;

		try {
			BasicBlockModel blockModel = new BasicBlockModel(program);

			// TODO: this keeps giving issues, but it HAS worked, as Lee can attest.
			// Leaving this for now to focus on more fruitful pursuits.
			// We can do a deep dive on this later, or have someone look in parallel.
			File originalFile = new File(program.getExecutablePath());
			// Calculate sha1 here since it's used elsewhere
			String originalSha1 = helpers.hashFile(originalFile, "SHA-1");

			// Generate the file's JSON data
			JSONObject fileData = new JSONObject();
			fileData.put("image_base", 0);
			fileData.put("md5", program.getExecutableMD5());
			fileData.put("sha1", originalSha1);
			fileData.put("sha256", program.getExecutableSHA256());
			fileData.put("sha512", helpers.hashFile(originalFile, "SHA-512"));
			// TODO: program.getExecutableFormat() does not return values that we use; it will need some form of mapping
			fileData.put("unix_filetype", program.getExecutableFormat());

			// Create and write to the file's JSON file
			fileJson = Files.createTempFile("", ".json");
			Files.write(fileJson, fileData.toJSONString().getBytes());

			// Create the procedure's subdirectory
			procDirectory = Files.createTempDirectory("");

			// Iterate over all functions in a program
			for (Function f : fIterator) {
				// Set top-level blocks array
				JSONArray blockArray = new JSONArray();

				// Iterate over blocks
				CodeBlockIterator blockIterator = blockModel.getCodeBlocksContaining(f.getBody(), TaskMonitor.DUMMY);
				while (blockIterator.hasNext()) {
					CodeBlock currentBlock = blockIterator.next();

					// Set line array that exists per block
					JSONArray lineArray = new JSONArray();

					// Iterate over lines
					InstructionIterator lineIterator = program.getListing().getInstructions(currentBlock, true);
					while (lineIterator.hasNext()) {
						// Create each JSON line object
						JSONObject lineJson = new JSONObject();
						// TODO: un-hardcode these values
						lineJson.put("startEA", 800);
						lineJson.put("endEA", 806);
						lineJson.put("type", "code");
						lineJson.put("bytes", "FF 25 E0 02 01 00");
						lineJson.put("mnem", "jmp");
						// Implementation for this will differ based on how operands is obtained
						lineJson.put("operands", new String[]{"dword ptr ds:102E0h"});
						lineJson.put("prolog_format", "jmp(dptr(ds+66272))");
						lineJson.put("api_call_name", null);
						lineJson.put("is_call", false);

						lineArray.add(lineJson);
					}

					// Create each JSON block object
					JSONObject blockJson = new JSONObject();
					blockJson.put("startEA", currentBlock.getMinAddress());
					blockJson.put("endEA", currentBlock.getMaxAddress());

					// Populate the JSON block's lines field with the line array
					blockJson.put("lines", lineArray);

					// Add the newly created JSON block object to the array
					blockArray.add(blockJson);
				}

				// Create and populate the overall JSON object for this procedure
				JSONObject procData = new JSONObject();
				procData.put("blocks", blockArray);
				procData.put("is_library", (f.isExternal() ? 128 : 0));
				procData.put("is_thunk", (f.isThunk() ? 128 : 0));
				procData.put("startEA", f.getEntryPoint());
				// TODO: getStackPurgeSize() as a way to calculate?
				procData.put("endEA", 806);
				procData.put("procedure_name", f.getName());
				// TODO: I can search for and find the segment name, but I can't figure out if/how ghidra can access it
				procData.put("segment_name", ".rsrc$02");
				// TODO: I need a better understanding of "Strings" to know what to look for here
				procData.put("strings", new String[0]);
				// TODO: look into getCalledFunctions(); look at getBody()
				procData.put("api_calls", new String[0]);
				// TODO: cfg will need further investigation
				procData.put("cfg", new JSONObject());

				// Create and write to the temporary file containing this procedure's JSON data
				Path procJson = Files.createTempFile(procDirectory, "", ".json");
				Files.write(procJson, procData.toJSONString().getBytes());
			}

			// Create zip file in temp directory, load in the file.json and procedure directory
			zip = new ZipFile(Files.createTempFile("", ".zip").toFile());
			zip.addFile(fileJson.toFile());
			zip.addFolder(procDirectory.toFile());

			try {
				// TODO: program.getExecutableFormat() does not return values that we use; it will need some form of mapping
				EnvelopedFileUploadResponse200 response = filesApi.uploadDisassembly(zip.getFile(), program.getExecutableFormat(), originalSha1, "json", false, false, "", true, false, false);
			} catch (Exception e) {
				Msg.error(this, e);
			}
		// TODO: Granularize try/catch behavior so we can have more intelligent error handling
		} catch (Exception e) {
			Msg.error(this, e);
		} finally {
			// Clean up
			if (fileJson != null) {
				if (fileJson.toFile().exists()) {
					fileJson.toFile().delete();
				}
			}
			if (procDirectory != null) {
				if (procDirectory.toFile().exists()) {
					procDirectory.toFile().delete();
				}
			}
			if (zip != null) {
				if (zip.getFile().exists()) {
					zip.getFile().delete();
				}
			}
		}
	}
	
	private void getFileMatches(String hash) {
		// TODO: get file match data from API
		// Load into data table
	}
	
	// This was used as part of the original code to create the menu bar popup action
	// Keeping for now as an example
	/*
	private void createActions() {
		action = new DockingAction("DockingAction", getName()) {
			@Override
			public void actionPerformed(ActionContext context) {
				announce("Hello World announcement");
			}
		};
		
		action.setEnabled(true);
		
		ImageIcon prevImage = ResourceManager.loadImage(PREV_IMAGE);
		// This adds an in-window menu dropdown
		action.setMenuBarData(new MenuData(new String[] {"Misc", "Unknown Cyber"}, prevImage));
		// This fires the action on keybind
		action.setKeyBindingData(new KeyBindingData(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK)));
		
		action.setToolBarData(new ToolBarData(prevImage));
		action.setDescription("Unknown Cyber setDescription");
		
		// No clue what this actually does
		action.setHelpLocation(HELP);
		
		// This adds the action to a local button in the in-window menu
		addLocalAction(action);
		
		// No idea how this is meant to fire
		// I assume it has to do with the myButton suppressing an unused warning...
		DockingAction popupAction = new DockingAction("Unknown Cyber Popup", getName()) {
			@Override
			public void actionPerformed(ActionContext context) {
				announce("Hello World announcement2");
				
				Object contextObject = context.getContextObject();
				
				@SuppressWarnings("unused")
				MyButton myButton = (MyButton) contextObject;
			}
			
			@Override
			public boolean isAddToPopup(ActionContext context) {
				if (context.getContextObject() instanceof MyButton) {
					return true;
				}
				return false;
			}
		};
		popupAction.setEnabled(true);
		popupAction.setPopupMenuData(new MenuData(new String[] { "Example of Popup" }));
		addLocalAction(popupAction);
	} //*/
	
	@Override
	public JComponent getComponent() {
		return mainPanel;
	}
	
	// This function puts together the UI
	private void buildMainPanel() {
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createTitledBorder("Example of a Component"));
		
		idLabel = new JLabel("ID LABEL");
		nameLabel = new JLabel("NAME LABEL");
		
		activeButtonObj = new MyButton("Dummy button");
		Font f = activeButtonObj.getFont();
		activeButtonObj.setFont(new Font(f.getFontName(), Font.BOLD, 14));
		
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(grid);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(idLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(nameLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(activeButtonObj, gbc);
		
		changedLabel = new JLabel("Has file changed?");
		accessLabel = new JLabel("Do I have access?");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(changedLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(accessLabel, gbc);
		
		String matchColumns[] = {"ID", "FILENAME", "MATCHES", "OWNED?"};
		String matchDummyData[][] = {
				{"0001", "dummy1", "16", "true"},
				{"0002", "dummy2", "2", "false"},
		};
		matchTable = new JTable(matchDummyData, matchColumns);
		// Setting bounds doesn't seem to do anything right now.  Whether it's a constraint of
		// the gridBag or whether I'm doing something wrong is unknown
		// matchTable.setBounds(30, 40, 200, 300);
		matchTable.setEnabled(false);
		// should show table in scrollpane, but the bastard doesn't want to accept a size
		//matchScroller = new JScrollPane(matchTable);
		//matchScroller.setBounds(30, 40, 200, 300);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(matchTable, gbc);
		
		submitButton = new JButton("Submit File");
		submitDisassembledButton = new JButton("Submit Disassembly");
		submitButton.addActionListener(new ActionListener() {
			// You can't just set this to a function, it has to be a 
			// public void actionPerformed(){}, but you can outsource to another function
			public void actionPerformed(ActionEvent e) {
				System.out.println("Submit button has been clicked");
				submitFile();
				// this is how to set label text
				// changedLabel.setText("DING");
			}
		});
		submitDisassembledButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Disassembly button has been clicked");
				submitDisassembly();
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(submitButton, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(submitDisassembledButton, gbc);
		
		mainPanel.add(panel, BorderLayout.CENTER);
	}
	
	// This is part of the original "Dummy Button" code.  Not sure what this is
	// actually doing or why it's not just a JButton.  JButtons seem to work just fine
	@Override
	public ActionContext getActionContext(MouseEvent event) {
		if (event != null) {
			Object source = event.getSource();
			if (source == activeButtonObj) {
				return new ActionContext(this, activeButtonObj);
			}
		}
		return null;
	}
	
	// This is the built announce function that runs on the DummyButton
	// Original code used it in other places as well, as a general use function
	protected void announce(String message) {
		Msg.showInfo(getClass(),  mainPanel,  "Hello World showInfo", message);
	}
	
	// This constructs the Dummy Button.  Again, no idea why it has to extend
	// the EmptyBorderButton instead of using a JButton
	private class MyButton extends EmptyBorderButton {
		MyButton(String name) {
			super(name);
			setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
			
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					announce("Hello World announcement3");
				}
			});
		}
	}
}
