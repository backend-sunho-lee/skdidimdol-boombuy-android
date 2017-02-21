package com.taca.boombuy.ui.mainview.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;
import com.taca.boombuy.R;
import com.taca.boombuy.evt.OTTOBus;
import com.taca.boombuy.modelRes.ResBbSearchItemBody;
import com.taca.boombuy.modelRes.ResBbSearchItemId;
import com.taca.boombuy.net.Network;
import com.taca.boombuy.ui.mainview.fragment.GiftSelectProductDetailFrag;
import com.taca.boombuy.ui.mainview.fragment.GiftSelectProductMatterFrag;
import com.taca.boombuy.util.ImageProc;

public class GiftSelectDetailInfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    // 상품번호 담아올 변수

    ImageView selected_gift_imageview;
    ResBbSearchItemId reqBbSearchItemId;

    boolean ottoFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_select_detail_info);

        ResBbSearchItemBody item = (ResBbSearchItemBody)getIntent().getSerializableExtra("item");
        Log.i("ITEM DATA", item.toString());

        Network.getInstance().bb_search_item_Id(this, item.getId());
        OTTOBus.getInstance().getSelected_item_detail_bus().register(this);

        selected_gift_imageview = (ImageView) findViewById(R.id.selected_gift_imageview);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gift_select_detail_info, menu);
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



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Fragment [] frags = new Fragment[]{
                new GiftSelectProductDetailFrag(), new GiftSelectProductMatterFrag()
        };

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
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
                    return "상품설명";
                case 1:
                    return "유의사항";
            }
            return null;
        }
    }

    @Subscribe
    public void FinishLoad(ResBbSearchItemId data){
        reqBbSearchItemId = data;
        ImageProc.getInstance().drawImage(reqBbSearchItemId.getBody().getLocation() , selected_gift_imageview);
    }
}
