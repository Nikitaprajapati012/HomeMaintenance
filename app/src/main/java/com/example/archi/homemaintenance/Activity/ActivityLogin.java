package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_login_btnnext)
    Button btnNext;
    @BindView(R.id.activity_login_txtcreate)
    TextView txtCreateAccount;
    @BindView(R.id.headerLogin)
    RelativeLayout headerLogin;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;
    @BindView(R.id.header_iv_back)
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // TODO: 2/15/2017 set header name
        headerName.setText("Sign In");
        imgBack.setVisibility(View.INVISIBLE);

        click();
    }

    // TODO: 2/15/2017  on click perform 
    private void click() {
        btnNext.setOnClickListener(this);
        txtCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_login_btnnext:
                Intent iNext = new Intent(ActivityLogin.this, ActivityPassword.class);
                startActivity(iNext);
                break;
            case R.id.activity_login_txtcreate:
                Intent iCreate = new Intent(ActivityLogin.this, ActivityRegistration.class);
                startActivity(iCreate);
                break;

        }
    }
}
