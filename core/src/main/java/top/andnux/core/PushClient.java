package top.andnux.core;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PushClient {

    private Map<String, PushManager> mPushManagerMap = new HashMap<>();
    private String mUsePushName;
    private Selector mSelector;
    private String mReceiverPermission = null;// 避免被其它APP接收
    private Class<? extends PushIntentService> mPushIntentServiceClass;
    private static volatile PushClient INSTALCE;

    public static PushClient getInstance() {
        if (INSTALCE == null) {
            synchronized (PushClient.class) {
                if (INSTALCE == null) {
                    INSTALCE = new PushClient();
                }
            }
        }
        return INSTALCE;
    }

    private PushClient() {

    }

    public void setPushIntentService(Class<? extends PushIntentService> pushIntentServiceClass) {
        mPushIntentServiceClass = pushIntentServiceClass;
    }

    public void setSelector(Selector selector) {
        mSelector = selector;
        mUsePushName = mSelector.select(mPushManagerMap, Build.BRAND);
    }

    public String getUsePushName() {
        return mUsePushName;
    }

    public void addPushManager(PushManager pushAdapter) {
        mPushManagerMap.put(pushAdapter.getName(), pushAdapter);
        pushAdapter.setMessageProvider(mMessageProvider);
    }

    public void registerPush(Context context) {
        mReceiverPermission = context.getPackageName() + ".permission.PUSH_RECEIVE";
        Set<String> keys = mPushManagerMap.keySet();
        for (String key : keys) {
            PushManager pushManager = mPushManagerMap.get(key);
            if (key.equals(mUsePushName)) {
                if (pushManager != null) {
                    pushManager.registerPush(context);
                }
            } else {
                if (pushManager != null) {
                    pushManager.unRegisterPush(context);
                }
            }
        }
    }

    private PushManager getPushManager() {
        if (mUsePushName == null) {
            throw new RuntimeException("you need setSelector or setUsePushName");
        }
        return mPushManagerMap.get(mUsePushName);
    }

    public void unRegisterPush(Context context) {
        getPushManager().unRegisterPush(context);
    }

    public void setUsePushName(String sUsePushName) {
        mUsePushName = sUsePushName;
    }

    public void setAlias(Context context, String alias) {
        getPushManager().setAlias(context, alias);
    }

    public void unsetAlias(Context context, String alias) {
        getPushManager().unsetAlias(context, alias);
    }

    public void setTags(Context context, String... tags) {
        getPushManager().setTags(context, tags);
    }

    public void unsetTags(Context context, String... tags) {
        getPushManager().unsetTags(context, tags);
    }

    public static class Selector {
        public String select(Map<String, PushManager> pushAdapterMap, String brand) {
            if (pushAdapterMap.containsKey("meizuPush") && brand.equalsIgnoreCase("meizu")) {
                return "meizuPush";
            } else if (pushAdapterMap.containsKey("mipush") && brand.equalsIgnoreCase("xiaomi")) {
                return "mipush";
            } else if (pushAdapterMap.containsKey("huawei") && brand.equalsIgnoreCase("huawei")) {
                return "huawei";
            } else if (pushAdapterMap.containsKey("getui")) {
                return "getui";
            }
            return null;
        }
    }

    private MessageProvider mMessageProvider = new MessageProvider() {
        @Override
        public void onReceivePassThroughMessage(Context context, PushMessage message) {
            message.setNotify(0);
            Intent intent = new Intent(PushMessageReceiver.RECEIVE_THROUGH_MESSAGE);
            intent.putExtra("message", message);
            context.sendBroadcast(intent, mReceiverPermission);
            Log.d("onReceivePassThrough", message.getContent());

            if (mPushIntentServiceClass != null) {
                intent.setClass(context, mPushIntentServiceClass);
                context.startService(intent);
            }
        }

        @Override
        public void onNotificationMessageClicked(Context context, PushMessage message) {
            message.setNotify(1);
            Intent intent = new Intent(PushMessageReceiver.NOTIFICATION_CLICKED);
            intent.putExtra(PushMessageReceiver.MESSAGE, message);
            context.sendBroadcast(intent, mReceiverPermission);
            Log.d("onNotificationClicked", message.getContent());

            if (mPushIntentServiceClass != null) {
                intent.setClass(context, mPushIntentServiceClass);
                context.startService(intent);
            }
        }

        @Override
        public void onNotificationMessageArrived(Context context, PushMessage message) {
            Intent intent = new Intent(PushMessageReceiver.NOTIFICATION_ARRIVED);
            intent.putExtra("message", message);
            context.sendBroadcast(intent, mReceiverPermission);
            Log.d("onNotificationArrived", message.getContent());
            if (mPushIntentServiceClass != null) {
                intent.setClass(context, mPushIntentServiceClass);
                context.startService(intent);
            }
        }
    };
}
