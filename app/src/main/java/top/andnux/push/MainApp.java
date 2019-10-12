package top.andnux.push;

import android.app.Application;

import java.util.Map;

import top.andnux.core.PushClient;
import top.andnux.core.PushManager;
import top.andnux.getui.GeTuiManager;
import top.andnux.google.GooglePushManager;
import top.andnux.huawei.HuaWeiManager;
import top.andnux.meizu.MeizuPushManager;
import top.andnux.mipush.MiPushManager;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushClient instance = PushClient.getInstance();
        instance.addPushManager(new MiPushManager("2882303761518200312", "5731820040312"));
        instance.addPushManager(new GeTuiManager());
        instance.addPushManager(new HuaWeiManager());
        instance.addPushManager(new GooglePushManager());
        instance.addPushManager(new MeizuPushManager("", "", 0, 0));
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
