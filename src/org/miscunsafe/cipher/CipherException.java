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
