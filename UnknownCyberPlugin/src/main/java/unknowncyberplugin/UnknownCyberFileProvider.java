package unknowncyberplugin;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.api.ProceduresApi;

import docking.WindowPosition;
import docking.action.DockingAction;
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
	private static final String PREV_IMAGE = "/images/check_icon.jpg";
	private static final HelpLocation HELP = new HelpLocation("SampleHelpTopic", "SampleHelpTopic_Anchor_Name");
	private DockingAction action;

	// Set component references ahead of time so they can be accessed at a global level
	// as opposed to being passed into functions as a local reference.
	private ApiClient apiClient;
	private FilesApi filesApi;

	// GUI's main panel which holds all other panels
	private JPanel mainPanel;

  private Program program;
	private FunctionIterator fIterator;

	// Declare file-dependent variables in advance
	FileModel originalFile;
	String originalSha1;
	String originalSha512;
	public void setProgram(Program programIn) {
		program = programIn;
		if (program != null) {
			// Set up program-dependent function iterator
			fIterator = program.getFunctionManager().getFunctions(true);

			// Attempt to hash file locally, alert user on failure
			try {
				originalFile = new FileModel(program.getExecutablePath());
				originalSha1 = Helpers.hashFile(originalFile, "SHA-1");
				originalSha512 = Helpers.hashFile(originalFile, "SHA-512");
				// TODO: enable upload buttons if previously disabled
			} catch (FileNotFoundException e) {
				// TODO: disable upload buttons
				announce(
					"Cannot Find File",
					"Ghidra is unable to access the original copy of this file.\n" +
					"Therefore, this file cannot be uploaded to Unknown Cyber.\n" +
					"\n" +
					"Potential causes:\n" +
					" - The file is not present on your computer.\n" +
					" - The file has been moved from its original location.\n" +
					" - The file is inside an archive.",
					true
				);
			} catch (Exception e) {
				// TODO: disable upload buttons
				announce(
					"Unknown Error Loading File",
					"An unexpected error has occurred when trying to access the original copy of this file.\n" +
					"Therefore, this file cannot be uploaded to Unknown Cyber.",
					true
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

	public String getOriginalSha1(){
		return originalSha1;
	}

	public String getOriginalSha512(){
		return originalSha512;
	}

	@Override
	public JComponent getComponent() {
		return mainPanel;
	}

	// This function puts together the UI
	private void buildMainPanel() {
		mainPanel = new MainPanel();
	}

	// This is the built announce function that runs on the DummyButton
	// Original code used it in other places as well, as a general use function
	public void announce(String title, String message, boolean asError) {
		if (asError) {
			Msg.showError(getClass(),  mainPanel,  title, message);
		} else {
			Msg.showInfo(getClass(),  mainPanel,  title, message);
		}
		
	}
}
