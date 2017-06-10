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

import com.example.archi.homemaintenance.Adapter.DateAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/17/2017.
 */
public class DateFragment extends Fragment {
    public RecyclerView recyclerViewDate;
    public DateAdapter adapater;
    public DbHelper db;
    public List<HashMap<String, String>> dateDetailList;
    public RelativeLayout headerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_receipts_date, container, false);
        headerView = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerView.setVisibility(View.VISIBLE);
        init();
        findById(view);
        return view;
    }

    private void init() {

        db = new DbHelper(getActivity());
        dateDetailList = db.getAllReceiptsDetails();
        // dateDetailList.add(dateObj);
    }

    private void findById(View view) {

        recyclerViewDate = (RecyclerView) view.findViewById(R.id.fragment_receipts_date_recycleview);
        adapater = new DateAdapter(getActivity(), dateDetailList,getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDate.setLayoutManager(mLayoutManager);
        recyclerViewDate.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDate.setAdapter(adapater);
        adapater.notifyDataSetChanged();
    }
}
