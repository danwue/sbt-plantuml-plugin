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
package net.sourceforge.plantuml.dedication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

public class QBlock {

	private final BigInteger big;

	public static QBlock read(InputStream source, int size) throws IOException {
		final byte[] block = new byte[size + 1];
		for (int i = 0; i < size; i++) {
			final int read = source.read();
			if (read == -1) {
				if (i == 0) {
					return null;
				}
				break;
			}
			block[i + 1] = (byte) read;
		}
		return new QBlock(new BigInteger(block));
	}

	public QBlock(BigInteger number) {
		this.big = number;
	}

	public QBlock change(BigInteger E, BigInteger N) {
		final BigInteger changed = big.modPow(E, N);
		return new QBlock(changed);
	}

	@Override
	public String toString() {
		return big.toByteArray().length + " " + big.toString();
	}

	public void write(OutputStream os, int size) throws IOException {
		final byte[] data = big.toByteArray();
		final int start = data.length - size;
		if (start < 0) {
			for (int i = 0; i < -start; i++) {
				os.write(0);
			}
		}
		for (int i = Math.max(start, 0); i < data.length; i++) {
			int b = data[i];
			os.write(b);
		}

	}

}
