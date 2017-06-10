package com.example.archi.homemaintenance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.Adapter.AboutHomeMainPager;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.archi.homemaintenance.Constant.Constant.PREFS_NAME;
import static com.example.archi.homemaintenance.Constant.Constant.QuestionId;
import static com.example.archi.homemaintenance.Constant.Constant.questionNumber;
import static com.example.archi.homemaintenance.Constant.SharedPrefManagerQuestion.editor;
import static com.example.archi.homemaintenance.Constant.SharedPrefManagerQuestion.sp;

public class ActivityAboutYourHome extends AppCompatActivity implements View.OnClickListener {
    public static ViewPager viewPager;
    public static TextView btnContinue;
    public static String key, value, Que, Ans;
    public static DbHelper db;
    public FragmentTransaction transaction;
    public int currentPage, page = 0;
    public Fragment fragment;
    @BindView(R.id.header_about_home)
    RelativeLayout header;
    @BindView(R.id.header_iv_back)
    ImageView headerBack;
    @BindView(R.id.header_tv_edit)
    TextView txtSkip;
    @BindView(R.id.activity_about_your_home_txtpage)
    TextView txtPage;
    @BindView(R.id.activity_about_your_home_btndone)
    TextView btnDone;
    @BindView(R.id.header_tv_header_name)
    TextView headerName;

    public static void addHomeManageDetail() {
        // TODO: 2/17/2017 split by comma
        List<String> homeQueList = Arrays.asList(key.split(","));
        List<String> homeList = Arrays.asList(value.split(","));

        for (int j = 0; j < homeQueList.size(); j++) {
            Que = homeQueList.get(j);
            Log.d("sharedQ", "" + Que);
            if (Que == "1") {
                for (int i = 0; i < homeList.size(); i++) {
                    Ans = homeList.get(i);
                    Log.d("sharedA", "" + Ans);
                }
                // TODO: 2/17/2017 insert into database
                db.addQuetion(Que, Ans);
                Log.d("sharedINSERT", "" + Que + Ans);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_your_home);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.activity_about_your_home_view_pager);
        btnContinue = (Button) findViewById(R.id.activity_about_your_home_btncontinue);

        init();
        txtSkip.setText(R.string.skip);
        headerName.setText(R.string.about_home);
        btnDone.setVisibility(View.GONE);
        btnContinue.setVisibility(View.GONE);
        viewPager.setAdapter(new AboutHomeMainPager(getSupportFragmentManager()));
        click();
    }

    private void init() {
        db = new DbHelper(this);
    }

    private void click() {
        btnContinue.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        headerBack.setOnClickListener(this);
        txtSkip.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private static final float thresholdOffset = 0.5f;
            private static final int thresholdOffsetPixels = 1;
            private boolean scrollStarted, checkDirection;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (thresholdOffset > positionOffset && positionOffsetPixels > thresholdOffsetPixels) {
                } else {
                }
                checkDirection = false;
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                if (currentPage > 1) {
                    btnDone.setVisibility(View.VISIBLE);
                    if (currentPage > 3) {
                        btnContinue.setVisibility(View.GONE);
                    }
                    if (currentPage > 11) {
                        Intent ipos = new Intent(ActivityAboutYourHome.this, MainActivity.class);
                        startActivity(ipos);
                        finish();
                    }
                } else {
                    btnDone.setVisibility(View.GONE);
                    btnContinue.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        sp = getSharedPreferences(PREFS_NAME, this.MODE_PRIVATE);
        editor = sp.edit().clear();
        key = sp.getString(QuestionId, "");
        value = sp.getString(questionNumber, "");

        switch (v.getId()) {

            case R.id.activity_about_your_home_btncontinue:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;

            case R.id.activity_about_your_home_btndone:
                Toast.makeText(this, "" + sp.getString(questionNumber, ""), Toast.LENGTH_SHORT).show();
                addHomeManageDetail();
                break;

            case R.id.header_iv_back:

                if (viewPager.getCurrentItem() == 0) {
                    finish();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                    page = currentPage - 1;
                }
                break;

            case R.id.header_tv_edit:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                page = currentPage + 1;
                break;
        }
    }


}
