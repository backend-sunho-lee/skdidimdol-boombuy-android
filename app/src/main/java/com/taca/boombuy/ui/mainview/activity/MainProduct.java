package com.taca.boombuy.ui.mainview.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.taca.boombuy.R;
import com.taca.boombuy.ui.mainview.fragment.brandfrag;
import com.taca.boombuy.ui.mainview.fragment.couponfrag;
import com.taca.boombuy.ui.mainview.fragment.totalfrag;

public class MainProduct extends AppCompatActivity implements
        brandfrag.OnFragmentInteractionListener,
        couponfrag.OnFragmentInteractionListener {

    TabLayout tabLayout;
    ViewPager viewPager;

    FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);


        fragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        Fragment[] frags = new Fragment[]{
                new totalfrag(), new brandfrag(), new couponfrag()
        };

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
