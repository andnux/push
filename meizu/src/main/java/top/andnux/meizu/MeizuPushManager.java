package top.andnux.meizu;

import android.content.Context;

import androidx.annotation.IdRes;

import com.meizu.cloud.pushsdk.PushManager;

import top.andnux.core.MessageProvider;

/**
 * 魅族推送只支持Flyme系统，务必需要注意
 */

public class MeizuPushManager implements top.andnux.core.PushManager {

    public static final String NAME = "meizuPush";
    public  static MessageProvider sMessageProvider;

    private String appId;
    private String appKey;
    public static int mIcon;
    public static int mIconSmall;

    public MeizuPushManager(String appId, String appKey, @IdRes int icon, @IdRes int iconSmall) {
        this.appId = appId;
        this.appKey = appKey;
        mIcon = icon;
        mIconSmall = iconSmall;
    }

    @Override
    public void registerPush(Context context) {
        PushManager.register(context, appId, appKey);
    }

    @Override
    public void unRegisterPush(Context context) {
        PushManager.unRegister(context, appId, appKey);
    }

    @Override
    public void setAlias(Context context, String alias) {
        PushManager.subScribeAlias(context, appId, appKey, PushManager.getPushId(context), alias);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        PushManager.unSubScribeAlias(context, appId, appKey, PushManager.getPushId(context), alias);
    }

    @Override
    public void setTags(Context context, String... tags) {
        for (String tag : tags) {
            PushManager.subScribeTags(context, appId, appKey, PushManager.getPushId(context), tag);
        }
    }

    @Override
    public void unsetTags(Context context, String... tags) {
        for (String tag : tags) {
            PushManager.unSubScribeTags(context, appId, appKey, PushManager.getPushId(context), tag);
        }
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
