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
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.regex.Matcher2;
import net.sourceforge.plantuml.command.regex.MyPattern;
import net.sourceforge.plantuml.command.regex.Pattern2;
import net.sourceforge.plantuml.core.Diagram;

public abstract class CommandMultilinesBracket<S extends Diagram> implements Command<S> {

	private final Pattern2 starting;

	public CommandMultilinesBracket(String patternStart) {
		if (patternStart.startsWith("(?i)^") == false || patternStart.endsWith("$") == false) {
			throw new IllegalArgumentException("Bad pattern " + patternStart);
		}
		this.starting = MyPattern.cmpile(patternStart);
	}

	protected boolean isCommandForbidden() {
		return false;
	}
	
	public String[] getDescription() {
		return new String[] { "BRACKET: " + starting.pattern() };
	}

	protected void actionIfCommandValid() {
	}

	protected final Pattern2 getStartingPattern() {
		return starting;
	}

	final public CommandControl isValid(BlocLines lines) {
		if (isCommandForbidden()) {
			return CommandControl.NOT_OK;
		}
		final Matcher2 m1 = starting.matcher(StringUtils.trin(lines.getFirst499()));
		if (m1.matches() == false) {
			return CommandControl.NOT_OK;
		}
		if (lines.size() == 1) {
			return CommandControl.OK_PARTIAL;
		}

		int level = 1;
		for (CharSequence cs : lines.subExtract(1, 0)) {
			final String s = StringUtils.trin(cs);
			if (isLineConsistent(s, level) == false) {
				return CommandControl.NOT_OK;
			}
			if (s.endsWith("{")) {
				level++;
			}
			if (s.endsWith("}")) {
				level--;
			}
			if (level < 0) {
				return CommandControl.NOT_OK;
			}
		}

		if (level != 0) {
			return CommandControl.OK_PARTIAL;
		}

		actionIfCommandValid();
		return CommandControl.OK;
	}

	protected abstract boolean isLineConsistent(String line, int level);
}
