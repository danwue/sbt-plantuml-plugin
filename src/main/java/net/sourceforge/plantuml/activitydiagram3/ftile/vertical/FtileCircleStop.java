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
package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractFtile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.ugraphic.UChangeBackColor;
import net.sourceforge.plantuml.ugraphic.UChangeColor;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class FtileCircleStop extends AbstractFtile {

	private static final int SIZE = 20;

	private final HtmlColor backColor;
	private final Swimlane swimlane;

	public FtileCircleStop(ISkinParam skinParam, HtmlColor backColor, Swimlane swimlane) {
		super(skinParam);
		this.backColor = backColor;
		this.swimlane = swimlane;
	}

	public Set<Swimlane> getSwimlanes() {
		if (swimlane == null) {
			return Collections.emptySet();
		}
		return Collections.singleton(swimlane);
	}

	public Swimlane getSwimlaneIn() {
		return swimlane;
	}

	public Swimlane getSwimlaneOut() {
		return swimlane;
	}

	public void drawU(UGraphic ug) {
		double xTheoricalPosition = 0;
		double yTheoricalPosition = 0;
		xTheoricalPosition = Math.round(xTheoricalPosition);
		yTheoricalPosition = Math.round(yTheoricalPosition);

		final UEllipse circle = new UEllipse(SIZE, SIZE);
		if (skinParam().shadowing()) {
			circle.setDeltaShadow(3);
		}
		ug.apply(new UChangeColor(backColor)).apply(new UChangeBackColor(null))
				.apply(new UTranslate(xTheoricalPosition, yTheoricalPosition)).draw(circle);

		final double delta = 4;
		final UEllipse circleSmall = new UEllipse(SIZE - delta * 2, SIZE - delta * 2);
		if (skinParam().shadowing()) {
			circleSmall.setDeltaShadow(3);
		}
		ug.apply(new UChangeColor(null)).apply(new UChangeBackColor(backColor))
				.apply(new UTranslate(xTheoricalPosition + delta + .5, yTheoricalPosition + delta + .5))
				.draw(circleSmall);
	}

	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		return new FtileGeometry(SIZE, SIZE, SIZE / 2, 0);
	}

}
