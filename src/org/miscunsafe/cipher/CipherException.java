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

package org.miscunsafe.cipher;

import java.security.GeneralSecurityException;

public class CipherException extends GeneralSecurityException {

	public CipherException () {
	}

	public CipherException (String str) {
		super (str);
	}
	
	private static final long serialVersionUID = -7237021133010442521L;

}
