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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.api.ProceduresApi;

import docking.WindowPosition;
import docking.action.*;
import ghidra.framework.plugintool.ComponentProviderAdapter;
import ghidra.framework.plugintool.PluginTool;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.Program;
import ghidra.util.HelpLocation;
import ghidra.util.Msg;
import io.swagger.client.ApiClient;
import resources.ResourceManager;
import unknowncyberplugin.components.panels.MainPanel;


public class UnknownCyberFileProvider extends ComponentProviderAdapter {
	private final static String PREV_IMAGE = "/images/check_icon.jpg";
	private final static HelpLocation HELP = new HelpLocation("SampleHelpTopic", "SampleHelpTopic_Anchor_Name");
	private DockingAction action;

	// Set component references ahead of time so they can be accessed at a global level
	// as opposed to being passed into functions as a local reference.
	private ApiClient apiClient;
	private FilesApi filesApi;
	private ProceduresApi procApi;

	// GUI's main panel which holds all other panels
	private JPanel mainPanel;

  private Program program;
	private FunctionIterator fIterator;

	// Declare file-dependent variables in advance
	File originalFile;
	String originalSha1;
	String originalSha512;
	public void setProgram(Program programIn) {
		program = programIn;
		if (program != null) {
			// Set up program-dependent function iterator
			fIterator = program.getFunctionManager().getFunctions(true);

			// Attempt to hash file locally, alert user on failure
			try {
				originalFile = new File(program.getExecutablePath());
				originalSha1 = helpers.hashFile(originalFile, "SHA-1");
				originalSha512 = helpers.hashFile(originalFile, "SHA-512");
				// TODO: enable upload buttons if previously disabled
			} catch (FileNotFoundException e) {
				// TODO: disable upload buttons
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
			} catch (Exception e) {
				// TODO: disable upload buttons
				announce(
					"ERROR\n" +
					"\n" +
					"An unexpected error has occurred when trying to access the original copy of this file.\n" +
					"Therefore, this file cannot be uploaded to Unknown Cyber."
				);
			}
			// Check if file is accessible to user on the backend
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
		procApi = new ProceduresApi(apiClient);
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

	public ProceduresApi getProcApi() {
		return procApi;
	}

	public FunctionIterator getFunctionIterator(){
		return fIterator;
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
		mainPanel = new MainPanel(this);
	}

	// This is the built announce function that runs on the DummyButton
	// Original code used it in other places as well, as a general use function
	protected void announce(String message) {
		Msg.showInfo(getClass(),  mainPanel,  "Hello World showInfo", message);
	}
}
