package com.chenjian.net.demo.activity2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.chenjian.net.R;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.demo.bean.NetInfoBean;
import com.chenjian.net.demo.bean.NetInfoListBean;
import com.chenjian.net.demo.bean.NetPageBean;
import com.chenjian.net.demo.listener.async.NetCustomBeanListener;
import com.chenjian.net.demo.listener.sync.SyncNetCustomBeanListener;
import com.chenjian.net.demo.url.UrlConst;
import com.chenjian.net.demo.util.LogUtil;
import com.chenjian.net.demo.util.NetToastUtil;
import com.chenjian.net.helper.NetHelper;
import com.chenjian.net.helper.NetHelper2;
import com.chenjian.net.listener.async.NetSingleBeanListener;
import com.chenjian.net.listener.async.NetStringListener;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.url.UrlParse;

import java.util.List;
import java.util.Map;

/**
 * 例子：定义一个Activity
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 12:20
 */

@SuppressWarnings("unchecked")
public class CustomActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        MyOnClickListener listener = new MyOnClickListener();

        findViewById(R.id.btn_custom_customstring).setOnClickListener(listener);
        findViewById(R.id.btn_custom_custombean).setOnClickListener(listener);
        findViewById(R.id.btn_custom_custom).setOnClickListener(listener);
        findViewById(R.id.btn_custom_sync_custom).setOnClickListener(listener);
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_custom_customstring:
                    customString();
                    break;
                case R.id.btn_custom_custombean:
                    customBean();
                    break;
                case R.id.btn_custom_custom:
                    custom();
                    break;
                case R.id.btn_custom_sync_custom:
                    syncCustom();
                    break;
            }
        }
    }

    private void sendMessage(NetRetBean netRetBean) {
        Message message = Message.obtain();
        message.what = 0;
        message.obj = netRetBean;
        mHandler.sendMessage(message);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    NetRetBean netRetBean = (NetRetBean) msg.obj;
                    requestError(netRetBean);
                    break;
            }
        }
    };

    private void requestError(NetRetBean netRetBean) {
        NetToastUtil.requestError(this, netRetBean);
    }

    private void customString() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO_LIST);
        UrlParse paramParse = new UrlParse().putValue("page", "1").putValue("page_size", "3");

        NetHelper2.create()
                .url(urlParse.toStringOnlyHeader())
                .param(paramParse.toStringOnlyParam())
                .netListener(new NetStringListener() {
                    @Override
                    protected void onSuccess(String string) {
                        LogUtil.d(string);
                    }

                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                        LogUtil.d(netRetBean.toString());
                        requestError(netRetBean);
                    }
                })
                .post();
    }

    private void customBean() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO_LIST);
        UrlParse paramParse = new UrlParse().putValue("page", "1").putValue("page_size", "3");

        NetHelper2.create()
                .url(urlParse.toStringOnlyHeader())
                .param(paramParse.toStringOnlyParam())
                .netListener(new NetSingleBeanListener<NetInfoListBean>() {
                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                        LogUtil.d(netRetBean.toString());
                        requestError(netRetBean);
                    }

                    @Override
                    protected void onSuccess(NetInfoListBean infoListBean) {
                        LogUtil.d(infoListBean.toString());
                    }
                })
                .post();
    }

    private void custom() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO_LIST);
        UrlParse paramParse = new UrlParse().putValue("page", "1").putValue("page_size", "3");

        NetHelper2.create()
                .url(urlParse.toStringOnlyHeader())
                .param(paramParse.toStringOnlyParam())
                .netListener(new NetCustomBeanListener<NetPageBean, NetInfoBean>() {
                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                        LogUtil.d(netRetBean.toString());
                        requestError(netRetBean);
                    }

                    @Override
                    protected void onSuccess(NetPageBean pageBean, List<NetInfoBean> infoBeen) {
                        LogUtil.d(pageBean.toString());
                        for (int i = 0; i < infoBeen.size(); i++) {
                            LogUtil.d(infoBeen.get(i).toString());
                        }
                    }
                })
                .post();
    }

    private void syncCustom() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO_LIST);
                UrlParse paramParse = new UrlParse().putValue("page", "1").putValue("page_size", "3");

                NetRetBean netRetBean = NetHelper2.create()
                        .url(urlParse.toStringOnlyHeader())
                        .param(paramParse.toStringOnlyParam())
                        .syncNetListener(new SyncNetCustomBeanListener<NetPageBean, NetInfoBean>() {
                        })
                        .syncPost();

                CallbackCode callbackCode = netRetBean.getCallbackCode();
                switch (callbackCode) {
                    case CODE_SUCCESS_REQUEST:
                        Map<String, Object> map = netRetBean.getServerObjectMap();
                        NetPageBean pageBean = (NetPageBean) map.get("page");
                        List<NetInfoBean> infoBeen = (List<NetInfoBean>) map.get("list");
                        LogUtil.d(pageBean.toString());
                        for (int i = 0; i < infoBeen.size(); i++) {
                            LogUtil.d(infoBeen.get(i).toString());
                        }
                        break;
                    default:
                        LogUtil.d(netRetBean.toString());
                        sendMessage(netRetBean);
                        break;
                }
            }
        }.start();
    }
}
