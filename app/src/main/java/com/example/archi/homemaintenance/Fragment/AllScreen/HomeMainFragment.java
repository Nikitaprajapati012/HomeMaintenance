package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.Activity.ActivityAddHomeImage;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/12/2017.
 */

public class HomeMainFragment extends Fragment implements View.OnClickListener {
    public TextView tvHeader, tvEdit, tvUpload;
    public ImageView ivAddHomeImage, imgAdd;
    public List<HashMap<String, String>> homeImageList;
    public HashMap<String, String> map = new HashMap<>();
    public DbHelper db;
    public String homeImagesId, homeManageId;
    public RelativeLayout headerHome;
    public LinearLayout layout;
    public RelativeLayout headerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home_main, container, false);

        // TODO: 2/15/2017 make header as per screen
        headerview = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.VISIBLE);
        tvHeader = (TextView) getActivity().findViewById(R.id.header_tv_headername_main);
        tvEdit = (TextView) getActivity().findViewById(R.id.header_tv_edit_main);
        imgAdd = (ImageView) getActivity().findViewById(R.id.header_iv_add_main);
        imgAdd.setVisibility(View.INVISIBLE);
        tvHeader.setText(R.string.my_home);
        tvEdit.setVisibility(View.INVISIBLE);
        db = new DbHelper(getActivity());
        homeImageList = db.getAllHomeImagesList();
        findById(view);

        click();
        init();
        return view;
    }

    private void init() {
        if (getArguments() != null) {
            homeManageId = getArguments().getString("manage_home");
            Log.d("homeManageId", "" + homeManageId);
        }

    }

    private void findById(View view) {

        layout = (LinearLayout) view.findViewById(R.id.fragment_home_main);
        ivAddHomeImage = (ImageView) view.findViewById(R.id.fragment_home_main_imghome);
        tvEdit = (TextView) view.findViewById(R.id.header_tv_edit_main);
        tvUpload = (TextView) view.findViewById(R.id.fragment_home_main_txtupload);

    }

    private void click() {
        ivAddHomeImage.setOnClickListener(this);
        //  imgAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_main_imghome:
                Intent i = new Intent(getActivity(), ActivityAddHomeImage.class);
                startActivityForResult(i, 3);
                break;
           /* case R.id.header_iv_add_main:
                Intent iadd = new Intent(getActivity(), ActivityAddHomeDetails.class);
                startActivity(iadd);
                break;*/
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ivAddHomeImage != null) {
            if (requestCode == 3) {
                String message = data.getStringExtra("imagepath");
                Bitmap bitmap = BitmapFactory.decodeFile(message);
                tvUpload.setVisibility(View.GONE);
                ivAddHomeImage.setImageBitmap(bitmap);
            }
        }
    }
}
