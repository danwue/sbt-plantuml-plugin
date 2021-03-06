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
package net.sourceforge.plantuml.ugraphic.svg;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.PathIterator;

import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.ColorMapper;
import net.sourceforge.plantuml.ugraphic.UClip;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.ugraphic.UFont;
import net.sourceforge.plantuml.ugraphic.UParam;
import net.sourceforge.plantuml.ugraphic.UShape;
import net.sourceforge.plantuml.ugraphic.UText;

public class DriverTextAsPathSvg implements UDriver<SvgGraphics> {

	private final FontRenderContext fontRenderContext;
	private final ClipContainer clipContainer;

	public DriverTextAsPathSvg(FontRenderContext fontRenderContext, ClipContainer clipContainer) {
		this.fontRenderContext = fontRenderContext;
		this.clipContainer = clipContainer;
	}

	public void draw(UShape ushape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {

		final UClip clip = clipContainer.getClip();
		if (clip != null && clip.isInside(x, y) == false) {
			return;
		}

		final UText shape = (UText) ushape;
		final FontConfiguration fontConfiguration = shape.getFontConfiguration();
		final UFont font = fontConfiguration.getFont();
		
		final TextLayout t = new TextLayout(shape.getText(), font.getFont(), fontRenderContext);
		drawPathIterator(svg, x, y, t.getOutline(null).getPathIterator(null));

	}
	
	static void drawPathIterator(SvgGraphics svg, double x, double y, PathIterator path) {

		svg.newpath();
		final double coord[] = new double[6];
		while (path.isDone() == false) {
			final int code = path.currentSegment(coord);
			if (code == PathIterator.SEG_MOVETO) {
				svg.moveto(coord[0] + x, coord[1] + y);
			} else if (code == PathIterator.SEG_LINETO) {
				svg.lineto(coord[0] + x, coord[1] + y);
			} else if (code == PathIterator.SEG_CLOSE) {
				svg.closepath();
			} else if (code == PathIterator.SEG_CUBICTO) {
				svg.curveto(coord[0] + x, coord[1] + y, coord[2] + x, coord[3] + y, coord[4] + x, coord[5] + y);
			} else if (code == PathIterator.SEG_QUADTO) {
				svg.quadto(coord[0] + x, coord[1] + y, coord[2] + x, coord[3] + y);
			} else {
				throw new UnsupportedOperationException("code=" + code);
			}

			path.next();
		}

		svg.fill(path.getWindingRule());

	}

}
