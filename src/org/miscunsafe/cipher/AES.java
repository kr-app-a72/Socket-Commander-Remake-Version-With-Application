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

import org.miscunsafe.Cipher;
import org.miscunsafe.util.Boost;
import java.util.Objects;

public final class AES extends Cipher {

	private static int getRounds (int key_len) {
		if (key_len == 16)
			return 9;
		else if (key_len == 24)
			return 11;
		else if (key_len == 32)
			return 13;
		else return
			0;
	}

	private static char gmul (char first, char second) {
		char third = 0, fourth;
		long i = 0;

		for (; i < 8 ; i ++) {
			if ((second & 1) != 0)
				third ^= first;

			fourth = (char) (first & 0x80);
			first <<= 1;

			if (fourth != 0)
				first ^= 0x1B;

			second >>= 1;
		}

		return third;
	}
	
	private boolean isInBlock (char data []) {
		if (Objects.requireNonNull (key) [0].length != data.length)
			return false;
		
		return true;
	}

	private static char s_box (char data) {
		return s_box [data];
	}

	private static char inv_s_box (char data) {
		return inv_s_box [data];
	}
	
	public static char [] substitute (final char [] str) {
		char data [] = new char [Objects.requireNonNull (str).length];
		
		for (int i = 0 ; i < str.length ; i ++)
			data [i] = s_box (str [i]);
		
		return data;
	}
	
	public static char [] invSubstitute (final char [] str) {
		char data [] = new char [Objects.requireNonNull (str).length];
		
		for (int i = 0 ; i < str.length ; i ++)
			data [i] = inv_s_box (str [i]);
		
		return data;
	}
	
	public static char [] shiftFrame (AES this_, char data []) throws CipherException {
		if (!Objects.requireNonNull (this_).isInBlock (Objects.requireNonNull (data)))
			throw new CipherException ("Block size error.");
		
		char ret [] = new char [data.length];
		int key_len = this_.key [0].length;
		
		for (int i = 0 ; i < (data.length / 4) ; i ++)
			ret [i] = data [i];
		
		for (int all = 0 ; all < data.length ; all += key_len) {
			if (key_len == 16) {
				ret [4 + all] = data [5];
				ret [5 + all] = data [6];
				ret [6 + all] = data [7];
				ret [7 + all] = data [4];
				ret [8 + all] = data [10];
				ret [9 + all] = data [11];
				ret [10 + all] = data [8];
				ret [11 + all] = data [9];
				ret [12 + all] = data [15];
				ret [13 + all] = data [12];
				ret [14 + all] = data [13];
				ret [15 + all] = data [14];
			}
			else if (key_len == 24) {
				
			}
			else if (key_len == 32) {
				
			}
		}
		
		return ret;
	}

	public static char [] invShiftFrames (AES this_, char data []) {
		if (!Objects.requireNonNull (this_).isInBlock (Objects.requireNonNull (data)))
			return null;
		
		char ret [] = new char [data.length];
		return ret;
	}

	public static char [] mixColumns (AES this_, char data []) {
		if (!Objects.requireNonNull (this_).isInBlock (Objects.requireNonNull (data)))
			return null;
		
		char ret [] = new char [data.length];
		return ret;
	}

	public static char [] invMixColumns (AES this_, char data []) {
		if (!Objects.requireNonNull (this_).isInBlock (Objects.requireNonNull (data)))
			return null;
		
		char ret [] = new char [data.length];
		return ret;
	}

	private static String base64_encode (byte data []) {
		return null;
	}

	private static char [] base64_Decode (String str) {
		return null;
	}

	@Override
	public boolean encode (char data [], char result []) {
		return true;
	}

	@Override
	public boolean decode (char data [], char result []) {
		return true;
	}

