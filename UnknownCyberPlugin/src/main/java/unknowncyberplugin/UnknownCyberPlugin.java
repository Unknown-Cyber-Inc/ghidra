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

import java.io.PrintWriter;

import generic.jar.ResourceFile;
import ghidra.app.ExamplesPluginPackage;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.app.plugin.ProgramPlugin;
import ghidra.app.script.GhidraScript;
import ghidra.app.script.GhidraScriptProvider;
import ghidra.app.script.GhidraScriptUtil;
import ghidra.app.script.GhidraState;
import ghidra.framework.plugintool.*;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.program.model.listing.Program;
import ghidra.util.Msg;
import ghidra.util.task.ConsoleTaskMonitor;
import ghidra.util.task.TaskMonitor;

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

	@Override
	public void programOpened(Program program) {
		if (fileProvider != null) {
			fileProvider.setProgram(program);
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
		fileProvider.setProgram(currentProgram);

		Api.setFileProvider(fileProvider);
	}

	@Override
	public void dispose() {
		fileProvider.setVisible(false);
	}

	public void runPythonScript(String script_name, Program program) {
		Msg.info(this, ("Program program in runPyScrpt: " + program));
        // Create a new GhidraState
        GhidraState state = new GhidraState(this.tool, this.tool.getProject(), program, currentLocation, currentSelection, currentHighlight);

        try {
            // Get the script instance
            ResourceFile sourceFile = GhidraScriptUtil.findScriptByName(script_name);
			PrintWriter writer = new PrintWriter(System.out);

			// Create a new script provider for Python
            GhidraScriptProvider scriptProvider = GhidraScriptUtil.getProvider(sourceFile);
			GhidraScript script = scriptProvider.getScriptInstance(sourceFile, writer);

            // Set the state for the script
			TaskMonitor monitor = new ConsoleTaskMonitor();
            script.set(state, monitor, writer);

            // Execute the script
            // script.execute(state, monitor, writer);
			script.runScript(script_name, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
