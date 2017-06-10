package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.content.Intent;
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

import com.example.archi.homemaintenance.Activity.ActivityAddReceipts;
import com.example.archi.homemaintenance.R;


/**
 * Created by Ravi archi on 1/12/2017.
 */

public class ReceiptsFragment extends Fragment implements View.OnClickListener {

    public RelativeLayout headerReceipts, headerView;
    public ImageView imgAdd;
    public Button btnDate, btnBusiness, btnProduct;
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;
    public TextView txtHeaderName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_receipts, container, false);

        txtHeaderName = (TextView) getActivity().findViewById(R.id.header_tv_headername_main);
        txtHeaderName.setText(R.string.my_receipts);
        imgAdd = (ImageView) getActivity().findViewById(R.id.header_iv_add_main);
        headerView = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerView.setVisibility(View.VISIBLE);

        findById(view);
        click();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnDate.performClick();
    }

    private void click() {

        imgAdd.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnProduct.setOnClickListener(this);
        btnBusiness.setOnClickListener(this);
        btnDate.performClick();
    }

    private void findById(View view) {

        btnDate = (Button) view.findViewById(R.id.fragment_receipts_btndate);
        btnProduct = (Button) view.findViewById(R.id.fragment_receipts_btnproduct);
        btnBusiness = (Button) view.findViewById(R.id.fragment_receipts_btnbusiness);

    }


    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();

        switch (v.getId()) {
            case R.id.header_iv_add_main:
                Intent iAdd = new Intent(getActivity(), ActivityAddReceipts.class);
                startActivity(iAdd);
                break;

            case R.id.fragment_receipts_btndate:
                btnDate.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_light));
                btnProduct.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                btnBusiness.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                fragment = new DateFragment();
                break;

            case R.id.fragment_receipts_btnproduct:
                btnDate.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                btnProduct.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_light));
                btnBusiness.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));


                fragment = new ProductFragment();
                break;

            case R.id.fragment_receipts_btnbusiness:
                btnDate.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                btnProduct.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_black_2));
                btnBusiness.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue_light));

                fragment = new BusinessFragment();
                break;
        }

        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_receipts_framelayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }
}



