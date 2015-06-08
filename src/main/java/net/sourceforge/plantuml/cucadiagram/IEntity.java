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
package net.sourceforge.plantuml.cucadiagram;

import java.util.Map;

import net.sourceforge.plantuml.Hideable;
import net.sourceforge.plantuml.LineConfigurable;
import net.sourceforge.plantuml.Removeable;
import net.sourceforge.plantuml.SpecificBackcolorable;
import net.sourceforge.plantuml.Url;
import net.sourceforge.plantuml.graphic.USymbol;

public interface IEntity extends SpecificBackcolorable, Hideable, Removeable, LineConfigurable {

	public Code getCode();

	public LongCode getLongCode();

	public USymbol getUSymbol();

	public void setUSymbol(USymbol symbol);

	public LeafType getEntityType();

	public Display getDisplay();

	public IGroup getParentContainer();

	public void setDisplay(Display display);

	public String getUid();

	public Url getUrl99();

	public Stereotype getStereotype();

	public void setStereotype(Stereotype stereotype);

	public Bodier getBodier();

	public void addUrl(Url url);

	public boolean isGroup();

	public boolean hasUrl();

	public int getHectorLayer();

	public void setHectorLayer(int layer);

	public int getRawLayout();

	public void putTip(String member, Display display);

	public Map<String, Display> getTips();

}