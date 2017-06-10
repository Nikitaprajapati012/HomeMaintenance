package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.archi.homemaintenance.Constant.Validation;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/20/2017.
 */
public class EditVendorFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public ImageView imgHeaderBack;
    public Button btnSubmit;
    public DbHelper db;
    public EditText edCompanyName, edContactName, edContactNumber, edEmail, edWebsite, edComment;
    public String VendorId, ContactNumber, ContactName, CompanyName, Email, Website, Category, Comment;
    public Dialog dialog;
    public List<HashMap<String, String>> detailList;
    public HashMap<String, String> map = new HashMap<>();
    public RelativeLayout headerview;
    public String[] spinnerList;
    public Spinner spinnerCategories;
    public ImageView headerBack, imgCall;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_vender_editlistdetail, container, false);
        headerview = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.GONE);
        imgHeaderBack = (ImageView) getActivity().findViewById(R.id.header_iv_back);
        finById(view);
        init();
        click();
        return view;
    }

    // TODO: 2/14/2017  get data from activity
    private void init() {

        db = new DbHelper(getActivity());
        detailList = db.getAllVendorDetails();

        if (getArguments() != null) {
            VendorId = getArguments().getString("VendorId");
            detailList.add(map);
            map = detailList.get(Integer.parseInt(VendorId) - 1);
            ContactNumber = map.get("vendor_contact");
            CompanyName = map.get("vendor_company_name");
            ContactName = map.get("vendor_contact_name");
            Email = map.get("vendor_email");
            Website = map.get("vendor_comapany_website");
            Comment = map.get("vendor_Comment");
            Category = map.get("vendor_add_category");
            //spinnerList.add(Category);
            edContactName.setText(ContactName);
            edCompanyName.setText(CompanyName);
            edContactNumber.setText(ContactNumber);
            edEmail.setText(Email);
            edWebsite.setText(Website);
            edComment.setText(Comment);

        }

    }

    // TODO: 2/14/2017 on click event 
    private void click() {
        btnSubmit.setOnClickListener(this);
        headerBack.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        spinnerCategories.setOnItemSelectedListener(this);
    }

    // TODO: 2/14/2017 bind data 
    private void finById(View view) {
        headerBack = (ImageView) view.findViewById(R.id.header_iv_back_vendor);
        imgCall = (ImageView) view.findViewById(R.id.fragment_vendor_editlistdetail_imgcall);
        edCompanyName = (EditText) view.findViewById(R.id.fragment_vendor_editlistdetail_edcompanyname);
        edContactName = (EditText) view.findViewById(R.id.fragment_vendor_editlistdetail_edcontactname);

        spinnerCategories = (Spinner) view.findViewById(R.id.fragment_vendor_editlistdetail_spinnercategory);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(spinnerCategories.getWindowToken(), 0);

        spinnerList = getResources().getStringArray(R.array.categories);
        edContactNumber = (EditText) view.findViewById(R.id.fragment_vendor_editlistdetail_edcontactnum);
        edEmail = (EditText) view.findViewById(R.id.fragment_vendor_editlistdetail_edemail);

        edWebsite = (EditText) view.findViewById(R.id.fragment_vendor_editlistdetail_edcompanywebsite);
        edComment = (EditText) view.findViewById(R.id.fragment_vendor_editlistdetail_edcomment);
        btnSubmit = (Button) view.findViewById(R.id.fragment_vendor_editlistdetail_btn_submit);
    }

    @Override
    public void onClick(View v) {
        // TODO: 2/13/2017 get data
        ContactName = edContactName.getText().toString();
        ContactNumber = edContactNumber.getText().toString();
        CompanyName = edCompanyName.getText().toString();
        Log.d("cn", "" + edCompanyName.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("business", CompanyName);
        getActivity().setResult(2, intent);
        Email = edEmail.getText().toString();
        Website = edWebsite.getText().toString();
        Comment = edComment.getText().toString();
        switch (v.getId()) {
            case R.id.fragment_vendor_editlistdetail_imgcall:
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
                break;

            case R.id.header_iv_back_vendor:
                getActivity().onBackPressed();
                break;

            case R.id.fragment_vendor_editlistdetail_btn_submit:
                updateVendorDetail();
               // getActivity().onBackPressed();
                break;
        }
    }

    // TODO: 2/14/2017 update details
    private void updateVendorDetail() {
        if (!CompanyName.equalsIgnoreCase("")) {
            if (!ContactName.equalsIgnoreCase("")) {
                if (!Category.equalsIgnoreCase("") && (spinnerCategories.getSelectedItemPosition() > 1)) {
                    if (!ContactNumber.equalsIgnoreCase("")) {
                        if (!Email.equalsIgnoreCase("") && Validation.isEmailAddress(Email)) {
                            if (!Website.equalsIgnoreCase("") && Validation.isWebsite(Website)) {
                                //if (!Comment.equalsIgnoreCase("")) {
                                db.updateVendor(VendorId,CompanyName, ContactName, Category, ContactNumber, Email, Website, Comment);
                                Toast.makeText(getActivity(), "Vendor details Update Sucessfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Please Enter website name", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Please Enter the Contact Number", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Please Enter Category", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please Enter Company Name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Category = spinnerCategories.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

