package top.andnux.push;

import android.content.Context;
import android.util.Log;

import top.andnux.core.PushMessage;
import top.andnux.core.PushMessageReceiver;

public class MessageReceiver extends PushMessageReceiver {

    private static final String TAG = "MessageReceiver";

    @Override
    public void onReceivePassThroughMessage(Context context, PushMessage message) {
        Log.d(TAG,"收到透传消息 -> "+message.getContent());
    }

    @Override
    public void onNotificationMessageClicked(Context context, PushMessage message) {
        Log.d(TAG,"通知栏消息点击 -> "+message.getContent());
    }

    @Override
    public void onNotificationMessageArrived(Context context, PushMessage message) {
        Log.d(TAG,"通知栏消息到达 -> "+message.getContent());
    }
}