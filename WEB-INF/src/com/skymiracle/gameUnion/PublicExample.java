package com.skymiracle.gameUnion;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignatureException;

public class PublicExample {
	public static void main(String[] args) throws Exception {

		String pwd = "tianliang";
		//		
		// byte[] plainText=pwd.getBytes("UTF8");
		// // 构成一个RSA密钥
		// System.out.println("\nStart generating RSA key");
		// KeyPairGenerator keyGen=KeyPairGenerator.getInstance("RSA");
		// keyGen.initialize(1024);
		// KeyPair key=keyGen.generateKeyPair();
		// System.out.println("Finish generating RSA key");
		// // 获得一个RSA的Cipher类，使用公鈅加密
		// Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
		// System.out.println("\n"+cipher.getProvider().getInfo());
		// System.out.println("\nStart encryption");
		//
		// cipher.init(Cipher.ENCRYPT_MODE,key.getPublic());
		// byte[] cipherText=cipher.doFinal(plainText);
		// System.out.println("Finish encryption:");
		// System.out.println(new String(cipherText,"UTF8"));
		//
		// // 使用私鈅解密
		// System.out.println("\nStart decryption");
		// cipher.init(Cipher.DECRYPT_MODE,key.getPrivate());
		// byte[] newPlainText=cipher.doFinal(cipherText);
		// System.out.println("Finish decryption:");
		// System.out.println(new String(newPlainText,"UTF8"));

		byte[] plainText = pwd.getBytes("UTF8");

		// 形成RSA公钥对
		System.out.println("\nStart generating RSA key");
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);
		KeyPair key = keyGen.generateKeyPair();
		System.out.println("Finish generating RSA key");
	
		// 使用私鈅签名
		Signature sig = Signature.getInstance("SHA1WithRSA");
		sig.initSign(key.getPrivate());
		sig.update(plainText);
		byte[] signature = sig.sign();
		System.out.println(sig.getProvider().getInfo());
		System.out.println("\nSignature:");
		System.out.println(new String(signature, "UTF8"));

		// 使用公鈅验证
		System.out.println("\nStart signature verification");
		sig.initVerify(key.getPublic());
		sig.update(plainText);
		try {
			if (sig.verify(signature)) {
				System.out.println("Signature verified");
			} else
				System.out.println("Signature failed");
		} catch (SignatureException e) {
			System.out.println("Signature failed");
		}

	}
}
