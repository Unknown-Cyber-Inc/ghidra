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
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponse200;
import com.unknowncyber.magic.model.EnvelopedFileUploadResponseList200;

import docking.WindowPosition;
import docking.action.*;
import ghidra.framework.plugintool.ComponentProviderAdapter;
import ghidra.framework.plugintool.PluginTool;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.Program;
import ghidra.util.HelpLocation;
import ghidra.util.Msg;
import ghidra.util.task.TaskMonitor;
import io.swagger.client.ApiClient;
import net.lingala.zip4j.ZipFile;
import resources.ResourceManager;
import unknowncyberplugin.Components.*;
import unknowncyberplugin.Components.Buttons.*;



public class UnknownCyberFileProvider extends ComponentProviderAdapter {
	private final static String PREV_IMAGE = "/images/check_icon.jpg";
	private final static HelpLocation HELP = new HelpLocation("SampleHelpTopic", "SampleHelpTopic_Anchor_Name");
	private DockingAction action;

	// Set component references ahead of time so they can be accessed at a global level
	// as opposed to being passed into functions as a local reference.
	private ApiClient apiClient;
	private FilesApi filesApi;

	// GUI starts here
	private JPanel mainPanel;
	private JPanel fileButtonsPanel, filePanel, fileCRUDPanel;
	private JPanel centralPanel, centralCRUDPanel;
	private JPanel procButtonsPanel, procTablePanel;
	private JTabbedPane fileTabs, centralTabs;
	private JScrollPane fileScroll, centralScroll, procScroll;
	private FileList<String> fileList;
	private JTree centralTree;
	private JTable procTable;
	private JButton fileToggle, fileUpload, fileDisassemblyUpload;
	private JButton fileCreate, fileEdit, fileDelete;
	private JButton centralCreate, centralEdit, centralDelete;
	private JButton procToggle, procRequest;

    private Program program;
	private FunctionIterator fIterator;

	// Declare file-dependent variables in advance
	File originalFile;
	String originalSha1;
	String originalSha512;
	public void setProgram(Program programIn) {
		program = programIn;
		if (program != null) {
			fIterator = program.getFunctionManager().getFunctions(true);
			try {
				originalFile = new File(program.getExecutablePath());
				originalSha1 = helpers.hashFile(originalFile, "SHA-1");
				originalSha512 = helpers.hashFile(originalFile, "SHA-256");
			} catch (FileNotFoundException e) {
				announce(
					"ERROR\n" +
					"\n" +
					"Ghidra is unable to access the original copy of this file.\n" +
					"Therefore, this file cannot be uploaded to Unknown Cyber.\n" +
					"\n" +
					"Potential causes:\n" +
					" - The file is not present on your computer.\n" +
					" - The file has been moved from its original location.\n" +
					" - The file is inside an archive."
				);

				// TODO: disable upload buttons
			} catch (Exception e) {
				announce(
					"ERROR\n" +
					"\n" +
					"An unexpected error has occurred when trying to access the original copy of this file.\n" +
					"Therefore, this file cannot be uploaded to Unknown Cyber."
				);

				// TODO: disable upload buttons
			}
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
		// procsApi = new ProcsApi(apiClient);
	}

	public Program getProgram(){
		return program;
	}

	public String getHash(String hashType){
		return (hashType.equals("sha1") ? originalSha1 : originalSha512);
	}

	public FilesApi getFilesApi() {
		return filesApi;
	}

	public FunctionIterator getFunctionIterator(){
		return fIterator;
	}

	private void checkFileAccess(String hash) {
		// TODO: ping API for hash, check if user is owner of hash, set visible indicator for user
		// Used for easy display to user
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
			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				// file buttons panel
				fileButtonsPanel = new JPanel();
				fileButtonsPanel.setLayout(new FlowLayout());
					fileToggle = new FileToggleButton();
					fileUpload = new FileUploadButton(this);
				fileButtonsPanel.add(fileToggle);
				fileButtonsPanel.add(fileUpload);
				// file list panel
				filePanel = new JPanel();
				filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
					fileTabs = new JTabbedPane();
						DefaultListModel<String> fileListModel = new DefaultListModel<>();
						fileList = new FileList<>(fileListModel);
						fileScroll = new JScrollPane(fileList);
					fileTabs.addTab("Notes", fileScroll);
						DefaultListModel<String> fileListModel2 = new DefaultListModel<>();
						DefaultListModel<String> fileListModel3 = new DefaultListModel<>();
						FileList<String> fileList2 = new FileList<>(fileListModel2);
						FileList<String> fileList3 = new FileList<>(fileListModel3);
						JScrollPane fileScroll2 = new JScrollPane(fileList2);
						JScrollPane fileScroll3 = new JScrollPane(fileList3);
					fileTabs.addTab("Tags", fileScroll2);
					fileTabs.addTab("Matches", fileScroll3);
				filePanel.add(fileTabs);
					fileCRUDPanel = new JPanel();
					fileCRUDPanel.setLayout(new FlowLayout());
						fileCreate = new FileCreateButton();
						fileEdit = new FileEditButton();
						fileDelete = new FileDeleteButton();
					fileCRUDPanel.add(fileCreate);
					fileCRUDPanel.add(fileEdit);
					fileCRUDPanel.add(fileDelete);
				filePanel.add(fileCRUDPanel);
				// central tabbed tree panel
				centralPanel = new JPanel();
				centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
					centralTabs = new JTabbedPane();
						DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Placeholder name");
						DefaultMutableTreeNode notesRootNode = new DefaultMutableTreeNode("Notes");
						DefaultMutableTreeNode tagsRootNode = new DefaultMutableTreeNode("Tags");
						rootNode.add(notesRootNode);
						rootNode.add(tagsRootNode);
						DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
						centralTree = new CentralTree(treeModel);
						centralScroll = new JScrollPane(centralTree);
					centralTabs.addTab("Placeholder tab", centralScroll);
				centralPanel.add(centralTabs);
					centralCRUDPanel = new JPanel();
					centralCRUDPanel.setLayout(new FlowLayout());
						centralCreate = new CenterCreateButton();
						centralEdit = new CenterEditButton();
						centralDelete = new CenterDeleteButton();
					centralCRUDPanel.add(centralCreate);
					centralCRUDPanel.add(centralEdit);
					centralCRUDPanel.add(centralDelete);
				centralPanel.add(centralCRUDPanel);
				// procedure buttons panel
				procButtonsPanel = new JPanel();
				procButtonsPanel.setLayout(new FlowLayout());
					procToggle = new ProcToggleButton();
					procRequest = new ProcRetrievalButton();
				procButtonsPanel.add(procToggle);
				procButtonsPanel.add(procRequest);
				// procedure table panel
				procTablePanel = new JPanel();
				procTablePanel.setLayout(new BoxLayout(procTablePanel, BoxLayout.Y_AXIS));
					Object[][] rowData = {{"0x1", "5", "Mlwr", "0", "0"}};
					procTable = new ProcTable(rowData);
					procScroll = new JScrollPane(procTable);
				procTablePanel.add(procScroll);
			mainPanel.add(fileButtonsPanel);
			mainPanel.add(filePanel);
			mainPanel.add(centralPanel);
			mainPanel.add(procButtonsPanel);
			mainPanel.add(procTablePanel);
	}

	// This is the built announce function that runs on the DummyButton
	// Original code used it in other places as well, as a general use function
	protected void announce(String message) {
		Msg.showInfo(getClass(),  mainPanel,  "Hello World showInfo", message);
	}
}
