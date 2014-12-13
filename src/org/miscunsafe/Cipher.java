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

import org.miscunsafe.cipher.JavaAES;
import org.miscunsafe.cipher.CipherException;

/**
 * Cipher class.
 * This class is abstract class for encryptions.
 * @author yourmama397
 * @version 0.3
 */
public strictfp abstract class Cipher extends Object {

	protected Cipher () {
	}

	public static final Cipher getCipher (String str) throws CipherException {
		if (str.equalsIgnoreCase ("JAES"))
			return new JavaAES ();

		throw new CipherException ("Unknown cipher name.");
	}

	public void encode (char data [], char result []) throws CipherException {
	}

	public void decode (char data [], char result []) throws CipherException {
	}
	
	public String encode (char data []) throws CipherException { return null;
	}

	public char [] decode (String str) throws CipherException { return null;
	}

	public abstract void setKey (char key []) throws CipherException;

	public void encodeString (String str) throws CipherException {
		char data [] = new char [str.length ()], extra [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);

		encode (data, extra);
		System.arraycopy (extra, 0, data, 0, str.length ());
	}

	public void decodeString (String str) throws CipherException {
		char data [] = new char [str.length ()], extra [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);

		decode (data, extra);
		System.arraycopy (extra, 0, data, 0, str.length ());
	}

	public void setKey (String str) throws CipherException {
		char data [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);
		setKey (data);
	}

	public static final String AES_ENCRYPTION = "JAES";

}
