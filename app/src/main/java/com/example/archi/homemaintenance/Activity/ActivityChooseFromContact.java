package com.example.archi.homemaintenance.Activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.archi.homemaintenance.Adapter.ChooseFromContactAdapter;
import com.example.archi.homemaintenance.DbHelper.DbHelper;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityChooseFromContact extends AppCompatActivity {
    public RecyclerView recyclerViewContacts;
    public Context context;
    public DbHelper db;
    public List<HashMap<String, String>> contactsDetailList = new ArrayList<>();
    public ChooseFromContactAdapter adapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_from_contact);

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                HashMap<String, String> contactObj = new HashMap<>();
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contactObj.put("contacts_name", name);
                        contactObj.put("contacts_number", phoneNo);
                        contactsDetailList.add(contactObj);
                        /*Toast.makeText(ActivityChooseFromContact.this, "Name: " + name
                                + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();*/
                    }
                    pCur.close();
                }
            }
            recyclerViewContacts = (RecyclerView) findViewById(R.id.activity_choose_from_contact_recyclerview);
            adapater = new ChooseFromContactAdapter(this, contactsDetailList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ActivityChooseFromContact.this);
            recyclerViewContacts.setLayoutManager(mLayoutManager);
            recyclerViewContacts.setItemAnimator(new DefaultItemAnimator());
            recyclerViewContacts.setAdapter(adapater);
        }

    }


}
