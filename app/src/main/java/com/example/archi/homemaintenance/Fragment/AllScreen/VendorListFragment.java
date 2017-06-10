package com.example.archi.homemaintenance.Fragment.AllScreen;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.Activity.ActivityAddVendorManually;
import com.example.archi.homemaintenance.Activity.ActivityChooseFromContact;
import com.example.archi.homemaintenance.Adapter.VenderAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.example.archi.homemaintenance.Activity.DbBitmapUtility.getBytes;

/**
 * Created by archi on 1/13/2017.
 */

public class VendorListFragment extends Fragment implements View.OnClickListener {

    public RecyclerView recyclerViewVendorList;
    public DbHelper db;
    public List<HashMap<String, String>> vendorList, vendorDetailAllList, vendorCategoryWiseWList;
    public RelativeLayout headerview, headerVendorList;
    public ImageView imgBack, imgAdd;
    public Dialog dialog;
    public Button btnCancel, btnChooseContact, btnAddVendorMenual, btnAddPhotoVendor;
    public byte[] inputData;
    public TextView tvHeader;
    public HashMap<String, String> map = new HashMap<>();
    public String VendorId,ID;
    private VenderAdapter adapater;
    private int REQUEST_CAMERA = 0;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_vender_list, container, false);
        headerview = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerview.setVisibility(View.GONE);
        tvHeader = (TextView) view.findViewById(R.id.header_tv_header_name);
        init();
        findById(view);
        click();
        return view;
    }

    private void click() {
        imgBack.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        } else {
            Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_SHORT).show();


        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap resizedbitmap = (Bitmap) data.getExtras().get("data");
        Log.e("CAMERA", ">>>> " + resizedbitmap);
        ContextWrapper cw = new ContextWrapper(getActivity());
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
            //  Picasso.with(getActivity()).load(mypath).into(imageView);
            Uri uri = Uri.fromFile(mypath);
            if (saveImageInDB(uri)) {
                Toast.makeText(getActivity(), "CameraImage Saved in Database...", Toast.LENGTH_SHORT).show();
                //imageView.setImageURI(uri);
                Log.d("PATH", "" + mypath);
                //captImagePath = mypath.getAbsolutePath();
            }
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
        }
    }

    private boolean saveImageInDB(Uri selectedImageUri) {
        try {
            InputStream iStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
            inputData = getBytes(iStream);

            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            return false;
        }
    }

    private void init() {
        db = new DbHelper(getActivity());
        vendorDetailAllList = db.getAllVendorDetails();
        vendorList=db.getCountVendorAllVendorDetails(/*Integer.parseInt(VendorId)*/);

        Log.d("vendor","" + vendorList);
        if (getArguments() != null) {
            VendorId = getArguments().getString("COUNTVendorId");
            vendorDetailAllList.add(map);
            map = vendorDetailAllList.get(Integer.parseInt(VendorId) - 1);
            tvHeader.setText(map.get("vendor_add_category"));
        }
    }

    private void findById(View view) {
        headerVendorList = (RelativeLayout) view.findViewById(R.id.header_vender_list);
        imgBack = (ImageView) view.findViewById(R.id.header_iv_back);
        imgAdd = (ImageView) view.findViewById(R.id.header_imgadd);
        recyclerViewVendorList = (RecyclerView) view.findViewById(R.id.fragment_directory_vender_recyclerview);
        adapater = new VenderAdapter(getActivity(), vendorList, getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewVendorList.setLayoutManager(mLayoutManager);
        recyclerViewVendorList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVendorList.setAdapter(adapater);
        adapater.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_iv_back:
                // fragment = new ReceiptsFragment();
                getActivity().onBackPressed();
                break;
            case R.id.header_imgadd:
                showDialog();

                break;

        }
    }

    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_vender_with_photo);
        dialog.setTitle("");
        btnAddPhotoVendor = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_addvendorphoto);
        btnCancel = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_cancel);
        btnChooseContact = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_choosecontact);
        btnAddVendorMenual = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_addvendor);
        dialog.show();
        btnAddVendorMenual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iAddVendor = new Intent(getActivity(), ActivityAddVendorManually.class);
                startActivity(iAddVendor);
                // startActivityForResult(iAddVendor, 1);
                dialog.dismiss();
            }
        });

        btnAddPhotoVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, REQUEST_CAMERA);
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
                Intent icontact = new Intent(getActivity(), ActivityChooseFromContact.class);
                startActivity(icontact);
                dialog.dismiss();
            }
        });
    }
}

