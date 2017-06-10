package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.archi.homemaintenance.R;

import butterknife.BindView;

/**
 * Created by Ravi archi on 1/12/2017.
 */

public class ReminderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_reminder,container,false);

        return view;
    }
}
