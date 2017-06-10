package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRegistration extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.header_reg)
    RelativeLayout headerReg;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;
    @BindView(R.id.activity_registration_btnnext)
    TextView btnNext;
    @BindView(R.id.header_iv_back)
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraiotn);
        ButterKnife.bind(this);
        headerName.setText(R.string.reg_createaccount);
        click();
    }

    // TODO: 2/15/2017 on click perform
    private void click() {
        btnNext.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        //txtCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_registration_btnnext:
                Intent iNext = new Intent(ActivityRegistration.this, MainActivity.class);
                startActivity(iNext);
                break;
            case R.id.header_iv_back:
                Intent iBack = new Intent(ActivityRegistration.this, ActivityLogin.class);
                startActivity(iBack);
                break;
        }
    }
}
