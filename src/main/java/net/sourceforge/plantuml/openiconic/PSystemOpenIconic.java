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
package net.sourceforge.plantuml.openiconic;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.DiagramDescriptionImpl;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.graphic.HtmlColorUtils;
import net.sourceforge.plantuml.ugraphic.ColorMapperIdentity;
import net.sourceforge.plantuml.ugraphic.ImageBuilder;

public class PSystemOpenIconic extends AbstractPSystem {

	private final String iconName;
	private final double factor;

	public PSystemOpenIconic(String iconName, double factor) {
		this.iconName = iconName;
		this.factor = factor;
	}

	public ImageData exportDiagram(OutputStream os, int num, FileFormatOption fileFormat) throws IOException {
		final OpenIcon icon = OpenIcon.retrieve(iconName);
		// final Dimension2D dim = new Dimension2DDouble(100, 100);

		final ImageBuilder imageBuilder = new ImageBuilder(new ColorMapperIdentity(), 1.0,
				null, null, null, 5, 5, null, false);
		imageBuilder.setUDrawable(icon.asTextBlock(HtmlColorUtils.BLACK, factor));
		return imageBuilder.writeImageTOBEMOVED(fileFormat, os);

//		UGraphic2 ug = fileFormat.createUGraphic(dim);
//		ug = (UGraphic2) ug.apply(new UTranslate(10, 10));
//		// ug = ug.apply(new UChangeColor(HtmlColorUtils.BLACK));
//		// ug.draw(new URectangle(7, 6));
//		icon.asTextBlock(HtmlColorUtils.BLACK, factor).drawU(ug);
//		ug.writeImageTOBEMOVED(os, null, 96);
//		return new ImageDataSimple(dim);
	}

	// private GraphicStrings getGraphicStrings() throws IOException {
	// final UFont font = new UFont("SansSerif", Font.PLAIN, 12);
	// final GraphicStrings result = new GraphicStrings(strings, font, HtmlColorUtils.BLACK, HtmlColorUtils.WHITE,
	// UAntiAliasing.ANTI_ALIASING_ON);
	// return result;
	// }

	public DiagramDescription getDescription() {
		return new DiagramDescriptionImpl("(Open iconic)", getClass());
	}

}
