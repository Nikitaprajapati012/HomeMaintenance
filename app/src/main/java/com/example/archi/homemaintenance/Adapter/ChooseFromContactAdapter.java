package com.example.archi.homemaintenance.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.Activity.ActivityAddVendorManually;
import com.example.archi.homemaintenance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archirayan on 27-Dec-16.
 */

public class ChooseFromContactAdapter extends RecyclerView.Adapter<ChooseFromContactAdapter.Holder> {

    public List<HashMap<String, String>> contactsDetailList = new ArrayList<>();
    public Context context;
    public HashMap<String, String> contactObj;

    public ChooseFromContactAdapter(Context context, List<HashMap<String, String>> contactsDetailList) {
        Log.d("Length", "" + contactsDetailList.size());
        this.context = context;
        this.contactsDetailList = contactsDetailList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_contacts_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        contactObj = contactsDetailList.get(position);
        holder.contactsName.setText(contactObj.get("contacts_name"));
        holder.contactsNumber.setText(contactObj.get("contacts_number"));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactsList(position);
                ((Activity)context).finish();

            }
        });
    }

    private void openContactsList(final int position) {

        final  HashMap<String, String> contactObj = contactsDetailList.get(position);

        Intent updateContactList = new Intent(context, ActivityAddVendorManually.class);
        //updateContactList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        updateContactList.putExtra("ContactsId", contactObj.get("contacts_id"));
        updateContactList.putExtra("ContactsName", contactObj.get("contacts_name"));
        updateContactList.putExtra("ContactsNumber", contactObj.get("contacts_number"));

        Log.d("contactAdapter", " " + contactObj.get("contacts_name") + contactObj.get("contacts_number"));

        context.startActivity(updateContactList);
    }


    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return contactsDetailList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView contactsName, contactsNumber;
        public LinearLayout linearLayout;

        public Holder(View view) {
            super(view);
            contactsName = (TextView) view.findViewById(R.id.adapter_contacts_list__txtcontactname);
            contactsNumber = (TextView) view.findViewById(R.id.adapter_contacts_list__txtcontactnumber);
            linearLayout = (LinearLayout) view.findViewById(R.id.adapter_contacts_list_layout);

        }
    }
}




