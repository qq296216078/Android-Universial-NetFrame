package com.chenjian.net.demo.util;

import android.util.Log;

/**
 * 作者： ChenJian
 * 时间： 2017.1.3 16:52
 */

public class LogUtil {

    public static void d(String msg) {
        d("ChenJianNet", msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
        System.out.println(tag + " " + msg); // 华为手机Log打不出来，只能用System.out
    }
}
