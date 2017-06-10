package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.Activity.ActivityAddAppliance;
import com.example.archi.homemaintenance.Constant.Constant;
import com.example.archi.homemaintenance.R;


/**
 * Created by Ravi archi on 1/12/2017.
 */

public class AppllianceFragment extends Fragment implements View.OnClickListener {

    public RelativeLayout headerAppliance,headerview;
    public ImageView imgAdd, imgHome, imgReminders, imgDirectory, imgAppliances, imgReceipts;
    public Button btnDetail, btnManual;
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;
    public TextView txtHeaderName;
    public SharedPreferences sp;
    public String one;
    public SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_appliance, container, false);
        headerview=(RelativeLayout)getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.VISIBLE);

        txtHeaderName = (TextView) getActivity().findViewById(R.id.header_tv_headername_main);
        txtHeaderName.setText(R.string.my_appliance);
        imgHome = (ImageView) getActivity().findViewById(R.id.footer_imghome);
        imgReminders = (ImageView) getActivity().findViewById(R.id.footer_imgreminder);
        imgDirectory = (ImageView) getActivity().findViewById(R.id.footer_imgdirectory);
        imgAppliances = (ImageView) getActivity().findViewById(R.id.footer_imgapplicance);
        imgReceipts = (ImageView) getActivity().findViewById(R.id.footer_imgreceipts);

        imgAdd = (ImageView) getActivity().findViewById(R.id.header_iv_add_main);
        findById(view);
        click();
        return view;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("userdetails", getActivity().MODE_PRIVATE);
        one = sp.getString(Constant.fragmentNumber, "");
        // editor = sp.edit();
        // editor.clear();
        // editor.commit();

        if (one.equalsIgnoreCase("1")) {
            imgHome.performClick();

        } else if (one.equalsIgnoreCase("2")) {
            imgReminders.performClick();

        } else if (one.equalsIgnoreCase("3")) {
            imgDirectory.performClick();

        } else if (one.equalsIgnoreCase("4")) {
            imgAppliances.performClick();

        } else if (one.equalsIgnoreCase("5")) {
            imgReceipts.performClick();

        }

    }*/

    private void click() {
        imgAdd.setOnClickListener(this);
        btnManual.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        btnDetail.performClick();


    }

    private void findById(View view) {
        headerAppliance = (RelativeLayout) view.findViewById(R.id.header_appiance);
        btnDetail = (Button) view.findViewById(R.id.fragment_appliance_btnDetail);
        btnManual = (Button) view.findViewById(R.id.fragment_appliance_btnManual);
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();

        switch (v.getId()) {
            case R.id.header_iv_add_main:
                Intent iAdd = new Intent(getActivity(), ActivityAddAppliance.class);
                startActivity(iAdd);
                break;

            case R.id.fragment_appliance_btnDetail:
                btnDetail.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_light));
                btnManual.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                fragment = new DetailFragment();
                break;

            case R.id.fragment_appliance_btnManual:
                btnDetail.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                btnManual.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_light));
                fragment = new MenualFragment();
                break;
        }

        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_appliance_framelayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }
}



