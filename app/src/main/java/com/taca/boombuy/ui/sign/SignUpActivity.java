package com.taca.boombuy.ui.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.otto.Subscribe;
import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.database.StorageHelper;
import com.taca.boombuy.evt.OTTOBus;
import com.taca.boombuy.net.Network;
import com.taca.boombuy.netmodel.SignUpModel;
import com.taca.boombuy.ui.popup.SignUpPopupActivity;

public class SignUpActivity extends AppCompatActivity {

    // 입력 부분
    EditText et_signup_name;
    EditText et_signup_id;
    EditText et_signup_password;
    EditText et_signup_re_password;

    // 버튼
    ImageButton btn_back_to_signin;
    ImageButton btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 이벤트 받을 녀석(회원가입 완료 메시지)
        OTTOBus.getInstance().getSign_up_bus().register(this);

        // EditText 매칭
        et_signup_name = (EditText) findViewById(R.id.et_signup_name);
        et_signup_id = (EditText) findViewById(R.id.et_signup_id);
        et_signup_password = (EditText) findViewById(R.id.et_signup_password);
        et_signup_re_password = (EditText) findViewById(R.id.et_signup_re_password);

        // 회원가입 페이지에 들어올 때 권한 받고 얻은 쉐어드프리퍼런스의 내 전화번호 내용을 setText
        et_signup_id.setText(StorageHelper.getInstance().getString(SignUpActivity.this, "my_phone_number"));

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
                // 비밀번호와 비밀번호 확인이 같을 때 회원가입 진행(서버)
                if (et_signup_password.getText().toString().equals(et_signup_re_password.getText().toString())) {
                    Single_Value.getInstance().signUpModel = new SignUpModel();
                    Single_Value.getInstance().signUpModel.setPhone(et_signup_id.getText().toString());
                    Single_Value.getInstance().signUpModel.setPassword(et_signup_password.getText().toString());
                    Single_Value.getInstance().signUpModel.setName(et_signup_name.getText().toString());
                    Single_Value.getInstance().signUpModel.setToken(FirebaseInstanceId.getInstance().getToken());
                    // 가입할 때 토큰 얻고 이 때 처음으로 쉐어드프리퍼런스에 저장
                    StorageHelper.getInstance().setString(SignUpActivity.this, "my_token", FirebaseInstanceId.getInstance().getToken());
                    Single_Value.getInstance().signUpModel.setProfile("");
                    Network.getInstance().bb_Signup(getApplicationContext(), Single_Value.getInstance().signUpModel);
                } else {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 오토버스 이벤트 도착
    @Subscribe
    public void FinishLoad(String data) {
        Log.i("OTTO", data);
        if (data.contains("성공")) {
            StorageHelper.getInstance().setBoolean(SignUpActivity.this, "auto_login", true);
            StorageHelper.getInstance().setString(SignUpActivity.this, "auto_login_password", et_signup_password.getText().toString());
            StorageHelper.getInstance().setString(SignUpActivity.this, "user_name", et_signup_name.getText().toString());
            Intent intent = new Intent(SignUpActivity.this, SignUpPopupActivity.class);
            // 오토버스 썼으면 등록해제
            OTTOBus.getInstance().getSign_up_bus().unregister(this);
            startActivity(intent);
        } else {
            Toast.makeText(SignUpActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
