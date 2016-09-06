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
package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.creole.Stencil;

public class UGraphicStencil extends AbstractUGraphicHorizontalLine {

	private final Stencil stencil;
	private final UStroke defaultStroke;

	public UGraphicStencil(UGraphic ug, Stencil stencil, UStroke defaultStroke) {
		super(ug);
		this.stencil = stencil;
		this.defaultStroke = defaultStroke;
	}

	@Override
	protected AbstractUGraphicHorizontalLine copy(UGraphic ug) {
		return new UGraphicStencil(ug, stencil, defaultStroke);
	}

	@Override
	protected void drawHline(UGraphic ug, UHorizontalLine line, UTranslate translate) {
		line.drawLineInternal(ug, stencil, translate.getDy(), defaultStroke);
		// final UDrawable ud = stencil.convert(line, ug.getStringBounder());
		// ud.drawU(ug.apply(translate));
		// line.drawLine(ug.apply(translate), startingX, endingX, 0, defaultStroke);
	}
}
