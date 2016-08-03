/*     */ package net.risesoft.soa.filecube.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.CodeSource;
/*     */ import java.security.Principal;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.security.Provider;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.CertificateFactory;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.Manifest;
/*     */ import net.risesoft.soa.framework.util.Base64;
/*     */ import org.osgi.framework.Bundle;
/*     */ import org.osgi.framework.BundleContext;
/*     */ 
/*     */ public final class FrameServerJCEProvicer extends Provider
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  85 */   private static boolean verifiedSelfIntegrity = false;
/*     */ 
/*  88 */   private static X509Certificate providerCert = null;
/*     */ 
/*  93 */   private static final byte[] bytesOfProviderCert = { 48, -126, 3, -76, 
/*  94 */     48, -126, 3, 114, -96, 3, 2, 1, 
/*  95 */     2, 2, 2, 1, 4, 48, 11, 6, 
/*  96 */     7, 42, -122, 72, -50, 56, 4, 3, 
/*  97 */     5, 0, 48, -127, -112, 49, 11, 48, 
/*  98 */     9, 6, 3, 85, 4, 6, 19, 2, 
/*  99 */     85, 83, 49, 11, 48, 9, 6, 3, 
/* 100 */     85, 4, 8, 19, 2, 67, 65, 49, 
/* 101 */     18, 48, 16, 6, 3, 85, 4, 7, 
/* 102 */     19, 9, 80, 97, 108, 111, 32, 65, 
/* 103 */     108, 116, 111, 49, 29, 48, 27, 6, 
/* 104 */     3, 85, 4, 10, 19, 20, 83, 117, 
/* 105 */     110, 32, 77, 105, 99, 114, 111, 115, 
/* 106 */     121, 115, 116, 101, 109, 115, 32, 73, 
/* 107 */     110, 99, 49, 35, 48, 33, 6, 3, 
/* 108 */     85, 4, 11, 19, 26, 74, 97, 118, 
/* 109 */     97, 32, 83, 111, 102, 116, 119, 97, 
/* 110 */     114, 101, 32, 67, 111, 100, 101, 32, 
/* 111 */     83, 105, 103, 110, 105, 110, 103, 49, 
/* 112 */     28, 48, 26, 6, 3, 85, 4, 3, 
/* 113 */     19, 19, 74, 67, 69, 32, 67, 111, 
/* 114 */     100, 101, 32, 83, 105, 103, 110, 105, 
/* 115 */     110, 103, 32, 67, 65, 48, 30, 23, 
/* 116 */     13, 48, 49, 49, 48, 49, 57, 50, 
/* 117 */     51, 48, 52, 51, 49, 90, 23, 13, 
/* 118 */     48, 54, 49, 48, 50, 51, 50, 51, 
/* 119 */     48, 52, 51, 49, 90, 48, 99, 49, 
/* 120 */     29, 48, 27, 6, 3, 85, 4, 10, 
/* 121 */     12, 20, 83, 117, 110, 32, 77, 105, 
/* 122 */     99, 114, 111, 115, 121, 115, 116, 101, 
/* 123 */     109, 115, 32, 73, 110, 99, 49, 35, 
/* 124 */     48, 33, 6, 3, 85, 4, 11, 12, 
/* 125 */     26, 74, 97, 118, 97, 32, 83, 111, 
/* 126 */     102, 116, 119, 97, 114, 101, 32, 67, 
/* 127 */     111, 100, 101, 32, 83, 105, 103, 110, 
/* 128 */     105, 110, 103, 49, 29, 48, 27, 6, 
/* 129 */     3, 85, 4, 3, 12, 20, 83, 117, 
/* 130 */     110, 32, 77, 105, 99, 114, 111, 115, 
/* 131 */     121, 115, 116, 101, 109, 115, 32, 73, 
/* 132 */     110, 99, 48, -126, 1, -75, 48, -126, 
/* 133 */     1, 42, 6, 5, 43, 14, 3, 2, 
/* 134 */     12, 48, -126, 1, 31, 2, -127, -127, 
/* 135 */     0, -3, 127, 83, -127, 29, 117, 18, 
/* 136 */     41, 82, -33, 74, -100, 46, -20, -28, 
/* 137 */     -25, -10, 17, -73, 82, 60, -17, 68, 
/* 138 */     0, -61, 30, 63, -128, -74, 81, 38, 
/* 139 */     105, 69, 93, 64, 34, 81, -5, 89, 
/* 140 */     61, -115, 88, -6, -65, -59, -11, -70, 
/* 141 */     48, -10, -53, -101, 85, 108, -41, -127, 
/* 142 */     59, -128, 29, 52, 111, -14, 102, 96, 
/* 143 */     -73, 107, -103, 80, -91, -92, -97, -97, 
/* 144 */     -24, 4, 123, 16, 34, -62, 79, -69, 
/* 145 */     -87, -41, -2, -73, -58, 27, -8, 59, 
/* 146 */     87, -25, -58, -88, -90, 21, 15, 4, 
/* 147 */     -5, -125, -10, -45, -59, 30, -61, 2, 
/* 148 */     53, 84, 19, 90, 22, -111, 50, -10, 
/* 149 */     117, -13, -82, 43, 97, -41, 42, -17, 
/* 150 */     -14, 34, 3, 25, -99, -47, 72, 1, 
/* 151 */     -57, 2, 21, 0, -105, 96, 80, -113, 
/* 152 */     21, 35, 11, -52, -78, -110, -71, -126, 
/* 153 */     -94, -21, -124, 11, -16, 88, 28, -11, 
/* 154 */     2, -127, -127, 0, -9, -31, -96, -123, 
/* 155 */     -42, -101, 61, -34, -53, -68, -85, 92, 
/* 156 */     54, -72, 87, -71, 121, -108, -81, -69, 
/* 157 */     -6, 58, -22, -126, -7, 87, 76, 11, 
/* 158 */     61, 7, -126, 103, 81, 89, 87, -114, 
/* 159 */     -70, -44, 89, 79, -26, 113, 7, 16, 
/* 160 */     -127, -128, -76, 73, 22, 113, 35, -24, 
/* 161 */     76, 40, 22, 19, -73, -49, 9, 50, 
/* 162 */     -116, -56, -90, -31, 60, 22, 122, -117, 
/* 163 */     84, 124, -115, 40, -32, -93, -82, 30, 
/* 164 */     43, -77, -90, 117, -111, 110, -93, 127, 
/* 165 */     11, -6, 33, 53, 98, -15, -5, 98, 
/* 166 */     122, 1, 36, 59, -52, -92, -15, -66, 
/* 167 */     -88, 81, -112, -119, -88, -125, -33, -31, 
/* 168 */     90, -27, -97, 6, -110, -117, 102, 94, 
/* 169 */     -128, 123, 85, 37, 100, 1, 76, 59, 
/* 170 */     -2, -49, 73, 42, 3, -127, -124, 
/* 171 */     0, 2, -127, -128, 7, -52, -10, 56, 58, 
/* 172 */     -51, -45, 88, -103, -112, 15, 113, -81, 
/* 173 */     -86, -48, 3, 39, 59, 116, -31, 100, 
/* 174 */     56, 17, -65, -6, -73, -65, 44, -25, 
/* 175 */     -69, -89, -110, 47, 8, -50, 39, -8, 
/* 176 */     -76, -3, -40, 20, 29, -93, -107, -69, 
/* 177 */     3, 22, -90, -70, -68, 53, -64, -51, 
/* 178 */     -7, -11, 108, -89, -108, 91, 35, 1, 
/* 179 */     -7, -82, -11, -55, -32, -127, 122, -24, 
/* 180 */     -28, 105, -21, -8, -11, -128, 37, 4, 
/* 181 */     44, -111, 115, -106, 89, -76, 6, -125, 
/* 182 */     23, -78, 80, -84, 79, -21, -99, 81, 
/* 183 */     37, 61, -9, -18, -80, 36, 37, 14, 
/* 184 */     -2, -76, 50, -95, -60, 14, -77, 102, 
/* 185 */     65, -32, 87, -50, -99, -66, 51, 46, 
/* 186 */     -109, -102, -55, 122, 87, -36, -51, -120, 
/* 187 */     96, -89, -50, -93, -127, -120, 48, -127, 
/* 188 */     -123, 48, 17, 6, 9, 96, -122, 72, 
/* 189 */     1, -122, -8, 66, 1, 1, 4, 4, 
/* 190 */     3, 2, 4, 16, 48, 14, 6, 3, 
/* 191 */     85, 29, 15, 1, 1, -1, 4, 4, 
/* 192 */     3, 2, 5, -32, 48, 29, 6, 3, 
/* 193 */     85, 29, 14, 4, 22, 4, 20, 85, 
/* 194 */     -115, 31, 42, 5, -85, -101, -50, -122, 
/* 195 */     16, -82, 59, 93, -10, -70, 63, 34, 
/* 196 */     -59, 106, -54, 48, 31, 6, 3, 85, 
/* 197 */     29, 35, 4, 24, 48, 22, -128, 20, 
/* 198 */     101, -30, -12, -122, -55, -45, 78, -16, 
/* 199 */     -111, 78, 88, -94, 106, -11, -40, 120, 
/* 200 */     90, -102, -63, -90, 48, 32, 6, 3, 
/* 201 */     85, 29, 17, 4, 25, 48, 23, -127, 
/* 202 */     21, 121, 117, 45, 99, 104, 105, 110, 
/* 203 */     103, 46, 112, 101, 110, 103, 64, 115, 
/* 204 */     117, 110, 46, 99, 111, 109, 48, 11, 
/* 205 */     6, 7, 42, -122, 72, -50, 56, 4, 
/* 206 */     3, 5, 0, 3, 47, 0, 48, 44, 
/* 207 */     2, 20, 117, 75, -24, 33, 55, 120, 
/* 208 */     121, 10, -48, -75, -36, 126, 54, 117, 
/* 209 */     -71, -28, 20, -75, -48, 70, 2, 20, 
/* 210 */     106, 81, -36, -70, 109, 26, 107, 92, 
/* 211 */     24, 35, 106, -15, -54, 33, -118, 119, 
/* 212 */     -62, 5, 22, 66 };
/*     */ 
/*     */   public FrameServerJCEProvicer()
/*     */   {
/* 223 */     super("FrameServerJCEProvicer", 3.1D, "Provider which supports FrameServer.");
/*     */   }
/*     */ 
/*     */   public static final synchronized boolean selfIntegrityChecking()
/*     */   {
/* 238 */     if (verifiedSelfIntegrity) {
/* 239 */       return true;
/*     */     }
/*     */ 
/* 242 */     URL providerURL = (URL)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public Object run() {
/* 244 */         CodeSource cs = FrameServerJCEProvicer.class.getProtectionDomain().getCodeSource();
/* 245 */         return cs.getLocation();
/*     */       }
/*     */     });
/* 249 */     verifiedSelfIntegrity = integrityCheckingImpl(providerURL.toString());
/*     */ 
/* 251 */     return verifiedSelfIntegrity;
/*     */   }
/*     */ 
/*     */   public static final synchronized boolean integrityCheckingImpl(String fullFileName) {
/* 255 */     if (fullFileName == null)
/* 256 */       return false;
/*     */     void tmp17_14 = new FrameServerJCEProvicer(); tmp17_14.getClass(); FrameServerJarVerifier jv = new FrameServerJarVerifier(fullFileName);
/*     */     try
/*     */     {
/* 265 */       if (providerCert == null) {
/* 266 */         providerCert = setupProviderCert();
/*     */       }
/* 268 */       jv.verify(providerCert);
/*     */     } catch (SecurityException se) {
/* 270 */       return error(se.getMessage() + fullFileName);
/*     */     } catch (IOException e) {
/* 272 */       return error(e.getMessage() + fullFileName);
/*     */     } catch (CertificateException e) {
/* 274 */       return error(e.getMessage() + fullFileName);
/*     */     }
/*     */ 
/* 277 */     return true;
/*     */   }
/*     */ 
/*     */   private static X509Certificate setupProviderCert()
/*     */     throws IOException, CertificateException
/*     */   {
/* 285 */     String rootCertificate = "MIIDYDCCAx4CBD/f1M0wCwYHKoZIzjgEAwUAMIGVMQswCQYDVQQGEwJDTjEPMA0GA1UECAwG5YyX5LqsMRIwEAYDVQQHDAnljJfkuqzluIIxMzAxBgNVBAoMKuWMl+S6rOacieeUn+WNmuWkp+i9r+S7tuaKgOacr+aciemZkOWFrOWPuDEZMBcGA1UECxMQd3d3LnJpc2Vzb2Z0Lm5ldDERMA8GA1UEAxMIUmlzZVNvZnQwHhcNMDMxMjE3MDQwMDEzWhcNMjMxMjEyMDQwMDEzWjCBlTELMAkGA1UEBhMCQ04xDzANBgNVBAgMBuWMl+S6rDESMBAGA1UEBwwJ5YyX5Lqs5biCMTMwMQYDVQQKDCrljJfkuqzmnInnlJ/ljZrlpKfova/ku7bmioDmnK/mnInpmZDlhazlj7gxGTAXBgNVBAsTEHd3dy5yaXNlc29mdC5uZXQxETAPBgNVBAMTCFJpc2VTb2Z0MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAc6smiN4ClODM48pkKjYVHu8DP777Q5DzKStPXCyD2dGXtqLSF2qy8ne/NG8t9SozO3LKHec7tAd58wGI5zwweereVR0vRBlDDopp5Oeb0YEmdjTKkyGxbO9GKGqT0dxaKT0nwgScBksbl48MgzKro09brHjTieVWlJ4Pl3Fal5YwCwYHKoZIzjgEAwUAAy8AMCwCFDcUAW5Ypec7dqEDc04eT38Jba+mAhQCfYN92zqX04o5SwHxt/AY8g2t0Q==";
/*     */ 
/* 302 */     CertificateFactory cf = CertificateFactory.getInstance("X.509");
/*     */ 
/* 305 */     new ByteArrayInputStream(bytesOfProviderCert);
/*     */ 
/* 309 */     byte[] certBytes = Base64.decode(rootCertificate);
/* 310 */     InputStream in = new ByteArrayInputStream(certBytes);
/* 311 */     X509Certificate cert = (X509Certificate)cf.generateCertificate(in);
/* 312 */     in.close();
/*     */ 
/* 314 */     return cert;
/*     */   }
/*     */ 
/*     */   private static boolean error(String msg) {
/* 318 */     System.out.println("[FATAL] CHECK LICENSE ERROR:" + msg);
/* 319 */     return false;
/*     */   }
/*     */ 
/*     */   private static String getInstallLocation(BundleContext bundleContext)
/*     */   {
/* 324 */     return System.getProperty("user.dir") + "/bundles/";
/*     */   }
/*     */ 
/*     */   public static boolean isBundleJarSigned(Bundle bundle) {
/*     */     try {
/* 329 */       String location = bundle.getLocation();
/*     */ 
/* 361 */       String osgiDev = System.getProperty("osgi.dev");
/* 362 */       if (osgiDev != null) {
/* 363 */         return true;
/*     */       }
/*     */ 
/* 366 */       String prefix = "initial@reference:file:";
/* 367 */       location = location.substring(prefix.length(), location.length() - 1);
/* 368 */       location = getInstallLocation(bundle.getBundleContext()) + location;
/*     */ 
/* 370 */       return integrityCheckingImpl(location);
/*     */     }
/*     */     catch (Exception e) {
/* 373 */       System.out.println("[FATAL] CHECK LICENSE ERROR:" + e.getMessage());
/* 374 */       System.exit(99);
/* 375 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws MalformedURLException
/*     */   {
/* 380 */     integrityCheckingImpl("d:/tmp/test.jar");
/*     */   }
/*     */ 
/*     */   final class FrameServerJarVerifier
/*     */   {
/* 385 */     private String fullFileName = null;
/* 386 */     private JarFile jarFile = null;
/*     */ 
/*     */     public FrameServerJarVerifier(String fullFileName) {
/* 389 */       this.fullFileName = fullFileName;
/*     */     }
/*     */ 
/*     */     private JarFile retrieveJarFileFromURL(String fullFileName)
/*     */       throws IOException
/*     */     {
/* 396 */       return new JarFile(fullFileName);
/*     */     }
/*     */ 
/*     */     public void verify(X509Certificate targetCert)
/*     */       throws IOException
/*     */     {
/* 406 */       if (targetCert == null) {
/* 407 */         throw new SecurityException("Provider certificate is invalid");
/*     */       }
/*     */       try
/*     */       {
/* 411 */         if (this.jarFile == null)
/* 412 */           this.jarFile = retrieveJarFileFromURL(this.fullFileName);
/*     */       }
/*     */       catch (Exception ex) {
/* 415 */         SecurityException se = new SecurityException();
/* 416 */         se.initCause(ex);
/* 417 */         throw se;
/*     */       }
/*     */ 
/* 420 */       Vector entriesVec = new Vector();
/*     */ 
/* 423 */       Manifest man = this.jarFile.getManifest();
/* 424 */       if (man == null) {
/* 425 */         throw new SecurityException("The provider is not signed");
/*     */       }
/*     */ 
/* 429 */       byte[] buffer = new byte[8192];
/* 430 */       Enumeration entries = this.jarFile.entries();
/*     */ 
/* 432 */       while (entries.hasMoreElements()) {
/* 433 */         JarEntry je = (JarEntry)entries.nextElement();
/*     */ 
/* 436 */         if (!je.isDirectory())
/*     */         {
/* 439 */           entriesVec.addElement(je);
/* 440 */           InputStream is = this.jarFile.getInputStream(je);
/*     */ 
/* 444 */           while (is.read(buffer, 0, buffer.length) != -1);
/* 447 */           is.close();
/*     */         }
/*     */       }
/*     */ 
/* 451 */       Enumeration e = entriesVec.elements();
/*     */ 
/* 453 */       while (e.hasMoreElements()) {
/* 454 */         JarEntry je = (JarEntry)e.nextElement();
/*     */ 
/* 457 */         Certificate[] certs = je.getCertificates();
/* 458 */         if ((certs == null) || (certs.length == 0)) {
/* 459 */           if (!je.getName().startsWith("META-INF")) {
/* 460 */             throw new SecurityException("The jar has unsigned " + je.getName() + " class files.");
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 465 */           int startIndex = 0;
/*     */ 
/* 467 */           boolean signedAsExpected = false;
/*     */           X509Certificate[] certChain;
/* 469 */           while ((certChain = getAChain(certs, startIndex)) != null)
/*     */           {
/*     */             X509Certificate[] certChain;
/* 470 */             if (certChain[0].equals(targetCert))
/*     */             {
/* 472 */               signedAsExpected = true;
/* 473 */               break;
/*     */             }
/*     */ 
/* 476 */             startIndex += certChain.length;
/*     */           }
/*     */ 
/* 479 */           if (!signedAsExpected)
/* 480 */             throw new SecurityException("The jar is not signed by a trusted signer");
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private X509Certificate[] getAChain(Certificate[] certs, int startIndex)
/*     */     {
/* 492 */       if (startIndex > certs.length - 1) {
/* 493 */         return null;
/*     */       }
/*     */ 
/* 498 */       for (int i = startIndex; i < certs.length - 1; i++) {
/* 499 */         if (!((X509Certificate)certs[(i + 1)]).getSubjectDN().equals(((X509Certificate)certs[i]).getIssuerDN()))
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/* 504 */       int certChainSize = i - startIndex + 1;
/* 505 */       X509Certificate[] ret = new X509Certificate[certChainSize];
/* 506 */       for (int j = 0; j < certChainSize; j++) {
/* 507 */         ret[j] = ((X509Certificate)certs[(startIndex + j)]);
/*     */       }
/* 509 */       return ret;
/*     */     }
/*     */ 
/*     */     protected void finalize() throws Throwable
/*     */     {
/* 514 */       this.jarFile.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   final class FrameServerJarVerifier1 {
/* 519 */     private URL jarURL = null;
/* 520 */     private JarFile jarFile = null;
/*     */ 
/*     */     public FrameServerJarVerifier1(URL jarURL) {
/* 523 */       this.jarURL = jarURL;
/*     */     }
/*     */ 
/*     */     private JarFile retrieveJarFileFromURL(URL url)
/*     */       throws PrivilegedActionException, MalformedURLException
/*     */     {
/* 530 */       JarFile jf = null;
/*     */ 
/* 533 */       this.jarURL = (url.getProtocol().equalsIgnoreCase("jar") ? url : new URL("jar:" + url.toString() + "!/"));
/*     */ 
/* 535 */       jf = (JarFile)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Object run() throws Exception {
/* 537 */           JarURLConnection conn = (JarURLConnection)FrameServerJCEProvicer.FrameServerJarVerifier1.this.jarURL.openConnection();
/*     */ 
/* 541 */           conn.setUseCaches(false);
/* 542 */           return conn.getJarFile();
/*     */         }
/*     */       });
/* 545 */       return jf;
/*     */     }
/*     */ 
/*     */     public void verify(X509Certificate targetCert)
/*     */       throws IOException
/*     */     {
/* 555 */       if (targetCert == null) {
/* 556 */         throw new SecurityException("Provider certificate is invalid");
/*     */       }
/*     */       try
/*     */       {
/* 560 */         if (this.jarFile == null)
/* 561 */           this.jarFile = retrieveJarFileFromURL(this.jarURL);
/*     */       }
/*     */       catch (Exception ex) {
/* 564 */         SecurityException se = new SecurityException();
/* 565 */         se.initCause(ex);
/* 566 */         throw se;
/*     */       }
/*     */ 
/* 569 */       Vector entriesVec = new Vector();
/*     */ 
/* 572 */       Manifest man = this.jarFile.getManifest();
/* 573 */       if (man == null) {
/* 574 */         throw new SecurityException("The provider is not signed");
/*     */       }
/*     */ 
/* 578 */       byte[] buffer = new byte[8192];
/* 579 */       Enumeration entries = this.jarFile.entries();
/*     */ 
/* 581 */       while (entries.hasMoreElements()) {
/* 582 */         JarEntry je = (JarEntry)entries.nextElement();
/*     */ 
/* 585 */         if (!je.isDirectory())
/*     */         {
/* 588 */           entriesVec.addElement(je);
/* 589 */           InputStream is = this.jarFile.getInputStream(je);
/*     */ 
/* 593 */           while (is.read(buffer, 0, buffer.length) != -1);
/* 596 */           is.close();
/*     */         }
/*     */       }
/*     */ 
/* 600 */       Enumeration e = entriesVec.elements();
/*     */ 
/* 602 */       while (e.hasMoreElements()) {
/* 603 */         JarEntry je = (JarEntry)e.nextElement();
/*     */ 
/* 606 */         Certificate[] certs = je.getCertificates();
/* 607 */         if ((certs == null) || (certs.length == 0)) {
/* 608 */           if (!je.getName().startsWith("META-INF")) {
/* 609 */             throw new SecurityException("The jar has unsigned " + je.getName() + " class files.");
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 614 */           int startIndex = 0;
/*     */ 
/* 616 */           boolean signedAsExpected = false;
/*     */           X509Certificate[] certChain;
/* 618 */           while ((certChain = getAChain(certs, startIndex)) != null)
/*     */           {
/*     */             X509Certificate[] certChain;
/* 619 */             if (certChain[0].equals(targetCert))
/*     */             {
/* 621 */               signedAsExpected = true;
/* 622 */               break;
/*     */             }
/*     */ 
/* 625 */             startIndex += certChain.length;
/*     */           }
/*     */ 
/* 628 */           if (!signedAsExpected)
/* 629 */             throw new SecurityException("The jar is not signed by a trusted signer");
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private X509Certificate[] getAChain(Certificate[] certs, int startIndex)
/*     */     {
/* 641 */       if (startIndex > certs.length - 1) {
/* 642 */         return null;
/*     */       }
/*     */ 
/* 647 */       for (int i = startIndex; i < certs.length - 1; i++) {
/* 648 */         if (!((X509Certificate)certs[(i + 1)]).getSubjectDN().equals(((X509Certificate)certs[i]).getIssuerDN()))
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/* 653 */       int certChainSize = i - startIndex + 1;
/* 654 */       X509Certificate[] ret = new X509Certificate[certChainSize];
/* 655 */       for (int j = 0; j < certChainSize; j++) {
/* 656 */         ret[j] = ((X509Certificate)certs[(startIndex + j)]);
/*     */       }
/* 658 */       return ret;
/*     */     }
/*     */ 
/*     */     protected void finalize() throws Throwable
/*     */     {
/* 663 */       this.jarFile.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.FrameServerJCEProvicer
 * JD-Core Version:    0.6.1
 */