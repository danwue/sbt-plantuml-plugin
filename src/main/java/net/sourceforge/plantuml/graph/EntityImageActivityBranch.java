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

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.ColorMapper;

class EntityImageActivityBranch extends AbstractEntityImage {

	private final int size = 10;

	public EntityImageActivityBranch(IEntity entity) {
		super(entity);
	}

	@Override
	public Dimension2D getDimension(StringBounder stringBounder) {
		return new Dimension2DDouble(size * 2, size * 2);
	}

	@Override
	public void draw(ColorMapper colorMapper, Graphics2D g2d) {
		final Polygon p = new Polygon();
		p.addPoint(size, 0);
		p.addPoint(size * 2, size);
		p.addPoint(size, size * 2);
		p.addPoint(0, size);

		g2d.setColor(colorMapper.getMappedColor(getYellow()));
		g2d.fill(p);
		g2d.setColor(colorMapper.getMappedColor(getRed()));
		g2d.draw(p);
	}
}
