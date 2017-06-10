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

public class QueThreeFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.fragment_que_three_ckfirst)
    CheckBox ckFirst;
    @BindView(R.id.fragment_que_three_cksecond)
    CheckBox ckSecond;
    @BindView(R.id.fragment_que_three_ckthird)
    CheckBox ckThird;
    @BindView(R.id.fragment_que_three_txtquetion)
    TextView txtQuestion;
    @BindView(R.id.fragment_que_three_txtoption3)
    TextView txtOption3;
    @BindView(R.id.fragment_que_three_txtoption2)
    TextView txtOption2;
    @BindView(R.id.fragment_que_three_txtoption1)
    TextView txtOption1;
    public String Option1, Option2, Option3, Option4,Value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_que_three, container, false);
        ButterKnife.bind(this, view);
        getValue();
        click();
        return view;
    }

    private void getValue() {
        Question = txtQuestion.getText().toString();
        Option1 = txtOption1.getText().toString();
        Option2 = txtOption2.getText().toString();
        Option3 = txtOption3.getText().toString();
    }

    // TODO: 2/14/2017 click event
    private void click() {
        ckFirst.setOnCheckedChangeListener(this);
        ckSecond.setOnCheckedChangeListener(this);
        ckThird.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        sp = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        editor = sp.edit().clear();
        Value = sp.getString(questionNumber, "");
        Key = sp.getString(QuestionId, "");

        switch (compoundButton.getId()) {
            case R.id.fragment_que_three_ckfirst:
                isChecked = editor.putString(questionNumber, Value + "," + Option1).commit();
                // btnContinue.setVisibility(View.VISIBLE);
                break;

            case R.id.fragment_que_three_cksecond:
                isChecked = editor.putString(questionNumber, Value + "," + Option2).commit();
                //btnContinue.setVisibility(View.VISIBLE);
                break;

            case R.id.fragment_que_three_ckthird:
                isChecked = editor.putString(questionNumber, Value + "," + Option3).commit();
                //  btnContinue.setVisibility(View.VISIBLE);
                break;
        }
        editor.putString(QuestionId, Key + "," + Question).commit();
        btnContinue.setVisibility(View.VISIBLE);
    }
}
