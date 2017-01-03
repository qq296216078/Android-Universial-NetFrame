package com.chenjian.net.request;

import com.chenjian.net.exp.RequestErrorException;
import com.chenjian.net.exp.RespondErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http请求工具类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 13:53
 */

public class HttpUtil {

    private static final int CONNECT_TIMEOUT = 1000 * 10;
    private static final int READ_TIMEOUT = 1000 * 10;
    private static final int BUF_SIZE = 1024 * 10;

    public static String post(String requestUrl, String param) throws Exception {
        HttpURLConnection conn = null;
        OutputStream out = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            out = conn.getOutputStream();
            if (param != null && param.length() > 0) {
                out.write(param.getBytes());
            }
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new RespondErrorException();
            }
            is = conn.getInputStream();
            byte[] tmp = new byte[BUF_SIZE];
            baos = new ByteArrayOutputStream();
            int len = is.read(tmp);
            while (len != -1) {
                baos.write(tmp, 0, len);
                len = is.read(tmp);
            }
            return baos.toString("utf-8");
        } catch (Exception e) {
            throw new RequestErrorException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String get(String requestUrl) throws Exception {
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setDoInput(true);
            conn.connect();
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                throw new Exception("get responseCode: " + code);
            }
            is = conn.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUF_SIZE];
            int size;
            while ((size = is.read(buffer)) != -1) {
                baos.write(buffer, 0, size);
            }
            return baos.toString("utf-8");
        } catch (Exception e) {
            throw new RequestErrorException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
