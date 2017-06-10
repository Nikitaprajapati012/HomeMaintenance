
package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPassword extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_password_txtforgot)
    TextView txtForgot;
    @BindView(R.id.activity_password_btnsignin)
    Button btnSignIn;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        headerName.setText("Sign In");
        click();
    }

    // TODO: 2/15/2017 on click perform
    private void click() {
        txtForgot.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_password_btnsignin:
                Intent iNext = new Intent(ActivityPassword.this,MainActivity.class);
                startActivity(iNext);

                break;
            case R.id.activity_password_txtforgot:
                Intent iForgot = new Intent(ActivityPassword.this,ActivityForgotPassword.class);
                startActivity(iForgot);

                break;
        }
    }
}
