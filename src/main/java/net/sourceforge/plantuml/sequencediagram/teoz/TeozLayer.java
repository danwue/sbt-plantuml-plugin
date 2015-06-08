/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2014, Arnaud Roques
 *
 * Project Info:  http://plantuml.sourceforge.net
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
package net.sourceforge.plantuml.sequencediagram.teoz;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.png.PngTitler;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class TeozLayer extends AbstractTextBlock implements TextBlock {

	private final PngTitler titler;
	private Dimension2D dimension;
	private final FontParam param;

	public TeozLayer(PngTitler titler, StringBounder stringBounder, FontParam param) {
		this.titler = titler;
		this.param = param;

		dimension = new Dimension2DDouble(0, 0);
		if (titler != null && titler.getTextBlock() != null) {
			dimension = titler.getTextBlock().calculateDimension(stringBounder);
		}
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		return dimension;
	}

	public FontParam getParam() {
		return param;
	}

	public void drawU(UGraphic ug) {
		if (titler != null) {
			titler.getTextBlock().drawU(ug);
		}
	}

}