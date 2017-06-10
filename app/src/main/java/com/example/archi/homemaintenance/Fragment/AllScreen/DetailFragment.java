package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.archi.homemaintenance.Adapter.DetailAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/17/2017.
 */
public class DetailFragment extends Fragment {
    public RecyclerView recyclerViewDetail;
    public DetailAdapter adapater;
    public DbHelper db;
    public List<HashMap<String, String>> applianceDetailList;
    public RelativeLayout headerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_appliance_detail, container, false);
        headerView = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        init();
        findById(view);
        return view;
    }

    private void init() {

        db = new DbHelper(getActivity());
        applianceDetailList = db.getAllApplianceDetails();
    }


    private void findById(View view) {

        recyclerViewDetail = (RecyclerView) view.findViewById(R.id.fragment_appliance_detail_recycleview);
        adapater = new DetailAdapter(getActivity(), applianceDetailList, getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDetail.setLayoutManager(mLayoutManager);
        recyclerViewDetail.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDetail.setAdapter(adapater);
        adapater.notifyDataSetChanged();

    }
}
