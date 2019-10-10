package top.andnux.core;

import android.content.Context;

public interface MessageProvider {
    /**
     * 透传
     */
    public void onReceivePassThroughMessage(Context context, PushMessage message);

    /**
     * 通知栏消息点击
     */
    public void onNotificationMessageClicked(Context context, PushMessage message);

    /**
     * 通知栏消息到达
     */
    public void onNotificationMessageArrived(Context context, PushMessage message);

}
