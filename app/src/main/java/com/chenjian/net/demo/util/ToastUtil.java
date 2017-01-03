package com.chenjian.net.demo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ChenJian
 * 2016.7.1 16:54:54.
 */
public class ToastUtil {
    public static void prompt(Context context, String prompt) {
        Toast.makeText(context, prompt, Toast.LENGTH_SHORT).show();
    }

    public static void prompt(Context context, int strId) {
        prompt(context, context.getString(strId));
    }

    public static void longPrompt(Context context, String prompt) {
        Toast.makeText(context, prompt, Toast.LENGTH_LONG).show();
    }

    public static void longPrompt(Context context, int strId) {
        longPrompt(context, context.getString(strId));
    }
}
