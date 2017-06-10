package com.example.archi.homemaintenance.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.archi.homemaintenance.Constant.Validation;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ActivityAddVendorManually extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public EditText edCompanyName, edContactName,/* edAddCategory, */
            edContactNumber, edEmail, edCompanyWebsite, edComments;
    public Button btnSubmit;
    public DbHelper db;
    public Spinner spinnerCategories;
    public ImageView headerBack, imgCall;
    public String VendorId, CompanyName, ContactName, AddCategory, ContactNumber, Email, CompanyWebsite, Comments;
    public List<HashMap<String, String>> vendorList;
    public HashMap<String, String> map;
    public String[] spinnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor_manually);
        db = new DbHelper(this);
        vendorList = new ArrayList<>();
        vendorList.add(map);
        findById();
        init();
        click();
    }


    // TODO: 2/13/2017 get from previous activity 
    private void init() {

        if (getIntent().getExtras() != null) {
            VendorId = getIntent().getExtras().getString("ContactsId");
            ContactName = getIntent().getExtras().getString("ContactsName");
            ContactNumber = getIntent().getExtras().getString("ContactsNumber");
            edContactName.setText(ContactName);
            edContactNumber.setText(ContactNumber);
        }
    }

    // TODO: 2/13/2017 on click event 
    private void click() {
        btnSubmit.setOnClickListener(this);
        headerBack.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        spinnerCategories.setOnItemSelectedListener(this);
    }

    // TODO: 2/13/2017 bind data 
    private void findById() {

        headerBack = (ImageView) findViewById(R.id.header_iv_back_vendor);
        imgCall = (ImageView) findViewById(R.id.activity_add_vendor_manually_imgcall);
        edCompanyName = (EditText) findViewById(R.id.activity_add_vendor_manually_edcompanyname);
        edContactName = (EditText) findViewById(R.id.activity_add_vendor_manually_edcontactname);

        spinnerCategories = (Spinner) findViewById(R.id.activity_add_vendor_manually_spinnercategory);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(spinnerCategories.getWindowToken(), 0);

        spinnerList = getResources().getStringArray(R.array.categories);
        edContactNumber = (EditText) findViewById(R.id.activity_add_vendor_manually_edcontactnum);
        edEmail = (EditText) findViewById(R.id.activity_add_vendor_manually_edemail);

        edCompanyWebsite = (EditText) findViewById(R.id.activity_add_vendor_manually_edcompanywebsite);
        edComments = (EditText) findViewById(R.id.activity_add_vendor_manually_edcomment);
        btnSubmit = (Button) findViewById(R.id.activity_add_vendor_manually_btn_submit);

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
        setResult(2, intent);
        Email = edEmail.getText().toString();
        CompanyWebsite = edCompanyWebsite.getText().toString();
        Comments = edComments.getText().toString();

        switch (v.getId()) {

            case R.id.activity_add_vendor_manually_imgcall:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + ContactNumber));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
                finish();
                break;

            case R.id.activity_add_vendor_manually_btn_submit:
                addVendorDetail();
                break;
        }
    }
    private void addVendorDetail() {
        if (!CompanyName.equalsIgnoreCase("")) {
            if (!ContactName.equalsIgnoreCase("")) {
                if (!AddCategory.equalsIgnoreCase("") && (spinnerCategories.getSelectedItemPosition() > 0)) {
                    if (!ContactNumber.equalsIgnoreCase("")) {
                        if (!Email.equalsIgnoreCase("") && Validation.isEmailAddress(Email)) {
                            if (!CompanyWebsite.equalsIgnoreCase("") && Validation.isWebsite(CompanyWebsite)) {
                                //if (!Comments.equalsIgnoreCase("")) {
                                Log.d("Insert", "Inserting...");
                                db.addVendor(CompanyName, ContactName, AddCategory, ContactNumber, Email, CompanyWebsite, Comments);
                                Toast.makeText(this, "Vendor details Add Sucessfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ActivityAddVendorManually.this, "please enter website name", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityAddVendorManually.this, "please enter Email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityAddVendorManually.this, "please enter the Contact Number", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ActivityAddVendorManually.this, "please enter Category", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ActivityAddVendorManually.this, "please enter Contact Name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActivityAddVendorManually.this, "please enter Company Name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        AddCategory = spinnerCategories.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

