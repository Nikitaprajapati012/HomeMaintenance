package com.example.archi.homemaintenance.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.Adapter.ChooseFromContactAdapter;
import com.example.archi.homemaintenance.Adapter.HomeAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archi on 1/9/2017.
 */

public class ActivityAddHomeDetails extends AppCompatActivity implements View.OnClickListener

{

    public Context context;
    public DbHelper db;
    public List<HashMap<String, String>> homeList = new ArrayList<>();
    public HomeAdapter adapater;
    @BindView(R.id.activity_add_home_details_recycleview)
    RecyclerView recyclerViewHome;
    @BindView(R.id.header_home)
    RelativeLayout header;
    @BindView(R.id.header_iv_back)
    ImageView headerBack;
    @BindView(R.id.header_imgadd)
    ImageView headerAddHome;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_details);
        ButterKnife.bind(this);
        headerName.setText(R.string.my_home);
        db = new DbHelper(this);
        homeList = db.getAllHomeImagesList();
        recyclerViewHome = (RecyclerView) findViewById(R.id.activity_add_home_details_recycleview);
        adapater = new HomeAdapter(this, homeList,getSupportFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ActivityAddHomeDetails.this);
        recyclerViewHome.setLayoutManager(mLayoutManager);
        recyclerViewHome.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHome.setAdapter(adapater);
        click();

    }

    private void click() {
        headerBack.setOnClickListener(this);
        headerAddHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_iv_back:
                finish();
                break;
            case R.id.header_imgadd:
               Intent iadd= new Intent(ActivityAddHomeDetails.this,ActivityAddHomeMenually.class);
                startActivity(iadd);
                break;
        }

    }
}
