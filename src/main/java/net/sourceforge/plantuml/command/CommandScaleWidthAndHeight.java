/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * This file is part of PlantUML.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml.command;

import java.util.List;

import net.sourceforge.plantuml.ScaleWidthAndHeight;
import net.sourceforge.plantuml.UmlDiagram;

public class CommandScaleWidthAndHeight extends SingleLineCommand<UmlDiagram> {

	public CommandScaleWidthAndHeight() {
		super("(?i)^scale[%s]+([0-9.]+)[%s]*[*x][%s]*([0-9.]+)$");
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, List<String> arg) {
		final double width = Double.parseDouble(arg.get(0));
		final double height = Double.parseDouble(arg.get(1));
		diagram.setScale(new ScaleWidthAndHeight(width, height));
		return CommandExecutionResult.ok();
	}

}
