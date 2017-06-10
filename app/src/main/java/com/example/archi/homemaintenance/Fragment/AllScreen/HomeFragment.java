package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.archi.homemaintenance.Activity.ActivityAddHomeImage;
import com.example.archi.homemaintenance.R;

/**
 * Created by Ravi archi on 1/12/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView tvHeader, tvEdit;
    private ImageView ivAddHome;
    private RecyclerView recyclerviewAddHome;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findById();
        setHeader();
        init();
        return view;
    }

    private void setHeader() {
       /* tvHeader = (TextView) getActivity().findViewById(R.id.header_tv_header_name);
        tvHeader.setText(getString(R.string.home_name));
        tvEdit.setVisibility(View.INVISIBLE);*/
    }

    private void findById() {
        ivAddHome = (ImageView) getActivity().findViewById(R.id.header_iv_add);
       // tvEdit = (TextView) getActivity().findViewById(R.id.header_tv_edit);
       // recyclerviewAddHome = (RecyclerView) getActivity().findViewById(R.id.fragment_home_recyclerview);
    }

    private void init() {
        ivAddHome.setOnClickListener(this);
      //  recyclerviewAddHome.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_iv_add:
                Intent i = new Intent(getActivity(), ActivityAddHomeImage.class);
                startActivity(i);
                break;
        }

    }
}
