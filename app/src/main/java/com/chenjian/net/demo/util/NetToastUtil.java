package com.chenjian.net.demo.util;

import android.content.Context;

import com.chenjian.net.R;
import com.chenjian.net.bean.NetRetBean;

/**
 * Created by ChenJian
 * 2016.7.1 16:54:54.
 */
public class NetToastUtil {

    /**
     * 解析错误码
     */

    public static void requestError(Context context, NetRetBean netRetBean) {
        switch (netRetBean.getCallbackCode()) {
            case CODE_ERROR_SERVER_DATA_ERROR:
            case CODE_ERROR_JSON_EXP:
                serverError(context);
                break;

            case CODE_ERROR_HTTP_NOT_200:
            case CODE_ERROR_REQUEST_EXP:
            case CODE_ERROR_UNKNOWN:
            default:
                requestError(context);
                break;
        }
    }

    public static void requestError(Context context, NetRetBean netRetBean, int strId) {
        requestError(context, netRetBean, context.getString(strId));
    }

    private static void requestError(Context context, NetRetBean netRetBean, String str) {
        switch (netRetBean.getCallbackCode()) {
            case CODE_ERROR_SERVER_DATA_ERROR:
            case CODE_ERROR_JSON_EXP:
                serverError(context, str);
                break;

            case CODE_ERROR_HTTP_NOT_200:
            case CODE_ERROR_REQUEST_EXP:
            case CODE_ERROR_UNKNOWN:
            default:
                requestError(context, str);
                break;
        }
    }

    /**
     * 服务器返回数据异常
     */

    private static void serverError(Context context) {
        ToastUtil.longPrompt(context, R.string.request_failed_server);
    }

    public static void serverError(Context context, int strId) {
        serverError(context, context.getString(strId));
    }

    private static void serverError(Context context, String str) {
        ToastUtil.longPrompt(context, str + " : " + context.getString(R.string.request_failed_server));
    }

    /**
     * 请求出错
     */

    private static void requestError(Context context) {
        if (DeviceUtil.isNetConnect(context)) {
            netError(context);
        } else {
            noOpenNet(context);
        }
    }

    public static void requestError(Context context, int strId) {
        requestError(context, context.getString(strId));
    }

    private static void requestError(Context context, String str) {
        if (DeviceUtil.isNetConnect(context)) {
            netError(context, str);
        } else {
            noOpenNet(context, str);
        }
    }

    /**
     * 网络请求发生异常
     */

    private static void netError(Context context) {
        ToastUtil.longPrompt(context, R.string.request_failed);
    }

    public static void netError(Context context, int strId) {
        netError(context, context.getString(strId));
    }

    private static void netError(Context context, String str) {
        ToastUtil.longPrompt(context, str + " : " + context.getString(R.string.request_failed));
    }


    /**
     * 网络未打开
     */

    private static void noOpenNet(Context context) {
        ToastUtil.longPrompt(context, R.string.no_open_network);
    }

    public static void noOpenNet(Context context, int strId) {
        noOpenNet(context, context.getString(strId));
    }

    private static void noOpenNet(Context context, String str) {
        ToastUtil.longPrompt(context, str + " : " + context.getString(R.string.no_open_network));
    }
}
