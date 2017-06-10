package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.Constant.Constant;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.Fragment.AllScreen.AppllianceFragment;
import com.example.archi.homemaintenance.Fragment.AllScreen.EditApplianceFragment;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/20/2017.
 */

public class ApplianceListDetailFragment extends Fragment implements View.OnClickListener {

    public DbHelper db;
    public ImageView img;
    public TextView txtDate, txtBrand, txtBusiness, txtModel, txtSerial, txtPrice, txtCategory, txtNote, txtEdit, txtHeaderName;
    public String ApplianceId;
    public List<HashMap<String, String>> applianceDetailList;
    public HashMap<String, String> map = new HashMap<>();
    public byte[] bytesimage;
    public RelativeLayout layout;
    public ImageView imgHeaderBack;
    public Context context;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    public RelativeLayout headerview;

    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_appliance_listdetail, container, false);
        headerview=(RelativeLayout)getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.GONE);
        db = new DbHelper(getActivity());
        applianceDetailList = db.getAllApplianceDetails();
        findById(view);
        init();
        click();
        return view;
    }


    private void init() {
        if (getArguments() != null) {
            ApplianceId = getArguments().getString("applianceId");
            applianceDetailList.add(map);
            map = applianceDetailList.get(Integer.parseInt(ApplianceId) - 1);

            //for image
            String imageStr = map.get("appliance_image");
            Bitmap bitmap = BitmapFactory.decodeFile(imageStr);
            img.setImageBitmap(bitmap);

            //for text
            txtHeaderName.setText("User's " + map.get("appliance_category"));
            txtBrand.setText(map.get("appliance_brand"));
            txtModel.setText(map.get("appliance_model"));
            txtCategory.setText(map.get("appliance_category"));
            txtSerial.setText(map.get("appliance_serial"));
            txtPrice.setText("$" + map.get("appliance_price") + ".00");
            txtDate.setText(map.get("appliance_date"));
            txtBusiness.setText(map.get("appliance_relative_business"));
            txtNote.setText(map.get("appliance_notes"));
            Toast.makeText(getActivity(), "Detail List", Toast.LENGTH_SHORT).show();
        }
    }

    private void click() {
        txtEdit.setOnClickListener(this);
        imgHeaderBack.setOnClickListener(this);
        // img.setOnClickListener(this);
    }

    private void findById(View view) {
        txtHeaderName = (TextView) view.findViewById(R.id.header_tv_header_name);
        img = (ImageView) view.findViewById(R.id.fragment_appliance_listdetail_img);
        txtEdit = (TextView) view.findViewById(R.id.header_tv_edit);
        imgHeaderBack = (ImageView) view.findViewById(R.id.header_iv_back);
        txtBrand = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtbrand);
        txtModel = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtmodel);
        txtCategory = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtcategory);
        txtSerial = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtserial);
        txtPrice = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtprice);
        txtDate = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtdate);
        txtBusiness = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtbusiness);
        txtNote = (TextView) view.findViewById(R.id.fragment_appliance_listdetail_txtnotes);
        layout = (RelativeLayout) view.findViewById(R.id.header_appliance_edit);
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.header_iv_back:
                fragment = new AppllianceFragment();
                break;

            case R.id.header_tv_edit:

                fragment = new EditApplianceFragment();
                //put id
                Bundle bundle = new Bundle();
                bundle.putString("applianceId", map.get("appliance_id"));
                fragment.setArguments(bundle);
                Log.d("SET_ID", "" + map.get("appliance_id"));
                break;


        }
        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            // mDrawerLayout.closeDrawer(Gravity.LEFT);
        }

    }
}
