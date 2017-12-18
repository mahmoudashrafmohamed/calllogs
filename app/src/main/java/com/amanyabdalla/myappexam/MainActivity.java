package com.amanyabdalla.myappexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ViewPager viewPager;
    //  TabLayout tabLayout;
    String[] titles = {"Incoming", "Settings"};
    private Toolbar toolbar;
    private RelativeLayout rlToolbar;
    private TabLayout tabLayoutProfile1;
    private RelativeLayout contentProfileMine;
    private ViewPager viewpagerProfile1;
    StringBuffer sbIncoming;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ProfileStyle1Adapter adapter = new ProfileStyle1Adapter(getSupportFragmentManager());
        viewpagerProfile1.setAdapter(adapter);

        tabLayoutProfile1.setupWithViewPager(viewpagerProfile1);

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rlToolbar = (RelativeLayout) findViewById(R.id.rlToolbar);
        tabLayoutProfile1 = (TabLayout) findViewById(R.id.tab_layout_profile1);
        contentProfileMine = (RelativeLayout) findViewById(R.id.content_profile_mine);
        viewpagerProfile1 = (ViewPager) findViewById(R.id.viewpager_profile1);
    }

    // view pager adapter
    public class ProfileStyle1Adapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragments;

        public ProfileStyle1Adapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            fragments.add(new Incoming());
            fragments.add(new Settings());

        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int arrayPos) {
            return titles[arrayPos];
        }
    }



}
