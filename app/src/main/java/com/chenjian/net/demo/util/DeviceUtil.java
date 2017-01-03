package com.chenjian.net.demo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者： ChenJian
 * 时间： 2017.1.3 15:55
 */

public class DeviceUtil {

    public static boolean isNetConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo != null && networkinfo.isAvailable()) {
            return true;
        }

        return false;
    }
}
