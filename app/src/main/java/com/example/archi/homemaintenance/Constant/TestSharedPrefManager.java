package com.example.archi.homemaintenance.Constant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ravi archi on 2/15/2017.
 */

public class TestSharedPrefManager {
    //this is your shared preference file name, in which we will save data
    public static final String MY_EMP_PREFS = "MySharedPref";

    //saving the context, so that we can call all
    //shared pref methods from non activity classes.
    //because getSharedPreferences required the context.
    //but in activity class we can call without this context
    private static Context 	mContext;

    // will get user input in below variables, then will store in to shared pref
    private static String 	mName 			= "";
    private static String 	mEid 			= "";
    private static int 		mAge 			= 0;

    public static void Init(Context context)
    {
        mContext 		= context;
    }
    public static void LoadFromPref()
    {
        SharedPreferences settings 	= mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        // Note here the 2nd parameter 0 is the default parameter for private access,
        //Operating mode. Use 0 or MODE_PRIVATE for the default operation,
        mName 			= settings.getString("Name",""); // 1st parameter Name is the key and 2nd parameter is the default if data not found
        mEid 			= settings.getString("EmpID","");
        mAge 			= settings.getInt("Age",0);
    }
    public static void StoreToPref()
    {
        // get the existing preference file
        SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        //need an editor to edit and save values
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Name",mName); // Name is the key and mName is holding the value
        editor.putString("EmpID",mEid);// EmpID is the key and mEid is holding the value
        editor.putInt("Age", mAge); // Age is the key and mAge is holding the value

        //final step to commit (save)the changes in to the shared pref
        editor.commit();
    }

    public static void DeleteSingleEntryFromPref(String keyName)
    {
        SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        //need an editor to edit and save values
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(keyName);
        editor.commit();
    }

    public static void DeleteAllEntriesFromPref()
    {
        SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        //need an editor to edit and save values
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public static void SetName(String name)
    {
        mName =name;
    }
    public static void SetEmployeeID(String empID)
    {
        mEid = empID ;
    }
    public static void SetAge(int age)
    {
        mAge = age;
    }

    public static String GetName()
    {
        return mName ;
    }
    public static String GetEmployeeID()
    {
        return mEid ;
    }
    public static int GetAge()
    {
        return mAge ;
    }
}
