package com.chenjian.net.url;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Url工具类，提供Url操作的基本方法单元
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 11:24
 */

public class UrlParse {

    private Map<String, String> mMap = new LinkedHashMap<String, String>();
    private StringBuilder mHeaderBuilder;
    private boolean mUseCommonParam = true;

    public UrlParse() {

    }

    public boolean isUseCommonParam() {
        return mUseCommonParam;
    }

    public UrlParse setUseCommonParam(boolean useCommonParam) {
        this.mUseCommonParam = useCommonParam;
        return this;
    }

    public UrlParse(String url) {
        initUrl(url);
    }

    private void initUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            mHeaderBuilder = new StringBuilder("");
            return;
        }

        mMap.clear();
        int pos = url.indexOf("?");
        if (pos == -1) {
            mHeaderBuilder = new StringBuilder(url);
            return;
        }

        mHeaderBuilder = new StringBuilder(url.substring(0, pos));
        String temp = url.substring(pos + 1);
        StringTokenizer token = new StringTokenizer(temp, "&", false);
        while (token.hasMoreElements()) {
            String[] str = token.nextToken().split("=");
            if (str.length == 2) {
                putValue(str[0], str[1]);
            }
        }
    }

    public boolean containsKey(String key) {
        return mMap.containsKey(key.toLowerCase());
    }

    private String getValue(String key) {
        return mMap.get(key.toLowerCase());
    }

    private String decodeUtf8(String str) {
        try {
            if (str == null || "".equals(str)) {
                return str;
            }
            return URLDecoder.decode(str, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getUtf8Value(String key) {
        String temp = mMap.get(key.toLowerCase());
        return decodeUtf8(temp);
    }

    public int getInteger(String key, int def) {
        String value = getValue(key);
        if (TextUtils.isEmpty(value)) {
            return def;
        }
        try {
            return Integer.valueOf(value);
        } catch (Exception ex) {
            return def;
        }
    }

    public UrlParse putValue(String key, String value) {
        if (key == null || value == null) {
            return this;
        }
        mMap.put(key.toLowerCase(), value);
        return this;
    }

    private UrlParse putValue(String key, int value) {
        return putValue(key, String.valueOf(value));
    }

    public void removeValue(String key) {
        mMap.remove(key.toLowerCase());
    }

    public void optRemoveValue(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (mMap.containsKey(key)) {
            mMap.remove(key.toLowerCase());
        }
    }

    @Override
    public String toString() {
        if (mUseCommonParam) {
            addCommonParam();
        }

        return toStringWithParam();
    }

    public String toStringOnlyHeader() {
        return mHeaderBuilder.toString();
    }

    public String toStringOnlyParam() {
        if (mUseCommonParam) {
            addCommonParam();
        }

        return getUrlParam();
    }

    private void addCommonParam() {
        Map<String, String> paramMap = UrlManager.getInstance().getCommonParam();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                putValue(entry.getKey(), entry.getValue());
            }
        }
    }

    private String toStringWithParam() {
        String param = getUrlParam();
        if (param == null || param.equals("")) {
            return mHeaderBuilder.toString();
        } else {
            return mHeaderBuilder + "?" + getUrlParam();
        }
    }

    private String getUrlParam() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = mMap.keySet().iterator();
        for (; iterator.hasNext(); ) {
            String key = iterator.next();
            sb.append(key);
            sb.append("=");
            sb.append(mMap.get(key));
            sb.append("&");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * @param region host + / + region
     * @return 返回this
     */
    public UrlParse appendRegion(String region) {
        String str = mHeaderBuilder.toString();
        if (str.endsWith("/")) {
            mHeaderBuilder.append(region);
        } else {
            mHeaderBuilder.append("/").append(region);
        }
        return this;
    }

    public static String encode(String url) {
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]");
        Matcher m = pattern.matcher(url);
        while (m.find()) {
            String cn = m.group();
            try {
                url = url.replace(cn, URLEncoder.encode(cn, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
