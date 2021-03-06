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
package net.sourceforge.plantuml.graphic;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ugraphic.AbstractUGraphicHorizontalLine;
import net.sourceforge.plantuml.ugraphic.UChangeBackColor;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UHorizontalLine;
import net.sourceforge.plantuml.ugraphic.UPath;
import net.sourceforge.plantuml.ugraphic.UTranslate;

class USymbolDatabase extends USymbol {

	@Override
	public SkinParameter getSkinParameter() {
		return SkinParameter.DATABASE;
	}



	private void drawDatabase(UGraphic ug, double width, double height, boolean shadowing) {
		final UPath shape = new UPath();
		if (shadowing) {
			shape.setDeltaShadow(3.0);
		}
		shape.moveTo(0, 10);
		shape.cubicTo(0, 0, width / 2, 0, width / 2, 0);
		shape.cubicTo(width / 2, 0, width, 0, width, 10);
		shape.lineTo(width, height - 10);
		shape.cubicTo(width, height, width / 2, height, width / 2, height);
		shape.cubicTo(width / 2, height, 0, height, 0, height - 10);
		shape.lineTo(0, 10);

		ug.draw(shape);

		final UPath closing = getClosingPath(width);
		ug.apply(new UChangeBackColor(null)).draw(closing);

	}

	private UPath getClosingPath(double width) {
		final UPath closing = new UPath();
		closing.moveTo(0, 10);
		closing.cubicTo(0, 20, width / 2, 20, width / 2, 20);
		closing.cubicTo(width / 2, 20, width, 20, width, 10);
		return closing;
	}

	class MyUGraphicDatabase extends AbstractUGraphicHorizontalLine {

		private final double endingX;

		@Override
		protected AbstractUGraphicHorizontalLine copy(UGraphic ug) {
			return new MyUGraphicDatabase(ug, endingX);
		}

		public MyUGraphicDatabase(UGraphic ug, double endingX) {
			super(ug);
			this.endingX = endingX;
		}

		@Override
		protected void drawHline(UGraphic ug, UHorizontalLine line, UTranslate translate) {
			final UPath closing = getClosingPath(endingX);
			ug = ug.apply(translate);
			ug.apply(line.getStroke()).apply(new UChangeBackColor(null)).apply(new UTranslate(0, -15)).draw(closing);
			if (line.isDouble()) {
				ug.apply(line.getStroke()).apply(new UChangeBackColor(null)).apply(new UTranslate(0, -15 + 2))
						.draw(closing);
			}
			line.drawTitleInternal(ug, 0, endingX, 0, true);
		}

	}

	private Margin getMargin() {
		return new Margin(10, 10, 20, 5);
	}

	public TextBlock asSmall(TextBlock name, final TextBlock label, final TextBlock stereotype, final SymbolContext symbolContext) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final Dimension2D dim = calculateDimension(ug.getStringBounder());
				ug = symbolContext.apply(ug);
				drawDatabase(ug, dim.getWidth(), dim.getHeight(), symbolContext.isShadowing());
				final Margin margin = getMargin();
				final TextBlock tb = TextBlockUtils.mergeTB(stereotype, label, HorizontalAlignment.CENTER);
				final UGraphic ug2 = new MyUGraphicDatabase(ug, dim.getWidth());
				tb.drawU(ug2.apply(new UTranslate(margin.getX1(), margin.getY1())));
			}

			public Dimension2D calculateDimension(StringBounder stringBounder) {
				final Dimension2D dimLabel = label.calculateDimension(stringBounder);
				final Dimension2D dimStereo = stereotype.calculateDimension(stringBounder);
				return getMargin().addDimension(Dimension2DDouble.mergeTB(dimStereo, dimLabel));
			}
		};
	}

	public TextBlock asBig(final TextBlock title, final TextBlock stereotype, final double width, final double height,
			final SymbolContext symbolContext) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final Dimension2D dim = calculateDimension(ug.getStringBounder());
				ug = symbolContext.apply(ug);
				drawDatabase(ug, dim.getWidth(), dim.getHeight(), symbolContext.isShadowing());
				final Dimension2D dimStereo = stereotype.calculateDimension(ug.getStringBounder());
				final double posStereo = (width - dimStereo.getWidth()) / 2;
				stereotype.drawU(ug.apply(new UTranslate(posStereo, 0)));

				final Dimension2D dimTitle = title.calculateDimension(ug.getStringBounder());
				final double posTitle = (width - dimTitle.getWidth()) / 2;
				title.drawU(ug.apply(new UTranslate(posTitle, 21)));
			}

			public Dimension2D calculateDimension(StringBounder stringBounder) {
				return new Dimension2DDouble(width, height);
			}
		};
	}

	public boolean manageHorizontalLine() {
		return true;
	}

	@Override
	public int suppHeightBecauseOfShape() {
		return 15;
	}

}
