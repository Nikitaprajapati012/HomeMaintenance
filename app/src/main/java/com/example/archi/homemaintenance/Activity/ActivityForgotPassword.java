package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityForgotPassword extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.headerForgot)
    RelativeLayout headerReg;
    @BindView(R.id.activity_forgot_password_btn_submit)
    TextView btnNext;
    @BindView(R.id.header_iv_back)
    ImageView imgBack;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        headerName.setText("Forgot Password");
        click();

    }


    // TODO: 2/15/2017 on click perform
    private void click() {
        btnNext.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_registration_btnnext:
                Intent iNext = new Intent(ActivityForgotPassword.this,ActivityPassword.class);
                startActivity(iNext);
                break;
            case R.id.header_iv_back:
                Intent iBack = new Intent(ActivityForgotPassword.this, ActivityPassword.class);
                startActivity(iBack);
                break;
        }
    }
}
