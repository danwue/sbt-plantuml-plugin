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
package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.Url;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class EmptyTile implements Tile {

	private final double height;

	private final Real origin;

	public EmptyTile(double height, TileArguments tileArguments) {
		this.origin = tileArguments.getOrigin();
		this.height = height;
	}

	public void drawU(UGraphic ug) {
	}

	public double getPreferredHeight(StringBounder stringBounder) {
		return height;
	}

	public void addConstraints(StringBounder stringBounder) {
	}

	public Real getMinX(StringBounder stringBounder) {
		return origin;
	}

	public Real getMaxX(StringBounder stringBounder) {
		return origin;
	}

	public Event getEvent() {
		return new Event() {
			public boolean dealWith(Participant someone) {
				return false;
			}

			public Url getUrl() {
				return null;
			}

			public boolean hasUrl() {
				return false;
			}

			public boolean isParallel() {
				return false;
			}
		};
	}

}
