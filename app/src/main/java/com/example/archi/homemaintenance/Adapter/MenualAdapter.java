package com.example.archi.homemaintenance.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.Fragment.AllScreen.ReceiptsListDetailFragment;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archirayan on 27-Dec-16.
 */

public class MenualAdapter extends RecyclerView.Adapter<MenualAdapter.Holder> implements View.OnClickListener {

    public List<HashMap<String, String>> menualApplianceList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public String ID;


    public MenualAdapter(Context context, List<HashMap<String, String>> menualApplianceList, FragmentManager fm) {
        Log.d("Length", "" + menualApplianceList.size());
        this.context = context;
        this.menualApplianceList = menualApplianceList;
        this.fm = fm;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_menual_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        map = menualApplianceList.get(position);
        ID = map.get("appliance_id");
        holder.applianceBrand.setText(map.get("appliance_brand"));
        holder.applianceModel.setText(map.get("appliance_model"));
        holder.applianceCategory.setText(map.get("appliance_category"));
        holder.appliancePdf.setOnClickListener(this);
        holder.linearLayout.setOnClickListener(this);


    }

    private void openDetailList(int position) {
        final HashMap<String, String> map = menualApplianceList.get(position);
        // Toast.makeText(context, "Click" + map.get("receipts_id") , Toast.LENGTH_SHORT).show();
        Fragment fragment = new ReceiptsListDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("applianceId", map.get("appliance_id"));

        if (fragment != null) {
            fragment.setArguments(bundle);
            transaction = fm.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return menualApplianceList.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.adapter_menual_list_txtpdf:
                break;
            case R.id.adapter_menual_list_layout:
                Toast.makeText(context, "menualclick", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public class Holder extends RecyclerView.ViewHolder {


        @BindView(R.id.adapter_menual_list_txtmodel)
        TextView applianceModel;
        @BindView(R.id.adapter_menual_list_txtcategory)
        TextView applianceCategory;
        @BindView(R.id.adapter_menual_list_txtbrand)
        TextView applianceBrand;
        @BindView(R.id.adapter_menual_list_txtpdf)
        TextView appliancePdf;
        @BindView(R.id.adapter_menual_list_layout)
        LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




