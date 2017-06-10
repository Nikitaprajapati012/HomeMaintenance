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
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
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

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.Holder> {

    public List<HashMap<String, String>> dateDetailList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public String ID;
    public DbHelper db;

    public DateAdapter(Context context, List<HashMap<String, String>> dateDetailList, FragmentManager fm) {
        Log.d("Length", "" + dateDetailList.size());
        this.context = context;
        this.dateDetailList = dateDetailList;
        this.fm = fm;
        db= new DbHelper(context);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_date_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        map = dateDetailList.get(position);
        ID = map.get("receipts_id");
        holder.receiptsDate.setText(map.get("receipts_date"));
        holder.receiptsBusiness.setText(map.get("receipts_relative_business"));
        holder.receiptsCost.setText("$" + map.get("receipts_cost") + ".00 ");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        final HashMap<String, String> map = dateDetailList.get(position);
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

    private void openDetailList(int position) {
        final HashMap<String, String> map = dateDetailList.get(position);
        // Toast.makeText(context, "Click" + map.get("receipts_id") , Toast.LENGTH_SHORT).show();
        Fragment fragment = new ReceiptsListDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ReceiptsId", map.get("receipts_id"));

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
        return dateDetailList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_date_list_txtdate)
        TextView receiptsDate;
        @BindView(R.id.adapter_date_list_txtbusiness)
        TextView receiptsBusiness;
        @BindView(R.id.adapter_date_list_txtcost)
        TextView receiptsCost;
        @BindView(R.id.adapter_date_list_layout)
        LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




