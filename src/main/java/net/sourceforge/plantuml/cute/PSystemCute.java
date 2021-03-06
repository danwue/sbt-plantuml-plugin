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
package net.sourceforge.plantuml.cute;

import java.io.IOException;
import java.io.OutputStream;
import java.util.StringTokenizer;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.DiagramDescriptionImpl;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.ugraphic.ColorMapperIdentity;
import net.sourceforge.plantuml.ugraphic.ImageBuilder;

public class PSystemCute extends AbstractPSystem {

	// private final List<Positionned> shapes = new ArrayList<Positionned>();
	// private final Map<String, Group> groups = new HashMap<String, Group>();
	private final Group root = Group.createRoot();
	private Group currentGroup = root;

	public PSystemCute() {
	}

	public DiagramDescription getDescription() {
		return new DiagramDescriptionImpl("(Cute)", getClass());
	}

	public void doCommandLine(String line) {
		line = StringUtils.trin(line);
		if (line.length()==0 || line.startsWith("'")) {
			return;
		}
		if (line.startsWith("group ")) {
			final StringTokenizer st = new StringTokenizer(line);
			st.nextToken();
			final String groupName = st.nextToken();
			currentGroup = currentGroup.createChild(groupName);
		} else if (line.startsWith("}")) {
			currentGroup = currentGroup.getParent();
		} else {
			final Positionned shape = new CuteShapeFactory(currentGroup.getChildren()).createCuteShapePositionned(line);
			// if (currentGroup == null) {
			// shapes.add(shape);
			// } else {
			currentGroup.add(shape);
			// }
		}
	}

	public ImageData exportDiagram(OutputStream os, int num, FileFormatOption fileFormat) throws IOException {
		final ImageBuilder builder = new ImageBuilder(new ColorMapperIdentity(), 1.0, null, null, null, 10, 10, null, false);
		builder.setUDrawable(root);
		return builder.writeImageTOBEMOVED(fileFormat, os);
	}
}
