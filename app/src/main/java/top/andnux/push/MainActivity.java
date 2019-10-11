package top.andnux.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;

import top.andnux.core.PushClient;
import top.andnux.core.PushMessage;
import top.andnux.core.PushMessageReceiver;
import top.andnux.getui.GeTuiManager;
import top.andnux.huawei.HuaWeiManager;
import top.andnux.meizu.MeizuPushManager;
import top.andnux.mipush.MiPushManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    TextView text_use_push;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        text_use_push = (TextView) findViewById(R.id.text_use_push);
        text_use_push.setText("当前推送平台：" + PushClient.getInstance().getUsePushName());
        IntentFilter filter = new IntentFilter();
        filter.addAction(PushMessageReceiver.RECEIVE_THROUGH_MESSAGE);
        filter.addAction(PushMessageReceiver.NOTIFICATION_ARRIVED);
        filter.addAction(PushMessageReceiver.NOTIFICATION_CLICKED);
        filter.addAction(PushMessageReceiver.ERROR);
        this.registerReceiver(mBroadcastReceiver, filter);

        findViewById(R.id.btn_switch_mipush).setOnClickListener(this);
        findViewById(R.id.btn_switch_meizu).setOnClickListener(this);
        findViewById(R.id.btn_switch_getui).setOnClickListener(this);
        findViewById(R.id.btn_switch_huawei).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_set_alias).setOnClickListener(this);
        findViewById(R.id.btn_set_tags).setOnClickListener(this);


        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d("mipush", content, t);
            }

            @Override
            public void log(String content) {
                Log.d("mipush", content);
            }
        };
        Logger.setLogger(this, newLogger);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver mBroadcastReceiver = new PushMessageReceiver() {

        @Override
        public void onReceivePassThroughMessage(Context context, PushMessage message) {
            text.setText(text.getText().toString() + "\n收到透传消息:" + message.getPlatform());
            text.setText(text.getText().toString() + "\n收到透传消息:" + message.getContent());
        }

        @Override
        public void onNotificationMessageClicked(Context context, PushMessage message) {
            text.setText(text.getText().toString() + "\n通知栏消息点击:" + message.getPlatform());
            text.setText(text.getText().toString() + "\n通知栏消息点击:" + message.getContent());
        }

        @Override
        public void onNotificationMessageArrived(Context context, PushMessage message) {
            text.setText(text.getText().toString() + "\n通知栏消息到达:" + message.getPlatform());
            text.setText(text.getText().toString() + "\n通知栏消息到达:" + message.getContent());
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch_mipush:
                PushClient.getInstance().setUsePushName(MiPushManager.NAME);
                text_use_push.setText("当前推送平台：" + PushClient.getInstance().getUsePushName());
                break;
            case R.id.btn_switch_meizu:
                PushClient.getInstance().setUsePushName(MeizuPushManager.NAME);
                text_use_push.setText("当前推送平台：" + PushClient.getInstance().getUsePushName());
                break;
            case R.id.btn_switch_getui:
                PushClient.getInstance().setUsePushName(GeTuiManager.NAME);
                text_use_push.setText("当前推送平台：" + PushClient.getInstance().getUsePushName());
                break;
            case R.id.btn_switch_huawei:
                PushClient.getInstance().setUsePushName(HuaWeiManager.NAME);
                text_use_push.setText("当前推送平台：" + PushClient.getInstance().getUsePushName());
                break;
            case R.id.btn_start:
                PushClient.getInstance().registerPush(getApplicationContext());
                break;
            case R.id.btn_close:
                PushClient.getInstance().unRegisterPush(getApplicationContext());
                break;
            case R.id.btn_set_alias:
                PushClient.getInstance().setAlias(getApplicationContext(), "103");
                break;
            case R.id.btn_set_tags:
                PushClient.getInstance().setTags(getApplicationContext(), "广东");
                break;
        }
    }
}
