package com.taca.boombuy.ui.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.net.Network;
import com.taca.boombuy.netmodel.LonInModel;

public class SignInActivity extends AppCompatActivity {

    // 버튼
    ImageButton btn_auto_signin;
    TextView btn_signup;

    EditText et_signin_id;
    EditText et_signin_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_signin_id = (EditText) findViewById(R.id.et_signin_id);
        et_signin_password = (EditText) findViewById(R.id.et_signin_password);

        // 버튼 매칭 및 클릭리스너
        btn_auto_signin = (ImageButton) findViewById(R.id.btn_auto_signin);
        btn_auto_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Single_Value.getInstance().lonInModel = new LonInModel();
                Single_Value.getInstance().lonInModel.setPhone(et_signin_id.getText().toString());
                Single_Value.getInstance().lonInModel.setPassword(et_signin_password.getText().toString());
                Network.getInstance().bb_Login(getApplicationContext(), Single_Value.getInstance().lonInModel);


                /*Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();*/
            }
        });

        btn_signup = (TextView) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
