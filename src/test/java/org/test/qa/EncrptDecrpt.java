package org.test.qa;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.*;

public class EncrptDecrpt extends BaseClass{
	public static String key;
	public static String initVector;
	
	public static String encrypt( String value) {
		try {
			key= getDataFromConfigPropertyFile("key");
			initVector =  getDataFromConfigPropertyFile("initVector");
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			String encodeBase64String = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
			System.out.println("encrypted string: " + encodeBase64String);

			return encodeBase64String;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	public static String decrypt(String encrypted) {
		try {
			key= getDataFromConfigPropertyFile("key");
			initVector =  getDataFromConfigPropertyFile("initVector");
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static void keys() {

		String key = "Bar12345Bar12345";
		String initVector = "RandomInitVector";

		//System.out.println(decrypt(key, initVector, encrypt("Hello World")));

	}
	public static void main(String[] args) {
		
		System.out.println(decrypt(encrypt("Test@2022")));
		
	}

}
