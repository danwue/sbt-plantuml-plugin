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
import net.sourceforge.plantuml.ugraphic.CompressionTransform;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UGraphicCompress2;

public class TextBlockCompressed2 extends AbstractTextBlock implements TextBlock {

	private final TextBlock textBlock;
	private final CompressionTransform compressionTransform;

	public TextBlockCompressed2(TextBlock textBlock, CompressionTransform compressionTransform) {
		this.textBlock = textBlock;
		this.compressionTransform = compressionTransform;
	}

	public void drawU(final UGraphic ug) {
		textBlock.drawU(new UGraphicCompress2(ug, compressionTransform));
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		final Dimension2D dim = textBlock.calculateDimension(stringBounder);
		return new Dimension2DDouble(compressionTransform.transform(dim.getWidth()), dim.getHeight());
	}
}
