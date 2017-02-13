package com.taca.boombuy.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.iid.FirebaseInstanceId;
import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.net.Network;
import com.taca.boombuy.netmodel.SignUpModel;

public class SignUpActivity extends AppCompatActivity {

    // 입력 부분
    EditText et_signup_name;
    EditText et_signup_id;
    EditText et_signup_password;
    EditText et_signup_re_password;

    // 버튼
    ImageButton btn_back_to_signin;
    ImageButton btn_signup;

    // 전화번호 불러오기
    TelephonyManager telemamanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // EditText 매칭
        et_signup_name = (EditText) findViewById(R.id.et_signup_name);
        et_signup_id = (EditText) findViewById(R.id.et_signup_id);
        et_signup_password = (EditText) findViewById(R.id.et_signup_password);
        et_signup_re_password = (EditText) findViewById(R.id.et_signup_re_password);

        // 버튼 매칭 및 클릭리스너
        btn_back_to_signin = (ImageButton) findViewById(R.id.btn_back_to_signin);
        btn_back_to_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_signup = (ImageButton) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Single_Value.getInstance().signUpModel = new SignUpModel();
                Single_Value.getInstance().signUpModel.setPhone(et_signup_id.getText().toString());
                Single_Value.getInstance().signUpModel.setPassword(et_signup_password.getText().toString());
                Single_Value.getInstance().signUpModel.setName(et_signup_name.getText().toString());
                Single_Value.getInstance().signUpModel.setToken(FirebaseInstanceId.getInstance().getToken());
                Single_Value.getInstance().signUpModel.setProfile("");
                Network.getInstance().bb_Signup(getApplicationContext(), Single_Value.getInstance().signUpModel);

                /*Intent intent = new Intent(SignUpActivity.this, SignUpConfirmPopupActivity.class);
                startActivity(intent);*/
            }
        });

        // TelephonyManager를 통해서 번호 받아와 setText 해주기
        telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        et_signup_id.setText(telemamanger.getLine1Number());
    }
}
