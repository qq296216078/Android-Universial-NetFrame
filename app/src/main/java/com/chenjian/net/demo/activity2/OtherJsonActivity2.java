package com.chenjian.net.demo.activity2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.chenjian.net.R;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.demo.bean.NetFastjsonBean;
import com.chenjian.net.demo.bean.NetGsonBean;
import com.chenjian.net.demo.bean.NetInfoBean;
import com.chenjian.net.demo.url.UrlConst;
import com.chenjian.net.demo.util.LogUtil;
import com.chenjian.net.helper.NetHelper;
import com.chenjian.net.helper.NetHelper2;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.listener.fastjson.NetSingleFastjsonListener;
import com.chenjian.net.listener.gson.NetSingleGsonListener;
import com.chenjian.net.url.UrlParse;

/**
 * 作者： ChenJian
 * 时间： 2017.1.4 9:45
 */

public class OtherJsonActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherjson);

        MyOnClickListener listener = new MyOnClickListener();

        findViewById(R.id.btn_otherjson_gson).setOnClickListener(listener);
        findViewById(R.id.btn_otherjson_gson_error).setOnClickListener(listener);
        findViewById(R.id.btn_otherjson_fastjson).setOnClickListener(listener);
        findViewById(R.id.btn_otherjson_fastjson_error).setOnClickListener(listener);
        findViewById(R.id.btn_otherjson_otherjson).setOnClickListener(listener);
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_otherjson_gson:
                    gson();
                    break;
                case R.id.btn_otherjson_gson_error:
                    gsonError();
                    break;
                case R.id.btn_otherjson_fastjson:
                    fastjson();
                    break;
                case R.id.btn_otherjson_fastjson_error:
                    fastjsonError();
                    break;
                case R.id.btn_otherjson_otherjson:
                    otherjson();
                    break;
            }
        }
    }

    private void gson() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO);

        NetHelper2.create()
                .url(urlParse.toString())
                .get(new NetSingleGsonListener<NetGsonBean>() {
                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
                        // 这里是ui线程
                    }

                    @Override
                    protected void onSuccess(NetGsonBean gsonBean) {
                        // 这里是ui线程
                        // 解析成功
                        LogUtil.d(gsonBean.toString());
                    }
                });
    }

    private void gsonError() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO);

        NetHelper2.create()
                .url(urlParse.toString())
                .get(new NetSingleGsonListener<NetInfoBean>() {
                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {

                    }

                    @Override
                    protected void onSuccess(NetInfoBean infoBean) {
                        // 打印出来三个字段都为null。gson的规则是解析不到的字段为null
                        LogUtil.d(infoBean.toString());
                    }
                });
    }

    private void fastjson() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO);

        NetHelper2.create()
                .url(urlParse.toString())
                .get(new NetSingleFastjsonListener<NetFastjsonBean>() {
                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {

                    }

                    @Override
                    protected void onSuccess(NetFastjsonBean fastjsonBean) {
                        // 解析成功
                        LogUtil.d(fastjsonBean.toString());
                    }
                });
    }

    private void fastjsonError() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO);

        NetHelper2.create()
                .url(urlParse.toString())
                .get(new NetSingleFastjsonListener<NetInfoBean>() {
                    @Override
                    protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {

                    }

                    @Override
                    protected void onSuccess(NetInfoBean infoBean) {
                        // 打印出来三个字段都为null。fastjson的规则是解析不到的字段为null
                        LogUtil.d(infoBean.toString());
                    }
                });
    }

    private void otherjson() {
        UrlParse urlParse = new UrlParse(UrlConst.BASE_URL).appendRegion(UrlConst.INFO);

        /*
         * 需要将NetSingleBeanListener中onReceivedRet方法里的 T t = NetBaseBeanUtil.parseItem(getClass(), 0, object);
         * 改成 T t = NetCommonBeanUtil.parseItem(getClass(), 0, object); 才能用
         * NetSingleBeanListener中的泛型继承类也要修改成NetCommonBean而不是NetBaseBean
         * 不然编译不通过，因为NetNewsBean继承的是NetCommonBean而不是NetBaseBean
         *
         * 当然，你所有的bean都能继承NetCommonBean，在使用通用或者自定义Listener（非otherjson）时候，
         * 把其中的NetBaseBeanUtil.parseItem改成NetCommonBeanUtil.parseItem就行了，
         * 这样用户就可以在bean里面自行选择是否使用otherjson了
         */
//        NetHelper.get(urlParse.toString(), new NetSingleBeanListener<NetNewsBean>() {
//            @Override
//            protected void onError(CallbackCode errorCode, NetRetBean netRetBean) {
//
//            }
//
//            @Override
//            protected void onSuccess(NetNewsBean newsBean) {
//                LogUtil.d(newsBean.toString());
//            }
//        });
    }
}
