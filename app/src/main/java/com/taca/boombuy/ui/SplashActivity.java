package com.taca.boombuy.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.TutorialActivity;
import com.taca.boombuy.database.StorageHelper;
import com.taca.boombuy.ui.sign.LoginActivity;
import com.taca.boombuy.util.ImageProc;

public class SplashActivity extends Activity {

    private int SPLASH_TIME_OUT = 2000;
    boolean nonFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 처음 실행하면 nonFirst는 false
        nonFirst = StorageHelper.getInstance().getBoolean(SplashActivity.this, "nonFirst");
        if (nonFirst) {
            // 앱 실행 처음이 아니면

        } else {
            // 앱 실행 처음이면
            StorageHelper.getInstance().setBoolean(SplashActivity.this, "nonFirst", true);
            // 푸시 받는다고 설정
            StorageHelper.getInstance().setBoolean(SplashActivity.this, "getPush", true);
        }

        ImageProc.getInstance().getImageLoader(this);

        // 초기값들 초기화
        Single_Value.getInstance().initValues();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
