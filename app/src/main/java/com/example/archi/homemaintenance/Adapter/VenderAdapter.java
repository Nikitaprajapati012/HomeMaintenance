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

import com.example.archi.homemaintenance.Fragment.AllScreen.DirectoyVendorDetailListFragment;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archirayan on 27-Dec-16.
 */
public class VenderAdapter extends RecyclerView.Adapter<VenderAdapter.Holder> {

    public List<HashMap<String, String>> vendorDetailList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public String ID;

    public VenderAdapter(Context context, List<HashMap<String, String>> vendorDetailList, FragmentManager fm) {
        Log.d("Length", "" + vendorDetailList.size());
        this.context = context;
        this.vendorDetailList = vendorDetailList;
        this.fm = fm;

        notifyDataSetChanged();


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_vender_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        map = vendorDetailList.get(position);
       // ID = map.get("Countvendor_id");
        holder.directoryVendorName.setText(map.get("Count_vendor_company_name"));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openVendorList(position);
            }
        });
    }

    private void openVendorList(int position) {
        final HashMap<String, String> map = vendorDetailList.get(position);
        // Toast.makeText(context, "Click" + map.get("receipts_id") , Toast.LENGTH_SHORT).show();
        Fragment fragment = new DirectoyVendorDetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("VendorId", map.get("Count_vendor_id"));

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
        return vendorDetailList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        /*  @BindView(R.id.adapter_date_list_txtbusiness)
          TextView receiptsBusiness;*/
        @BindView(R.id.adapter_vender_list_txtcategoryname)
        TextView directoryVendorName;
        @BindView(R.id.adapter_categorylist_layout)
        LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




