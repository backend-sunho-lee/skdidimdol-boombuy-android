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
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.ui.mainview.fragment.brandBaseFrag;
import com.taca.boombuy.ui.mainview.fragment.brandfrag;
import com.taca.boombuy.ui.mainview.fragment.couponfrag;
import com.taca.boombuy.ui.mainview.fragment.totalfrag;

public class MainProduct extends AppCompatActivity implements
        brandBaseFrag.OnFragmentInteractionListener,
        couponfrag.OnFragmentInteractionListener,
        brandfrag.OnFragmentInteractionListener{

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
                new totalfrag(), new brandBaseFrag(), new couponfrag()
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

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "전체 상품";
                case 1:
                    return "브랜드";
                case 2:
                    return "상품권";
            }
            return null;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        OttoBus.getInstance().getSearchItems_Bus().unregister(this);

        OttoBus.getInstance().getSearchBrands_Bus().unregister(this);
        OttoBus.getInstance().getSearchBrandItem_Bus().unregister(this);

        OttoBus.getInstance().getSearchCoupons_Bus().unregister(this);
    }
}
