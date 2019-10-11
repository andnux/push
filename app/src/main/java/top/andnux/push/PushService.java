package top.andnux.push;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import top.andnux.core.PushIntentService;
import top.andnux.core.PushMessage;

/**
 * 需要定义一个receiver 或 Service 来接收透传和通知栏点击的信息，建议使用Service，更加简单
 * Created by Wiki on 2017/6/3.
 */

public class PushService extends PushIntentService {

    private static final String TAG = "PushService";

    @Override
    public void onReceivePassThroughMessage(PushMessage message) {
        Log.e(TAG, "收到透传消息 -> " + message.getPlatform());
        Log.e(TAG, "收到透传消息 -> " + message.getContent());
    }

    @Override
    public void onNotificationMessageClicked(PushMessage message) {
        Log.e(TAG, "通知栏消息点击 -> " + message.getPlatform());
        Log.e(TAG, "通知栏消息点击 -> " + message.getContent());
    }
}
