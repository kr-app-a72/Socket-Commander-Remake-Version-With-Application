package org.miscunsafe.cipher;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.miscunsafe.Cipher;
import org.miscunsafe.util.Boost;

public final class JavaAES extends Cipher {

	@Override
	public String encode (char data []) throws CipherException {
		Boost boost = new Boost ();

		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance ("AES/ECB/PKCS7Padding");
			cipher.init (javax.crypto.Cipher.ENCRYPT_MODE, secretKey);

			return AES.base64_encode (boost.asCharArray(cipher.doFinal (boost.asByteArray (data))));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CipherException (e.getLocalizedMessage ());
		}
	}

	@Override
	public char [] decode (String str) throws CipherException {
		Boost boost = new Boost ();

		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance ("AES/ECB/PKCS7Padding");
			cipher.init (javax.crypto.Cipher.DECRYPT_MODE, secretKey);

			return boost.asCharArray(cipher.doFinal (boost.asByteArray (AES.base64_decode (str))));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CipherException (e.getLocalizedMessage ());
		}
	}

	@Override
	public void setKey (char key []) throws CipherException {
		MessageDigest sha = null;
		Boost boost = new Boost ();

        try {
			this.key = boost.asByteArray (key);

			sha = MessageDigest.getInstance ("SHA-1");

			this.key = sha.digest (this.key);
			this.key = Arrays.copyOf (this.key, 16);

			secretKey = new SecretKeySpec (this.key, "AES");
		} catch (NoSuchAlgorithmException e) {
			throw new CipherException (e.getLocalizedMessage());
		}
	}

	private SecretKeySpec secretKey;
	private byte key [];

}
