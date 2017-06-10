package com.example.archi.homemaintenance.Constant;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ravi archi on 1/25/2017.
 */

public class Validation {
    public static final String ZIP_CODE_REGEX = "^[0-9]{5}$|(^([0-9]{6})$)(?:-[0-9]{4})?$";

    public static final String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static final String PHONE_REGEX = "[\\+]\\d{3}\\d{7}";

    public static final String WEBSITE_REGEX = "^[a-zA-Z0-9\\-\\.]+\\.(com|org|net|mil|edu|COM|ORG|NET|MIL|EDU)$";

    public static boolean isEmailAddress(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isZipCode(String zipcode) {
        Pattern pattern = Pattern.compile(ZIP_CODE_REGEX);
        Matcher matcher = pattern.matcher(zipcode);
        return matcher.matches();
    }

    public static boolean isWebsite(CharSequence phone) {
        Pattern pattern = Pattern.compile(WEBSITE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        // text required and editText is blank, so return false
        //if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        ;

        return true;
    }
   /* public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError("Required to fill");
            return false;
        }

        return true;
    }*/
}
