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
package net.sourceforge.plantuml.salt.factory;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Dictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementImage;

public class ElementFactoryImage implements ElementFactory {

	final private DataSource dataSource;
	final private Dictionary dictionary;

	public ElementFactoryImage(DataSource dataSource, Dictionary dictionary) {
		this.dataSource = dataSource;
		this.dictionary = dictionary;
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final String header = dataSource.next().getElement();
		final String name = header.length() > 2 ? header.substring(2) : null;
		final List<String> img = new ArrayList<String>();
		while (dataSource.peek(0).getElement().equals(">>") == false) {
			img.add(dataSource.next().getElement());
		}
		final Terminated<String> next = dataSource.next();
		final ElementImage element = new ElementImage(img);
		if (name != null) {
			dictionary.put(name, element);
		}
		return new Terminated<Element>(element, next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		return text.equals("<<") || text.matches("\\<\\<\\w+");
	}
}
