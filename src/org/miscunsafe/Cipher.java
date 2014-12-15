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

	/**
	 * Creates new cipher instance.
	 * @return Cipher Instance of Cipher.
	 * @param str "Nickname" of cipher class.
	 * @throw CipherException This exception will occur when following cipher name does not match any of 
	 * instances of cipher class.
	 */
	public static final Cipher getCipher (String str) throws CipherException {
		if (str.equalsIgnoreCase ("JAES"))
			return new JavaAES ();

		throw new CipherException ("Unknown cipher name.");
	}

	/**
	 * Encodes data.
	 * @param data Data to encode.
	 * @param result Result of data will be stored into this variable. This array length should be longer than data array.
	 * @throw CipherException This exception will occur when there was an error.
	 * @see org.miscunsafe.Cipher#decode(char [], char []);
	 */
	public void encode (char data [], char result []) throws CipherException {
	}

	/**
	 * Decodes data.
	 * @param data Data to decode.
	 * @param result Result of data will be stored into this variable. This array length should be longer than data array.
	 * @throw CipherException This exception will occur when there was an error.
	 * @see org.miscunsafe.Cipher#encode(char [], char []);
	 */
	public void decode (char data [], char result []) throws CipherException {
	}
	
	/**
	 * Encodes data.
	 * This method will return result of encryption with BASE64 encoding.
	 * @param data Data to encode.
	 * @return String result of encoding. Will be encoded with base64
	 * @throw CipherException This exception will occur if there was an error.
	 */
	public String encode (char data []) throws CipherException { return null;
	}

	/**
	 * Decodes data.
	 * This method will return result of encryption with BASE64 format.
	 * @param str String to decode. Must be in base64 format.
	 * @return char [] Result.
	 * @throw CipherException This exception will occur if there was an error.
	 */
	public char [] decode (String str) throws CipherException { return null;
	}

	/**
	 * Sets key for current cipher.
	 * @param key Key value.
	 * @throw CipherException This exception will be thrown if the key is not eligible for this cipher.
	 */
	public abstract void setKey (char key []) throws CipherException;

	/**
	 * Encodes string.
	 * Basically, this method converts string to char array, then try to encode the data.
	 * @param str String to encode.
	 * @throw CipherException Will be thrown when encoding fails.
	 * @return String Result of encoding. 
	 */
	public String encodeString (String str) throws CipherException {
		char data [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);
		return encode (data);
	}

	/**
	 * Decodes string.
	 * Basically, this method converts string to char array, then try to decode the data.
	 * @param str String to decode.
	 * @throw CipherException Will be thrown when decoding fails.
	 * @return String Result of decoding. 
	 */
	public String decodeString (String str) throws CipherException {
		return new String (decode (str));
	}

	/**
	 * Sets key for current cipher.
	 * @param str Key vlaue.
	 * @throw CipherException Will be thrown when your key sucks.
	 */
	public void setKey (String str) throws CipherException {
		char data [] = new char [str.length ()];

		str.getChars (0, str.length (), data, 0);
		setKey (data);
	}

	public static final String AES_ENCRYPTION = "JAES";

}
