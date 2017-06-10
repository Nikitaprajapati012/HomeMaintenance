package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.archi.homemaintenance.Adapter.MenualAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/17/2017.
 */
public class MenualFragment extends Fragment {
    public RecyclerView recyclerViewMenual;
    public MenualAdapter adapater;
    public DbHelper db;
    public List<HashMap<String, String>> applianceDetailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_appliance_menual, container, false);
        Toast.makeText(getActivity(), "Menual Fragment", Toast.LENGTH_SHORT).show();
        init();
        findById(view);

        return view;
    }

    private void init() {

        db = new DbHelper(getActivity());
        applianceDetailList = db.getAllApplianceDetails();
    }

    private void findById(View view) {

        recyclerViewMenual = (RecyclerView) view.findViewById(R.id.fragment_appliance_menual_recycleview);
        adapater = new MenualAdapter(getActivity(), applianceDetailList,getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMenual.setLayoutManager(mLayoutManager);
        recyclerViewMenual.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenual.setAdapter(adapater);
        adapater.notifyDataSetChanged();
    }
}
