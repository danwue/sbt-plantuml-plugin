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
package net.sourceforge.plantuml.graphic;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.ugraphic.MinMax;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class TextBlockRecentred extends AbstractTextBlock implements TextBlockBackcolored {

	private final TextBlock textBlock;

	public TextBlockRecentred(TextBlock textBlock) {
		this.textBlock = textBlock;
	}

	public void drawU(final UGraphic ug) {
		final MinMax minMax = TextBlockUtils.getMinMax(textBlock, ug.getStringBounder());
		textBlock.drawU(ug.apply(new UTranslate(-minMax.getMinX(), -minMax.getMinY())));
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		final MinMax minMax = TextBlockUtils.getMinMax(textBlock, stringBounder);
		return minMax.getDimension();
	}

	public HtmlColor getBackcolor() {
		return ((TextBlockBackcolored) textBlock).getBackcolor();
	}

}
