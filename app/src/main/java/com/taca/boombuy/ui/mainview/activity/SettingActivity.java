package com.taca.boombuy.ui.mainview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.taca.boombuy.R;
import com.taca.boombuy.database.StorageHelper;

public class SettingActivity extends AppCompatActivity {

    CheckBox cb_push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        cb_push = (CheckBox) findViewById(R.id.cb_push);
        if (StorageHelper.getInstance().getBoolean(SettingActivity.this, "getPush")) {
            cb_push.setChecked(true);
        } else {
            cb_push.setChecked(false);
        }
        cb_push.setOnCheckedChangeListener(new Check_Push());
    }

    class Check_Push implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                StorageHelper.getInstance().setBoolean(SettingActivity.this, "getPush", true);
            } else {
                StorageHelper.getInstance().setBoolean(SettingActivity.this, "getPush", false);
            }
        }
    }

    public void onConfirm(View view) {

    }
}
