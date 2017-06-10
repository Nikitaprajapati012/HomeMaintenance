package com.example.archi.homemaintenance.Fragment.AboutHomeQuetion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.archi.homemaintenance.Activity.ActivityAboutYourHome.btnContinue;
import static com.example.archi.homemaintenance.Constant.Constant.PREFS_NAME;
import static com.example.archi.homemaintenance.Constant.Constant.Question;
import static com.example.archi.homemaintenance.Constant.Constant.QuestionId;
import static com.example.archi.homemaintenance.Constant.Constant.questionNumber;
import static com.example.archi.homemaintenance.Constant.SharedPrefManagerQuestion.editor;
import static com.example.archi.homemaintenance.Constant.SharedPrefManagerQuestion.sp;

/**
 * Created by Ravi archi on 1/31/2017.
 */

public class QueFourFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    public String key, value;
    @BindView(R.id.fragment_que_four_ckfirst)
    CheckBox ckFirst;
    @BindView(R.id.fragment_que_four_cksecond)
    CheckBox ckSecond;
    @BindView(R.id.fragment_que_four_ckthird)
    CheckBox ckThird;
    @BindView(R.id.fragment_que_four_ckfour)
    CheckBox ckFour;
    @BindView(R.id.fragment_que_four_txtquetion)
    TextView txtQuestion;
    @BindView(R.id.fragment_que_four_txtoption4)
    TextView txtOption4;
    @BindView(R.id.fragment_que_four_txtoption3)
    TextView txtOption3;
    @BindView(R.id.fragment_que_four_txtoption2)
    TextView txtOption2;
    @BindView(R.id.fragment_que_four_txtoption1)
    TextView txtOption1;
    public String Option1, Option2, Option3, Option4,Value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_que_four, container, false);
        ButterKnife.bind(this, view);
        // TODO: 2/16/2017 get value from text
        getValue();
        btnContinue.setVisibility(View.INVISIBLE);
        click();
        return view;
    }

    private void getValue() {
        Question = txtQuestion.getText().toString();
        Option1 = txtOption1.getText().toString();
        Option2 = txtOption2.getText().toString();
        Option3 = txtOption3.getText().toString();
        Option4 = txtOption4.getText().toString();
    }


    // TODO: 2/15/2017 on click perform
    private void click() {
        ckFirst.setOnCheckedChangeListener(this);
        ckSecond.setOnCheckedChangeListener(this);
        ckThird.setOnCheckedChangeListener(this);
        ckFour.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        // TODO: 2/17/2017 get previous Que & Ans
        sp = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        editor = sp.edit().clear();
        value = sp.getString(questionNumber, "");
        key = sp.getString(QuestionId, "");

        switch (compoundButton.getId()) {
            case R.id.fragment_que_four_ckfirst:
                isChecked = editor.putString(questionNumber, value + "," + Option1).commit();
                //btnContinue.setVisibility(View.VISIBLE);
                break;

            case R.id.fragment_que_four_cksecond:
                isChecked = editor.putString(questionNumber, value + "," + Option2).commit();
                // btnContinue.setVisibility(View.VISIBLE);
                break;

            case R.id.fragment_que_four_ckthird:
                isChecked = editor.putString(questionNumber, value + "," + Option3).commit();
                // btnContinue.setVisibility(View.VISIBLE);
                break;
            case R.id.fragment_que_four_ckfour:
                isChecked = editor.putString(questionNumber, value + "," + Option4).commit();
                // btnContinue.setVisibility(View.VISIBLE);
                break;
        }
        editor.putString(QuestionId, key + "," + Question).commit();
        btnContinue.setVisibility(View.VISIBLE);
    }

}
