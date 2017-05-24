package com.chenjian.net.request;

import com.chenjian.net.demo.url.UrlConst;

/**
 * 对Http请求进行包装，涉及到加解密以及其它内容等，都放到这个类里面处理，而不与HttpUtil耦合
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 14:32
 */

public class RequestUtil {

    private static String TAG = RequestUtil.class.getName();

//    /**
//     * get请求
//     *
//     * @param url url
//     * @return 返回请求的结果
//     * @throws Exception e
//     */
//    public static String getRequest(HttpEngine httpEngine, String url) throws Exception {
//        String ret;
//
//        LogUtil.d(TAG, "request: " + url);
//
//        String tmp = httpEngine.get(url);
//        LogUtil.d(TAG, "response: " + " ret: " + tmp);
//
//        ret = AesUtil.decryptToString(tmp);
//        LogUtil.d(TAG, "response: " + " ret: " + ret);
//
//        return ret;
//    }
//
//    /**
//     * post请求
//     *
//     * @param url   url
//     * @param param post请求的参数
//     * @return 返回请求的结果
//     * @throws Exception e
//     */
//    public static String postRequest(HttpEngine httpEngine, String url, String param) throws Exception {
//        String ret;
//
//        LogUtil.d(TAG, "request: " + url + " param: " + param);
//        String data = AesUtil.encryptToString(param);
//
//        String tmp = httpEngine.post(url, data);
//        LogUtil.d(TAG, "response: " + " ret: " + tmp);
//
//        ret = AesUtil.decryptToString(tmp);
//        LogUtil.d(TAG, "response: " + " ret: " + ret);
//
//        return ret;
//    }

    /**
     * 下面两个方法用于模拟网络请求返回的数据。如果你无法搭建服务端的话，那么请用下面的两个方法
     */

    /**
     * get请求。暂时没用
     *
     * @param url url
     * @return 返回请求的结果
     * @throws Exception e
     */
    public static String getRequest(HttpEngine httpEngine, String url) throws Exception {
        String ret = "";
        if (url.contains(UrlConst.BASE_URL + UrlConst.LOGIN)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":\"login success\"\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.USER_LIST)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":[\n" +
                    "        {\n" +
                    "            \"id\":\"10086\",\n" +
                    "            \"name\":\"yidong\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\":\"10011\",\n" +
                    "            \"name\":\"liantong\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\":\"10000\",\n" +
                    "            \"name\":\"dianxin\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.USER)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":{\n" +
                    "        \"id\":\"10086\",\n" +
                    "        \"name\":\"yidong\"\n" +
                    "    }\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.INFO_LIST)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":{\n" +
                    "        \"list\":[\n" +
                    "            {\n" +
                    "                \"title\":\"title1\",\n" +
                    "                \"desc\":\"desc1\",\n" +
                    "                \"date\":\"date1\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"title\":\"title2\",\n" +
                    "                \"desc\":\"desc2\",\n" +
                    "                \"date\":\"date2\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"title\":\"title3\",\n" +
                    "                \"desc\":\"desc3\",\n" +
                    "                \"date\":\"date3\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"page\":\"1\",\n" +
                    "        \"page_size\":\"3\",\n" +
                    "        \"total\":\"3\"\n" +
                    "    }\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.INFO)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":{\n" +
                    "        \"title\":\"title\",\n" +
                    "        \"desc\":\"desc\",\n" +
                    "        \"date\":\"date\"\n" +
                    "    }\n" +
                    "}";
        }
        return ret;
    }

    /**
     * post请求
     *
     * @param url   url
     * @param param post请求的参数
     * @return 返回请求的结果
     * @throws Exception e
     */
    public static String postRequest(HttpEngine httpEngine, String url, String param) throws Exception {
        String ret = "";
        if (url.contains(UrlConst.BASE_URL + UrlConst.LOGIN)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":\"login success\"\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.USER_LIST)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":[\n" +
                    "        {\n" +
                    "            \"id\":\"10086\",\n" +
                    "            \"name\":\"yidong\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\":\"10011\",\n" +
                    "            \"name\":\"liantong\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\":\"10000\",\n" +
                    "            \"name\":\"dianxin\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.USER)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":{\n" +
                    "        \"id\":\"10086\",\n" +
                    "        \"name\":\"yidong\"\n" +
                    "    }\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.INFO_LIST)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":{\n" +
                    "        \"list\":[\n" +
                    "            {\n" +
                    "                \"title\":\"title1\",\n" +
                    "                \"desc\":\"desc1\",\n" +
                    "                \"date\":\"date1\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"title\":\"title2\",\n" +
                    "                \"desc\":\"desc2\",\n" +
                    "                \"date\":\"date2\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"title\":\"title3\",\n" +
                    "                \"desc\":\"desc3\",\n" +
                    "                \"date\":\"date3\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"page\":\"1\",\n" +
                    "        \"page_size\":\"3\",\n" +
                    "        \"total\":\"3\"\n" +
                    "    }\n" +
                    "}";
        } else if (url.contains(UrlConst.BASE_URL + UrlConst.INFO)) {
            ret = "{\n" +
                    "    \"code\":\"00001\",\n" +
                    "    \"message\":\"message\",\n" +
                    "    \"time\":\"time\",\n" +
                    "    \"data\":{\n" +
                    "        \"title\":\"title\",\n" +
                    "        \"desc\":\"desc\",\n" +
                    "        \"date\":\"date\"\n" +
                    "    }\n" +
                    "}";
        }
        return ret;
    }
}
