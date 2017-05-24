package com.chenjian.net.helper;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.core.async.NetExcutor;
import com.chenjian.net.core.async.NetListener;
import com.chenjian.net.core.sync.SyncNetExcutor;
import com.chenjian.net.core.sync.SyncNetListener;
import com.chenjian.net.data.NetConstants;

/**
 * 网络请求工具类，这个类已经过时了
 *
 * 在版本1.0.1，版本号为 10001 中，已经更新了 NetHelper2 类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 11:24
 */

@Deprecated
public class NetHelper {

    /**
     * get异步请求
     *
     * @param url      url
     * @param listener 监听器
     */
    public static void get(String url, NetListener listener) {
        get(url, NetConstants.defaultWaitForToken, listener);
    }

    /**
     * get异步请求
     *
     * @param url            url
     * @param isWaitForToken 是否等待token请求成功
     * @param listener       监听器
     */
    public static void get(String url, boolean isWaitForToken, NetListener listener) {
        NetExcutor netExcutor = new NetExcutor();
        netExcutor.setUrl(url);
        netExcutor.setIsWaitForToken(isWaitForToken);
        netExcutor.setNetListener(listener);
        netExcutor.get();
    }

    /**
     * post异步请求
     *
     * @param url      url
     * @param params   参数
     * @param listener 监听器
     */
    public static void post(String url, String params, NetListener listener) {
        post(url, params, NetConstants.defaultWaitForToken, listener);
    }

    /**
     * post异步请求
     *
     * @param url            url
     * @param params         参数
     * @param isWaitForToken 是否等待token请求成功
     * @param listener       监听器
     */
    public static void post(String url, String params, boolean isWaitForToken, NetListener listener) {
        NetExcutor netExcutor = new NetExcutor();
        netExcutor.setUrl(url);
        netExcutor.setParams(params);
        netExcutor.setIsWaitForToken(isWaitForToken);
        netExcutor.setNetListener(listener);
        netExcutor.post();
    }

    /**
     * get同步请求
     *
     * @param url             url
     * @param syncNetListener 监听器
     * @return NetRetBean
     */
    public static NetRetBean getSync(String url, SyncNetListener syncNetListener) {
        return getSync(url, NetConstants.defaultWaitForToken, syncNetListener);
    }

    /**
     * get同步请求
     *
     * @param url             url
     * @param isWaitForToken  是否等待token请求成功
     * @param syncNetListener 监听器
     * @return NetRetBean
     */
    public static NetRetBean getSync(String url, boolean isWaitForToken, SyncNetListener syncNetListener) {
        SyncNetExcutor syncNetExcutor = new SyncNetExcutor();
        syncNetExcutor.setUrl(url);
        syncNetExcutor.setIsWaitForToken(isWaitForToken);
        syncNetExcutor.setSyncNetListener(syncNetListener);
        return syncNetExcutor.get();
    }

    /**
     * post同步请求
     *
     * @param url             url
     * @param params          参数
     * @param syncNetListener 监听器
     * @return NetRetBean
     */
    public static NetRetBean postSync(String url, String params, SyncNetListener syncNetListener) {
        return postSync(url, params, NetConstants.defaultWaitForToken, syncNetListener);
    }

    /**
     * post同步请求
     *
     * @param url             url
     * @param params          参数
     * @param isWaitForToken  是否等待token请求成功
     * @param syncNetListener 监听器
     * @return NetRetBean
     */
    public static NetRetBean postSync(String url, String params, boolean isWaitForToken, SyncNetListener syncNetListener) {
        SyncNetExcutor syncNetExcutor = new SyncNetExcutor();
        syncNetExcutor.setUrl(url);
        syncNetExcutor.setParams(params);
        syncNetExcutor.setIsWaitForToken(isWaitForToken);
        syncNetExcutor.setSyncNetListener(syncNetListener);
        return syncNetExcutor.post();
    }

    /**
     * get同步请求，不设置监听器，直接返回数据给调用者
     *
     * @param url url
     * @return String
     */
    public static String getStringSync(String url) {
        return getStringSync(url, NetConstants.defaultWaitForToken);
    }

    /**
     * get同步请求，不设置监听器，直接返回数据给调用者
     *
     * @param url            url
     * @param isWaitForToken 是否等待token请求成功
     * @return String
     */
    public static String getStringSync(String url, boolean isWaitForToken) {
        SyncNetExcutor syncNetExcutor = new SyncNetExcutor();
        syncNetExcutor.setUrl(url);
        syncNetExcutor.setIsWaitForToken(isWaitForToken);
        return syncNetExcutor.getString();
    }

    /**
     * post同步请求，不设置监听器，直接返回数据给调用者
     *
     * @param url    url
     * @param params 参数
     * @return String
     */
    public static String postStringSync(String url, String params) {
        return postStringSync(url, params, NetConstants.defaultWaitForToken);
    }

    /**
     * post同步请求，不设置监听器，直接返回数据给调用者
     *
     * @param url            url
     * @param params         参数
     * @param isWaitForToken 是否等待token请求成功
     * @return String
     */
    public static String postStringSync(String url, String params, boolean isWaitForToken) {
        SyncNetExcutor syncNetExcutor = new SyncNetExcutor();
        syncNetExcutor.setUrl(url);
        syncNetExcutor.setParams(params);
        syncNetExcutor.setIsWaitForToken(isWaitForToken);
        return syncNetExcutor.postString();
    }
}
