package com.taca.boombuy.ui.mainview.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.taca.boombuy.R;
import com.taca.boombuy.ui.mainview.fragment.ReceivedGift;
import com.taca.boombuy.ui.mainview.fragment.SentGift;

public class GiftManageActivity extends AppCompatActivity implements ReceivedGift.OnFragmentInteractionListener,
SentGift.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private FragmentAdapter fragPagerAdapter ;

    ViewPager mViewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_manage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager = (ViewPager) findViewById(R.id.container);
        //tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        fragPagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(fragPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gift_manage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class FragmentAdapter extends FragmentPagerAdapter {

        Fragment[] frags = new Fragment[]{
                new ReceivedGift(), new SentGift()
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

}
