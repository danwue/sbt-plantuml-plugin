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
package net.sourceforge.plantuml.svek;

public class ShapePseudoImpl implements IShapePseudo {

	private final String uid;
	private final double width;
	private final double height;

	public ShapePseudoImpl(String uid, double width, double height) {
		this.uid = uid;
		this.width = width;
		this.height = height;
	}

	public String getUid() {
		return uid;
	}

	public void appendShape(StringBuilder sb) {
		sb.append(uid + " [shape=rect,label=\"\"");
		sb.append(",width=" + SvekUtils.pixelToInches(width));
		sb.append(",height=" + SvekUtils.pixelToInches(height));
		sb.append("];");
	}

}