	@Override
	public boolean setKey (char key []) {
		if (key.length != 16 && key.length != 24 && key.length != 32 && key.length < 16)
			return false;
		else {
			int rounds = getRounds (key.length);

			if (rounds == 16)
				this.key = new char [11] [16];
			else if (rounds == 24)
				this.key = new char [13] [24];
			else if (rounds == 32)
				this.key = new char [15] [32];
				
			System.arraycopy (key, 0, this.key [0], 0, key.length);
			
			/*
			 * 1. for-loop statement is slower than direct declaration.
			 * 2. If performance can be improved, code does need to be long enough to make people bored and dead.
			 */
			if (key.length == 16) {
				for (int i = 1 ; i < rounds ; i ++) {
					this.key [i] [ 0] = (char) (this.key [i - 1] [ 0] ^ s_box (this.key [i - 1] [15]) ^ rcon [i - 1]);
					this.key [i] [ 1] = (char) (this.key [i - 1] [ 1] ^ s_box (this.key [i - 1] [12]) ^ 0x00);
					this.key [i] [ 2] = (char) (this.key [i - 1] [ 2] ^ s_box (this.key [i - 1] [13]) ^ 0x00);
					this.key [i] [ 3] = (char) (this.key [i - 1] [ 3] ^ s_box (this.key [i - 1] [14]) ^ 0x00);
					this.key [i] [ 4] = (char) (this.key [i] [ 0] ^ this.key [i - 1] [ 4]);
					this.key [i] [ 5] = (char) (this.key [i] [ 1] ^ this.key [i - 1] [ 5]);
					this.key [i] [ 6] = (char) (this.key [i] [ 2] ^ this.key [i - 1] [ 6]);
					this.key [i] [ 7] = (char) (this.key [i] [ 3] ^ this.key [i - 1] [ 7]);
					this.key [i] [ 8] = (char) (this.key [i] [ 4] ^ this.key [i - 1] [ 8]);
					this.key [i] [ 9] = (char) (this.key [i] [ 5] ^ this.key [i - 1] [ 9]);
					this.key [i] [10] = (char) (this.key [i] [ 6] ^ this.key [i - 1] [10]);
					this.key [i] [11] = (char) (this.key [i] [ 7] ^ this.key [i - 1] [11]);
					this.key [i] [12] = (char) (this.key [i] [ 8] ^ this.key [i - 1] [12]);
					this.key [i] [13] = (char) (this.key [i] [ 9] ^ this.key [i - 1] [13]);
					this.key [i] [14] = (char) (this.key [i] [10] ^ this.key [i - 1] [14]);
					this.key [i] [15] = (char) (this.key [i] [11] ^ this.key [i - 1] [15]);
				}
			}
			else if (key.length == 24) {
				for (int i = 1 ; i < rounds ; i ++) {
					this.key [i] [ 0] = (char) (this.key [i - 1] [ 0] ^ s_box (this.key [i - 1] [23]) ^ rcon [i - 1]);
					this.key [i] [ 1] = (char) (this.key [i - 1] [ 1] ^ s_box (this.key [i - 1] [18]) ^ 0x00);
					this.key [i] [ 2] = (char) (this.key [i - 1] [ 2] ^ s_box (this.key [i - 1] [19]) ^ 0x00);
					this.key [i] [ 3] = (char) (this.key [i - 1] [ 3] ^ s_box (this.key [i - 1] [20]) ^ 0x00);
					this.key [i] [ 4] = (char) (this.key [i - 1] [ 4] ^ s_box (this.key [i - 1] [21]) ^ 0x00);
					this.key [i] [ 5] = (char) (this.key [i - 1] [ 5] ^ s_box (this.key [i - 1] [22]) ^ 0x00); 
					this.key [i] [ 6] = (char) (this.key [i] [ 0] ^ this.key [i - 1] [ 6]);
					this.key [i] [ 7] = (char) (this.key [i] [ 1] ^ this.key [i - 1] [ 7]);
					this.key [i] [ 8] = (char) (this.key [i] [ 2] ^ this.key [i - 1] [ 8]);
					this.key [i] [ 9] = (char) (this.key [i] [ 3] ^ this.key [i - 1] [ 9]);
					this.key [i] [10] = (char) (this.key [i] [ 4] ^ this.key [i - 1] [10]);
					this.key [i] [11] = (char) (this.key [i] [ 5] ^ this.key [i - 1] [11]);
					this.key [i] [12] = (char) (this.key [i] [ 6] ^ this.key [i - 1] [12]);
					this.key [i] [13] = (char) (this.key [i] [ 7] ^ this.key [i - 1] [13]);
					this.key [i] [14] = (char) (this.key [i] [ 8] ^ this.key [i - 1] [14]);
					this.key [i] [15] = (char) (this.key [i] [ 9] ^ this.key [i - 1] [15]);
					this.key [i] [16] = (char) (this.key [i] [10] ^ this.key [i - 1] [16]);
					this.key [i] [17] = (char) (this.key [i] [11] ^ this.key [i - 1] [17]);
					this.key [i] [18] = (char) (this.key [i] [12] ^ this.key [i - 1] [18]);
					this.key [i] [19] = (char) (this.key [i] [13] ^ this.key [i - 1] [19]);
					this.key [i] [20] = (char) (this.key [i] [14] ^ this.key [i - 1] [20]);
					this.key [i] [21] = (char) (this.key [i] [15] ^ this.key [i - 1] [21]);
					this.key [i] [22] = (char) (this.key [i] [16] ^ this.key [i - 1] [22]);
					this.key [i] [23] = (char) (this.key [i] [17] ^ this.key [i - 1] [23]);
				}
			}
			else if (key.length == 32) {
				for (int i = 0 ; i < rounds ; i ++) {
					this.key [i] [ 0] = (char) (this.key [i - 1] [ 0] ^ s_box (this.key [i - 1] [31]) ^ rcon [i - 1]);
					this.key [i] [ 1] = (char) (this.key [i - 1] [ 1] ^ s_box (this.key [i - 1] [24]) ^ 0x00);
					this.key [i] [ 2] = (char) (this.key [i - 1] [ 2] ^ s_box (this.key [i - 1] [25]) ^ 0x00);
					this.key [i] [ 3] = (char) (this.key [i - 1] [ 3] ^ s_box (this.key [i - 1] [26]) ^ 0x00);
					this.key [i] [ 4] = (char) (this.key [i - 1] [ 4] ^ s_box (this.key [i - 1] [27]) ^ 0x00);
					this.key [i] [ 5] = (char) (this.key [i - 1] [ 5] ^ s_box (this.key [i - 1] [28]) ^ 0x00); 
					this.key [i] [ 6] = (char) (this.key [i - 1] [ 6] ^ s_box (this.key [i - 1] [29]) ^ 0x00);
					this.key [i] [ 7] = (char) (this.key [i - 1] [ 7] ^ s_box (this.key [i - 1] [30]) ^ 0x00); 
					this.key [i] [ 8] = (char) (this.key [i] [ 0] ^ this.key [i - 1] [ 8]);
					this.key [i] [ 9] = (char) (this.key [i] [ 1] ^ this.key [i - 1] [ 9]);
					this.key [i] [10] = (char) (this.key [i] [ 2] ^ this.key [i - 1] [10]);
					this.key [i] [11] = (char) (this.key [i] [ 3] ^ this.key [i - 1] [11]);
					this.key [i] [12] = (char) (this.key [i] [ 4] ^ this.key [i - 1] [12]);
					this.key [i] [13] = (char) (this.key [i] [ 5] ^ this.key [i - 1] [13]);
					this.key [i] [14] = (char) (this.key [i] [ 6] ^ this.key [i - 1] [14]);
					this.key [i] [15] = (char) (this.key [i] [ 7] ^ this.key [i - 1] [15]);
					this.key [i] [16] = (char) (this.key [i] [ 8] ^ this.key [i - 1] [16]);
					this.key [i] [17] = (char) (this.key [i] [ 9] ^ this.key [i - 1] [17]);
					this.key [i] [18] = (char) (this.key [i] [10] ^ this.key [i - 1] [18]);
					this.key [i] [19] = (char) (this.key [i] [11] ^ this.key [i - 1] [19]);
					this.key [i] [20] = (char) (this.key [i] [12] ^ this.key [i - 1] [20]);
					this.key [i] [21] = (char) (this.key [i] [13] ^ this.key [i - 1] [21]);
					this.key [i] [22] = (char) (this.key [i] [14] ^ this.key [i - 1] [22]);
					this.key [i] [23] = (char) (this.key [i] [15] ^ this.key [i - 1] [23]);
					this.key [i] [24] = (char) (this.key [i] [16] ^ this.key [i - 1] [24]);
					this.key [i] [25] = (char) (this.key [i] [17] ^ this.key [i - 1] [25]);
					this.key [i] [26] = (char) (this.key [i] [ 9] ^ this.key [i - 1] [26]);
					this.key [i] [27] = (char) (this.key [i] [10] ^ this.key [i - 1] [27]);
					this.key [i] [28] = (char) (this.key [i] [11] ^ this.key [i - 1] [28]);
					this.key [i] [29] = (char) (this.key [i] [12] ^ this.key [i - 1] [29]);
					this.key [i] [30] = (char) (this.key [i] [13] ^ this.key [i - 1] [30]);
					this.key [i] [31] = (char) (this.key [i] [14] ^ this.key [i - 1] [31]);
				}
			}
		}

		return true;
	}

	private char key [] [] = null;
	private static final char rcon [] = { 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36, 0x6C, 0xD8, 0xAB, 0x4D, 0x9A };
	private static final String base64_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static final char s_box [] = {
		0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 
		0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0, 
		0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 
		0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75, 
		0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 
		0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF, 
		0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 
		0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2, 
		0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73, 
		0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB, 
		0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xd3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79, 
		0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 
		0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xdD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A, 
		0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 
		0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF, 
		0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
	}, inv_s_box [] = {
		0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB, 
		0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB, 
		0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E, 
		0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25, 
		0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92, 
		0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84, 
		0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06, 
		0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B, 
		0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73, 
		0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E, 
		0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B, 
		0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4, 
		0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F, 
		0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF, 
		0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61, 
		0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D
	};

}
