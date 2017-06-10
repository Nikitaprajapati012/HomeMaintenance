package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.Constant.Validation;
import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ravi archi on 1/30/2017.
 */
public class ActivityAddHomeMenually extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.header_home_menually)
    RelativeLayout headerHome;
    @BindView(R.id.header_iv_back)
    ImageView imgBack;
    @BindView(R.id.header_imgadd)
    ImageView imgNext;
    @BindView(R.id.activity_add_home_manually_edpostal)
    EditText edPostal;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;
    String Postal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_menually);
        ButterKnife.bind(this);
        headerName.setText(R.string.add_propery);
        click();
    }

    private void click() {
        imgNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Postal = edPostal.getText().toString().trim();
        switch (v.getId()) {

            case R.id.header_iv_back:
                onBackPressed();
                break;
            case R.id.header_imgadd:
                if (!Postal.equalsIgnoreCase("") && Validation.isZipCode(Postal)) {
                    Intent inext = new Intent(ActivityAddHomeMenually.this, ActivityAboutYourHome.class);
                    startActivity(inext);
                } else {
                    Toast.makeText(this, R.string.valid_zipcode, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
