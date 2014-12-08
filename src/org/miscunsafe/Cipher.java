/*
 * Copyright (C) 2014 yourmama397.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.miscunsafe;

import org.miscunsafe.cipher.AES;

/**
 * @author yourmama397
 * @version 0.1
 */
public abstract class Cipher {

	protected Cipher () {
	}

	public static final Cipher getCipher (String str) {
		if (str.equalsIgnoreCase ("AES"))
			return new AES ();

		return null;
	}

	public abstract boolean encode (char data [], char result []);

	public abstract boolean decode (char data [], char result []);

	public abstract boolean setKey (char key []);

	public void encodeString (String str) {
		char data [] = new char [str.length ()], extra [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);

		encode (data, extra);
		System.arraycopy (extra, 0, data, 0, str.length ());
	}

	public void decodeString (String str) {
		char data [] = new char [str.length ()], extra [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);

		decode (data, extra);
		System.arraycopy (extra, 0, data, 0, str.length ());
	}

	public void setKey (String str) {
		char data [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);
		setKey (data);
	}


	public static void main (String ... args) {
	}


}
