package unknowncyberplugin;

import ghidra.app.ExamplesPluginPackage;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.app.plugin.ProgramPlugin;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.program.model.listing.Program;

// TODO: set up metadata correctly for release
//@formatter:off
@PluginInfo(
	status = PluginStatus.RELEASED,
	packageName = ExamplesPluginPackage.NAME,
	category = PluginCategoryNames.MISC,
	shortDescription = "Unknown Cyber",
	description = "Unknown Cyber's API integration plugin"
)
//@formatter:on
public class UnknownCyberPlugin extends ProgramPlugin {

	private UnknownCyberFileProvider fileProvider;

	/**
	 * Ghidra will, paradoxically, auto-open a plugin before opening a program/file,
	 *   even though you have to open the program/file first.  As such, we use the
	 *   programOpened method to handle program-dependent starting code that logically
	 *   shouldn't need to be babied to work.
	 */
	@Override
	public void programOpened(Program program) {
		if (fileProvider != null) {
			fileProvider.setProgram(program);
			References.enableFullPlugin(Api.isFileAccessible(program.getExecutableMD5()));
		}
	}

	/**
	 * Plugin constructor.
	 *
	 * @param tool The plugin tool that this plugin is added to.
	 */
	public UnknownCyberPlugin(PluginTool tool) {
		super(tool);

		fileProvider = new UnknownCyberFileProvider(tool, getName());
		References.setFileProvider(fileProvider);
	}

	@Override
	public void dispose() {
		fileProvider.setVisible(false);
	}
}
