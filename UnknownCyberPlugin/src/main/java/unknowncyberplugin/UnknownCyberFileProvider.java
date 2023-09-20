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

import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionManager;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.database.function.FunctionManagerDB;

import docking.ActionContext;
import docking.WindowPosition;
import docking.action.*;
import docking.widgets.EmptyBorderButton;
import ghidra.framework.plugintool.ComponentProviderAdapter;
import ghidra.framework.plugintool.PluginTool;
import ghidra.util.HelpLocation;
import ghidra.util.Msg;
import resources.ResourceManager;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import ghidra.program.model.listing.Program;

import com.unknowncyber.magic.model.EnvelopedFileList200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;
import com.unknowncyber.magic.api.FilesApi;
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
	protected ApiClient apiClient;
	protected FilesApi filesApi;
    protected FunctionManager fMan;

    protected Program program;

    public void setProgram(Program programIn) {
        program = programIn;
        fMan = program.getFunctionManager();
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
//		createActions();
        apiClient = new ApiClient();
        filesApi = new FilesApi(apiClient);
	}
	
	private void checkFileAccess(String hash) {
		// TODO: ping API for hash, check if user is owner of hash, set visible indicator for user
		// Used for easy display to user
	}
	
	private void submitFile() {
		// TODO: submits whole file to API
		// Need to figure out actual filetype
		
		// TODO: disable submit/disassembly buttons at start
		// Run API call
		// Re-enable buttons on completion
		// Ping user of success/failure via popup

        File myFile = new File(program.getExecutablePath());
        List<File> files = Arrays.asList(myFile);
		try {
			EnvelopedFileUploadResponseList200 response = filesApi.uploadFile(files, "", Arrays.asList(), Arrays.asList(), "json", false, false, "", true, false, false, false, false, false, false);
		} catch (Exception e) {
			Msg.info(null, e);
		}
		announce("Success or Failure");
		
		// Edit file access on success
	}
	
	private void submitDisassembly() {
		// TODO: submits disassembled file to API
		// Need to figure out actual data type to intake
		// Need to figure out how to format for API
		
		// TODO: disable submit/disassembly buttons at start
		// Run API call
		// Re-enable buttons on completion
		// Ping user of success/failure

        Msg.debug(this, "functions!");
        Msg.debug(this, fMan.getFunctionCount());
        FunctionIterator funs = fMan.getFunctions(true);
        for (Function f : funs) {
          String name = f.getName();
          // OPen {name}.json file to write
             // create json payload
             /*
              * Use dummy values for now
              * {
              * blocks (list of objects)
              * startEA - int (in decimal)
              * endEA - int (in decimal)
              * lines (list of objects)
              * startEA - int (in decimal)
              * endEA - int (in decimal)
              * type - str
              * bytes - str
              * mnem - str
              * operands - List<str>
              * prolog_format - str
              * api_call_name - str
              * is_call - bool
              * is_library - bool (0 = false, 128 = true)  <==== isExternal()
              * is_thunk - bool (0 = false, 128 = true)  <=== isThunk()
              * startEA - int (in decimal)  <=== getEntryPoint()
              * endEA - int (in decimal)
              * procedure_name - str <=== getName()
              * segment_name - str
              * strings - List<str>
              * api_calls - List<str>
              * cfg - Dict<str, List[str]>
              * }
             */
          // Write to file
          /*
          Msg.debug(this, f.getName());
          Msg.debug(this, f.getEntryPoint());
          Msg.debug(this, f.isThunk());
          Msg.debug(this, f.isExternal()); // isLibrary
          Msg.debug(this, f.getReturnType());
          Msg.debug(this, f.getSignature());
          */
        }
        // Create archive
        // Add list of json files to it 
        // Upload archive
        try {
            Msg.debug(this, "Testing getting values from Ghidra");
        } catch (Exception e) {
            Msg.info(this, e);
        }
		announce("Success or Failure");
		
		// Edit file access on success?
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
