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

package org.miscunsafe.util;

import org.miscunsafe.Cipher;
import org.miscunsafe.cipher.CipherException;

import java.io.InputStream;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * yourmama397's boost class.
 * This class contains several useless bullshit codes that might boost up speed, or
 * delays software.
 * This class cannot be instantiate by other classes from other package.
 * @author yourmama397
 * @version 0.7
 */
public final class Boost {

	public Boost () {
		String cname = getCallerClass ().getName ();

		if (!cname.startsWith ("org.miscunsafe"))
			throw new SecurityException (buildString ("Class, ", cname, " is not allowed to use this class."));
	}

	public String buildString (String str, Object ... obj) {
		StringBuilder sb = new StringBuilder (str);
		
		for (Object o : obj)
			sb.append (o);
		
		return sb.toString ();
	}

	public Class <?> getCallerClass () {
		StackTraceElement ste [] = (new Exception ()).getStackTrace ();

		try {
			return Class.forName(ste[ste.length - 1].getClassName());
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public int gc () {
		List <GarbageCollectorMXBean> gcmb = ManagementFactory.getGarbageCollectorMXBeans();
		int bf = 0, at = 0;

		for (GarbageCollectorMXBean bean : gcmb) 
			bf += bean.getCollectionCount();
		
		Runtime.getRuntime().gc();

		for (GarbageCollectorMXBean bean : gcmb) 
			bf += bean.getCollectionCount();
		
		return at - bf;
	}

	public int fread (InputStream is, byte test []) {
		try {
			return is.read (test, 0, test.length);
		} catch (IOException e) {
			e.printStackTrace ();
		}

		return -1;
	}

	public String requireStartsWith (String str, String target) {
		if (str.startsWith (target));
		else
			throw new RuntimeException ("Does not starts with.");

		return str;
	}

	public Cipher getAESCipher () {
		try {
			return Cipher.getCipher (Cipher.AES_ENCRYPTION);
		} catch (CipherException e) {
		}
		return null;
	}

	public char [] asCharArray (String str) {
		char ret [] = new char [str.length ()];
		str.getChars (0, str.length (), ret, 0);
		return ret;
	}

	public char [] asCharArray (byte data []) {
		char ret [] = new char [data.length];
		
		for (int i = 0 ; i < ret.length ; i ++)
			ret [i] = ((char) data [i]);
		
		return ret;
	}

	public byte [] asByteArray (char data []) {
		byte ret [] = new byte [data.length];
		
		for (int i = 0 ; i < ret.length ; i ++)
			ret [i] = ((byte) data [i]);
		
		return ret;
	}

	public String base64_encode (char data []) {
		char arr_3 [] = new char [3], arr_4 [] = new char [4];
		int i = 0, j = 0, k = 0, len = data.length;
		StringBuilder ret = new StringBuilder ();

		while ((len --) != 0) {
			arr_3 [i ++] = data [k ++];

			if (i == 3) {
				arr_4 [0] = (char) ((arr_3 [0] & 0xFC) >> 2);
				arr_4 [1] = (char) (((arr_3 [0] & 0x03) << 4) + ((arr_3 [1] & 0xF0) >> 4));
				arr_4 [2] = (char) (((arr_3 [1] & 0x0F) << 2) + ((arr_3 [2] & 0xC0) >> 6));
				arr_4 [3] = (char) (arr_3 [2] & 0x3F);

				for (i = 0 ; i < 4 ; i ++)
					ret.append (base64_chars.charAt(arr_4 [i]));

				i = 0;
			}
		}

		if (i != 0) { // If need to put extra character.
			for (j = i ; j < 3 ; j ++)
				arr_3 [j] = 0;

			arr_4 [0] = (char) ((arr_3 [0] & 0xFC) >> 2);
			arr_4 [1] = (char) (((arr_3 [0] & 0x03) << 4) + ((arr_3 [1] & 0xF0) >> 4));
			arr_4 [2] = (char) (((arr_3 [1] & 0x0F) << 2) + ((arr_3 [2] & 0xC0) >> 6));
			arr_4 [3] = (char) (arr_3 [2] & 0x3F);

			for (j = 0 ; j < (i + 1) ; j ++)
				ret.append (base64_chars.charAt (arr_4 [j]));

			while (i ++ < 3)
				ret.append ('=');
		}

		return ret.toString ();
	}

	public char [] base64_decode (String str) {
		char arr_3 [] = new char [3], arr_4 [] = new char [4], retc [];
		int i = 0, j = 0, k = 0, len = str.length ();
		StringBuilder ret = new StringBuilder ();

		while (len != 0 && str.charAt (k) != '=' && isBase64 (str.charAt (k))) {
			arr_4 [i ++] = str.charAt (k ++);
			len --;

			if (i == 4) {
				for (i = 0 ; i < 4 ; i ++)
					arr_4 [i] = (char) base64_chars.indexOf (arr_4 [i]);

				arr_3 [0] = (char) ((arr_4 [0] << 2) + ((arr_4 [1] & 0x30) >> 4));
				arr_3 [1] = (char) (((arr_4 [1] & 0x0F) << 4) + ((arr_4 [2] & 0x3C) >> 2));
				arr_3 [2] = (char) (((arr_4 [2] & 0x03) << 6) + arr_4 [3]);

				for (i = 0 ; i < 3 ; i ++)
					ret.append (arr_3 [i]);

				i = 0;
			}
		}

		if (i != 0) { // left overs.
			for (j = i ; j < 4 ; j ++)
				arr_4 [j] = 0;

			for (j = 0 ; j < 4 ; j ++)
				arr_4 [j] = (char) base64_chars.indexOf (arr_4 [j]);

			arr_3 [0] = (char) ((arr_4 [0] << 2) + ((arr_4 [1] & 0x30) >> 4));
			arr_3 [1] = (char) (((arr_4 [1] & 0x0F) << 4) + ((arr_4 [2] & 0x3C) >> 2));
			arr_3 [2] = (char) (((arr_4 [2] & 0x03) << 6) + arr_4 [3]);

			for (j = 0 ; j < (i - 1) ; j ++) 
				ret.append (arr_3 [j]);
		}
		
		retc = new char [ret.toString ().length ()];
		ret.toString ().getChars(0, ret.toString ().length (), retc, 0);

		return retc;
	}

	public static boolean isBase64 (char data) {
		return (data >= 0x20 && data < 0x7F) || data == '+' || data == '/';
	}

	private static final String base64_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

}
