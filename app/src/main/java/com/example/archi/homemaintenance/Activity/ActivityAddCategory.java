package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.archi.homemaintenance.R;

/**
 * Created by NIKI on 26-Jan-17.
 */
public class ActivityAddCategory extends AppCompatActivity implements View.OnClickListener {
    public ImageView imgBack;
    public Button btnSubmit;
    public EditText edCategory;
    public String CategoryName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        findById();
        click();
    }

    private void click() {
        imgBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void findById() {
        imgBack = (ImageView) findViewById(R.id.header_iv_backcategory);
        btnSubmit = (Button) findViewById(R.id.activity_add_category_btnSubmit);
        edCategory = (EditText) findViewById(R.id.activity_add_category_edcategory);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_add_category_btnSubmit:
                CategoryName = edCategory.getText().toString().trim();

                Intent intent = new Intent();
                intent.putExtra("category", CategoryName);
                setResult(1, intent);
                finish();
                break;

            case R.id.header_iv_backcategory:
                finish();
                break;
        }

    }
}
