package com.taca.boombuy.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.ui.sign.SignInActivity;
import com.taca.boombuy.vo.VO_giftitem_list;

public class SplashActivity extends Activity {

    private int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            request_permission();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);*/

        Single_Value.getInstance().vo_giftitem_lists.clear();
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        Single_Value.getInstance().vo_giftitem_list = new VO_giftitem_list();
        Single_Value.getInstance().vo_giftitem_list.setProduct_imageView_cell(icon);
        Single_Value.getInstance().vo_giftitem_list.setProduct_title_cell("김지민");
        Single_Value.getInstance().vo_giftitem_list.setProduct_price_cell("30,000원");
        Single_Value.getInstance().vo_giftitem_lists.add(Single_Value.getInstance().vo_giftitem_list);

        Bitmap icon2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.product_icon);
        for (int i = 0; i < 5; i++) {
            Single_Value.getInstance().vo_giftitem_list = new VO_giftitem_list();
            Single_Value.getInstance().vo_giftitem_list.setProduct_imageView_cell(icon2);
            Single_Value.getInstance().vo_giftitem_list.setProduct_title_cell("김지민"+i);
            Single_Value.getInstance().vo_giftitem_list.setProduct_price_cell("30,000원");
            Single_Value.getInstance().item_arraylist.add(Single_Value.getInstance().vo_giftitem_list);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                } else {
                    // 사용자가 권한 동의를 안하므로 종료
                    finish();
                }
            }
        }
    }

    public void request_permission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            //권한이 없을 경우

            //최초 권한 요청인지 혹은 사용자에 의한 재요청인지 확인
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                //사용자가 임의로 권한을 취소시킨 경우
                //권한 재요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            } else {
                //최초로 권한을 요청하는 경우(첫실행)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }
}
