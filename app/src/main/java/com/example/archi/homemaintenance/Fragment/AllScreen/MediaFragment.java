package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.archi.homemaintenance.R;

/**
 * Created by Ravi archi on 2/3/2017.
 */
public class MediaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_media_directory_vender_detail_list, container, false);
        Toast.makeText(getActivity(), "media", Toast.LENGTH_SHORT).show();

        return view;
    }
}
