package org.example.services;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLReader {

    public static void trust(String trustPath, String pwd) {
        try {
            File trustStoreFile = new File(trustPath);
            char[] trustStorePassword = pwd.toCharArray();

            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            tmf.init(trustStore);

            for(TrustManager t: tmf.getTrustManagers()){
                System.out.println(t);
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);

        } catch (IOException | NoSuchAlgorithmException | CertificateException |
                 KeyManagementException | KeyStoreException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readURL(String urlstr) throws Exception {
        String site = urlstr;
        URL siteURL = new URL(site);
        URLConnection urlConnection = siteURL.openConnection();
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            if (headerName != null) {
                System.out.print(headerName + ":");
            }
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value);
            }
            System.out.println("");
        }



        System.out.println("-------message-body------");
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        try (reader) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}