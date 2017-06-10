package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/20/2017.
 */

public class ReceiptsListDetailFragment extends Fragment implements View.OnClickListener {

    public DbHelper db;
    public ImageView img;
    public TextView txtDate, txtProduct, txtBusiness, txtCost, txtNote, txtEdit;
    public String receiptsId;
    public List<HashMap<String, String>> detailList;
    public HashMap<String, String> map = new HashMap<>();
    // public HashMap<String, byte[]> byteimage = new HashMap<>();
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
        View view = inflater.inflate(R.layout.fragment_receipts_listdetail, container, false);
        headerview=(RelativeLayout)getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.GONE);
        db = new DbHelper(getActivity());
        detailList = db.getAllReceiptsDetails();
        findById(view);
        init();
        click();
        return view;
    }


    private void init() {
        if (getArguments() != null) {

            receiptsId = getArguments().getString("ReceiptsId");
            Log.d("ridget","" + receiptsId);
            detailList.add(map);
            map = detailList.get(Integer.parseInt(receiptsId)-1);

            //for image
            String imageStr = map.get("receipts_image");
            Bitmap bitmap = BitmapFactory.decodeFile(imageStr);
            img.setImageBitmap(bitmap);

            //for text
            txtDate.setText(map.get("receipts_date"));
            txtProduct.setText(map.get("receipts_product"));
            txtBusiness.setText(map.get("receipts_relative_business"));
            txtCost.setText("$" + map.get("receipts_cost") + ".00");
            txtNote.setText(map.get("receipts_notes"));
            Toast.makeText(getActivity(), "Detail List", Toast.LENGTH_SHORT).show();
        }
    }


    private void click() {
        txtEdit.setOnClickListener(this);
        imgHeaderBack.setOnClickListener(this);
       // img.setOnClickListener(this);
    }

    private void findById(View view) {

        img = (ImageView) view.findViewById(R.id.fragment_receipts_listdetail_img);
        txtEdit = (TextView) view.findViewById(R.id.header_tv_edit);
        imgHeaderBack = (ImageView) view.findViewById(R.id.header_iv_back);
        txtDate = (TextView) view.findViewById(R.id.fragment_receipts_list_detail_txtdate);
        txtProduct = (TextView) view.findViewById(R.id.fragment_receipts_list_detail_txtproduct);
        txtBusiness = (TextView) view.findViewById(R.id.fragment_receipts_list_detail_txtbusiness);
        txtCost = (TextView) view.findViewById(R.id.fragment_receipts_list_detail_txtcost);
        txtNote = (TextView) view.findViewById(R.id.fragment_receipts_list_detail_txtnotes);
        layout = (RelativeLayout) view.findViewById(R.id.header_receipts_edit);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();

        switch (v.getId()) {
            case R.id.header_iv_back:
                fragment = new ReceiptsFragment();

                break;

            case R.id.header_tv_edit:


              fragment = new EditReceiptsFragment();
              // put id
               Bundle bundle = new Bundle();
               bundle.putString("ReceiptsId", map.get("receipts_id"));

               fragment.setArguments(bundle);
                Log.d("SET_ID", "" + map.get("receipts_id"));
                break;

        }
        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }


}
