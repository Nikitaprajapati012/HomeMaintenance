package com.example.archi.homemaintenance.Fragment.AboutHomeQuetion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.archi.homemaintenance.Activity.ActivityAboutYourHome;
import com.example.archi.homemaintenance.Fragment.AllScreen.HomeMainFragment;
import com.example.archi.homemaintenance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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


public class QueThirteenFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fragment_que_thirteen_txtoption1)
    TextView txtOption1;
    @BindView(R.id.fragment_que_thirteen_txtoption2)
    TextView txtOption2;
    @BindView(R.id.fragment_que_thirteen_txtoption3)
    TextView txtOption3;
    @BindView(R.id.fragment_que_thirteen_txtoption4)
    TextView txtOption4;
    @BindView(R.id.fragment_que_thirteen_txtquetion)
    TextView txtQuestion;
    public String Option1, Option2, Option3, Option4,Value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_que_thirteen, container, false);
        ButterKnife.bind(this, view);
        // TODO: 2/16/2017 get value from text
        getValue();
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

    private void click() {
        txtOption1.setOnClickListener(this);
        txtOption2.setOnClickListener(this);
        txtOption3.setOnClickListener(this);
        txtOption4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sp = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        editor = sp.edit().clear();
        Value = sp.getString(questionNumber, "");
        Key = sp.getString(QuestionId, "");
        editor.putString(QuestionId, Key + "," + Question).commit();

        switch (v.getId()) {
            case R.id.fragment_que_thirteen_txtoption1:
                // TODO: 2/16/2017 put value
                editor.putString(questionNumber, Value + "," + Option1).commit();
                ActivityAboutYourHome.addHomeManageDetail();
                break;

            case R.id.fragment_que_thirteen_txtoption2:
                editor.putString(questionNumber, Value + "," + Option2).commit();
                ActivityAboutYourHome.addHomeManageDetail();
                break;

            case R.id.fragment_que_thirteen_txtoption3:
                editor.putString(questionNumber, Value + "," + Option3).commit();
                ActivityAboutYourHome.addHomeManageDetail();
                break;

            case R.id.fragment_que_thirteen_txtoption4:
                editor.putString(questionNumber, Value + "," + Option4).commit();
                ActivityAboutYourHome.addHomeManageDetail();
                break;
        }

        Fragment fragment = new HomeMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("manage_home", "" + PREFS_NAME);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
