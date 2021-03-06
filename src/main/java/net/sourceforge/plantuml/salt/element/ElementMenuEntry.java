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

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.ugraphic.UChangeBackColor;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.URectangle;

public class ElementMenuEntry extends AbstractElement {

	private final TextBlock block;
	private final String text;
	private HtmlColor background;
	private double xxx;

	public ElementMenuEntry(String text, UFont font, ISkinSimple spriteContainer) {
		final FontConfiguration config = FontConfiguration.blackBlueTrue(font);
		this.block = Display.create(text).create(config, HorizontalAlignment.LEFT, spriteContainer);
		this.text = text;
	}

	public Dimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		if (text.equals("-")) {
			return new Dimension2DDouble(10, 5);
		}
		return block.calculateDimension(stringBounder);
	}

	public void drawU(UGraphic ug, int zIndex, Dimension2D dimToUse) {
		if (background != null) {
			final Dimension2D dim = getPreferredDimension(ug.getStringBounder(), 0, 0);
			ug.apply(new UChangeBackColor(background)).draw(new URectangle(dim.getWidth(), dim.getHeight()));
		}
		block.drawU(ug);
	}

	public double getX() {
		return xxx;
	}

	public void setX(double x) {
		this.xxx = x;
	}

	public String getText() {
		return text;
	}

	public HtmlColor getBackground() {
		return background;
	}

	public void setBackground(HtmlColor background) {
		this.background = background;
	}
}
