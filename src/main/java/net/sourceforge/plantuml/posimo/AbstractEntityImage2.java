/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2014, Arnaud Roques
 *
 * Project Info:  http://plantuml.sourceforge.net
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
package net.sourceforge.plantuml.posimo;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.ColorParam;
import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UFont;

abstract class AbstractEntityImage2 implements IEntityImageBlock {

	private final IEntity entity;
	private final ISkinParam skinParam;
	
	private final Rose rose = new Rose();

	public AbstractEntityImage2(IEntity entity, ISkinParam skinParam) {
		if (entity == null) {
			throw new IllegalArgumentException("entity null");
		}
		this.entity = entity;
		this.skinParam = skinParam;
	}

	public abstract Dimension2D getDimension(StringBounder stringBounder);

	protected final IEntity getEntity() {
		return entity;
	}

	protected UFont getFont(FontParam fontParam) {
		return skinParam.getFont(fontParam, null, false);
	}

	protected HtmlColor getFontColor(FontParam fontParam) {
		return skinParam.getFontHtmlColor(fontParam, null);
	}

	protected final HtmlColor getColor(ColorParam colorParam) {
		return rose.getHtmlColor(skinParam, colorParam);
	}

	protected final ISkinParam getSkinParam() {
		return skinParam;
	}
}
