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
package net.sourceforge.plantuml.cucadiagram.dot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DebugTrace {

	private static final File out = new File("debug" + System.currentTimeMillis() + ".txt");

	private static PrintWriter pw;

	private synchronized static PrintWriter getPrintWriter() {
		if (pw == null) {
			try {
				pw = new PrintWriter(out);
			} catch (FileNotFoundException e) {

			}
		}
		return pw;
	}

	public synchronized static void DEBUG(String s) {
		final PrintWriter pw = getPrintWriter();
		pw.println(s);
		pw.flush();
	}

	public synchronized static void DEBUG(String s, Throwable t) {
		DEBUG(s);
		t.printStackTrace(pw);
		pw.flush();
	}

}
