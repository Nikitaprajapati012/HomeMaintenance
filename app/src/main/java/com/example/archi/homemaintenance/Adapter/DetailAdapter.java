package com.example.archi.homemaintenance.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.Fragment.AllScreen.ApplianceListDetailFragment;
import com.example.archi.homemaintenance.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archirayan on 27-Dec-16.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.Holder> {

    public List<HashMap<String, String>> detailApplianceList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public String ID;
    public Dialog dialog;
    public DbHelper db;



    public DetailAdapter(Context context, List<HashMap<String, String>> detailApplianceList, FragmentManager fm) {
        Log.d("Length", "" + detailApplianceList.size());
        this.context = context;
        this.detailApplianceList = detailApplianceList;
        this.fm = fm;
        db= new DbHelper(context);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_detail_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        map = detailApplianceList.get(position);
        ID = map.get("appliance_id");
        holder.applianceBrand.setText(map.get("appliance_brand"));
        holder.applianceModel.setText(map.get("appliance_model"));
        holder.applianceCategory.setText(map.get("appliance_category"));



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                openDetailList(position);

            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog(position);
                return true;
            }
        });
    }

    private void showDialog(int position) {
        final HashMap<String, String> map = detailApplianceList.get(position);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage("Are you sure you want to delete this receipt?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                db.deleteAppliance(ID);
                notifyDataSetChanged();
                //Toast.makeText(context, "Receipt deleted Sucessfully",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Toast.makeText(context, "You clicked on NO", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }


    private void openDetailList(int position) {
        final HashMap<String, String> map = detailApplianceList.get(position);
        // Toast.makeText(context, "Click" + map.get("receipts_id") , Toast.LENGTH_SHORT).show();
        Fragment fragment = new ApplianceListDetailFragment();
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
        return detailApplianceList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {


        @BindView(R.id.adapter_detail_list_txtmodel)
        TextView applianceModel;
        @BindView(R.id.adapter_detail_list_txtcategory)
        TextView applianceCategory;
        @BindView(R.id.adapter_detail_list_txtbrand)
        TextView applianceBrand;
        @BindView(R.id.adapter_detail_list_layout)
        LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




