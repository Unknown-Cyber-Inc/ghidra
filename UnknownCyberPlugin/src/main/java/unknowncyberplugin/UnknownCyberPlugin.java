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

import ghidra.app.ExamplesPluginPackage;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.app.plugin.ProgramPlugin;
import ghidra.app.script.GhidraScript;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.program.model.listing.Program;
import ghidra.util.Msg;
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

		References.enableFullPlugin(Api.isFileAccessible("05a470c6b40b43ca571b2b4292634bbc23c95128"));
	}

	@Override
	public void dispose() {
		fileProvider.setVisible(false);
	}
}
