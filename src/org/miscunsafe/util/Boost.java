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
import java.io.InputStream;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public final class Boost {

	public Boost () {
		String cname = getCallerClass ().getName ();

		if (!cname.startsWith ("org.miscunsafe"))
			throw new RuntimeException (buildString ("Class, ", cname, " is not allowed to use this class."));
	}

	public String buildString (String str, Object ... obj) {
		return new StringBuilder (str).append (obj).toString ();
	}

	public Class <?> getCallerClass () {
		StackTraceElement ste [] = (new Exception ()).getStackTrace ();

		try {
			return Class.forName(ste[ste.length - 2].getClassName());
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
		return Cipher.getCipher ("AES");
	}

}
