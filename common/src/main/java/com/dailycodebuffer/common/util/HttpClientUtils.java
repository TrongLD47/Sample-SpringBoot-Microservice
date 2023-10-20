package com.dailycodebuffer.common.util;

import com.dailycodebuffer.common.dto.ResponseDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpClientUtils {

    private HttpClientUtils() {
    }

    /**
     * Call with string body
     *
     * @param uri
     * @param headers
     * @param body
     * @return
     */
    @Deprecated
    public static String callPostRequest(String uri, Map<String, String> headers, String body) {

        StringBuilder sBuilder = new StringBuilder();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uri);
        try {
            for (String key : headers.keySet()) {
                post.setHeader(key, headers.get(key));
            }
            post.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String line;
                while ((line = rd.readLine()) != null) {
//                    System.out.println(line);
                    sBuilder.append(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }

    /**
     * x-www-form-urlencoded
     *
     * @param uri
     * @param headers
     * @param nameValuePairs
     * @return
     */
    @Deprecated
    public static String callPostRequest(String uri, Map<String, String> headers, List<NameValuePair> nameValuePairs) {

        StringBuilder sBuilder = new StringBuilder();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uri);
        try {
            for (String key : headers.keySet()) {
                post.setHeader(key, headers.get(key));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String line;
                while ((line = rd.readLine()) != null) {
//                    System.out.println(line);
                    sBuilder.append(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }

    /**
     * Call with string body
     * update : return code status and message
     *
     * @param uri
     * @param headers
     * @param body
     * @return
     */
    public static ResponseDTO<StringBuilder> newCallPostRequest(String uri, Map<String, String> headers, String body) {

        StringBuilder sBuilder = new StringBuilder();
        ResponseDTO<StringBuilder> rp = new ResponseDTO<>();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uri);
        try {
            for (String key : headers.keySet()) {
                post.setHeader(key, headers.get(key));
            }
            post.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            int statusCode = response.getStatusLine().getStatusCode();
            rp.setStatus(statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                String line;
                while ((line = rd.readLine()) != null) {
                    sBuilder.append(line);
                }
                rp.setMessage("");
                rp.setBody(sBuilder);
            } else {
                String line;
                while ((line = rd.readLine()) != null) {
                    sBuilder.append(line);
                }
                rp.setMessage(sBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rp;
    }

    /**
     * Call with param
     *
     * @param uri
     * @param headers
     * @param nameValuePairs
     * @return
     */
    public static ResponseDTO<StringBuilder> newCallPostRequest(String uri, Map<String, String> headers, List<NameValuePair> nameValuePairs) {

        StringBuilder sBuilder = new StringBuilder();
        ResponseDTO<StringBuilder> rp = new ResponseDTO<>();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uri);
        try {
            for (String key : headers.keySet()) {
                post.setHeader(key, headers.get(key));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            int statusCode = response.getStatusLine().getStatusCode();
            rp.setStatus(statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                String line;
                while ((line = rd.readLine()) != null) {
                    sBuilder.append(line);
                }
                rp.setMessage("");
                rp.setBody(sBuilder);
                Map<String, String> mapHeader = new HashMap<>();
                for (int i = 0; i < response.getAllHeaders().length; i++) {
                    mapHeader.put(response.getAllHeaders()[i].getName(), response.getAllHeaders()[i].getValue());
                }
            } else {
                String line;
                while ((line = rd.readLine()) != null) {
                    sBuilder.append(line);
                }
                rp.setMessage(sBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rp;
    }

    public static String callNewServer(String domain, String uri, Map<String, String> data, String Token) {
        String url = domain+ uri;
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("Authorization", "Bearer " + Token);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (String key : data.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, data.get(key)));
        }
        return callPostRequest(url, header, nameValuePairs);
    }


//    String response = HttpClientUtils.callNewServer("/order/mobile/shoppingCart", objectMapper.writeValueAsString(requestInformationDTO));


    public static String callNewServer(String domain, String uri, String body) {
//        System.out.println(uri);
        System.out.println(uri + " - " + body);
        String url = domain + uri;
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return callPostRequest(url, header, body);
    }


    public static String callNewServer(String domain, String uri, String body, String Token) {
//        System.out.println(uri);
        System.out.println(uri + " - " + body);
        String url = domain + uri;
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "Bearer " + Token);
        return callPostRequest(url, header, body);
    }

    public static String sendPost(String url, Map<String, String> headers, String body) throws KeyManagementException, NoSuchAlgorithmException {
        //Creating SSLContextBuilder object
        SSLContext sslContext = SSLContexts.custom().useProtocol("TLSv1.2").build();

        //Creating SSLConnectionSocketFactory SSLConnectionSocketFactory object
        SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        //Creating HttpClientBuilder
        HttpClientBuilder clientbuilder = HttpClients.custom();

        //Setting the SSLConnectionSocketFactory
        clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);

        //Building the CloseableHttpClient
        CloseableHttpClient httpClient = clientbuilder.build();

        //Creating the HttpPost request
        HttpPost post = new HttpPost(url);

        StringBuilder sBuilder = new StringBuilder();
        try {
            for (String key : headers.keySet()) {
                post.setHeader(key, headers.get(key));
            }
            post.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {
                sBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

    public static  String sendGet(String url, Map<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException {
        //Creating SSLContextBuilder object
        SSLContext sslContext = SSLContexts.custom().useProtocol("TLSv1.2").build();

        //Creating SSLConnectionSocketFactory SSLConnectionSocketFactory object
        SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        //Creating HttpClientBuilder
        HttpClientBuilder clientbuilder = HttpClients.custom();

        //Setting the SSLConnectionSocketFactory
        clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);

        //Building the CloseableHttpClient
        CloseableHttpClient httpClient = clientbuilder.build();

        //Creating the HttpGet request
        HttpGet httpget = new HttpGet(url);

        StringBuilder sBuilder = new StringBuilder();
        try {
            for (String key : headers.keySet()) {
                httpget.setHeader(key, headers.get(key));
            }
            CloseableHttpResponse response = httpClient.execute(httpget);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {
                sBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sBuilder.toString();
    }

}
