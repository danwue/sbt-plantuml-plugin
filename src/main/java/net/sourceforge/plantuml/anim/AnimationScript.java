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
package net.sourceforge.plantuml.anim;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AnimationScript {

	private final ScriptEngine engine;

	public AnimationScript() {

		final ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("js");

		// ScriptEngineManager manager = new ScriptEngineManager();
		// List<ScriptEngineFactory> factories = manager.getEngineFactories();
		// for (ScriptEngineFactory factory : factories) {
		// System.out.println("Name : " + factory.getEngineName());
		// System.out.println("Version : " + factory.getEngineVersion());
		// System.out.println("Language name : " + factory.getLanguageName());
		// System.out.println("Language version : " + factory.getLanguageVersion());
		// System.out.println("Extensions : " + factory.getExtensions());
		// System.out.println("Mime types : " + factory.getMimeTypes());
		// System.out.println("Names : " + factory.getNames());
		//
		// }

	}

	public String eval(String line) throws ScriptException {
		final ScriptContext context = engine.getContext();
		final StringWriter sw = new StringWriter();
		context.setWriter(new PrintWriter(sw));
		engine.eval(line, context);
		final String result = sw.toString();
		return result;
	}
}
