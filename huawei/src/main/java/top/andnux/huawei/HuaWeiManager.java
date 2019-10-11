package top.andnux.huawei;

import android.content.Context;

import top.andnux.core.MessageProvider;

public class HuaWeiManager implements top.andnux.core.PushManager {

    public static final String NAME = "huawei";
    public static MessageProvider sMessageProvider;

    @Override
    public void registerPush(Context context) {

     }

    @Override
    public void unRegisterPush(Context context) {

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
