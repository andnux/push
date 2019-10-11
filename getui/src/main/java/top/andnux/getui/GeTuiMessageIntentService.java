package top.andnux.getui;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import top.andnux.core.PushMessage;

public class GeTuiMessageIntentService extends GTIntentService {

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        if (GeTuiManager.sMessageProvider == null) {
            return;
        }
        PushMessage mixPushMessage = new PushMessage();
        mixPushMessage.setPlatform(GeTuiManager.NAME);
        mixPushMessage.setContent(new String( msg.getPayload()));
        GeTuiManager.sMessageProvider.onReceivePassThroughMessage(context, mixPushMessage);
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.e(TAG, "onReceiveCommandResult -> " + "action = " + cmdMessage.getAction());

    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gtNotificationMessage) {
        if (GeTuiManager.sMessageProvider != null) {
            PushMessage message = new PushMessage();
            message.setContent(gtNotificationMessage.getContent());
            message.setTitle(gtNotificationMessage.getTitle());
            message.setPlatform(GeTuiManager.NAME);
            GeTuiManager.sMessageProvider.onNotificationMessageArrived(context, message);
        }
    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gtNotificationMessage) {
        if (GeTuiManager.sMessageProvider != null) {
            PushMessage message = new PushMessage();
            message.setContent(gtNotificationMessage.getContent());
            message.setTitle(gtNotificationMessage.getTitle());
            message.setPlatform(GeTuiManager.NAME);
            GeTuiManager.sMessageProvider.onNotificationMessageClicked(context, message);
        }
    }
}