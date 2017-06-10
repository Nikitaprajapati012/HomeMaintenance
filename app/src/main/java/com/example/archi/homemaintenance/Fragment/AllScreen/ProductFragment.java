package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.archi.homemaintenance.Adapter.DateAdapter;
import com.example.archi.homemaintenance.Adapter.ProductAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/17/2017.
 */
public class ProductFragment extends Fragment {
    public RecyclerView recyclerViewDate;
    public ProductAdapter adapater;
    public DbHelper db;
    public List<HashMap<String, String>> productDetailList;
    public HashMap<String, String> dateObj = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_receipts_product, container, false);
        init();
        findById(view);
        click();
        return view;
    }

    private void init() {

        db = new DbHelper(getActivity());
        productDetailList = db.getCountAllReceiptsDetails();
        // productDetailList.add(dateObj);
    }

    private void click() {

    }

    private void findById(View view) {

        recyclerViewDate = (RecyclerView) view.findViewById(R.id.fragment_receipts_product_recycleview);
        adapater = new ProductAdapter(getActivity(), productDetailList,getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDate.setLayoutManager(mLayoutManager);
        recyclerViewDate.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDate.setAdapter(adapater);
        adapater.notifyDataSetChanged();
    }
}
