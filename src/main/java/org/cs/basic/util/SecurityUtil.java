package org.cs.basic.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	public static String md5(String password) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, md.digest()).toString(16);
	}

}