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


public class AddStyle implements FontChange {

	private final FontStyle style;
	private final HtmlColor extendedColor;

	public AddStyle(FontStyle style, HtmlColor extendedColor) {
		this.style = style;
		this.extendedColor = extendedColor;
	}

	public AddStyle(String s) {
		this(FontStyle.getStyle(s), FontStyle.getStyle(s).getExtendedColor(s));
	}

	public FontConfiguration apply(FontConfiguration initial) {
		initial = initial.add(style);
		if (extendedColor != null) {
			initial = initial.changeExtendedColor(extendedColor);
		}
		return initial;
	}

}
