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
package net.sourceforge.plantuml;

/**
 * Indicates the location of a line of code within a resource.
 * The resource maybe a local file or a remote URL.
 *
 */
public interface LineLocation {
	
	/**
	 * Position of the line, starting at 0.
	 */
	public int getPosition();
	
	/**
	 * A description of the ressource.
	 * If the ressource is a file, this is the complete path of the file.
	 */
	public String getDescription();
	
	/**
	 * Get the parent of this location.
	 * If this resource has been included by a !include or !includeurl directive,
	 * this return the location of the !include line.
	 */
	public LineLocation getParent();

}