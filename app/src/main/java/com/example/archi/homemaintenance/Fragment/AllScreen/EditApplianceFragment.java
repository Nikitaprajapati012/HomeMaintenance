package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.Activity.ActivityAddVendorManually;
import com.example.archi.homemaintenance.Activity.ActivityChooseFromContact;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 1/20/2017.
 */
public class EditApplianceFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public TextView txtAddNewVendor, txtAddNewCategory;
    public ImageView imgHeaderBack;
    public Button btnSubmit, btnCancel, btnChooseContact, btnAddVendorMenual;
    public DbHelper db;
    public EditText edDate, edModel, edBrand, edNote, edSerial, edPrice;
    public String getCategory,applianceId, Date, Brand, RelativeBusiness, SelectCategory, Model, Notes, Serial, Price;
    public Spinner spinnerRelativeBusiness, spinnerAddCategory;
    public Dialog dialog;
    public List<HashMap<String, String>> applianceDetailList;
    public HashMap<String, String> map = new HashMap<>();
    public RelativeLayout headerAppliance;
    public ArrayList<String> spinnerList = new ArrayList<>();
    public ArrayList<String> spinnerCategoryList = new ArrayList<>();
    public ArrayAdapter<String> spinnerAdapter;
    public String[] categoryList;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_appliance_editlistdetail, container, false);

        finById(view);
        init();
        click();
        return view;
    }

    private void init() {
        db = new DbHelper(getActivity());
        applianceDetailList = db.getAllApplianceDetails();
        if (getArguments() != null) {
            applianceId = getArguments().getString("applianceId");
            applianceDetailList.add(map);
            map = applianceDetailList.get(Integer.parseInt(applianceId) - 1);
            //for business
            String business = map.get("appliance_relative_business");
            spinnerList.add(business);
            spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerRelativeBusiness.setAdapter(spinnerAdapter);
            spinnerAdapter.notifyDataSetChanged();



            //for text
            edBrand.setText(map.get("appliance_brand"));
            edModel.setText(map.get("appliance_model"));
            Brand = map.get("appliance_brand");
            Model = map.get("appliance_model");
            edSerial.setText(map.get("appliance_serial"));
            edPrice.setText(map.get("appliance_price"));
            edDate.setText(map.get("appliance_date"));
            edNote.setText(map.get("appliance_notes"));
        }
    }

    private void click() {

        btnSubmit.setOnClickListener(this);
        imgHeaderBack.setOnClickListener(this);
        edDate.setOnClickListener(this);
        txtAddNewVendor.setOnClickListener(this);
        spinnerRelativeBusiness.setOnItemSelectedListener(this);
        spinnerAddCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SelectCategory = spinnerAddCategory.getItemAtPosition(position).toString();
                getCategory = map.get("appliance_category");
                spinnerCategoryList.add(getCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void finById(View view) {
        imgHeaderBack = (ImageView) view.findViewById(R.id.header_iv_back);
        edBrand = (EditText) view.findViewById(R.id.fragment_appliance_editlistdetail_edbrand);
        edModel = (EditText) view.findViewById(R.id.fragment_appliance_editlistdetail_edModel);
        spinnerAddCategory = (Spinner) view.findViewById(R.id.fragment_appliance_editlistdetail_spinnercategory);
        edSerial = (EditText) view.findViewById(R.id.fragment_appliance_editlistdetail_edSerial);
        edPrice = (EditText) view.findViewById(R.id.fragment_appliance_editlistdetail_edPrice);
        edDate = (EditText) view.findViewById(R.id.fragment_appliance_editlistdetail_ed_bought_date);
        spinnerRelativeBusiness = (Spinner) view.findViewById(R.id.fragment_appliance_editlistdetail_releted_spinnerbusiness_service_pro);
        txtAddNewVendor = (TextView) view.findViewById(R.id.fragment_appliance_editlistdetail_tv_add_vendor);
        edNote = (EditText) view.findViewById(R.id.fragment_appliance_editlistdetail_ednotes);
        headerAppliance = (RelativeLayout) view.findViewById(R.id.header_receipts_edit);
        btnSubmit = (Button) view.findViewById(R.id.fragment_appliance_editlistdetail_btnsubmit);

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();

        switch (v.getId()) {

            case R.id.fragment_appliance_editlistdetail_tv_add_vendor:

                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_vendor);
                dialog.setTitle("");
                btnCancel = (Button) dialog.findViewById(R.id.activity_add_vendor_btn_cancel);
                btnChooseContact = (Button) dialog.findViewById(R.id.activity_add_vendor_btn_choosecontact);
                btnAddVendorMenual = (Button) dialog.findViewById(R.id.activity_add_vendor_btn_addvencer);
                dialog.show();
                btnAddVendorMenual.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent iAddVendor = new Intent(getActivity(), ActivityAddVendorManually.class);
                        startActivityForResult(iAddVendor, 2);
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnChooseContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent icontact = new Intent(getActivity(), ActivityChooseFromContact.class);
                        startActivity(icontact);
                    }
                });

                break;

            case R.id.fragment_appliance_editlistdetail_ed_bought_date:
                openDate();
                break;
            case R.id.header_iv_back:
                fragment = new AppllianceFragment();
                break;

            case R.id.fragment_appliance_editlistdetail_btnsubmit:

                updateapplianceDetailList();
                fragment = new AppllianceFragment();
                break;
        }
        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String message = data.getStringExtra("business");
            spinnerList.add(message);
            spinnerAdapter.notifyDataSetChanged();
        }
        /*if (requestCode == 1) {
            String message = data.getStringExtra("category");
            spinnerCategoryList.add(message);
            spinnerAdapter.notifyDataSetChanged();
        }*/
    }

    private void updateapplianceDetailList() {

        Date = edDate.getText().toString().trim();
        // Brand = edBrand.getText().toString().trim();
        // Model = edModel.getText().toString().trim();
        Serial = edSerial.getText().toString().trim();
        Price = edPrice.getText().toString().trim();
        Notes = edNote.getText().toString().trim();


        if (!Date.equalsIgnoreCase("")) {

            if (!Serial.equalsIgnoreCase("")) {
                if (!Price.equalsIgnoreCase("")) {

                    if (!RelativeBusiness.equalsIgnoreCase("")) {

                        db.updateAppliance(applianceId, Brand, Model, SelectCategory, Serial, Price, Date, RelativeBusiness, Notes);

                        Log.d("UpdateData", "" + Date + Brand + Model + RelativeBusiness + Notes);
                        Toast.makeText(getActivity(), "Update Data Sucessfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "please enter the Relative Business or Service", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "please enter the Price", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "please enter the Serial", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "please enter the Date", Toast.LENGTH_SHORT).show();

        }
    }

    private void openDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = 1990;
        int mMonth = 01;
        int mDay = 01;


        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String year1 = String.valueOf(year);
                        String month1 = String.valueOf(monthOfYear + 1);
                        String day1 = String.valueOf(dayOfMonth);

                        edDate.setText(day1 + "-" + month1 + "-" + year1);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        RelativeBusiness = spinnerRelativeBusiness.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

