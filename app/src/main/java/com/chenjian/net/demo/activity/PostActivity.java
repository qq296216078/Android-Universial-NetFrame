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
public class PostActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        MyOnClickListener listener = new MyOnClickListener();

        findViewById(R.id.btn_post_poststring).setOnClickListener(listener);
        findViewById(R.id.btn_post_sync_poststring).setOnClickListener(listener);
        findViewById(R.id.btn_post_postbean).setOnClickListener(listener);
        findViewById(R.id.btn_post_sync_postbean).setOnClickListener(listener);
        findViewById(R.id.btn_post_postlistbean).setOnClickListener(listener);
        findViewById(R.id.btn_post_sync_postlistbean).setOnClickListener(listener);
    }

    private void sendMessage(NetRetBean netRetBean) {
        Message message = Message.obtain();
        message.what = 0;
        message.obj = netRetBean;
        mHandler.sendMessage(message);
    }

    private void requestError(NetRetBean netRetBean) {
        NetToastUtil.requestError(this, netRetBean);
    }

    private void postString() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.LOGIN);
        UrlParse paramParse = new UrlParse().putValue("username", "chenjian").putValue("password", "12345678");

        NetHelper.post(urlParse.toStringOnlyHeader(), paramParse.toStringOnlyParam(), new NetStringListener() {
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

    private void syncPostString() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.LOGIN);
                UrlParse paramParse = new UrlParse().putValue("username", "chenjian").putValue("password", "12345678");

                String originalString = NetHelper.postStringSync(urlParse.toStringOnlyHeader(), paramParse.toStringOnlyParam());
                LogUtil.d(originalString);

                NetRetBean netRetBean = NetHelper.postSync(urlParse.toStringOnlyHeader(), paramParse.toStringOnlyParam(), new SyncNetStringListener() {
                });
                CallbackCode callbackCode = netRetBean.getCallbackCode();
                switch (callbackCode) {
                    case CODE_SUCCESS_REQUEST:
                        String string = (String) netRetBean.getServerObject();
                        LogUtil.d(string);
                        break;
                    default:
                        LogUtil.d(netRetBean.toString());
                        sendMessage(netRetBean);
                        break;
                }
            }
        }.start();
    }

    private void postBean() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER);

        NetHelper.post(urlParse.toStringOnlyHeader(), "", new NetSingleBeanListener<NetUserBean>() {
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

    private void syncPostBean() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER);
                NetRetBean netRetBean = NetHelper.postSync(urlParse.toStringOnlyHeader(), "", new SyncNetSingleBeanListener<NetUserBean>() {
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

    private void postListBean() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER_LIST);

        NetHelper.post(urlParse.toStringOnlyHeader(), "", new NetListBeanListener<NetUserBean>() {
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

    private void syncPostListBean() {
        new Thread() {
            @Override
            public void run() {
                UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.USER_LIST);
                NetRetBean netRetBean = NetHelper.postSync(urlParse.toStringOnlyHeader(), "", new SyncNetListBeanListener<NetUserBean>() {
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

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_post_poststring:
                    postString();
                    break;
                case R.id.btn_post_sync_poststring:
                    syncPostString();
                    break;
                case R.id.btn_post_postbean:
                    postBean();
                    break;
                case R.id.btn_post_sync_postbean:
                    syncPostBean();
                    break;
                case R.id.btn_post_postlistbean:
                    postListBean();
                    break;
                case R.id.btn_post_sync_postlistbean:
                    syncPostListBean();
                    break;
            }

        }
    }
}
