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
import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResItemDetail;
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.networkmodel.ItemDTO;
import com.taca.boombuy.ui.mainview.fragment.GiftSelectProductDetailFrag;
import com.taca.boombuy.ui.mainview.fragment.GiftSelectProductMatterFrag;
import com.taca.boombuy.util.ImageProc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftSelectDetailInfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    // 상품번호 담아올 변수

    ImageView selected_gift_imageview;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ;
    ResItemDetail resItemDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_select_detail_info);

        ItemDTO item = (ItemDTO)getIntent().getSerializableExtra("item");
        Log.i("ITEM DATA", item.toString());


        Call<ResItemDetail> NetSearchItemDetail = NetSSL.getInstance().getMemberImpFactory().NetSearchItemDetail(item.getBid());
        NetSearchItemDetail.enqueue(new Callback<ResItemDetail>() {
            @Override
            public void onResponse(Call<ResItemDetail> call, Response<ResItemDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResult() != null) {

                        OttoBus.getInstance().getSearchItemDetail_Bus().post(response.body());

                    } else {
                        Log.i("RESPONSE RESULT 1: ", response.message());
                    }
                } else {
                    Log.i("RESPONSE RESULT 2 : ", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResItemDetail> call, Throwable t) {

            }
        });

        OttoBus.getInstance().getSearchItemDetail_Bus().register(this);

        selected_gift_imageview = (ImageView) findViewById(R.id.selected_gift_imageview);


        ImageProc.getInstance().drawImage(item.getLocation(), selected_gift_imageview);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.gift_select_detail_tabs);
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
    public void FinishLoad(ResItemDetail data){

        resItemDetail = data;

        Log.i("fff" , resItemDetail.getResult().getLocation());
        ImageProc.getInstance().drawImage(resItemDetail.getResult().getLocation(), selected_gift_imageview);
        OttoBus.getInstance().getSearchItemDetail_Bus().unregister(this);
    }
}
