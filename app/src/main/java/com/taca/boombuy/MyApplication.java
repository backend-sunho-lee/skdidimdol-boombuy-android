package com.taca.boombuy;

import android.app.Application;

import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.database.StorageHelper;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // StorageHelpr 에 사용할 context 를 받아오기 위함
        StorageHelper.getInstance().setContext(getApplicationContext());
        // 받은 context 를 retrofit 설정에 보내줌

        NetSSL.getInstance().launch(getApplicationContext());
        //
    }
}
