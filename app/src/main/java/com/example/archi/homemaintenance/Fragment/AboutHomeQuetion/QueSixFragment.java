package com.example.archi.homemaintenance.Fragment.AboutHomeQuetion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.archi.homemaintenance.Activity.ActivityAboutYourHome.viewPager;
import static com.example.archi.homemaintenance.Constant.Constant.Key;
import static com.example.archi.homemaintenance.Constant.Constant.PREFS_NAME;
import static com.example.archi.homemaintenance.Constant.Constant.Question;
import static com.example.archi.homemaintenance.Constant.Constant.QuestionId;
import static com.example.archi.homemaintenance.Constant.Constant.questionNumber;
import static com.example.archi.homemaintenance.Constant.SharedPrefManagerQuestion.editor;
import static com.example.archi.homemaintenance.Constant.SharedPrefManagerQuestion.sp;

/**
 * Created by Ravi archi on 1/31/2017.
 */

public class QueSixFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fragment_que_six_txtoption1)
    TextView txtOption1;
    @BindView(R.id.fragment_que_six_txtoption2)
    TextView txtOption2;
    @BindView(R.id.fragment_que_six_txtquetion)
    TextView txtQuestion;
    public String Option1, Option2, Option3, Option4,Value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_que_six, container, false);
        ButterKnife.bind(this, view);
        getValue();
        click();
        return view;
    }

    // TODO: 2/17/2017 get value of text
    private void getValue() {
        Question = txtQuestion.getText().toString();
        Option1 = txtOption1.getText().toString();
        Option2 = txtOption2.getText().toString();
    }


    // TODO: 2/15/2017 perform click
    private void click() {
        txtOption1.setOnClickListener(this);
        txtOption2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sp = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        editor = sp.edit().clear();
        Value = sp.getString(questionNumber, "");
        Key = sp.getString(QuestionId, "");


        switch (v.getId()) {
            case R.id.fragment_que_six_txtoption1:
                editor.putString(questionNumber, Value + "," + Option1).commit();
               // viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                break;
            case R.id.fragment_que_six_txtoption2:
                editor.putString(questionNumber, Value + "," + Option2).commit();
               // viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
        editor.putString(QuestionId, Key + "," + Question).commit();
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }
}
