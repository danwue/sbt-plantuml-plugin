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
package net.sourceforge.plantuml.activitydiagram3.ftile;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.creole.Stencil;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.ugraphic.UPolygon;

public class Diamond {

	final static public double diamondHalfSize = 12;

	public static UPolygon asPolygon(boolean shadowing) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(diamondHalfSize, 0);
		diams.addPoint(diamondHalfSize * 2, diamondHalfSize);
		diams.addPoint(diamondHalfSize, diamondHalfSize * 2);
		diams.addPoint(0, diamondHalfSize);
		diams.addPoint(diamondHalfSize, 0);

		if (shadowing) {
			diams.setDeltaShadow(3);
		}

		return diams;
	}

	public static UPolygon asPolygon(boolean shadowing, double width, double height) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(diamondHalfSize, 0);
		diams.addPoint(width - diamondHalfSize, 0);
		diams.addPoint(width, height / 2);
		diams.addPoint(width - diamondHalfSize, height);
		diams.addPoint(diamondHalfSize, height);
		diams.addPoint(0, height / 2);
		diams.addPoint(diamondHalfSize, 0);

		if (shadowing) {
			diams.setDeltaShadow(3);
		}

		return diams;
	}

	public static Stencil asStencil(final TextBlock tb) {
		return new Stencil() {

			private final double getDeltaX(double height, double y) {
				final double p = y / height * 2;
				if (p <= 1) {
					return diamondHalfSize * p;
				}
				return diamondHalfSize * (2 - p);
			}

			public double getStartingX(StringBounder stringBounder, double y) {
				final Dimension2D dim = tb.calculateDimension(stringBounder);
				return -getDeltaX(dim.getHeight(), y);
			}

			public double getEndingX(StringBounder stringBounder, double y) {
				final Dimension2D dim = tb.calculateDimension(stringBounder);
				return dim.getWidth() + getDeltaX(dim.getHeight(), y);
			}
		};
	}

	public static UPolygon asPolygonFoo1(boolean shadowing, double width, double height) {
		final UPolygon diams = new UPolygon();

		diams.addPoint(width / 2, 0);
		diams.addPoint(width, height / 2);
		diams.addPoint(width / 2, height);
		diams.addPoint(0, height / 2);

		if (shadowing) {
			diams.setDeltaShadow(3);
		}

		return diams;
	}

}
