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
package net.sourceforge.plantuml.graph;

import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.SpriteContainerEmpty;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Member;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.skin.VisibilityModifier;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class MethodsOrFieldsArea {

	private final UFont font;
	private final List<String> strings = new ArrayList<String>();

	public MethodsOrFieldsArea(List<Member> attributes, UFont font) {
		this.font = font;
		for (Member att : attributes) {
			this.strings.add(att.getDisplay(false));
		}
	}

	public VisibilityModifier getVisibilityModifier() {
		throw new UnsupportedOperationException();
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		double x = 0;
		double y = 0;
		for (String s : strings) {
			final TextBlock bloc = createTextBlock(s);
			final Dimension2D dim = bloc.calculateDimension(stringBounder);
			y += dim.getHeight();
			x = Math.max(dim.getWidth(), x);
		}
		return new Dimension2DDouble(x, y);
	}

	private TextBlock createTextBlock(String s) {
		return Display.create(s).create(FontConfiguration.blackBlueTrue(font), HorizontalAlignment.LEFT,
				new SpriteContainerEmpty());
	}

	public void draw(UGraphic ug, double x, double y) {
		for (String s : strings) {
			final TextBlock bloc = createTextBlock(s);
			bloc.drawU(ug.apply(new UTranslate(x, y)));
			y += bloc.calculateDimension(ug.getStringBounder()).getHeight();
		}
	}

}
