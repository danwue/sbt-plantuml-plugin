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
package net.sourceforge.plantuml.salt.element;

import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.graphic.HtmlColorSet;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UChangeBackColor;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class ElementMenuPopup extends AbstractElement {

	private final Collection<ElementMenuEntry> entries = new ArrayList<ElementMenuEntry>();
	private final UFont font;
	private final ISkinSimple spriteContainer;

	public ElementMenuPopup(UFont font, ISkinSimple spriteContainer) {
		this.font = font;
		this.spriteContainer = spriteContainer;
	}

	public void addEntry(String s) {
		entries.add(new ElementMenuEntry(s, font, spriteContainer));
	}

	public Dimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		double w = 0;
		double h = 0;
		for (ElementMenuEntry entry : entries) {
			final Dimension2D dim = entry.getPreferredDimension(stringBounder, x, y);
			w = Math.max(w, dim.getWidth());
			h += dim.getHeight();
		}
		return new Dimension2DDouble(w, h);
	}

	public void drawU(UGraphic ug, int zIndex, Dimension2D dimToUse) {
		if (zIndex != 1) {
			return;
		}
		ug.apply(new UChangeBackColor(HtmlColorSet.getInstance().getColorIfValid("#DDDDDD"))).draw(
				new URectangle(dimToUse.getWidth(), dimToUse.getHeight()));

		double y1 = 0;
		for (ElementMenuEntry entry : entries) {
			final double h = entry.getPreferredDimension(ug.getStringBounder(), 0, y1).getHeight();
			if (entry.getText().equals("-")) {
				ug.apply(new UTranslate(0, y1 + h / 2)).draw(new ULine(dimToUse.getWidth(), 0));
			} else {
				entry.drawU(ug.apply(new UTranslate(0, y1)), zIndex, dimToUse);
			}
			y1 += h;
		}
	}

}
