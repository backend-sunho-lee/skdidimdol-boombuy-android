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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.ui.mainview.fragment.ReceivedGiftFrag;
import com.taca.boombuy.ui.mainview.fragment.SentGiftFrag;

public class GiftManageActivity extends AppCompatActivity implements SentGiftFrag.OnFragmentInteractionListener,
ReceivedGiftFrag.OnFragmentInteractionListener{

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

        Log.i("TOTAL PRODUCT", Single_Value.getInstance().vo_gift_total_member.toString());

        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        fragPagerAdapter = new FragmentAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(fragPagerAdapter);

        if(getIntent().getStringExtra("receivedFCM") != null){
            mViewPager.setCurrentItem(1);
        }
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
                new SentGiftFrag(), new ReceivedGiftFrag()
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

            if(position == 0) {

                return "보낸 선물";

            }else{
                return "받은 선물";
            }
        }
    }

}
