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
package net.sourceforge.plantuml.ugraphic.tikz;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.Url;
import net.sourceforge.plantuml.creole.AtomText;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.posimo.DotPath;
import net.sourceforge.plantuml.tikz.TikzGraphics;
import net.sourceforge.plantuml.ugraphic.AbstractCommonUGraphic;
import net.sourceforge.plantuml.ugraphic.AbstractUGraphic;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.ColorMapper;
import net.sourceforge.plantuml.ugraphic.UCenteredCharacter;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic2;
import net.sourceforge.plantuml.ugraphic.UImage;
import net.sourceforge.plantuml.ugraphic.UImageSvg;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UPath;
import net.sourceforge.plantuml.ugraphic.UPolygon;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UText;

public class UGraphicTikz extends AbstractUGraphic<TikzGraphics> implements ClipContainer, UGraphic2 {

	private final StringBounder stringBounder;

	private UGraphicTikz(ColorMapper colorMapper, TikzGraphics tikz) {
		super(colorMapper, tikz);
		this.stringBounder = FileFormat.PNG.getDefaultStringBounder();
		register();

	}

	public UGraphicTikz(ColorMapper colorMapper, boolean withPreamble) {
		this(colorMapper, new TikzGraphics(withPreamble));

	}

	@Override
	protected AbstractCommonUGraphic copyUGraphic() {
		return new UGraphicTikz(this);
	}

	private UGraphicTikz(UGraphicTikz other) {
		super(other);
		this.stringBounder = other.stringBounder;
		register();
	}

	private void register() {
		registerDriver(URectangle.class, new DriverRectangleTikz());
		registerDriver(UText.class, new DriverUTextTikz());
		registerDriver(AtomText.class, new DriverAtomTextTikz());
		registerDriver(ULine.class, new DriverLineTikz());
		registerDriver(UPolygon.class, new DriverPolygonTikz());
		registerDriver(UEllipse.class, new DriverEllipseTikz());
		registerDriver(UImage.class, new DriverNoneTikz());
		registerDriver(UImageSvg.class, new DriverNoneTikz());
		registerDriver(UPath.class, new DriverUPathTikz());
		registerDriver(DotPath.class, new DriverDotPathTikz());
		registerDriver(UCenteredCharacter.class, new DriverCenteredCharacterTikz());
	}

	public StringBounder getStringBounder() {
		return stringBounder;
	}

	public void startUrl(Url url) {
		getGraphicObject().openLink(url.getUrl(), url.getTooltip());
	}

	public void closeAction() {
		getGraphicObject().closeLink();
	}

	public void writeImageTOBEMOVED(OutputStream os, String metadata, int dpi) throws IOException {
		createTikz(os);
	}

	public void createTikz(OutputStream os) throws IOException {
		getGraphicObject().createData(os);
	}

	public boolean isSpecialTxt() {
		return true;
	}

}
