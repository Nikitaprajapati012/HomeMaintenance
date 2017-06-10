package com.example.archi.homemaintenance.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.archi.homemaintenance.Constant.TestSharedPrefManager;
import com.example.archi.homemaintenance.R;

public class Test extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        TestSharedPrefManager.Init(this);
    }

    public void onClickStore(View v) {

        //get user input first, then store. we will use our SharedPrefManager Class functions
        EditText editTextName,editTextEmpID,editTextAge;
        editTextName=(EditText)findViewById(R.id.editTextEnterName);
        editTextEmpID=(EditText)findViewById(R.id.editTextEnterEid);
        editTextAge=(EditText)findViewById(R.id.editTextEnterAge);

        //convert EditText to string
        String srtTextName = editTextName.getText().toString();
        String srtTextEmpID = editTextEmpID.getText().toString();
        String strTextAge = editTextAge.getText().toString();

        if(0!= srtTextName.length())
            TestSharedPrefManager.SetName(srtTextName); // need string value so convert it
        if(0 !=srtTextEmpID.length())
            TestSharedPrefManager.SetEmployeeID(srtTextEmpID); // need string value so convert it
        if(0 != strTextAge.length())
            TestSharedPrefManager.SetAge(Integer.parseInt(strTextAge)); // need integer value so convert it

        //now save all to shared pref, all updated values are now available in SharedPrefManager class, as we set above
        TestSharedPrefManager.StoreToPref();

        //reset all fields to blank before load and update from sharedpref
        EditText tv = null;
        tv = (EditText)findViewById(R.id.editTextEnterName);
        tv.setText("");
        tv = (EditText)findViewById(R.id.editTextEnterEid);
        tv.setText("");
        tv = (EditText)findViewById(R.id.editTextEnterAge);
        tv.setText("");
        Toast.makeText(this, "Data Successfully Stored to SharedPreference", Toast.LENGTH_LONG).show();
    }

    public void onClickLoad(View v) {

        //Get all values from SharedPrefference file
        TestSharedPrefManager.LoadFromPref(); // all values are loaded into corresponding variables of SharedPrefManager class

        //Now get the values form SharedPrefManager class using it's static functions.
        String strTextName,strTextEmpID;
        int iTextAge;
        strTextName = TestSharedPrefManager.GetName();
        strTextEmpID = TestSharedPrefManager.GetEmployeeID();
        iTextAge = TestSharedPrefManager.GetAge();

        //Now we can show these persistent values on our activity (GUI)
        EditText tv = null;
        tv = (EditText)findViewById(R.id.editTextEnterName);
        tv.setText(strTextName);
        tv = (EditText)findViewById(R.id.editTextEnterEid);
        tv.setText(strTextEmpID);
        tv = (EditText)findViewById(R.id.editTextEnterAge);
        tv.setText(String.valueOf(iTextAge));

        Toast.makeText(this, "Data Successfully Loaded from SharedPreference", Toast.LENGTH_LONG).show();
    }

}