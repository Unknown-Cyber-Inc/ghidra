package unknowncyberplugin;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.unknowncyber.magic.api.FilesApi;
import com.unknowncyber.magic.api.ProceduresApi;

import docking.WindowPosition;
import ghidra.framework.plugintool.ComponentProviderAdapter;
import ghidra.framework.plugintool.PluginTool;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.Program;
import ghidra.util.Msg;
import io.swagger.client.ApiClient;
import unknowncyberplugin.components.panels.MainPanel;


public class UnknownCyberFileProvider extends ComponentProviderAdapter {
	// Set component references ahead of time so they can be accessed at a global level
	// as opposed to being passed into functions as a local reference.
	private ApiClient apiClient;
	private FilesApi filesApi;
	private ProceduresApi procsApi;

	// GUI's main panel which holds all other panels
	private JPanel mainPanel;

  private Program program;
	private FunctionIterator fIterator;

	// Declare file-dependent variables in advance
	File originalFile;
	String originalSha1 = null;
	String originalSha512 = null;
	public void setProgram(Program programIn) {
		// TODO: testing
		//program = null;
		//originalFile = null;
		//if (programIn != null) {
		//	program = programIn;

		program = programIn;
		if (program != null) {
			// Set up program-dependent function iterator
			fIterator = program.getFunctionManager().getFunctions(true);

			// Attempt to hash file locally, alert user on failure
			try {
				originalFile = new File(program.getExecutablePath());
				originalSha1 = Helpers.hashFile(originalFile, "SHA-1");
				originalSha512 = Helpers.hashFile(originalFile, "SHA-512");
			} catch (FileNotFoundException e) {
				originalSha1 = null;
				originalSha512 = null;
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
				originalSha1 = null;
				originalSha512 = null;
				announce(
					"Unknown Error Loading File",
					"An unexpected error has occurred when trying to access the original copy of this file.\n" +
					"Therefore, this file cannot be uploaded to Unknown Cyber.",
					true
				);
			}
		}
	}

	public UnknownCyberFileProvider(PluginTool tool, String name) {
		super(tool, name, name);
		buildMainPanel();
		setDefaultWindowPosition(WindowPosition.WINDOW);
		setTitle("Unknown Cyber");
		setVisible(true);
		apiClient = new ApiClient();
		filesApi = new FilesApi(apiClient);
		procsApi = new ProceduresApi(apiClient);
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

	public ProceduresApi getProcsApi() {
		return procsApi;
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
