package com.example.archi.homemaintenance.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.archi.homemaintenance.Activity.DbBitmapUtility.getBytes;

/**
 * Created by Ravi archi on 1/13/2017.
 */

public class ActivityAddAppliance extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "ActivityAddAppliance";
    public TextView txtaddApplianceMenual, txtAddNewVendor, txtCamera, txtGallery, txtOR, txtAddInfo, txtAddNewCategory;
    public LinearLayout firdtView, secondView, imgFirst, imgSecond;
    public RelativeLayout headerReceipts;
    public Button btnCancel, btnChooseContact, btnAddVendorMenual, btnSubmit, btnSubmitManually;
    public Dialog dialog;
    public ImageView headerBack, imageView;
    public EditText edDate, edModel, edBrand, edNote, edSerial, edPrice;
    public Spinner spinnerRelativeBusiness, spinnerAddCategory;
    public DbHelper db;
    public String imagePath, Date, Brand, RelativeBusiness, SelectCategory, Model, Notes, Serial, Price;
    public ArrayList<String> spinnerList = new ArrayList<String>();
    public ArrayList<String> spinnerListCategory = new ArrayList<String>();
    public byte[] inputData;
    public ArrayAdapter<String> spinnerBusinessAdapter, spinnerCategoryAdapter;
    public String captImagePath;
    public String[] spinnerCategoryList;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public  RelativeLayout headerView;

    // get Captured Image Rotation Degree
    public static int getImageOrientation(String imagePath) {
        int rotate = 0;
        try {

            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appliance);
        db = new DbHelper(ActivityAddAppliance.this);
        findById();
        init();
        click();
    }

    private void init() {
        //business
        spinnerBusinessAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerBusinessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRelativeBusiness.setAdapter(spinnerBusinessAdapter);

        //category
        spinnerCategoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerListCategory);
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryList = getResources().getStringArray(R.array.categories);
        spinnerAddCategory.setAdapter(spinnerCategoryAdapter);

    }

    private void click() {
        txtaddApplianceMenual.setOnClickListener(this);
        txtAddNewVendor.setOnClickListener(this);
        txtAddNewCategory.setOnClickListener(this);
        txtAddInfo.setOnClickListener(this);
        txtOR.setOnClickListener(this);
        headerBack.setOnClickListener(this);
        txtCamera.setOnClickListener(this);
        txtGallery.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnSubmitManually.setOnClickListener(this);
        edDate.setOnClickListener(this);
        spinnerRelativeBusiness.setOnItemSelectedListener(this);
        spinnerAddCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SelectCategory = spinnerAddCategory.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void findById() {
        headerBack = (ImageView) findViewById(R.id.header_iv_back);
        imageView = (ImageView) findViewById(R.id.activity_add_appliance_imageview);
        txtCamera = (TextView) findViewById(R.id.activity_add_appliance_txtcamera);
        txtGallery = (TextView) findViewById(R.id.activity_add_appliance_txtgallery);
        edDate = (EditText) findViewById(R.id.activity_add_appliance_ed_bought_date);
        spinnerRelativeBusiness = (Spinner) findViewById(R.id.activity_add_appliance_releted_spinnerbusiness_service_pro);
        spinnerAddCategory = (Spinner) findViewById(R.id.activity_add_appliance_spinnercategory);

        // Hiding the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(spinnerRelativeBusiness.getWindowToken(), 0);
        // imm.hideSoftInputFromWindow(spinnerAddCategory.getWindowToken(), 1);

        edBrand = (EditText) findViewById(R.id.activity_add_appliance_edbrand);
        edModel = (EditText) findViewById(R.id.activity_add_appliance_edModel);
        edNote = (EditText) findViewById(R.id.activity_add_appliance_ednotes);
        edSerial = (EditText) findViewById(R.id.activity_add_appliance_edSerial);
        edPrice = (EditText) findViewById(R.id.activity_add_appliance_edPrice);
        btnSubmit = (Button) findViewById(R.id.activity_add_appliance_btn_submit);
        btnSubmitManually = (Button) findViewById(R.id.activity_add_appliance_btn_submitmanually);
        headerReceipts = (RelativeLayout) findViewById(R.id.header_add_appliances);
        txtAddNewVendor = (TextView) findViewById(R.id.activity_add_appliance_tv_add_vendor);
        txtAddNewCategory = (TextView) findViewById(R.id.activity_add_appliance_tv_add_category);
        txtAddInfo = (TextView) findViewById(R.id.activity_add_appliance_firstviewaddinfo);
        txtOR = (TextView) findViewById(R.id.activity_add_appliance_firstviewor);
        txtaddApplianceMenual = (TextView) findViewById(R.id.activity_add_appliance_tv_add_manual);
        firdtView = (LinearLayout) findViewById(R.id.activity_add_appliance_firstview);
        secondView = (LinearLayout) findViewById(R.id.activity_add_appliancemenually_secondview);
        imgFirst = (LinearLayout) findViewById(R.id.activity_add_appliance_imgfirst);
        imgSecond = (LinearLayout) findViewById(R.id.activity_add_appliance_imgsecond);
        txtOR.setVisibility(View.VISIBLE);
        txtAddInfo.setVisibility(View.GONE);
        headerReceipts.setVisibility(View.VISIBLE);
        firdtView.setVisibility(View.VISIBLE);
        secondView.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_add_appliance_tv_add_manual:

                if (firdtView.getVisibility() == View.VISIBLE) {

                    firdtView.setVisibility(View.GONE);
                    secondView.setVisibility(View.VISIBLE);

                } else {
                    firdtView.setVisibility(View.VISIBLE);
                    secondView.setVisibility(View.GONE);
                }
                break;

            case R.id.activity_add_appliance_tv_add_vendor:
                openDialog();
                break;

            case R.id.activity_add_appliance_tv_add_category:
                Intent icategory = new Intent(ActivityAddAppliance.this, ActivityAddCategory.class);
                startActivityForResult(icategory, 1);
                break;

            case R.id.activity_add_appliance_btn_submitmanually:
                addApplianceMenual();
                finish();
                break;

            case R.id.activity_add_appliance_ed_bought_date:
                openDate();
                break;

            case R.id.activity_add_appliance_txtcamera:
                cameraIntent();

                break;

            case R.id.activity_add_appliance_txtgallery:
                galleryIntent();
                break;

            case R.id.activity_add_appliance_btn_submit:
                if (saveImageInDB(Uri.EMPTY)) {
                    Toast.makeText(this, "Brand name is required", Toast.LENGTH_SHORT).show();
                } /*else {
                    addApplianceMenual();
                    finish();
                }*/
                break;

            case R.id.header_iv_back:
                finish();
                //Intent intent = new Intent();
                // setResult(2, intent);
                break;
        }
    }

    private void openDialog() {
        dialog = new Dialog(ActivityAddAppliance.this);
        dialog.setContentView(R.layout.dialog_add_vendor);
        dialog.setTitle("");
        btnCancel = (Button) dialog.findViewById(R.id.activity_add_vendor_btn_cancel);
        btnChooseContact = (Button) dialog.findViewById(R.id.activity_add_vendor_btn_choosecontact);
        btnAddVendorMenual = (Button) dialog.findViewById(R.id.activity_add_vendor_btn_addvencer);
        dialog.show();
        btnAddVendorMenual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iAddVendor = new Intent(ActivityAddAppliance.this, ActivityAddVendorManually.class);
                startActivityForResult(iAddVendor, 2);
                dialog.dismiss();
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
                Intent icontact = new Intent(ActivityAddAppliance.this, ActivityChooseFromContact.class);

                startActivity(icontact);
                dialog.dismiss();
            }
        });

    }

    private void addApplianceMenual() {
        Date = edDate.getText().toString().trim();
        Brand = edBrand.getText().toString().trim();
        Model = edModel.getText().toString().trim();
        Serial = edSerial.getText().toString().trim();
        Price = edPrice.getText().toString().trim();
        Notes = edNote.getText().toString().trim();

        if (!Date.equalsIgnoreCase("")) {
            if (!Brand.equalsIgnoreCase("")) {
                if (!Model.equalsIgnoreCase("")) {

                    if (!RelativeBusiness.equalsIgnoreCase("")) {
                        if (!Notes.equalsIgnoreCase("")) {

                            db.addAppliance(captImagePath, Brand, Model, SelectCategory, Serial, Price, Date, RelativeBusiness, Notes);
                            Log.d("Insert", "Inserting...");
                            Log.d("business", "" + RelativeBusiness);
                            Log.d("imagepath", "" + captImagePath);
                            Toast.makeText(this, "Insert Sucessfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityAddAppliance.this, "please enter the Relative Business or Service", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityAddAppliance.this, "please enter the Model", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(ActivityAddAppliance.this, "please enter the Brand", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActivityAddAppliance.this, "please enter the Date", Toast.LENGTH_SHORT).show();
        }

        //  finish();


    }


    private void galleryIntent() {
        Intent intentPickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentPickImage, SELECT_FILE);
    }

    private void cameraIntent() {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            String message = data.getStringExtra("business");
            spinnerList.add(message);
            spinnerBusinessAdapter.notifyDataSetChanged();

        }
        if (requestCode == 1) {
            String message = data.getStringExtra("category");
            spinnerListCategory.add(message);
            spinnerCategoryAdapter.notifyDataSetChanged();
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap resizedbitmap = (Bitmap) data.getExtras().get("data");
        Log.e("CAMERA", ">>>> " + resizedbitmap);
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File mypath = new File(directory, System.currentTimeMillis() + ".png");

        Matrix matrix = new Matrix();
        matrix.postRotate(getImageOrientation(mypath.getPath()));
        Bitmap rotatedBitmap = Bitmap.createBitmap(resizedbitmap, 0, 0, resizedbitmap.getWidth(),
                resizedbitmap.getHeight(), matrix, true);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Picasso.with(getApplicationContext()).load(mypath).into(imageView);
            Uri uri = Uri.fromFile(mypath);
            if (saveImageInDB(uri)) {
                Toast.makeText(this, "CameraImage Saved in Database...", Toast.LENGTH_SHORT).show();
                imageView.setImageURI(uri);
                Log.d("PATH", "" + mypath);
                captImagePath = mypath.getAbsolutePath();
            }
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {
            try {
                Uri uri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri,
                        filePathColumn, null, null, null);

                if (cursor == null || cursor.getCount() < 1) {

                    return; // no cursor or no record. DO YOUR ERROR HANDLING
                }
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                if (saveImageInDB(uri)) {
                    Toast.makeText(this, "GalleryImage Saved in Database...", Toast.LENGTH_SHORT).show();
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageURI(uri);
                }
                imagePath = cursor.getString(columnIndex);
                if (columnIndex < 0) // no column index
                    return; // DO YOUR ERROR HANDLING

                String picturePath = cursor.getString(columnIndex);
                Log.d("picturePath", picturePath);
                cursor.close(); // close cursor


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean saveImageInDB(Uri selectedImageUri) {
        try {
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            inputData = getBytes(iStream);
            //imgFirst.setVisibility(View.GONE);
            //imgSecond.setVisibility(View.GONE);
            txtOR.setVisibility(View.GONE);
            txtAddInfo.setVisibility(View.VISIBLE);
            edBrand.setVisibility(View.GONE);
            edModel.setVisibility(View.GONE);
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            return false;
        }
    }

    private void openDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        final  int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        // On selecting a spinner item
        RelativeBusiness = spinnerRelativeBusiness.getItemAtPosition(position).toString();
        // SelectCategory = spinnerAddCategory.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(spinnerRelativeBusiness.getContext(), "You selected: " + RelativeBusiness,
        //  Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
