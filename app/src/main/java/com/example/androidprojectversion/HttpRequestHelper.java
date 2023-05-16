package com.example.androidprojectversion;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestHelper {


    public static String downloadURL(String address) throws IOException {

        InputStream inputStream = null;
        try{
            URL url = new URL(address);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.connect();

            int responseCode = httpsURLConnection.getResponseCode();
            if(responseCode != 200){
                throw new IOException("Response from api " + responseCode);
            }
            inputStream = httpsURLConnection.getInputStream();
            return readStream(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
        return null;
    }

    private static String readStream(InputStream stream) throws IOException {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = null;
        try {
            int length = 0;
            bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            while ((length = stream.read(buffer)) > 0){
                bufferedOutputStream.write(buffer, 0, length);
            }
            bufferedOutputStream.flush();
            return byteArrayOutputStream.toString();
        } finally {
            if(bufferedOutputStream != null){
                bufferedOutputStream.close();
            }
        }
    }
}
