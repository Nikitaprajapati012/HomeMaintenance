package com.example.archi.homemaintenance.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.archi.homemaintenance.Fragment.AllScreen.HomeMainFragment;
import com.example.archi.homemaintenance.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by archirayan on 27-Dec-16.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {

    public List<HashMap<String, String>> homeList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> map;
    public FragmentManager fm;
    public FragmentTransaction transaction;
    public String ID;


    public HomeAdapter(Context context, List<HashMap<String, String>> homeList, FragmentManager fm) {
        Log.d("Length", "" + homeList.size());
        this.context = context;
        this.homeList = homeList;
        this.fm = fm;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        map = homeList.get(position);
        ID = map.get("addhome_id");

        //holder.homeImage.setImageBitmap(map.get("addhome_image"));
       // String message = date.getStringExtra("imagepath");
        String message = map.get("addhome_image");
        Bitmap bitmap = BitmapFactory.decodeFile(message);
        holder.homeImage.setImageBitmap(bitmap);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                //openhomeList(position);

            }
        });
    }

    private void openhomeList(int position) {
        final HashMap<String, String> map = homeList.get(position);
        // Toast.makeText(context, "Click" + map.get("receipts_id") , Toast.LENGTH_SHORT).show();
        Fragment fragment = new HomeMainFragment();
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
        return homeList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_home_list_imghome)
        ImageView homeImage;
        @BindView(R.id.adapter_home_list_layout)
        RelativeLayout linearLayout;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}




