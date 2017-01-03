package com.chenjian.net.token;

import com.chenjian.net.data.NetVariables;

/**
 * Created by ChenJian
 * 2016.11.29 17:26:26.
 */

public class TokenUtil {

    private static boolean isWaitToken = true;

    public static void setWaitToken(boolean isWaitToken) {
        TokenUtil.isWaitToken = isWaitToken;
    }

    public static void waitToken() {
        while (isWaitToken) {
            if (NetVariables.token != null && !NetVariables.token.equals("")) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
