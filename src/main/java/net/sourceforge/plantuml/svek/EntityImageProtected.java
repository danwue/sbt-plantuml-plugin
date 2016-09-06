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
package net.sourceforge.plantuml.svek;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.cucadiagram.dot.Neighborhood;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class EntityImageProtected extends AbstractTextBlock implements IEntityImage, Untranslated {

	private final IEntityImage orig;
	private final double border;
	private final Bibliotekon bibliotekon;
	private final Neighborhood neighborhood;
	
	public Rectangle2D getInnerPosition(String member, StringBounder stringBounder) {
		throw new UnsupportedOperationException();
	}


	public EntityImageProtected(IEntityImage orig, double border, Neighborhood neighborhood, Bibliotekon bibliotekon) {
		this.orig = orig;
		this.border = border;
		this.bibliotekon = bibliotekon;
		this.neighborhood = neighborhood;
	}

	public boolean isHidden() {
		return orig.isHidden();
	}

	public HtmlColor getBackcolor() {
		return orig.getBackcolor();
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		return Dimension2DDouble.delta(orig.calculateDimension(stringBounder), 2 * border);
	}

	public void drawU(UGraphic ug) {
		orig.drawU(ug.apply(new UTranslate(border, border)));
	}

	public void drawUntranslated(UGraphic ug, double minX, double minY) {
		final Dimension2D dim = orig.calculateDimension(ug.getStringBounder());
		neighborhood.drawU(ug, minX + border, minY + border, bibliotekon, dim);
	}

	public ShapeType getShapeType() {
		return orig.getShapeType();
	}

	public int getShield() {
		return orig.getShield();
	}

}
