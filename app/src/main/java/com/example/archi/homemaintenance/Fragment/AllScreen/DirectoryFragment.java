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
import com.example.archi.homemaintenance.Adapter.CategoryAdapter;
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

public class DirectoryFragment extends Fragment {

    public RecyclerView recyclerViewCategories;
    public DbHelper db;
    public List<HashMap<String, String>> categoryDetailList, categoryList;
    public RelativeLayout headerView;
    public ImageView imgAdd;
    public Dialog dialog;
    public Button btnCancel, btnChooseContact, btnAddVenderMenual, btnAddPhotoVender;
    public byte[] inputData;
    public TextView tvHeader, tvEdit;
    public HashMap<String, String> map;
    private CategoryAdapter adapater;
    private int REQUEST_CAMERA = 0;

    // TODO: 2/13/2017   get Captured Image Rotation Degree
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
        View view = inflater.inflate(R.layout.fragment_directory, container, false);
        tvHeader = (TextView) getActivity().findViewById(R.id.header_tv_headername_main);
        tvEdit = (TextView) getActivity().findViewById(R.id.header_tv_edit_main);
        imgAdd = (ImageView) getActivity().findViewById(R.id.header_iv_add_main);
        tvHeader.setText(R.string.directory);
        tvEdit.setVisibility(View.INVISIBLE);
        headerView = (RelativeLayout) getActivity().findViewById(R.id.headerview);
        headerView.setVisibility(View.VISIBLE);
        init();
        findById(view);
        click();
        return view;
    }

    // TODO: 2/13/2017  perfom click event 
    private void click() {
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_vender_with_photo);
                dialog.setTitle("");
                btnAddPhotoVender = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_addvendorphoto);
                btnCancel = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_cancel);
                btnChooseContact = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_choosecontact);
                btnAddVenderMenual = (Button) dialog.findViewById(R.id.activity_add_vendor_with_photo_btn_addvendor);
                dialog.show();
                btnAddVenderMenual.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent iAddVender = new Intent(getActivity(), ActivityAddVendorManually.class);
                        startActivity(iAddVender);
                        dialog.dismiss();
                    }
                });
                btnAddPhotoVender.setOnClickListener(new View.OnClickListener() {
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
        });
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

    // TODO: 2/13/2017 initalize the arralist 
    private void init() {
        db = new DbHelper(getActivity());
//        if (categoryDetailList.size() != 0) {
        categoryDetailList = db.getCountAllVendorDetails();
        //      } else {
        // categoryList = new ArrayList<>();
        //    }
    }

    @Override
    public void onResume() {
        super.onResume();
        db = new DbHelper(getActivity());
        categoryDetailList = db.getCountAllVendorDetails();
    }

    // TODO: 2/13/2017 bind the all widget
    private void findById(View view) {
      /*  // TODO: 2/14/2017 add static list
        map = new HashMap<>();
        map.put("c", "Chimney Specialists");
        map.put("e", "Electricians");
        map.put("ex", "Exterminators");
        map.put("g", "Gutter Servicers");
        map.put("h", "HVAC Specialists");
        map.put("i", "Irrigation Specialists");
        categoryList.add(map);
        Log.d("map", "" + map);*/

        recyclerViewCategories = (RecyclerView) view.findViewById(R.id.fragment_directory_category_recyclerview);
        adapater = new CategoryAdapter(getActivity(), categoryDetailList, getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCategories.setLayoutManager(mLayoutManager);
        recyclerViewCategories.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategories.setAdapter(adapater);
        adapater.notifyDataSetChanged();
        adapater.notifyItemInserted(categoryDetailList.size() - 1);


    }
}
