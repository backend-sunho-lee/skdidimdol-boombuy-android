package com.taca.boombuy.fcm;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.database.StorageHelper;
import com.taca.boombuy.net.NetworkTEST;
import com.taca.boombuy.netmodel.UpdateTokenModel;

/**
 * FCM 고유 토큰, 아이디를 발급하는 서비스
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    // [START refresh_token]
    // 토큰이 갱신되면 호출된다.
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    // [END refresh_token]
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        // 토큰이 리프레시되면 서버에 토큰 업데이트 요청
        Single_Value.getInstance().updateTokenModel = new UpdateTokenModel();
        Single_Value.getInstance().updateTokenModel.setPhone(StorageHelper.getInstance().getString(getApplicationContext(), "my_phone_number"));
        Single_Value.getInstance().updateTokenModel.setToken(token);
        NetworkTEST.getInstance().bb_Update_token(getApplicationContext(), Single_Value.getInstance().updateTokenModel);

        // 토큰이 바뀌면 쉐어드프리퍼런스에 저장
        StorageHelper.getInstance().setString(getApplicationContext(), "my_token", token);
    }
}
