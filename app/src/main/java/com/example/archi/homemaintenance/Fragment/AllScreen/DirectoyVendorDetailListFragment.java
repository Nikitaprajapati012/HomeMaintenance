package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/20/2017.
 */

public class DirectoyVendorDetailListFragment extends Fragment implements View.OnClickListener {

    public DbHelper db;
    public ImageView imgCall, imgEmail, imgWebsite;
    public TextView txtContactName, txtCompanyName, txtBusiness, txtModel, txtSerial, txtPrice, txtCategory, txtNote, txtEdit, txtHeaderName;
    public String VenderId, ContactNumber, CompanyName;
    public List<HashMap<String, String>> directoryVenderListdetail;
    public HashMap<String, String> map = new HashMap<>();
    public byte[] bytesimage;
    public ImageView imgHeaderBack;
    public Context context;
    public RelativeLayout headerview, headerVenderlist;
    public FrameLayout frameLayout;
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;
    public Fragment fragment;
    public TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_directory_vender_detail_list, container, false);
        headerview = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.GONE);
        imgHeaderBack = (ImageView) getActivity().findViewById(R.id.header_iv_back);
        txtEdit = (TextView) getActivity().findViewById(R.id.header_tv_edit);
        db = new DbHelper(getActivity());
        directoryVenderListdetail = db.getAllVendorDetails();

        findById(view);
        init();
        tabLayoutPerform(view);
        click();
        return view;

    }

    private void tabLayoutPerform(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        frameLayout = (FrameLayout) view.findViewById(R.id.fragment_directory_vender_detail_list_framelayout);
        tabLayout.addTab(tabLayout.newTab().setText("About Us"));
        tabLayout.addTab(tabLayout.newTab().setText("Media"));
        tabLayout.addTab(tabLayout.newTab().setText("Promos"));
        tabLayout.addTab(tabLayout.newTab().setText("Map"));
        tabLayout.setTabTextColors(getResources().getColor(R.color.background),
                getResources().getColor(R.color.blue_light));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Bundle bundle = new Bundle();
                fragment = null;
                fragmentManager = getActivity().getSupportFragmentManager();
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new AboutUsFragment();
                        bundle.putString("VendorId", VenderId);

                        break;
                    case 1:
                        fragment = new MediaFragment();
                        break;
                    case 2:
                        fragment = new PromosFragment();
                        break;
                    case 3:
                        fragment = new MapFragment();
                        break;
                    default:
                        fragment = new AboutUsFragment();
                        break;

                }
                if (fragment != null) {
                    fragment.setArguments(bundle);
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment_directory_vender_detail_list_framelayout, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void init() {
        if (getArguments() != null) {
            VenderId = getArguments().getString("VendorId");
            directoryVenderListdetail.add(map);
            map = directoryVenderListdetail.get(Integer.parseInt(VenderId) - 1);
            ContactNumber = map.get("vendor_contact");
            CompanyName = map.get("vendor_company_name");
            txtContactName.setText(map.get("vendor_contact_name"));
            txtCompanyName.setText(CompanyName);
            txtCategory.setText(map.get("vendor_add_category"));
        }
    }

    private void click() {
        txtEdit.setOnClickListener(this);
        imgHeaderBack.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgEmail.setOnClickListener(this);
        imgWebsite.setOnClickListener(this);
    }

    private void findById(View view) {
        headerVenderlist = (RelativeLayout) view.findViewById(R.id.header_vender_list);
        txtHeaderName = (TextView) view.findViewById(R.id.header_tv_header_name);
        txtHeaderName.setText("Vender Details");
        txtEdit = (TextView) view.findViewById(R.id.header_tv_edit);
        imgHeaderBack = (ImageView) view.findViewById(R.id.header_iv_back);
        txtContactName = (TextView) view.findViewById(R.id.fragment_directory_vender_detail_list_txtcontactname);
        txtCompanyName = (TextView) view.findViewById(R.id.fragment_directory_vender_detail_list_txtcompanyname);
        txtCategory = (TextView) view.findViewById(R.id.fragment_directory_vender_detail_list_txtcategory);
        imgCall = (ImageView) view.findViewById(R.id.fragment_directory_vender_detail_list_imgcall);
        imgEmail = (ImageView) view.findViewById(R.id.fragment_directory_vender_detail_list_imgemail);
        imgWebsite = (ImageView) view.findViewById(R.id.fragment_directory_vender_detail_list_imgwebsite);

        /*txtCost = (TextView) view.findViewById(R.id.adapter_list_detail_txtcost);
            txtNote = (TextView) view.findViewById(R.id.adapter_list_detail_txtnotes);
            layout = (RelativeLayout) view.findViewById(R.id.header_receipts_edit);
            */
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();

        switch (v.getId()) {
            case R.id.header_iv_back:
                getActivity().onBackPressed();
                break;
            case R.id.fragment_directory_vender_detail_list_imgcall:
                showDialog();
                break;

            case R.id.fragment_directory_vender_detail_list_imgemail:
                Toast.makeText(getActivity(), "email", Toast.LENGTH_SHORT).show();

                break;
            case R.id.fragment_directory_vender_detail_list_imgwebsite:

                Uri uri = Uri.parse("http://" + map.get("vendor_comapany_website"));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.header_tv_edit:

                fragment = new EditVendorFragment();
                Bundle bundle = new Bundle();
                bundle.putString("VendorId", map.get("vendor_id"));
                fragment.setArguments(bundle);
                break;
        }
        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    private void showDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage(CompanyName + "\nCall " + ContactNumber + "?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + ContactNumber));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }

}
