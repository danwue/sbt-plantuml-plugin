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
package net.sourceforge.plantuml.creole;

import net.sourceforge.plantuml.command.regex.Matcher2;
import net.sourceforge.plantuml.command.regex.MyPattern;
import net.sourceforge.plantuml.command.regex.Pattern2;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.HtmlColorSet;

public class CommandCreoleColorAndSizeChange implements Command {

	private final Pattern2 pattern;

	public static final String fontPattern = "\\<font(?:[%s]+size[%s]*=[%s]*[%g]?(\\d+)[%g]?|[%s]+color[%s]*=[%s]*[%g]?(#[0-9a-fA-F]{6}|\\w+)[%g]?)+[%s]*\\>";

	public static Command create() {
		return new CommandCreoleColorAndSizeChange("^(?i)(" + fontPattern + "(.*?)\\</font\\>)");
	}

	public static Command createEol() {
		return new CommandCreoleColorAndSizeChange("^(?i)(" + fontPattern + "(.*))$");
	}

	private CommandCreoleColorAndSizeChange(String p) {
		this.pattern = MyPattern.cmpile(p);

	}

	public int matchingSize(String line) {
		final Matcher2 m = pattern.matcher(line);
		if (m.find() == false) {
			return 0;
		}
		return m.group(1).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) {
		final Matcher2 m = pattern.matcher(line);
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		// for (int i = 1; i <= m.groupCount(); i++) {
		// System.err.println("i=" + i + " " + m.group(i));
		// }

		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		FontConfiguration fc2 = fc1;
		if (m.group(2) != null) {
			fc2 = fc2.changeSize(Integer.parseInt(m.group(2)));
		}
		if (m.group(3) != null) {
			final HtmlColor color = HtmlColorSet.getInstance().getColorIfValid(m.group(3));
			fc2 = fc2.changeColor(color);
		}

		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(4));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}
}
