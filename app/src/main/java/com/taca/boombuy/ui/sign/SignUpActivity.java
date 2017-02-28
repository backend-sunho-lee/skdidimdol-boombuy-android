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
import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResBasic;
import com.taca.boombuy.database.StorageHelper;
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.networkmodel.SignUpDTO;
import com.taca.boombuy.ui.popup.SignUpPopupActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        OttoBus.getInstance().getSignUp_Bus().register(this);

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
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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

                    SignUpDTO signUpDTO = new SignUpDTO(
                            et_signup_id.getText().toString(),
                            et_signup_password.getText().toString(),
                            et_signup_name.getText().toString(),
                            FirebaseInstanceId.getInstance().getToken()
                    );

                    //NetWork.getInstance().NetSignUp(getApplicationContext(), signUpDTO);

                    // 회원가입 진행 (서버)
                    Call<ResBasic> NetSignUp = NetSSL.getInstance().getMemberImpFactory().NetSignUp(signUpDTO);
                    NetSignUp.enqueue(new Callback<ResBasic>() {
                        @Override
                        public void onResponse(Call<ResBasic> call, Response<ResBasic> response) {

                            if (response.isSuccessful()) {

                                if (response.body() != null && response.body().getMessage() != null) {

                                    Log.i("RES SUC", response.body().getMessage());
                                    OttoBus.getInstance().getSignUp_Bus().post(response.body());
                                } else {
                                    Log.i("RES FAIl", response.message().toString());
                                }
                            } else {

                                Log.i("RES FAIL", response.message().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResBasic> call, Throwable t) {

                        }
                    });

                    // 가입할 때 토큰 얻고 이 때 처음으로 쉐어드프리퍼런스에 저장
                    StorageHelper.getInstance().setString(SignUpActivity.this, "my_token", FirebaseInstanceId.getInstance().getToken());
                } else {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 오토버스 이벤트 도착

    ResBasic resBasic;

    @Subscribe
    public void FinishLoad(ResBasic data) {
        resBasic = data;
        // 회원가입 성공하면 sharedpreference에 저장
        if (resBasic.getMessage() != null) {
            StorageHelper.getInstance().setBoolean(SignUpActivity.this, "auto_login", true);
            StorageHelper.getInstance().setString(SignUpActivity.this, "auto_login_password", et_signup_password.getText().toString());
            StorageHelper.getInstance().setString(SignUpActivity.this, "user_name", et_signup_name.getText().toString());

            Intent intent = new Intent(SignUpActivity.this, SignUpPopupActivity.class);
            // 오토버스 썼으면 등록해제
            OttoBus.getInstance().getSignUp_Bus().unregister(this);
            startActivity(intent);
            //
        } else {
            Toast.makeText(SignUpActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
