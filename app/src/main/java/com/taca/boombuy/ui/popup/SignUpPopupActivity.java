package com.taca.boombuy.ui.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.taca.boombuy.R;
import com.taca.boombuy.ui.mainview.activity.MainActivity;

public class SignUpPopupActivity extends AppCompatActivity {
    /*// 백키 무시
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 윈도우 설정 : 잠겨있어도 보이고, 화면을 유지하고, 뒤를 블러처리하고
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        // 등장및 퇴장 애니메이션
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
        // 화면설정
        setContentView(R.layout.popup_sign_up);

    }

    public void okSignUpPopup(View view) {
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
        Intent intent = new Intent(SignUpPopupActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // 백 버튼 이벤트
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
            Intent intent = new Intent(SignUpPopupActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
