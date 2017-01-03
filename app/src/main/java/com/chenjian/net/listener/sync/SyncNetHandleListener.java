package com.chenjian.net.listener.sync;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.core.sync.SyncNetListener;
import com.chenjian.net.exp.RequestErrorException;
import com.chenjian.net.exp.RespondErrorException;
import com.chenjian.net.listener.common.CallbackCode;

import org.json.JSONException;
import org.json.JSONObject;

import static com.chenjian.net.listener.common.CallbackCode.CODE_ERROR_JSON_EXP;

/**
 * 公用网络逻辑，核心监听器。自定义监听器一般继承这个类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 11:12
 */

abstract public class SyncNetHandleListener implements SyncNetListener {

    /**
     * 处理NetRetBean并返回。是一个中转站。本类和其子类都可以调用这个方法
     *
     * @param netRetBean netRetBean
     * @return netRetBean
     */
    protected NetRetBean handleResult(NetRetBean netRetBean) {
        return netRetBean;
    }

    @Override
    public NetRetBean sendSuccess(String result) {
        NetRetBean netRetBean = new NetRetBean();
        try {
            JSONObject jsonObject = new JSONObject(result);
            String code = jsonObject.getString("code");
            String message = jsonObject.getString("message");
            String time = jsonObject.getString("time");
            String data = jsonObject.getString("data");
            netRetBean.setServerCode(code);
            netRetBean.setServerMsg(message);
            netRetBean.setServerTime(time);
            netRetBean.setServerData(data);
            if (code.equals("00001")) {
                netRetBean.setCallbackCode(CallbackCode.CODE_SUCCESS_REQUEST);
                netRetBean = onReceivedRet(netRetBean);
            } else {
                netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_SERVER_DATA_ERROR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            netRetBean.setCallbackCode(CODE_ERROR_JSON_EXP);
        }
        return handleResult(netRetBean);
    }

    @Override
    public NetRetBean sendError(Exception exp) {
        exp.printStackTrace();

        NetRetBean netRetBean = new NetRetBean();
        netRetBean.setException(exp);

        try {
            throw exp;
        } catch (RespondErrorException e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_HTTP_NOT_200);
        } catch (RequestErrorException e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_REQUEST_EXP);
        } catch (JSONException e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_JSON_EXP);
        } catch (Exception e) {
            netRetBean.setCallbackCode(CallbackCode.CODE_ERROR_UNKNOWN);
        }

        return handleResult(netRetBean);
    }

    /**
     * 子类根据业务区分，将netRetBean解析成list或者单个实体，或者解析成其它结果
     *
     * @param netRetBean server返回的数据实体，data字段将在子类中解析
     * @return 解析后的netRetBean
     * @throws JSONException 解析json异常
     */
    abstract protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException;
}
