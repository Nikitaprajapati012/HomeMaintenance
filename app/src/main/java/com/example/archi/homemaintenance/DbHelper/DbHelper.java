package com.example.archi.homemaintenance.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archi on 1/10/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "HOUSEMAINTAIN";
    //table name
    private static final String TABLE_HOME = "home";
    private static final String TABLE_VENDOR = "vendor";
    private static final String TABLE_RECEIPTS = "receipts";
    private static final String TABLE_APPLIANCE = "appliance";
    private static final String TABLE_HOME_ADD_IMAGES = "home_images";
    private static final String TABLE_QUESTION = "quetion";

    //Add Columns
    private static final String KEY_HOME_ID = "key_home_id";
    private static final String KEY_HOME_NAME = "key_home_name";
    //table profile calander
    private static final String KEY_HOME_PURCHASE_DATE = "key_home_purchase_date";
    private static final String KEY_HOME_PURCHASE_PRICE = "key_home_purchase_price";
    private static final String KEY_HOME_APPRAISED_VALUE = "key_home_appraised_value";
    private static final String KEY_HOME_ADDRESS = "key_home_addree";
    private static final String KEY_HOME_NOTES = "key_home_notes";

    //About home Quetion
    private static final String KEY_HOME_QUETIONS_ID = "key_home_quetions_id";
    private static final String KEY_QUETION_ID = "key_quetion_id";
    private static final String KEY_ANS_ID = "key_ans_id";

    //Add Home Images
    private static final String KEY_HOME_ADD_IMAGES_ID = "key_home_images_id";
    private static final String KEY_HOME_ADD_IMAGE = "key_home_images_image";

    //Add vendor Manually
    private static final String KEY_VENDOR_ID = "key_vendor_id";
    private static final String KEY_VENDOR_COMPANY_NAME = "key_vendor_companyname";
    private static final String KEY_VENDOR_CONTACT_NAME = "key_vendor_contactname";
    private static final String KEY_VENDOR_ADD_CATEGORY = "key_vendor_addcategory";
    private static final String KEY_VENDOR_CONTACT = "key_vendor_contact";
    private static final String KEY_VENDOR_EMAIL = "key_vendor_email";
    private static final String KEY_VENDOR_COMPANY_WEBSITE = "key_vendor_comapny_website";
    private static final String KEY_VENDOR_COMMENTS = "key_vendor_comments";
    //ADD receipts menually
    private static final String KEY_RECEIPTS_ID = "key_receipts_id";
    private static final String KEY_RECEIPTS = "key_receipts";
    private static final String KEY_RECEIPTS_IMAGE = "key_receipts_image";
    private static final String KEY_RECEIPTS_DATE = "key_receipts_date";
    private static final String KEY_RECEIPTS_PRODUCT = "key_receipts_product";
    private static final String KEY_RECEIPTS_COST = "key_receipts_cost";
    private static final String KEY_RECEIPTS_TOTAL = "key_receipts_total";
    private static final String KEY_RECEIPTS_RELATIVE_BUSINESS = "key_receipts_relative_business";
    private static final String KEY_RECEIPTS_NOTE = "key_receipts_note";

    //ADD appliance menually
    private static final String KEY_APPLIANCE_ID = "key_appliance_id";
    private static final String KEY_APPLIANCE = "key_appliance";
    private static final String KEY_APPLIANCE_IMAGE = "key_appliance_image";
    private static final String KEY_APPLIANCE_BRAND = "key_appliance_brand";
    private static final String KEY_APPLIANCE_MODEL = "key_appliance_model";
    private static final String KEY_APPLIANCE_CATEGORY = "key_appliance_category";
    private static final String KEY_APPLIANCE_SERIAL = "key_appliance_serial";
    private static final String KEY_APPLIANCE_PRICE = "key_appliance_price";
    private static final String KEY_APPLIANCE_DATE = "key_appliance_date";
    private static final String KEY_APPLIANCE_RELATIVE_BUSINESS = "key_appliance_relative_business";
    private static final String KEY_APPLIANCE_NOTE = "key_appliance_note";

    public byte[] imageBytes;
    String Category;
    private SQLiteDatabase mDb;
    private SQLiteOpenHelper mDbHelper;
    private Context context;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // open the database connection.
    public DbHelper open() {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        mDbHelper.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create home
        String CREATE_TABLE_HOME = "CREATE TABLE " + TABLE_HOME + "("
                + KEY_HOME_ID + " INTEGER PRIMARY KEY,"
                + KEY_HOME_NAME + " TEXT NOT NULL,"
                + KEY_HOME_PURCHASE_DATE + " TEXT NOT NULL,"
                + KEY_HOME_PURCHASE_PRICE + " INTEGER,"
                + KEY_HOME_APPRAISED_VALUE + " INTEGER,"
                + KEY_HOME_ADDRESS + " TEXT NOT NULL,"
                + KEY_HOME_NOTES + " TEXT NOT NULL" + ")";

        //create home
        String CREATE_TABLE_HOME_ADD_IMAGES = "CREATE TABLE " + TABLE_HOME_ADD_IMAGES + "("
                + KEY_HOME_ADD_IMAGES_ID + " INTEGER PRIMARY KEY,"
                + KEY_HOME_ADD_IMAGE + " TEXT NOT NULL"
                + ")";

        //create quetinon
        String CREATE_TABLE_HOME_QUETION = "CREATE TABLE " + TABLE_QUESTION + "("
                + KEY_HOME_QUETIONS_ID + " INTEGER PRIMARY KEY,"
                + KEY_QUETION_ID + " TEXT ,"
                + KEY_ANS_ID + " TEXT"
                + ")";

        //create add  vendor
        String CREATE_TABLE_VENDOR = "CREATE TABLE " + TABLE_VENDOR + "("
                + KEY_VENDOR_ID + " INTEGER PRIMARY KEY ,"
                + KEY_VENDOR_COMPANY_NAME + " TEXT ,"
                + KEY_VENDOR_CONTACT_NAME + " TEXT ,"
                + KEY_VENDOR_ADD_CATEGORY + " TEXT ,"
                + KEY_VENDOR_CONTACT + " INTEGER,"
                + KEY_VENDOR_EMAIL + " TEXT ,"
                + KEY_VENDOR_COMPANY_WEBSITE + " TEXT,"
                + KEY_VENDOR_COMMENTS + " TEXT"
                + ")";

        //creat add receipts
        String CREATE_TABLE_RECEIPTS = "CREATE TABLE " + TABLE_RECEIPTS + "("
                + KEY_RECEIPTS_ID + " INTEGER PRIMARY KEY ,"
                + KEY_RECEIPTS + " INTEGER ,"
                + KEY_RECEIPTS_IMAGE + " TEXT ,"
                + KEY_RECEIPTS_DATE + " INTEGER ,"
                + KEY_RECEIPTS_PRODUCT + " TEXT ,"
                + KEY_RECEIPTS_COST + " INTEGER ,"
                + KEY_RECEIPTS_RELATIVE_BUSINESS + " INTEGER,"
                + KEY_RECEIPTS_NOTE + " TEXT " +
                ")";


        //creat add receipts
        String CREATE_TABLE_APPLIANCE = "CREATE TABLE " + TABLE_APPLIANCE + "("
                + KEY_APPLIANCE_ID + " INTEGER PRIMARY KEY ,"
                + KEY_APPLIANCE + " INTEGER ,"
                + KEY_APPLIANCE_IMAGE + " TEXT ,"
                + KEY_APPLIANCE_BRAND + " TEXT ,"
                + KEY_APPLIANCE_MODEL + " TEXT ,"
                + KEY_APPLIANCE_CATEGORY + " TEXT ,"
                + KEY_APPLIANCE_SERIAL + " INTEGER ,"
                + KEY_APPLIANCE_PRICE + " INTEGER ,"
                + KEY_APPLIANCE_DATE + " INTEGER ,"
                + KEY_APPLIANCE_RELATIVE_BUSINESS + " INTEGER,"
                + KEY_APPLIANCE_NOTE + " TEXT " +
                ")";


        db.execSQL(CREATE_TABLE_HOME);
        db.execSQL(CREATE_TABLE_VENDOR);
        db.execSQL(CREATE_TABLE_RECEIPTS);
        db.execSQL(CREATE_TABLE_APPLIANCE);
        db.execSQL(CREATE_TABLE_HOME_ADD_IMAGES);
        db.execSQL(CREATE_TABLE_HOME_QUETION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_HOME);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_VENDOR);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_RECEIPTS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_APPLIANCE);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_QUESTION);
    }

    public void addHome(String name, String date, String price, String appriasedValue, String homeAddress, String notes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOME_NAME, name);
        values.put(KEY_HOME_PURCHASE_DATE, date);
        values.put(KEY_HOME_PURCHASE_PRICE, price);
        values.put(KEY_HOME_APPRAISED_VALUE, appriasedValue);
        values.put(KEY_HOME_ADDRESS, homeAddress);
        values.put(KEY_HOME_NOTES, notes);
        db.insert(TABLE_HOME, null, values);
    }

    //add home que
    public void addQuetion(String quetionnumber, String quetionanswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUETION_ID, quetionnumber);
        values.put(KEY_ANS_ID, quetionanswer);
        db.insert(TABLE_QUESTION, null, values);
        Log.d("DB_INSERT", "" + values);

    }

    //add Applicance
    public void addAppliance(String applianceimages, String appliancebrand, String appliancemodel, String appliancecategory, String applianceserial, String applianceprice, String appliancedate, String appliancerelativebusiness, String appliancenotes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_APPLIANCE_IMAGE, applianceimages);
        values.put(KEY_APPLIANCE_BRAND, appliancebrand);
        values.put(KEY_APPLIANCE_MODEL, appliancemodel);
        values.put(KEY_APPLIANCE_CATEGORY, appliancecategory);
        values.put(KEY_APPLIANCE_SERIAL, applianceserial);
        values.put(KEY_APPLIANCE_PRICE, applianceprice);
        values.put(KEY_APPLIANCE_DATE, appliancedate);
        values.put(KEY_APPLIANCE_RELATIVE_BUSINESS, appliancerelativebusiness);
        values.put(KEY_APPLIANCE_NOTE, appliancenotes);

        db.insert(TABLE_APPLIANCE, null, values);

    }

    //delete appliance
    public void deleteAppliance(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_APPLIANCE + " WHERE " + KEY_APPLIANCE_ID + "='" + id + "'");
        db.close();
    }

    //update appliance
    public void updateAppliance(String applianceid, String appliancebrand, String appliancemodel, String appliancecategory, String applianceserial, String applianceprice, String appliancedate, String appliancerelativebusiness, String appliancenotes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(KEY_APPLIANCE_IMAGE, applianceimages);
        values.put(KEY_APPLIANCE_BRAND, appliancebrand);
        values.put(KEY_APPLIANCE_MODEL, appliancemodel);
        values.put(KEY_APPLIANCE_CATEGORY, appliancecategory);
        values.put(KEY_APPLIANCE_SERIAL, applianceserial);
        values.put(KEY_APPLIANCE_PRICE, applianceprice);
        values.put(KEY_APPLIANCE_DATE, appliancedate);
        values.put(KEY_APPLIANCE_RELATIVE_BUSINESS, appliancerelativebusiness);
        values.put(KEY_APPLIANCE_NOTE, appliancenotes);

        db.update(TABLE_APPLIANCE, values, KEY_APPLIANCE_ID + " = ?", new String[]
                {String.valueOf(applianceid)});
    }

    //add Receipts
    public void addReceipts(String receiptsimages, String receiptsdate, String receiptsproduct, String receiptscost, String receiptsrelativebusiness, String receiptsnotes, String receipts) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RECEIPTS, receipts);
        values.put(KEY_RECEIPTS_IMAGE, receiptsimages);
        values.put(KEY_RECEIPTS_DATE, receiptsdate);
        values.put(KEY_RECEIPTS_PRODUCT, receiptsproduct);
        values.put(KEY_RECEIPTS_COST, receiptscost);
        values.put(KEY_RECEIPTS_RELATIVE_BUSINESS, receiptsrelativebusiness);
        values.put(KEY_RECEIPTS_NOTE, receiptsnotes);


        db.insert(TABLE_RECEIPTS, null, values);

    }

    //delete receipts
    public void deleteReceipts(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECEIPTS + " WHERE " + KEY_RECEIPTS_ID + "='" + id + "'");
        db.close();
    }

    //update receipts
    public void updateReceipts(String receiptsid/*, String receiptsimages*/, String receiptsdate, String receiptsproduct, String receiptscost, String receiptsrelativebusiness, String receiptsnotes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(KEY_RECEIPTS_IMAGE, receiptsimages);
        values.put(KEY_RECEIPTS_DATE, receiptsdate);
        values.put(KEY_RECEIPTS_PRODUCT, receiptsproduct);
        values.put(KEY_RECEIPTS_COST, receiptscost);
        values.put(KEY_RECEIPTS_RELATIVE_BUSINESS, receiptsrelativebusiness);
        values.put(KEY_RECEIPTS_NOTE, receiptsnotes);
        db.update(TABLE_RECEIPTS, values, KEY_RECEIPTS_ID + " = ?", new String[]
                {String.valueOf(receiptsid)});
    }

    //add vendor
    public void addVendor(String vendorcompanyname, String vendorcontactname, String vendoraddcategory, String vendorcontact, String vendoremail, String vendorcompanywebsite, String vendorcomments) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VENDOR_COMPANY_NAME, vendorcompanyname);
        values.put(KEY_VENDOR_CONTACT_NAME, vendorcontactname);
        values.put(KEY_VENDOR_ADD_CATEGORY, vendoraddcategory);
        values.put(KEY_VENDOR_CONTACT, vendorcontact);
        values.put(KEY_VENDOR_EMAIL, vendoremail);
        values.put(KEY_VENDOR_COMPANY_WEBSITE, vendorcompanywebsite);
        values.put(KEY_VENDOR_COMMENTS, vendorcomments);
        db.insert(TABLE_VENDOR, null, values);

    }

    //delete vendor
    public void deleteVendor(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VENDOR + " WHERE " + KEY_VENDOR_ID + "='" + id + "'");
        db.close();
    }

    //update vendor
    public void updateVendor(String vendorid, String vendorcompanyname, String vendorcontactname, String vendoraddcategory, String vendorcontact, String vendoremail, String vendorcompanywebsite, String vendorcomments) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VENDOR_COMPANY_NAME, vendorcompanyname);
        values.put(KEY_VENDOR_CONTACT_NAME, vendorcontactname);
        values.put(KEY_VENDOR_ADD_CATEGORY, vendoraddcategory);
        values.put(KEY_VENDOR_CONTACT, vendorcontact);
        values.put(KEY_VENDOR_EMAIL, vendoremail);
        values.put(KEY_VENDOR_COMPANY_WEBSITE, vendorcompanywebsite);
        values.put(KEY_VENDOR_COMMENTS, vendorcomments);
        db.update(TABLE_VENDOR, values, KEY_VENDOR_ID + " = ?", new String[]
                {String.valueOf(vendorid)});
    }

    //add contacts
    public void addHomeImages(String homeimages) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOME_ADD_IMAGE, homeimages);

        db.insert(TABLE_HOME_ADD_IMAGES, null, values);
        Log.d("AddImageHome", "" + values);
    }

    //delete contacts
    public void deleteHomeImages(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HOME_ADD_IMAGES + " WHERE " + KEY_HOME_ADD_IMAGES_ID + "='" + id + "'");
        db.close();
    }

    //update receipts
    public void updateHomeImages(String homeimageid, String homeimages) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOME_ADD_IMAGE, homeimages);
        db.update(TABLE_HOME_ADD_IMAGES, values, KEY_HOME_ADD_IMAGES_ID + " = ?", new String[]
                {String.valueOf(homeimageid)});
    }


    // Getting All vendor details
    public List<HashMap<String, String>> getAllVendorDetails() {

        List<HashMap<String, String>> vendorDetailList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VENDOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();


                map.put("vendor_id", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_ID)));
                map.put("vendor_company_name", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_COMPANY_NAME)));
                map.put("vendor_contact_name", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_CONTACT_NAME)));
                map.put("vendor_add_category", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_ADD_CATEGORY)));
                map.put("vendor_contact", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_CONTACT)));
                map.put("vendor_email", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_EMAIL)));
                map.put("vendor_comapany_website", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_COMPANY_WEBSITE)));
                map.put("vendor_comments", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_COMMENTS)));

                // Adding details to list
                vendorDetailList.add(map);


            } while (cursor.moveToNext());
        }
        // return User Detail list
        return vendorDetailList;
    }

    public List<HashMap<String, String>> getCountAllVendorDetails() {
        List<HashMap<String, String>> vendorcountDetailList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  *," + KEY_VENDOR_ADD_CATEGORY + " FROM " + TABLE_VENDOR + " Group by " + KEY_VENDOR_ADD_CATEGORY;
        Log.e("clist", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("vendor_id", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_ID)));
                map.put("vendor_add_category", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_ADD_CATEGORY)));

                // Adding details to list
                vendorcountDetailList.add(map);
                Log.d("list", "" + map);

            } while (cursor.moveToNext());
        }
        // return User Detail list
        return vendorcountDetailList;
    }

    // TODO: 2/10/2017 get vendor list
    public List<HashMap<String, String>> getCountVendorAllVendorDetails(/*int id*/) {
        List<HashMap<String, String>> vendorcountDetailList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT *," + KEY_VENDOR_COMPANY_NAME + " FROM " + TABLE_VENDOR
                //  + " WHERE '" + KEY_VENDOR_ID + "'= " + id
                + " Group by " + KEY_VENDOR_COMPANY_NAME;


        Log.e("vlist", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("Count_vendor_id", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_ID)));
                map.put("Count_vendor_company_name", cursor.getString(cursor.getColumnIndex(KEY_VENDOR_COMPANY_NAME)));

                // Adding details to list
                vendorcountDetailList.add(map);
            } while (cursor.moveToNext());
        }
        // return User Detail list
        return vendorcountDetailList;
    }


    // Getting All receipts details as per count
    public List<HashMap<String, String>> getCountAllReceiptsDetails() {
        List<HashMap<String, String>> receiptsCountDetailList = new ArrayList<>();

        String selectQuery = "SELECT  *,SUM(" + KEY_RECEIPTS_COST + ") as Sum , SUM(" + KEY_RECEIPTS + ") as Qty , " + KEY_RECEIPTS_PRODUCT + "," + KEY_RECEIPTS_COST + "," + KEY_RECEIPTS + " FROM " + TABLE_RECEIPTS + " Group by " + KEY_RECEIPTS_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();

                map.put("receipts_id", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_ID)));
                map.put("receipts_image", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_IMAGE)));
                map.put("receipts_date", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_DATE)));
                map.put("receipts_product", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_PRODUCT)));
                map.put("receipts_cost", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_COST)));
                map.put("receipts_relative_business", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_RELATIVE_BUSINESS)));
                map.put("receipts_notes", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_NOTE)));
                map.put("receipts", cursor.getString(cursor.getColumnIndex("Qty")));
                map.put("receipts_total", cursor.getString(cursor.getColumnIndex("Sum")));
                //   map.put("receipts_relative_business", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_RELATIVE_BUSINESS)));
                //   map.put("receipts_notes", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_NOTE)));
                // Adding details to list
                receiptsCountDetailList.add(map);

                Log.d("GET_DATA", "" + map);

            } while (cursor.moveToNext());
        }

        // return receipts Detail list
        return receiptsCountDetailList;
    }

    // Getting Home Image
    public List<HashMap<String, String>> getAllHomeImagesList() {
        List<HashMap<String, String>> addHomeImagesList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HOME_ADD_IMAGES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();

                map.put("addhome_id", cursor.getString(cursor.getColumnIndex(KEY_HOME_ADD_IMAGES_ID)));
                map.put("addhome_image", cursor.getString(cursor.getColumnIndex(KEY_HOME_ADD_IMAGE)));

                // Adding details to list
                addHomeImagesList.add(map);

                Log.d("GET_HOME_IMAGES", "" + map);

            } while (cursor.moveToNext());
        }
        // return vendor Detail list
        return addHomeImagesList;
    }

    // Getting All receipts details
    public List<HashMap<String, String>> getAllReceiptsDetails() {
        List<HashMap<String, String>> receiptsDetailList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECEIPTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();

                map.put("receipts_id", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_ID)));
                map.put("receipts_image", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_IMAGE)));
                map.put("receipts_date", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_DATE)));
                map.put("receipts_product", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_PRODUCT)));
                map.put("receipts_cost", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_COST)));
                map.put("receipts_relative_business", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_RELATIVE_BUSINESS)));
                map.put("receipts_notes", cursor.getString(cursor.getColumnIndex(KEY_RECEIPTS_NOTE)));

                // Adding details to list
                receiptsDetailList.add(map);

                Log.d("GET_DATA", "" + map);

            } while (cursor.moveToNext());
        }
        // return receipts Detail list
        return receiptsDetailList;
    }

    // Getting All appliance details
    public List<HashMap<String, String>> getAllApplianceDetails() {
        List<HashMap<String, String>> applianceDetailList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_APPLIANCE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();

                map.put("appliance_id", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_ID)));
                map.put("appliance_image", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_IMAGE)));
                map.put("appliance_date", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_DATE)));
                map.put("appliance_brand", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_BRAND)));
                map.put("appliance_model", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_MODEL)));
                map.put("appliance_serial", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_SERIAL)));
                map.put("appliance_price", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_PRICE)));
                map.put("appliance_category", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_CATEGORY)));
                map.put("appliance_relative_business", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_RELATIVE_BUSINESS)));
                map.put("appliance_notes", cursor.getString(cursor.getColumnIndex(KEY_APPLIANCE_NOTE)));

                // Adding details to list
                applianceDetailList.add(map);

                Log.d("GET_DATA", "" + map);

            } while (cursor.moveToNext());
        }
        // return vendor Detail list
        return applianceDetailList;
    }
}
