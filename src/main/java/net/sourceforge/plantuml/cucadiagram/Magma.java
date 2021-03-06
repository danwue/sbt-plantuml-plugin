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
package net.sourceforge.plantuml.cucadiagram;

import java.util.List;

class Magma {

	private final CucaDiagram system;
	private final List<ILeaf> standalones;
	private final LinkType linkType = new LinkType(LinkDecor.NONE, LinkDecor.NONE).getInvisible();

	public Magma(CucaDiagram system, List<ILeaf> standalones) {
		this.system = system;
		this.standalones = standalones;
	}

	public void putInSquare() {
		final SquareLinker<ILeaf> linker = new SquareLinker<ILeaf>() {
			public void topDown(ILeaf top, ILeaf down) {
				system.addLink(new Link(top, down, linkType, Display.NULL, 2));
			}

			public void leftRight(ILeaf left, ILeaf right) {
				system.addLink(new Link(left, right, linkType, Display.NULL, 1));
			}
		};
		new SquareMaker<ILeaf>().putInSquare(standalones, linker);
	}

	public IGroup getContainer() {
		final IGroup parent = standalones.get(0).getParentContainer();
		if (parent == null) {
			return null;
		}
		return parent.getParentContainer();
	}

	public boolean isComplete() {
		final IGroup parent = getContainer();
		if (parent == null) {
			return false;
		}
		return parent.size() == standalones.size();
	}

	private int squareSize() {
		return SquareMaker.computeBranch(standalones.size());
	}

	private ILeaf getTopLeft() {
		return standalones.get(0);
	}

	private ILeaf getBottomLeft() {
		int result = SquareMaker.getBottomLeft(standalones.size());
		return standalones.get(result);
	}

	private ILeaf getTopRight() {
		final int s = squareSize();
		return standalones.get(s - 1);
	}

	@Override
	public String toString() {
		return standalones.get(0).getParentContainer() + " " + standalones.toString() + " " + isComplete();
	}

	public void linkToDown(Magma down) {
		system.addLink(new Link(this.getBottomLeft(), down.getTopLeft(), linkType, Display.NULL, 2));

	}

	public void linkToRight(Magma right) {
		system.addLink(new Link(this.getTopRight(), right.getTopLeft(), linkType, Display.NULL, 1));
	}

}
