package cn.udrm.dev.demo.util;

import java.security.NoSuchAlgorithmException;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(Digest.SHA1("111111"));
	}
}
