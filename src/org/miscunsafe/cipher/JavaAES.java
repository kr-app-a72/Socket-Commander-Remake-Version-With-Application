package org.miscunsafe.cipher;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.miscunsafe.Cipher;
import org.miscunsafe.util.Boost;

public final class JavaAES extends Cipher {

	@Override
	public void encode (char data [], char result []) throws CipherException {
		Boost boost = new Boost ();
		
		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance ("AES/ECB/PKCS5Padding");
			cipher.init (javax.crypto.Cipher.ENCRYPT_MODE, secretKey);
			System.arraycopy (boost.asCharArray(cipher.doFinal (boost.asByteArray (data))), 0, Objects.requireNonNull (result), 0, result.length);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CipherException (e.getLocalizedMessage ());
		}
	}

	@Override
	public void decode (char data [], char result []) throws CipherException {
		Boost boost = new Boost ();

		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance ("AES/ECB/PKCS5Padding");
			cipher.init (javax.crypto.Cipher.DECRYPT_MODE, secretKey);
			System.arraycopy (boost.asCharArray(cipher.doFinal (boost.asByteArray (data))), 0, Objects.requireNonNull (result), 0, result.length);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CipherException (e.getLocalizedMessage ());
		}
	}

	@Override
	public String encode (char data []) throws CipherException {
		Boost boost = new Boost ();

		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance ("AES/ECB/PKCS5Padding");
			cipher.init (javax.crypto.Cipher.ENCRYPT_MODE, secretKey);

			return boost.base64_encode (boost.asCharArray(cipher.doFinal (boost.asByteArray (data))));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CipherException (e.getLocalizedMessage ());
		}
	}

	@Override
	public char [] decode (String str) throws CipherException {
		Boost boost = new Boost ();

		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance ("AES/ECB/PKCS5Padding");
			cipher.init (javax.crypto.Cipher.DECRYPT_MODE, secretKey);

			return boost.asCharArray(cipher.doFinal (boost.asByteArray (boost.base64_decode (str))));
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
