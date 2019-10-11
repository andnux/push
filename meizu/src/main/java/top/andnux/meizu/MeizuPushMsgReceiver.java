package top.andnux.meizu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

import top.andnux.core.PushMessage;

public class MeizuPushMsgReceiver extends MzPushMessageReceiver {

    private static final String TAG = "MeizuPushMsgReceiver";
    private int push_icon;
    private int push_small_icon;

    @Override
    public void onRegister(Context context, String pushid) {
        //应用在接受返回的pushid
    }

    @Override
    public void onHandleIntent(Context context, Intent intent) {
        push_icon = context.getResources().getIdentifier("ic_launcher", "mipmap", context.getPackageName());
        push_small_icon = context.getResources().getIdentifier("ic_launcher", "mipmap", context.getPackageName());
        super.onHandleIntent(context, intent);
    }

    @Override
    public void onMessage(Context context, String s) {
        //接收服务器推送的透传消息
        if (MeizuPushManager.sMessageProvider != null){
            PushMessage message = new PushMessage();
            message.setContent(s);
            message.setPlatform(MeizuPushManager.NAME);
            MeizuPushManager.sMessageProvider.onReceivePassThroughMessage(context,message);
        }
    }

    @Override
    @Deprecated
    public void onUnRegister(Context context, boolean b) {
        //调用PushManager.unRegister(context）方法后，会在此回调反注册状态
    }

    //设置通知栏小图标
    @Override
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        if (push_icon > 0){
            pushNotificationBuilder.setmLargIcon(push_icon);
            Log.d(TAG,"设置通知栏大图标");
        }else {
            if (MeizuPushManager.mIcon > 0) {
                pushNotificationBuilder.setmLargIcon(MeizuPushManager.mIcon);
            } else {
                Log.e(TAG, "缺少drawable/mipush_notification.png");
            }
        }
        if (push_small_icon > 0){
            pushNotificationBuilder.setmStatusbarIcon(push_small_icon);
            Log.d(TAG,"设置通知栏小图标");
        } else {
            if (MeizuPushManager.mIcon > 0) {
                pushNotificationBuilder.setmStatusbarIcon(MeizuPushManager.mIcon);
            } else {
                Log.e(TAG, "缺少drawable/mipush_small_notification.png");
            }
        }
    }

    @Override
    public void onPushStatus(Context context,PushSwitchStatus pushSwitchStatus) {
        //检查通知栏和透传消息开关状态回调
    }

    @Override
    public void onRegisterStatus(Context context,RegisterStatus registerStatus) {
        Log.i(TAG, "onRegisterStatus " + registerStatus);
        //新版订阅回调
    }

    @Override
    public void onUnRegisterStatus(Context context,UnRegisterStatus unRegisterStatus) {
        Log.i(TAG,"onUnRegisterStatus "+unRegisterStatus);
        //新版反订阅回调
    }

    @Override
    public void onSubTagsStatus(Context context,SubTagsStatus subTagsStatus) {
        Log.i(TAG, "onSubTagsStatus " + subTagsStatus);
        //标签回调
    }

    @Override
    public void onSubAliasStatus(Context context,SubAliasStatus subAliasStatus) {
        Log.i(TAG, "onSubAliasStatus " + subAliasStatus);
        //别名回调
    }

    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {
        super.onNotificationClicked(context, mzPushMessage);
        if (MeizuPushManager.sMessageProvider != null) {
            PushMessage message = new PushMessage();
            message.setPlatform(MeizuPushManager.NAME);
            message.setTitle(mzPushMessage.getTitle());
            message.setDescription(mzPushMessage.getContent());
            message.setContent(mzPushMessage.getSelfDefineContentString());
            MeizuPushManager.sMessageProvider.onNotificationMessageClicked(context, message);
        }
    }

    @Override
    public void onNotificationArrived(Context context, MzPushMessage mzPushMessage) {
        super.onNotificationArrived(context, mzPushMessage);
        if (MeizuPushManager.sMessageProvider != null) {
            PushMessage message = new PushMessage();
            message.setPlatform(MeizuPushManager.NAME);
            message.setTitle(mzPushMessage.getTitle());
            message.setDescription(mzPushMessage.getContent());
            message.setContent(mzPushMessage.getSelfDefineContentString());
            MeizuPushManager.sMessageProvider.onNotificationMessageArrived(context, message);
        }
    }

    @Override
    public void onNotificationDeleted(Context context, MzPushMessage mzPushMessage) {
        super.onNotificationDeleted(context, mzPushMessage);
    }
}