package net.risesoft.soa.asf.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public final class FrameServerJCEProvicer extends Provider
{
  private static final long serialVersionUID = 1L;
  private static boolean verifiedSelfIntegrity = false;

  private static X509Certificate providerCert = null;

  private static final byte[] bytesOfProviderCert = { 48, -126, 3, -76, 
    48, -126, 3, 114, -96, 3, 2, 1, 
    2, 2, 2, 1, 4, 48, 11, 6, 
    7, 42, -122, 72, -50, 56, 4, 3, 
    5, 0, 48, -127, -112, 49, 11, 48, 
    9, 6, 3, 85, 4, 6, 19, 2, 
    85, 83, 49, 11, 48, 9, 6, 3, 
    85, 4, 8, 19, 2, 67, 65, 49, 
    18, 48, 16, 6, 3, 85, 4, 7, 
    19, 9, 80, 97, 108, 111, 32, 65, 
    108, 116, 111, 49, 29, 48, 27, 6, 
    3, 85, 4, 10, 19, 20, 83, 117, 
    110, 32, 77, 105, 99, 114, 111, 115, 
    121, 115, 116, 101, 109, 115, 32, 73, 
    110, 99, 49, 35, 48, 33, 6, 3, 
    85, 4, 11, 19, 26, 74, 97, 118, 
    97, 32, 83, 111, 102, 116, 119, 97, 
    114, 101, 32, 67, 111, 100, 101, 32, 
    83, 105, 103, 110, 105, 110, 103, 49, 
    28, 48, 26, 6, 3, 85, 4, 3, 
    19, 19, 74, 67, 69, 32, 67, 111, 
    100, 101, 32, 83, 105, 103, 110, 105, 
    110, 103, 32, 67, 65, 48, 30, 23, 
    13, 48, 49, 49, 48, 49, 57, 50, 
    51, 48, 52, 51, 49, 90, 23, 13, 
    48, 54, 49, 48, 50, 51, 50, 51, 
    48, 52, 51, 49, 90, 48, 99, 49, 
    29, 48, 27, 6, 3, 85, 4, 10, 
    12, 20, 83, 117, 110, 32, 77, 105, 
    99, 114, 111, 115, 121, 115, 116, 101, 
    109, 115, 32, 73, 110, 99, 49, 35, 
    48, 33, 6, 3, 85, 4, 11, 12, 
    26, 74, 97, 118, 97, 32, 83, 111, 
    102, 116, 119, 97, 114, 101, 32, 67, 
    111, 100, 101, 32, 83, 105, 103, 110, 
    105, 110, 103, 49, 29, 48, 27, 6, 
    3, 85, 4, 3, 12, 20, 83, 117, 
    110, 32, 77, 105, 99, 114, 111, 115, 
    121, 115, 116, 101, 109, 115, 32, 73, 
    110, 99, 48, -126, 1, -75, 48, -126, 
    1, 42, 6, 5, 43, 14, 3, 2, 
    12, 48, -126, 1, 31, 2, -127, -127, 
    0, -3, 127, 83, -127, 29, 117, 18, 
    41, 82, -33, 74, -100, 46, -20, -28, 
    -25, -10, 17, -73, 82, 60, -17, 68, 
    0, -61, 30, 63, -128, -74, 81, 38, 
    105, 69, 93, 64, 34, 81, -5, 89, 
    61, -115, 88, -6, -65, -59, -11, -70, 
    48, -10, -53, -101, 85, 108, -41, -127, 
    59, -128, 29, 52, 111, -14, 102, 96, 
    -73, 107, -103, 80, -91, -92, -97, -97, 
    -24, 4, 123, 16, 34, -62, 79, -69, 
    -87, -41, -2, -73, -58, 27, -8, 59, 
    87, -25, -58, -88, -90, 21, 15, 4, 
    -5, -125, -10, -45, -59, 30, -61, 2, 
    53, 84, 19, 90, 22, -111, 50, -10, 
    117, -13, -82, 43, 97, -41, 42, -17, 
    -14, 34, 3, 25, -99, -47, 72, 1, 
    -57, 2, 21, 0, -105, 96, 80, -113, 
    21, 35, 11, -52, -78, -110, -71, -126, 
    -94, -21, -124, 11, -16, 88, 28, -11, 
    2, -127, -127, 0, -9, -31, -96, -123, 
    -42, -101, 61, -34, -53, -68, -85, 92, 
    54, -72, 87, -71, 121, -108, -81, -69, 
    -6, 58, -22, -126, -7, 87, 76, 11, 
    61, 7, -126, 103, 81, 89, 87, -114, 
    -70, -44, 89, 79, -26, 113, 7, 16, 
    -127, -128, -76, 73, 22, 113, 35, -24, 
    76, 40, 22, 19, -73, -49, 9, 50, 
    -116, -56, -90, -31, 60, 22, 122, -117, 
    84, 124, -115, 40, -32, -93, -82, 30, 
    43, -77, -90, 117, -111, 110, -93, 127, 
    11, -6, 33, 53, 98, -15, -5, 98, 
    122, 1, 36, 59, -52, -92, -15, -66, 
    -88, 81, -112, -119, -88, -125, -33, -31, 
    90, -27, -97, 6, -110, -117, 102, 94, 
    -128, 123, 85, 37, 100, 1, 76, 59, 
    -2, -49, 73, 42, 3, -127, -124, 
    0, 2, -127, -128, 7, -52, -10, 56, 58, 
    -51, -45, 88, -103, -112, 15, 113, -81, 
    -86, -48, 3, 39, 59, 116, -31, 100, 
    56, 17, -65, -6, -73, -65, 44, -25, 
    -69, -89, -110, 47, 8, -50, 39, -8, 
    -76, -3, -40, 20, 29, -93, -107, -69, 
    3, 22, -90, -70, -68, 53, -64, -51, 
    -7, -11, 108, -89, -108, 91, 35, 1, 
    -7, -82, -11, -55, -32, -127, 122, -24, 
    -28, 105, -21, -8, -11, -128, 37, 4, 
    44, -111, 115, -106, 89, -76, 6, -125, 
    23, -78, 80, -84, 79, -21, -99, 81, 
    37, 61, -9, -18, -80, 36, 37, 14, 
    -2, -76, 50, -95, -60, 14, -77, 102, 
    65, -32, 87, -50, -99, -66, 51, 46, 
    -109, -102, -55, 122, 87, -36, -51, -120, 
    96, -89, -50, -93, -127, -120, 48, -127, 
    -123, 48, 17, 6, 9, 96, -122, 72, 
    1, -122, -8, 66, 1, 1, 4, 4, 
    3, 2, 4, 16, 48, 14, 6, 3, 
    85, 29, 15, 1, 1, -1, 4, 4, 
    3, 2, 5, -32, 48, 29, 6, 3, 
    85, 29, 14, 4, 22, 4, 20, 85, 
    -115, 31, 42, 5, -85, -101, -50, -122, 
    16, -82, 59, 93, -10, -70, 63, 34, 
    -59, 106, -54, 48, 31, 6, 3, 85, 
    29, 35, 4, 24, 48, 22, -128, 20, 
    101, -30, -12, -122, -55, -45, 78, -16, 
    -111, 78, 88, -94, 106, -11, -40, 120, 
    90, -102, -63, -90, 48, 32, 6, 3, 
    85, 29, 17, 4, 25, 48, 23, -127, 
    21, 121, 117, 45, 99, 104, 105, 110, 
    103, 46, 112, 101, 110, 103, 64, 115, 
    117, 110, 46, 99, 111, 109, 48, 11, 
    6, 7, 42, -122, 72, -50, 56, 4, 
    3, 5, 0, 3, 47, 0, 48, 44, 
    2, 20, 117, 75, -24, 33, 55, 120, 
    121, 10, -48, -75, -36, 126, 54, 117, 
    -71, -28, 20, -75, -48, 70, 2, 20, 
    106, 81, -36, -70, 109, 26, 107, 92, 
    24, 35, 106, -15, -54, 33, -118, 119, 
    -62, 5, 22, 66 };

  public FrameServerJCEProvicer()
  {
    super("FrameServerJCEProvicer", 3.1D, "Provider which supports FrameServer.");
  }

  public static final synchronized boolean selfIntegrityChecking()
  {
    if (verifiedSelfIntegrity) {
      return true;
    }

    URL providerURL = (URL)AccessController.doPrivileged(new PrivilegedAction() {
      public Object run() {
        CodeSource cs = FrameServerJCEProvicer.class.getProtectionDomain().getCodeSource();
        return cs.getLocation();
      }
    });
    verifiedSelfIntegrity = integrityCheckingImpl(providerURL.toString());

    return verifiedSelfIntegrity;
  }

  public static final synchronized boolean integrityCheckingImpl(String fullFileName) {
    if (fullFileName == null)
      return false;
     tmp17_13 = new FrameServerJCEProvicer(); tmp17_13.getClass(); FrameServerJarVerifier jv = new FrameServerJarVerifier(fullFileName);
    try
    {
      if (providerCert == null) {
        providerCert = setupProviderCert();
      }
      jv.verify(providerCert);
    } catch (SecurityException se) {
      return error(se.getMessage() + fullFileName);
    } catch (IOException e) {
      return error(e.getMessage() + fullFileName);
    } catch (CertificateException e) {
      return error(e.getMessage() + fullFileName);
    }

    return true;
  }

  private static X509Certificate setupProviderCert()
    throws IOException, CertificateException
  {
    String rootCertificate = "MIIDYDCCAx4CBD/f1M0wCwYHKoZIzjgEAwUAMIGVMQswCQYDVQQGEwJDTjEPMA0GA1UECAwG5YyX5LqsMRIwEAYDVQQHDAnljJfkuqzluIIxMzAxBgNVBAoMKuWMl+S6rOacieeUn+WNmuWkp+i9r+S7tuaKgOacr+aciemZkOWFrOWPuDEZMBcGA1UECxMQd3d3LnJpc2Vzb2Z0Lm5ldDERMA8GA1UEAxMIUmlzZVNvZnQwHhcNMDMxMjE3MDQwMDEzWhcNMjMxMjEyMDQwMDEzWjCBlTELMAkGA1UEBhMCQ04xDzANBgNVBAgMBuWMl+S6rDESMBAGA1UEBwwJ5YyX5Lqs5biCMTMwMQYDVQQKDCrljJfkuqzmnInnlJ/ljZrlpKfova/ku7bmioDmnK/mnInpmZDlhazlj7gxGTAXBgNVBAsTEHd3dy5yaXNlc29mdC5uZXQxETAPBgNVBAMTCFJpc2VTb2Z0MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAc6smiN4ClODM48pkKjYVHu8DP777Q5DzKStPXCyD2dGXtqLSF2qy8ne/NG8t9SozO3LKHec7tAd58wGI5zwweereVR0vRBlDDopp5Oeb0YEmdjTKkyGxbO9GKGqT0dxaKT0nwgScBksbl48MgzKro09brHjTieVWlJ4Pl3Fal5YwCwYHKoZIzjgEAwUAAy8AMCwCFDcUAW5Ypec7dqEDc04eT38Jba+mAhQCfYN92zqX04o5SwHxt/AY8g2t0Q==";

    CertificateFactory cf = CertificateFactory.getInstance("X.509");

    new ByteArrayInputStream(bytesOfProviderCert);

    byte[] certBytes = Base64.decode(rootCertificate);
    InputStream in = new ByteArrayInputStream(certBytes);
    X509Certificate cert = (X509Certificate)cf.generateCertificate(in);
    in.close();

    return cert;
  }

  private static boolean error(String msg) {
    System.out.println("[FATAL] CHECK LICENSE ERROR:" + msg);
    return false;
  }

  private static String getInstallLocation(BundleContext bundleContext)
  {
    return System.getProperty("user.dir") + "/bundles/";
  }

  public static boolean isBundleJarSigned(Bundle bundle) {
    try {
      String location = bundle.getLocation();

      String osgiDev = System.getProperty("osgi.dev");
      if (osgiDev != null) {
        return true;
      }

      String prefix = "initial@reference:file:";
      location = location.substring(prefix.length(), location.length() - 1);
      location = getInstallLocation(bundle.getBundleContext()) + location;

      return integrityCheckingImpl(location);
    }
    catch (Exception e) {
      System.out.println("[FATAL] CHECK LICENSE ERROR:" + e.getMessage());
      System.exit(99); }
    return false;
  }

  public static void main(String[] args) throws MalformedURLException
  {
    integrityCheckingImpl("d:/tmp/test.jar");
  }

  final class FrameServerJarVerifier
  {
    private String fullFileName = null;
    private JarFile jarFile = null;

    public FrameServerJarVerifier(String paramString) {
      this.fullFileName = paramString;
    }

    private JarFile retrieveJarFileFromURL(String fullFileName)
      throws IOException
    {
      return new JarFile(fullFileName);
    }

    public void verify(X509Certificate targetCert)
      throws IOException
    {
      if (targetCert == null) {
        throw new SecurityException("Provider certificate is invalid");
      }
      try
      {
        if (this.jarFile == null)
          this.jarFile = retrieveJarFileFromURL(this.fullFileName);
      }
      catch (Exception ex) {
        SecurityException se = new SecurityException();
        se.initCause(ex);
        throw se;
      }

      Vector entriesVec = new Vector();

      Manifest man = this.jarFile.getManifest();
      if (man == null) {
        throw new SecurityException("The provider is not signed");
      }

      byte[] buffer = new byte[8192];
      Enumeration entries = this.jarFile.entries();

      while (entries.hasMoreElements()) {
        JarEntry je = (JarEntry)entries.nextElement();

        if (je.isDirectory()) {
          continue;
        }
        entriesVec.addElement(je);
        InputStream is = this.jarFile.getInputStream(je);

        while (is.read(buffer, 0, buffer.length) != -1);
        is.close();
      }

      Enumeration e = entriesVec.elements();

      while (e.hasMoreElements()) {
        JarEntry je = (JarEntry)e.nextElement();

        Certificate[] certs = je.getCertificates();
        if ((certs == null) || (certs.length == 0)) {
          if (!(je.getName().startsWith("META-INF"))) {
            throw new SecurityException("The jar has unsigned " + je.getName() + " class files.");
          }
        }
        else
        {
          int startIndex = 0;

          boolean signedAsExpected = false;
          X509Certificate[] certChain;
          while ((certChain = getAChain(certs, startIndex)) != null) {
            if (certChain[0].equals(targetCert))
            {
              signedAsExpected = true;
              break;
            }

            startIndex += certChain.length;
          }

          if (!(signedAsExpected))
            throw new SecurityException("The jar is not signed by a trusted signer");
        }
      }
    }

    private X509Certificate[] getAChain(Certificate[] certs, int startIndex)
    {
      if (startIndex > certs.length - 1) {
        return null;
      }

      for (int i = startIndex; i < certs.length - 1; ++i) {
        if (!(((X509Certificate)certs[(i + 1)]).getSubjectDN().equals(((X509Certificate)certs[i]).getIssuerDN()))) {
          break;
        }
      }

      int certChainSize = i - startIndex + 1;
      X509Certificate[] ret = new X509Certificate[certChainSize];
      for (int j = 0; j < certChainSize; ++j) {
        ret[j] = ((X509Certificate)certs[(startIndex + j)]);
      }
      return ret;
    }

    protected void finalize() throws Throwable
    {
      this.jarFile.close();
    }
  }

  final class FrameServerJarVerifier1 {
    private URL jarURL = null;
    private JarFile jarFile = null;

    public FrameServerJarVerifier1(URL paramURL) {
      this.jarURL = paramURL;
    }

    private JarFile retrieveJarFileFromURL(URL url)
      throws PrivilegedActionException, MalformedURLException
    {
      JarFile jf = null;

      this.jarURL = new URL("jar:" + url.toString() + "!/");

      jf = (JarFile)AccessController.doPrivileged(new PrivilegedExceptionAction() {
        public Object run() throws Exception {
          JarURLConnection conn = (JarURLConnection)FrameServerJCEProvicer.FrameServerJarVerifier1.this.jarURL.openConnection();

          conn.setUseCaches(false);
          return conn.getJarFile();
        }
      });
      return jf;
    }

    public void verify(X509Certificate targetCert)
      throws IOException
    {
      if (targetCert == null) {
        throw new SecurityException("Provider certificate is invalid");
      }
      try
      {
        if (this.jarFile == null)
          this.jarFile = retrieveJarFileFromURL(this.jarURL);
      }
      catch (Exception ex) {
        SecurityException se = new SecurityException();
        se.initCause(ex);
        throw se;
      }

      Vector entriesVec = new Vector();

      Manifest man = this.jarFile.getManifest();
      if (man == null) {
        throw new SecurityException("The provider is not signed");
      }

      byte[] buffer = new byte[8192];
      Enumeration entries = this.jarFile.entries();

      while (entries.hasMoreElements()) {
        JarEntry je = (JarEntry)entries.nextElement();

        if (je.isDirectory()) {
          continue;
        }
        entriesVec.addElement(je);
        InputStream is = this.jarFile.getInputStream(je);

        while (is.read(buffer, 0, buffer.length) != -1);
        is.close();
      }

      Enumeration e = entriesVec.elements();

      while (e.hasMoreElements()) {
        JarEntry je = (JarEntry)e.nextElement();

        Certificate[] certs = je.getCertificates();
        if ((certs == null) || (certs.length == 0)) {
          if (!(je.getName().startsWith("META-INF"))) {
            throw new SecurityException("The jar has unsigned " + je.getName() + " class files.");
          }
        }
        else
        {
          int startIndex = 0;

          boolean signedAsExpected = false;
          X509Certificate[] certChain;
          while ((certChain = getAChain(certs, startIndex)) != null) {
            if (certChain[0].equals(targetCert))
            {
              signedAsExpected = true;
              break;
            }

            startIndex += certChain.length;
          }

          if (!(signedAsExpected))
            throw new SecurityException("The jar is not signed by a trusted signer");
        }
      }
    }

    private X509Certificate[] getAChain(Certificate[] certs, int startIndex)
    {
      if (startIndex > certs.length - 1) {
        return null;
      }

      for (int i = startIndex; i < certs.length - 1; ++i) {
        if (!(((X509Certificate)certs[(i + 1)]).getSubjectDN().equals(((X509Certificate)certs[i]).getIssuerDN()))) {
          break;
        }
      }

      int certChainSize = i - startIndex + 1;
      X509Certificate[] ret = new X509Certificate[certChainSize];
      for (int j = 0; j < certChainSize; ++j) {
        ret[j] = ((X509Certificate)certs[(startIndex + j)]);
      }
      return ret;
    }

    protected void finalize() throws Throwable
    {
      this.jarFile.close();
    }
  }
}