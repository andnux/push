package top.andnux.core;

import android.app.IntentService;
import android.content.Intent;

public abstract class PushIntentService extends IntentService {

    public static final String TAG = "PushIntentService";

    public PushIntentService() {
        super("PushIntentService");
    }

    @Override
    public final void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            try {
                PushMessage message = (PushMessage) intent.getSerializableExtra(PushMessageReceiver.MESSAGE);
                if (PushMessageReceiver.RECEIVE_THROUGH_MESSAGE.equals(action)) {
                    onReceivePassThroughMessage(message);
                } else if (PushMessageReceiver.NOTIFICATION_ARRIVED.equals(action)) {
                    onNotificationMessageArrived(message);
                } else if (PushMessageReceiver.NOTIFICATION_CLICKED.equals(action)) {
                    onNotificationMessageClicked(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 透传
     */
    public abstract void onReceivePassThroughMessage(PushMessage message);

    /**
     * 通知栏消息点击
     */
    public abstract void onNotificationMessageClicked(PushMessage message);

    /**
     * 通知栏消息到达,
     * flyme6基于android6.0以上不再回调，
     * MIUI基于小米推送，在APP被杀死不会回调，
     * 在个推不会回调，所以不建议使用，
     */
    @Deprecated
    public void onNotificationMessageArrived(PushMessage message) {

    }
}
