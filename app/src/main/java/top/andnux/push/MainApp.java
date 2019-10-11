package top.andnux.push;

import android.app.Application;

import java.util.Map;

import top.andnux.core.PushClient;
import top.andnux.core.PushManager;
import top.andnux.getui.GeTuiManager;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushClient instance = PushClient.getInstance();
//        instance.addPushManager(new MiPushManager("2882303761518200312","5731820040312"));
        instance.addPushManager(new GeTuiManager());
        instance.setPushIntentService(PushService.class);
        instance.setSelector(new PushClient.Selector(){
            @Override
            public String select(Map<String, PushManager> pushAdapterMap, String brand) {
                return super.select(pushAdapterMap, brand);
            }
        });
        instance.registerPush(this);
    }
}
