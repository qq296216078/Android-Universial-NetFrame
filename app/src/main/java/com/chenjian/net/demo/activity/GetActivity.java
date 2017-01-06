package com.chenjian.net.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.chenjian.net.R;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.demo.bean.NetUserBean;
import com.chenjian.net.demo.url.UrlConst;
import com.chenjian.net.demo.util.LogUtil;
import com.chenjian.net.demo.util.NetToastUtil;
import com.chenjian.net.helper.NetHelper;
import com.chenjian.net.listener.async.NetListBeanListener;
import com.chenjian.net.listener.async.NetSingleBeanListener;
import com.chenjian.net.listener.async.NetStringListener;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.listener.sync.SyncNetListBeanListener;
import com.chenjian.net.listener.sync.SyncNetSingleBeanListener;
import com.chenjian.net.listener.sync.SyncNetStringListener;
import com.chenjian.net.url.UrlParse;

import java.util.List;

/**
 * 例子：定义一个Activity
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 12:20
 */

@SuppressWarnings("unchecked")
public class GetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        MyOnClickListener listener = new MyOnClickListener();

        findViewById(R.id.btn_get_getstring).setOnClickListener(listener);
        findViewById(R.id.btn_get_sync_getstring).setOnClickListener(listener);
        findViewById(R.id.btn_get_getbean).setOnClickListener(listener);
        findViewById(R.id.btn_get_sync_getbean).setOnClickListener(listener);
        findViewById(R.id.btn_get_getlistbean).setOnClickListener(listener);
        findViewById(R.id.btn_get_sync_getlistbean).setOnClickListener(listener);
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_get_getstring:
                    getString();
                    break;
                case R.id.btn_get_sync_getstring:
                    syncGetString();
                    break;
                case R.id.btn_get_getbean:
                    getBean();
                    break;
                case R.id.btn_get_sync_getbean:
                    syncGetBean();
                    break;
                case R.id.btn_get_getlistbean:
                    getListBean();
                    break;
                case R.id.btn_get_sync_getlistbean:
                    syncGetListBean();
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

    private void getString() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL)
                .appendRegion(UrlConst.LOGIN)
                .putValue("username", "chenjian")
                .putValue("password", "12345678");

        NetHelper.get(urlParse.toString(), new NetStringListener() {
            @Override
            protected void onCommon() {
                super.onCommon();
            }

            @Override
            protected void onSuccess(String string) {
                LogUtil.d(string);
            }

            @Override
            protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                LogUtil.d(netRetBean.toString());
                requestError(netRetBean);
            }
        });
    }

    private void syncGetString() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL)
                        .appendRegion(UrlConst.LOGIN)
                        .putValue("username", "chenjian")
                        .putValue("password", "12345678");

                String originalString = NetHelper.getStringSync(urlParse.toString());
                LogUtil.d(originalString);

                NetRetBean netRetBean = NetHelper.getSync(urlParse.toString(), new SyncNetStringListener());
                CallbackCode callbackCode = netRetBean.getCallbackCode();
                switch (callbackCode) {
                    case CODE_SUCCESS_REQUEST:
                        String string = (String) netRetBean.getServerObject();
                        LogUtil.d(string);
                        break;
                    case CODE_ERROR_HTTP_NOT_200:
                    case CODE_ERROR_REQUEST_EXP:
                    case CODE_ERROR_SERVER_DATA_ERROR:
                    case CODE_ERROR_JSON_EXP:
                    case CODE_ERROR_UNKNOWN:
                    default:
                        LogUtil.d(netRetBean.toString());
                        sendMessage(netRetBean);
                        break;
                }
            }
        }.start();
    }

    private void getBean() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER);

        NetHelper.get(urlParse.toString(), new NetSingleBeanListener<NetUserBean>() {
            @Override
            protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                LogUtil.d(netRetBean.toString());
                requestError(netRetBean);
            }

            @Override
            protected void onSuccess(NetUserBean userBean) {
                LogUtil.d(userBean.toString());
            }
        });
    }

    private void syncGetBean() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER);
                NetRetBean netRetBean = NetHelper.getSync(urlParse.toString(), new SyncNetSingleBeanListener<NetUserBean>() {
                });
                CallbackCode callbackCode = netRetBean.getCallbackCode();
                switch (callbackCode) {
                    case CODE_SUCCESS_REQUEST:
                        NetUserBean userBean = (NetUserBean) netRetBean.getServerObject();
                        LogUtil.d(userBean.toString());
                        break;
                    default:
                        LogUtil.d(netRetBean.toString());
                        sendMessage(netRetBean);
                        break;
                }
            }
        }.start();
    }

    private void getListBean() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER_LIST);

        NetHelper.get(urlParse.toString(), new NetListBeanListener<NetUserBean>() {
            @Override
            protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                LogUtil.d(netRetBean.toString());
                requestError(netRetBean);
            }

            @Override
            protected void onSuccess(List<NetUserBean> userBeen) {
                for (int i = 0; i < userBeen.size(); i++) {
                    LogUtil.d(userBeen.get(i).toString());
                }
            }
        });
    }

    private void syncGetListBean() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER_LIST);
                NetRetBean netRetBean = NetHelper.getSync(urlParse.toString(), new SyncNetListBeanListener<NetUserBean>() {
                });
                CallbackCode callbackCode = netRetBean.getCallbackCode();
                switch (callbackCode) {
                    case CODE_SUCCESS_REQUEST:
                        List<NetUserBean> userBeen = (List<NetUserBean>) netRetBean.getServerObject();
                        for (int i = 0; i < userBeen.size(); i++) {
                            LogUtil.d(userBeen.get(i).toString());
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
