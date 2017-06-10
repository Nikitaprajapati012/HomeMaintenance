package com.example.archi.homemaintenance.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.archi.homemaintenance.Fragment.AllScreen.AppllianceFragment;
import com.example.archi.homemaintenance.Fragment.AllScreen.DirectoryFragment;
import com.example.archi.homemaintenance.Fragment.AllScreen.HomeFragment;
import com.example.archi.homemaintenance.Fragment.AllScreen.HomeMainFragment;
import com.example.archi.homemaintenance.Fragment.AllScreen.ReceiptsFragment;
import com.example.archi.homemaintenance.Fragment.AllScreen.ReminderFragment;
import com.example.archi.homemaintenance.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public RelativeLayout headerView, footerView, drawerView;
    public ImageView imgHome, imgReminders, imgDirectory, imgAppliances, imgReceipts, imgMenu, imgAdd;
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;
    public DrawerLayout mDrawerLayout;
    public TextView txtManageHome, txtDemoVideo, txtLocal, txtHomeReqest;
    public RelativeLayout headerViewFrame;
    public String ID, DialogReqConnect, DialogRequest, LocalMailSubject, LocalMailContent, LocalMailContent1, LocalMailContent2,
            HomeRequestMailSubject, HomeRequestMailTitle, HomeRequestMailContent, HomeRequestMailZipCode, HomeRequestMailContent1, HomeRequestMailContent2;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        click();
        init();

        fragmentManager = getSupportFragmentManager();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                headerViewFrame.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
                mDrawerLayout.setScrimColor(ContextCompat.getColor(MainActivity.this, android.R.color.transparent));
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        imgHome.performClick();

    }

    @SuppressLint("NewApi")
    private void init() {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_contain_layout, new HomeMainFragment());
        tx.commit();

        imgHome.setImageAlpha(255);
        imgReceipts.setImageAlpha(100);
        imgAppliances.setImageAlpha(100);
        imgDirectory.setImageAlpha(100);
        imgReminders.setImageAlpha(100);
    }

    private void click() {
        imgMenu.setOnClickListener(this);
        imgHome.setOnClickListener(this);
        imgReminders.setOnClickListener(this);
        imgDirectory.setOnClickListener(this);
        imgAppliances.setOnClickListener(this);
        imgReceipts.setOnClickListener(this);
        txtManageHome.setOnClickListener(this);
        txtDemoVideo.setOnClickListener(this);
        txtLocal.setOnClickListener(this);
        txtHomeReqest.setOnClickListener(this);
    }

    private void findById() {
        headerView = (RelativeLayout) findViewById(R.id.headerview);
        headerViewFrame = (RelativeLayout) findViewById(R.id.headerview_frame);
        footerView = (RelativeLayout) findViewById(R.id.footerview);
        imgAdd = (ImageView) findViewById(R.id.header_iv_add_main);
        imgAdd.setVisibility(View.GONE);
        txtLocal = (TextView) findViewById(R.id.activity_main_txtlocal);
        txtHomeReqest = (TextView) findViewById(R.id.activity_main_homerequest);
        txtManageHome = (TextView) findViewById(R.id.activity_main_home);
        txtDemoVideo = (TextView) findViewById(R.id.activity_main_demovideo);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (RelativeLayout) findViewById(R.id.drawerView);
        imgMenu = (ImageView) findViewById(R.id.header_iv_drawer);
        imgHome = (ImageView) findViewById(R.id.footer_imghome);
        imgReminders = (ImageView) findViewById(R.id.footer_imgreminder);
        imgDirectory = (ImageView) findViewById(R.id.footer_imgdirectory);
        imgAppliances = (ImageView) findViewById(R.id.footer_imgapplicance);
        imgReceipts = (ImageView) findViewById(R.id.footer_imgreceipts);

    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        fragmentManager = getSupportFragmentManager();

        switch (v.getId()) {
            case R.id.activity_main_home:
                Intent ilocal = new Intent(MainActivity.this, ActivityAddHomeDetails.class);
                startActivity(ilocal);
                break;
            case R.id.activity_main_txtlocal:
                // TODO: 2/17/2017 open dialog for send local message in mail
                openDialogForLocalMessage();
                break;
            case R.id.activity_main_homerequest:
                // TODO: 2/20/2017 open dialog for send home request in mail
                openMailForHomeRequest();
                break;
            case R.id.activity_main_connecthomemaintenance:
                // TODO: 2/20/2017 open dialog for send home request in mail
                openMailForHomeRequest();
                break;

            case R.id.activity_main_demovideo:
                /*String videoId = "results?search_query=homekeeper";
                Intent idemovideo = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId));
                idemovideo.putExtra("VIDEO_ID", videoId);
                startActivity(idemovideo);*/
                String videoId = "results?search_query=homekeeper";
                Intent idemovideo = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + videoId));
                idemovideo.putExtra("VIDEO_ID", videoId);
                startActivity(idemovideo);
                break;

            case R.id.header_iv_drawer:
                if (drawerView.getVisibility() == View.VISIBLE) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;

            case R.id.footer_imghome:
                imgAdd.setVisibility(View.VISIBLE);
                headerView.setVisibility(View.VISIBLE);
                fragment = new HomeMainFragment();
                imgHome.setImageAlpha(255);
                imgReceipts.setImageAlpha(100);
                imgAppliances.setImageAlpha(100);
                imgDirectory.setImageAlpha(100);
                imgReminders.setImageAlpha(100);
                break;

            case R.id.footer_imgreminder:
                imgAdd.setVisibility(View.VISIBLE);
                headerView.setVisibility(View.VISIBLE);
                fragment = new ReminderFragment();
                imgHome.setImageAlpha(100);
                imgReceipts.setImageAlpha(100);
                imgAppliances.setImageAlpha(100);
                imgDirectory.setImageAlpha(100);
                imgReminders.setImageAlpha(255);
                break;


            case R.id.footer_imgdirectory:
                imgAdd.setVisibility(View.VISIBLE);
                headerView.setVisibility(View.VISIBLE);
                fragment = new DirectoryFragment();
                imgHome.setImageAlpha(100);
                imgReceipts.setImageAlpha(100);
                imgAppliances.setImageAlpha(100);
                imgDirectory.setImageAlpha(255);
                imgReminders.setImageAlpha(100);
                break;

            case R.id.footer_imgapplicance:
                imgAdd.setVisibility(View.VISIBLE);
                headerView.setVisibility(View.VISIBLE);
                fragment = new AppllianceFragment();
                imgHome.setImageAlpha(100);
                imgReceipts.setImageAlpha(100);
                imgAppliances.setImageAlpha(255);
                imgDirectory.setImageAlpha(100);
                imgReminders.setImageAlpha(100);
                break;


            case R.id.footer_imgreceipts:
                headerView.setVisibility(View.VISIBLE);
                fragment = new ReceiptsFragment();
                imgHome.setImageAlpha(100);
                imgReceipts.setImageAlpha(255);
                imgAppliances.setImageAlpha(100);
                imgDirectory.setImageAlpha(100);
                imgReminders.setImageAlpha(100);
                break;

            default:
                fragment = new HomeFragment();
                imgHome.setImageAlpha(255);
                imgReceipts.setImageAlpha(100);
                imgAppliances.setImageAlpha(100);
                imgDirectory.setImageAlpha(100);
                imgReminders.setImageAlpha(100);
                break;
        }

        if (fragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_contain_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            mDrawerLayout.closeDrawer(Gravity.LEFT);

        }
    }

    private void openDialogForLocalMessage() {
        DialogReqConnect = getString(R.string.local_dialog_content1);
        DialogRequest = getString(R.string.local_dialog_content2);
        LocalMailSubject = getString(R.string.local_mail_subject);
        LocalMailContent = getString(R.string.local_mail_content);
        LocalMailContent1 = getString(R.string.local_mail_zipcode);
        LocalMailContent2 = getString(R.string.local_mail_sincerely);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.local_title);
        alertDialog.setMessage(DialogReqConnect + " \n\n" + DialogRequest);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@homemainteneance.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, LocalMailSubject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, LocalMailContent + "\n\n" + LocalMailContent1 + " 384002" + "\n\n" + LocalMailContent2 + "\n" + "Username");
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }

    // TODO: 2/20/2017 send home evolation request
    private void openMailForHomeRequest() {
        HomeRequestMailTitle = getString(R.string.home_request_title);
        HomeRequestMailSubject = getString(R.string.home_request_subject);
        HomeRequestMailContent = getString(R.string.home_request_content);
        HomeRequestMailContent1 = getString(R.string.home_request_content1);
        HomeRequestMailZipCode = getString(R.string.home_request_zipcode);
        HomeRequestMailContent2 = getString(R.string.home_request_sincerely);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@homemainteneance.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, HomeRequestMailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, HomeRequestMailContent + "\n\n" + HomeRequestMailZipCode + " 384002" + "\n\n" + HomeRequestMailContent1 + " archi1@gmail.com" + "\n\n" + HomeRequestMailContent2 + "\n" + "Username");
        startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
    }
}
