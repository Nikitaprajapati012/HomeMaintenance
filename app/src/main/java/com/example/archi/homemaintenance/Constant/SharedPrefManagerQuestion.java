package com.example.archi.homemaintenance.Constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ravi archi on 2/15/2017.
 */

public class SharedPrefManagerQuestion {

    public static final String MY_EMP_PREFS = "MySharedPref";
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    private static Context mContext;
    private static String mQuestion = "";
    private static String mAnswer = "";
    private static String mId = "";

    public static void Init(Context context) {
        mContext = context;
        sp = context.getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void WriteSharePrefrence(Context context, String key, String value) {
        SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        //mQuestion = settings.getString("Question", "");
        //mAnswer = settings.getString("Answer", "");
        //mId = settings.getString("Id", " ");

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String ReadSharePrefrence(Context context, String key) {

       /* SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Question", mQuestion);
        editor.putString("Answer", mAnswer);
        editor.putString("Id", mId);
        Log.d("share", "" + mQuestion + mAnswer + mId);
        editor.commit();*/
        String data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        data = preferences.getString(key, "");
        editor.commit();
        return data;
    }

    public static void ClearaSharePrefrence(Context context) {
        String data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit().clear();
        editor.commit();

    }
    /*public static void DeleteSingleEntryFromPref(String keyName) {
        SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
        //need an editor to edit and save values
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(keyName);
        editor.commit();
    }*/

    /* public static void DeleteAllEntriesFromPref() {
         SharedPreferences settings = mContext.getSharedPreferences(MY_EMP_PREFS, 0);
         //need an editor to edit and save values
         SharedPreferences.Editor editor = settings.edit();
         editor.clear();
         editor.commit();
     }
 */
    public static void SetQuetion(String quetion) {
        mQuestion = quetion;
    }

    public static void SetAnswer(String answer) {
        mAnswer = answer;
    }

    public static void SetId(String id) {
        mId = id;
    }

    public static String GetQuetion() {
        return mQuestion;
    }

    public static String GetAnswer() {
        return mAnswer;
    }

    public static String GetId() {
        return mId;
    }
}
