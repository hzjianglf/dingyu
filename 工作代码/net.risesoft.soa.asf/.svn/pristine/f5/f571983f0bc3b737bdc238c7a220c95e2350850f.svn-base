package net.risesoft.soa.asf.core.util;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestSHA1PasswordEncoder
{
  public String encode(String password)
  {
    if ((password == null) || (password.length() == 0)) throw new IllegalArgumentException("Password cannot be null!");
    try
    {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
      messageDigest.update(password.getBytes());
      byte[] sha1Bytes = messageDigest.digest();
      return byte2hex(sha1Bytes);
    } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
      throw new IllegalArgumentException("sha-1 failed!");
    }
  }

  private static String byte2hex(byte[] b) {
    StringBuffer buff = new StringBuffer();

    for (int n = 0; n < b.length; ++n) {
      String stmp = Integer.toHexString(b[n] & 0xFF);
      if ((stmp != null) && (stmp.length() == 1))
        buff.append('0').append(stmp);
      else
        buff.append(stmp);
      if (n >= b.length - 1) continue; buff.append(':');
    }
    return buff.toString().toUpperCase();
  }

  public static void main(String[] args) {
    System.out.println(new DigestSHA1PasswordEncoder().encode("111111"));
  }
}