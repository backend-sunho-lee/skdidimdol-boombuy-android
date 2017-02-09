package com.taca.boombuy.ui.popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.taca.boombuy.R;
import com.taca.boombuy.ui.mainview.activity.MainActivity;

public class SignUpConfirmPopupActivity extends Activity {

    // 버튼
    ImageButton btn_confirm_popup_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_sign_up_confirm);

        // 버튼 매칭 및 클릭리스너
        btn_confirm_popup_signup = (ImageButton) findViewById(R.id.btn_confirm_popup_signup);
        btn_confirm_popup_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpConfirmPopupActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    // 백 버튼 이벤트
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SignUpConfirmPopupActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
