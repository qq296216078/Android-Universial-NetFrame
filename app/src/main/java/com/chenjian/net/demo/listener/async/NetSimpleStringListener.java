package com.chenjian.net.demo.listener.async;

import android.content.Context;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.demo.util.NetToastUtil;
import com.chenjian.net.listener.async.NetStringListener;
import com.chenjian.net.listener.common.CallbackCode;

/**
 * 作者： ChenJian
 * 时间： 2017.1.9 20:12
 */

abstract public class NetSimpleStringListener extends NetStringListener {
    private Context mContext;

    public NetSimpleStringListener(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
        NetToastUtil.requestError(mContext, netRetBean);
    }
}
