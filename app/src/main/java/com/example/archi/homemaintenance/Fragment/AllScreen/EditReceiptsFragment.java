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
public class EditReceiptsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public TextView txtEdit;
    public ImageView imgHeaderBack;
    public Button btnSubmit, btnCancel, btnChooseContact, btnAddVendorMenual;
    public DbHelper db;
    public EditText edDate, edProduct, edCost, edRelativeBusiness, edNote;
    public String ReceiptsId, Date, Product, Cost, RelativeBusiness, Notes;
    public TextView txtAddNewVendor;
    public Dialog dialog;
    public List<HashMap<String, String>> detailList;
    public HashMap<String, String> map = new HashMap<>();
    public ArrayAdapter<String> spinnerAdapter;
    public Spinner spinnerRelativeBusiness;
    public ArrayList<String> spinnerList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_receipts_editlistdetail, container, false);

        finById(view);
        init();
        click();
        return view;
    }

    private void init() {

        db = new DbHelper(getActivity());
        detailList = db.getAllReceiptsDetails();

        if (getArguments() != null) {
            ReceiptsId = getArguments().getString("ReceiptsId");
            detailList.add(map);
            map = detailList.get(Integer.parseInt(ReceiptsId) - 1);
            String message = map.get("receipts_relative_business");
            spinnerList.add(message);
            spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerRelativeBusiness.setAdapter(spinnerAdapter);
            spinnerAdapter.notifyDataSetChanged();
            edDate.setText(map.get("receipts_date"));
            edProduct.setText(map.get("receipts_product"));
            edCost.setText(map.get("receipts_cost"));
            edNote.setText(map.get("receipts_notes"));
        }
    }

    private void click() {
        btnSubmit.setOnClickListener(this);
        imgHeaderBack.setOnClickListener(this);
        edDate.setOnClickListener(this);
        txtAddNewVendor.setOnClickListener(this);
        spinnerRelativeBusiness.setOnItemSelectedListener(this);
    }

    private void finById(View view) {
        edDate = (EditText) view.findViewById(R.id.fragment_receipts_editlistdetail_ed_bought_date);
        edProduct = (EditText) view.findViewById(R.id.fragment_receipts_editlistdetail_edproduct_and_services);
        edCost = (EditText) view.findViewById(R.id.fragment_receipts_editlistdetail_edcost);
        spinnerRelativeBusiness = (Spinner) view.findViewById(R.id.fragment_receipts_editlistdetail_spinnerbusiness_service_pro);
        //edRelativeBusiness = (EditText) view.findViewById(R.id.fragment_receipts_editlistdetail_edbusiness_service_pro);
        edNote = (EditText) view.findViewById(R.id.fragment_receipts_editlistdetail_ednotes);
        txtAddNewVendor = (TextView) view.findViewById(R.id.fragment_receipts_editlistdetail_tv_add_vendor);
        txtEdit = (TextView) view.findViewById(R.id.header_tv_edit);
        imgHeaderBack = (ImageView) view.findViewById(R.id.header_iv_back);
        btnSubmit = (Button) view.findViewById(R.id.fragment_receipts_editlistdetail_btnsubmit);

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragmentManager = getActivity().getSupportFragmentManager();

        switch (v.getId()) {
            case R.id.fragment_receipts_editlistdetail_tv_add_vendor:

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
                        dialog.dismiss();
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
                        dialog.dismiss();
                        Intent icontact = new Intent(getActivity(), ActivityChooseFromContact.class);
                        startActivity(icontact);

                    }
                });
                break;

            case R.id.fragment_receipts_editlistdetail_ed_bought_date:
                openDate();
                break;
            case R.id.header_iv_back:
                fragment = new ReceiptsFragment();
                break;

            case R.id.fragment_receipts_editlistdetail_btnsubmit:

                updateDetailList();
                getActivity().onBackPressed();

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
    }

    private void updateDetailList() {
        Date = edDate.getText().toString().trim();
        Product = edProduct.getText().toString().trim();
        Cost = edCost.getText().toString().trim();
        Notes = edNote.getText().toString().trim();

        if (!Date.equalsIgnoreCase("")) {
            if (!Product.equalsIgnoreCase("")) {
                if (!Cost.equalsIgnoreCase("")) {

                    if (!RelativeBusiness.equalsIgnoreCase("")) {
                        if (!Notes.equalsIgnoreCase("")) {

                            db.updateReceipts(ReceiptsId, Date, Product, Cost, RelativeBusiness, Notes);

                            Log.d("UpdateData", "" + Date + Product + Cost + RelativeBusiness + Notes);
                            Toast.makeText(getActivity(), "Update Data Sucessfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "please enter the Relative Business or Service", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "please enter the Cost", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "please enter the Product", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "please enter the Date", Toast.LENGTH_SHORT).show();
        }
    }

    private void openDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        final  int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        // datePickerDialog.updateDate(mDay,mMonth,mYear);
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

