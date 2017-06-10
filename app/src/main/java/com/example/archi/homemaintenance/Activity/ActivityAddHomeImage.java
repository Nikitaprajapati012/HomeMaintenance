package com.example.archi.homemaintenance.Activity;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.archi.homemaintenance.Activity.DbBitmapUtility.getBytes;

/**
 * Created by archi on 1/9/2017.
 */

public class ActivityAddHomeImage extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "ActivityAddHomeImage";
    public String selectImagePath;
    public String imagePath;
    public DbHelper db;
    public String homeImagesId;
    public List<HashMap<String, String>> homeImageList = new ArrayList<>();
    public Button btnStart;
    public ImageView ivGallery, ivTakePic,imgBack;
    public int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public HashMap<String, String> map = new HashMap<>();
    public byte[] inputData;
    public RelativeLayout header;
    public TextView txtHeaderName;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_pic);
        db = new DbHelper(ActivityAddHomeImage.this);

        imgBack=(ImageView)findViewById(R.id.header_iv_back);
        header = (RelativeLayout) findViewById(R.id.header_homeimage);
        txtHeaderName = (TextView) findViewById(R.id.header_tv_header_name);
        txtHeaderName.setText(getString(R.string.addhome));
        btnStart = (Button) findViewById(R.id.act_add_home_pic_btn_start);
        ivGallery = (ImageView) findViewById(R.id.activity_add_home_image_take_gallery);
        ivTakePic = (ImageView) findViewById(R.id.activity_add_home_image_take_pic);
        init();
    }

    private void init() {
        btnStart.setOnClickListener(this);
        ivTakePic.setOnClickListener(this);
        ivGallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_add_home_pic_btn_start:

                if (selectImagePath.equals("")) {
                    Toast.makeText(ActivityAddHomeImage.this, "please select the image ", Toast.LENGTH_SHORT).show();
                } else {

                    db.addHomeImages(selectImagePath);
                    Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();

                    String message=selectImagePath; //editText1.getText().toString();
                    Intent intent=new Intent();
                    intent.putExtra("imagepath",message);
                    setResult(3,intent);
                    finish();

                }
                break;
            case R.id.activity_add_home_image_take_gallery:
                galleryIntent();
                break;
            case R.id.activity_add_home_image_take_pic:
                cameraIntent();
                break;
            case R.id.header_iv_back:
                finish();
                break;
        }
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
            // Picasso.with(getApplicationContext()).load(mypath).into(imageView);
            Uri uri = Uri.fromFile(mypath);
            if (saveImageInDB(uri)) {
                Toast.makeText(this, "CameraImage Saved in Database...", Toast.LENGTH_SHORT).show();
                // imageView.setImageURI(uri);
                Log.d("PATH", "" + mypath);
                selectImagePath = mypath.getAbsolutePath();
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
                    //imageView.setVisibility(View.VISIBLE);
                    //imageView.setImageURI(uri);
                }
                imagePath = cursor.getString(columnIndex);
                if (columnIndex < 0) // no column index
                    return; // DO YOUR ERROR HANDLING

                selectImagePath = cursor.getString(columnIndex);
                Log.d("picturePath", selectImagePath);
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

            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            return false;
        }
    }
}
