package com.taca.boombuy.ui.popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.taca.boombuy.R;

public class SignOutPopupActivity extends Activity {
    /*// 백키 무시
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // 윈도우 설정 : 잠겨있어도 보이고, 화면을 유지하고, 뒤를 블러처리하고
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        // 등장및 퇴장 애니메이션
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
        // 화면설정
        setContentView(R.layout.popup_sign_out);

    }


    public void SignOut(View view){
        setResult(404);
        finish();
    }

    public void SignOutCancel(View view){

        finish();
    }


    /*@Subscribe
    public void FinishLoad(ResBasic data) {

        resBasic = data;
        if (resBasic.getMessage() != null) {
            overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // 오토버스 썼으면 등록해제
            OttoBus.getInstance().getLogin_Bus().unregister(this);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SignUpPopupActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    }*/
}
