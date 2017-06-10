package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ravi archi on 2/3/2017.
 */
public class AboutUsFragment extends Fragment implements View.OnClickListener {
    public TextView txtComments, txtBusinessDescription, txtCategory, txtContactNumber, txtEmail, txtWebsite;
    public ImageView imgCall, imgEmail, imgWebsite;
    public String VenderId;
    public DbHelper db;
    public List<HashMap<String, String>> directoryVenderListdetail;
    public HashMap<String, String> map = new HashMap<>();
    public Button btnReferVender;
    public String Content, CompanyName, ContactName, Category, ContactNumber, Email, Website;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_aboutus_directory_vender_detail_list, container, false);
        Toast.makeText(getActivity(), "aboutus", Toast.LENGTH_SHORT).show();
        db = new DbHelper(getActivity());
        directoryVenderListdetail = db.getAllVendorDetails();
        findById(view);
        init();
        click();
        return view;
    }

    private void click() {
        imgCall.setOnClickListener(this);
        imgEmail.setOnClickListener(this);
        imgWebsite.setOnClickListener(this);
        btnReferVender.setOnClickListener(this);
    }

    private void findById(View view) {
        btnReferVender = (Button) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_btnrefervender);
        imgCall = (ImageView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_imgcall);
        imgEmail = (ImageView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_imgemail);
        imgWebsite = (ImageView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_imgwebsite);
        txtComments = (TextView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_txtcomments);
        txtBusinessDescription = (TextView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_txtdescription);
        txtCategory = (TextView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_txtcategory);
        txtContactNumber = (TextView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_txtcall);
        txtEmail = (TextView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_txtemail);
        txtWebsite = (TextView) view.findViewById(R.id.fragment_aboutus_directory_vender_detail_list_txtwebsite);
    }

    private void init() {
        if (getArguments() != null) {

            VenderId = getArguments().getString("VendorId");
            directoryVenderListdetail.add(map);
            map = directoryVenderListdetail.get(Integer.parseInt(VenderId) - 1);
            ContactNumber = map.get("vendor_contact");
            Category = map.get("vendor_add_category");
            Email = map.get("vendor_email");
            Website = map.get("vendor_comapany_website");
            ContactName = map.get("vendor_contact_name");
            CompanyName = map.get("vendor_company_name");
            txtComments.setText(map.get("vendor_comments"));
            txtBusinessDescription.setText("not yet");
            txtCategory.setText(Category);
            txtContactNumber.setText(ContactNumber);
            txtEmail.setText(Email);
            txtWebsite.setText(R.string.aboutus_visitwebsite);
            Content = getString(R.string.aboutus_emailcontent);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.header_iv_back:
                // fragment = new ReceiptsFragment();
                getActivity().onBackPressed();
                break;*/
            case R.id.fragment_aboutus_directory_vender_detail_list_imgcall:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + map.get("vender_contact")));
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
            case R.id.fragment_aboutus_directory_vender_detail_list_imgemail:
                break;

            case R.id.fragment_aboutus_directory_vender_detail_list_imgwebsite:
                Uri uri = Uri.parse("http://" + map.get("vender_comapany_website"));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                break;
            case R.id.fragment_aboutus_directory_vender_detail_list_btnrefervender:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                //emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"abc@xyz.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, map.get("vender_add_category"));
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Content + "\n\n" + CompanyName + "\nContact : " + ContactName + "\nVender Category : " + Category + "\nPhone : " + ContactNumber + "\nEmail : " + Email + "\nWebsite : " + Website + "\n\n\n-");
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
                break;
        }
    }
}
