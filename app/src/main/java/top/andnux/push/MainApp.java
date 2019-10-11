package top.andnux.push;

import android.app.Application;

import top.andnux.core.PushClient;
import top.andnux.mipush.MiPushManager;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushClient instance = PushClient.getInstance();
        instance.addPushManager(new MiPushManager("2882303761518200312","5731820040312"));
        instance.setPushIntentService(PushService.class);
        instance.registerPush(this);
    }
}
