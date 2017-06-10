package com.example.archi.homemaintenance.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.archi.homemaintenance.Fragment.AllScreen.VendorListFragment;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archirayan on 27-Dec-16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    public List<HashMap<String, String>> categoryDetailList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public String ID, Request, ContentTitle, Content, Content1, Content2, Thanks;

    public CategoryAdapter(Context context, List<HashMap<String, String>> categoryDetailList, FragmentManager fm) {
        Log.d("Length", "" + categoryDetailList.size());
        this.context = context;
        this.categoryDetailList = categoryDetailList;
        this.fm = fm;
        notifyDataSetChanged();

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_category_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        map = categoryDetailList.get(position);
        ID = map.get("vendor_id");

        holder.directoryCategoryName.setText(map.get("vendor_add_category"));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (map.get("vendor_add_category") != null) {
                    openVendorList(position);
                } else {
                    //Toast.makeText(context, "not in area ", Toast.LENGTH_SHORT).show();
                    openDialog();
                }
            }
        });
    }

    private void openDialog() {
        Request = context.getString(R.string.requestvendor);
        ContentTitle = context.getString(R.string.requesttitle);
        Content = context.getString(R.string.requestcontent);
        Content1 = context.getString(R.string.requestcontent1);
        Content2 = context.getString(R.string.requestcontent2);
        Thanks = context.getString(R.string.thanks);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.request);
        alertDialog.setMessage(Request);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"support@homemainteneance.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, map.get("vendor_add_category") + " Specialist Recommendation");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, ContentTitle + "\n\n" + Content + " " + map.get("vendor_add_category") + " " + Content1 + Content2 + " 384001." + "\n\n" + Thanks);
                context.startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }


    private void openVendorList(int position) {
        final HashMap<String, String> map = categoryDetailList.get(position);
        Fragment fragment = new VendorListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("COUNTVendorId", map.get("vendor_id"));
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
        return categoryDetailList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        /*  @BindView(R.id.adapter_date_list_txtbusiness)
          TextView receiptsBusiness;*/
        @BindView(R.id.adapter_category_list_txtcategoryname)
        TextView directoryCategoryName;
        @BindView(R.id.adapter_categorylist_layout)
        LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




