package com.example.archi.homemaintenance.Adapter;

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

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.Fragment.AllScreen.DateFragment;
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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {

    public List<HashMap<String, String>> productDetailList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public int total;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public DbHelper db;
    public String ID;

    public ProductAdapter(Context context, List<HashMap<String, String>> productDetailList, FragmentManager fm) {
        Log.d("Length", "" + productDetailList.size());
        this.context = context;
        this.productDetailList = productDetailList;
        this.fm = fm;
        db = new DbHelper(context);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_product_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        map = productDetailList.get(position);
        ID = map.get("receipts_id");
        holder.receiptsProduct.setText(map.get("receipts_product"));
        holder.receiptsReceipts.setText(map.get("receipts"));
        holder.receiptsTotal.setText("$" + map.get("receipts_total") + ".00");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReceiptsList(position);
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
        final HashMap<String, String> map = productDetailList.get(position);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage("Are you sure you want to delete this receipt?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                db.deleteReceipts(ID);
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

    private void openReceiptsList(int position) {
        final HashMap<String, String> map = productDetailList.get(position);
        // Toast.makeText(context, "Click" + map.get("receipts_id") , Toast.LENGTH_SHORT).show();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString("ReceiptsId", map.get("receipts_id"));
        String rec = map.get("receipts");
        int r;
        if (rec == "1") {
            r = 0;
        } else {
            r = 1;
        }

        Log.d("ridput", "" + bundle);

        switch (r) {
            case 0:
                fragment = new ReceiptsListDetailFragment();
                fragment.setArguments(bundle);
                break;
            case 1:

                fragment = new DateFragment();
                fragment.setArguments(bundle);
                break;
        }

        if (fragment != null) {

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
        return productDetailList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {


        @BindView(R.id.adapter_product_list_txtproduct)
        TextView receiptsProduct;
        @BindView(R.id.adapter_product_list_txtreceipts)
        TextView receiptsReceipts;
        @BindView(R.id.adapter_product_list_txttotal)
        TextView receiptsTotal;
        @BindView(R.id.adapter_product_list_layout)
        LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




