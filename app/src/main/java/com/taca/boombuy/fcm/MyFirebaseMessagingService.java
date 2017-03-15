package com.taca.boombuy.fcm;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.taca.boombuy.R;
import com.taca.boombuy.database.StorageHelper;
import com.taca.boombuy.ui.mainview.activity.SettingActivity;
import com.taca.boombuy.ui.sign.LoginActivity;

/**
 * Created by jimin on 2017-01-09.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    // 메시지 수신 : 수신 이벤트 발생 시 자동 호출
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload 1: " + remoteMessage.getData().get("key1"));
            //Log.i(TAG, "Message data payload 2: " + remoteMessage.getData().get("key2"));
            if (StorageHelper.getInstance().getBoolean(getApplicationContext(), "getPush")) {
                sendNotification(remoteMessage.getData().get("key1"));
            }
        }
    }
    // [END receive_message]

    // 노티 창에 띄움
    private void sendNotification(String messageBody) {
        // 노티 번호
        int msgID = (int)(Math.random()*10000);

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("NOTI_ID", msgID);  // 노티를 제거시 필요한 번호
        intent.putExtra("FCM", messageBody);// 푸시내용을 액티비티로 전달
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder;
        if(messageBody.contains("/")) {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("BOOMBUY")
                    .setContentText("선물이 도착했습니다")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        } else {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("BOOMBUY")
                    .setContentText("결제해주세요")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(msgID /* ID of notification */, notificationBuilder.build());
    }
}
