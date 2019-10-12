package top.andnux.google;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;
import top.andnux.core.MessageProvider;
import top.andnux.core.PushManager;

public class GooglePushManager implements PushManager {

    public static final String NAME = "google";
    public static MessageProvider sMessageProvider;

    @Override
    public void registerPush(Context context) {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    @Override
    public void unRegisterPush(Context context) {
        unsetAlias(context, null);
        FirebaseMessaging.getInstance().setAutoInitEnabled(false);
    }

    @Override
    public void setAlias(Context context, String alias) {

    }

    @Override
    public void unsetAlias(Context context, String alias) {

    }

    @Override
    public void setTags(Context context, String... tags) {

    }

    @Override
    public void unsetTags(Context context, String... tags) {

    }


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        sMessageProvider = provider;
    }

    @Override
    public void disable(Context context) {
        unRegisterPush(context);
    }
}
