package com.taca.boombuy;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.taca.boombuy.ui.mainview.activity.MainActivity;

public class TutorialActivity extends AppCompatActivity {

    ViewPager viewPager_tutorial;
    TextView curDot_tutorial;
    TutorialActivity.TutorialPageAdapter tutorialPageAdapter;
    int poster[] =
            {
                    R.drawable.banner_1,
                    R.drawable.banner_2,
                    R.drawable.banner_3
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ///////뷰페이저/////////////////////////////////////////////////////////////////////////////////
        viewPager_tutorial = (ViewPager) findViewById(R.id.viewPager_tutorial);
        curDot_tutorial = (TextView) findViewById(R.id.curDot_tutorial);
        tutorialPageAdapter = new TutorialActivity.TutorialPageAdapter();
        viewPager_tutorial.setAdapter(tutorialPageAdapter); // 뷰페이져에  페이지어뎁터를 넣는다
        viewPager_tutorial.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("SCPAGE", position + " : " + positionOffset + " : " + positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(MainActivity.this, position + "째 그림", Toast.LENGTH_SHORT).show();
                changeDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.i("SCSTATE", "변경 : " + state);

            }
        });

        pageCurPage = 0;
    }

    // 하단 페이지 도트 변경 view pager에서

    int pageCurPage;

    public void changeDot(int position) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < poster.length; i++) {

            if (i == position)
                sb.append("● ");
            else
                sb.append("○ ");
        }
        curDot_tutorial.setText(sb.toString().trim());
    }
    class TutorialPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return poster.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        // 뷰 추가
        @Override
        public Object instantiateItem(View container, final int position) {
            // position => 요청페이지 > 요청 페이지별 뷰를 생성해서 처리
            // 요청페이지 해당하는 url 획득
            int banner_image = poster[position];

            //이미지뷰 생성
            ImageView imageView = new ImageView(TutorialActivity.this);

            //이미지 셋팅
            imageView.setBackgroundResource(banner_image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY); // x,y 축 꽉채우기

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            ((ViewPager) container).addView(imageView);
            return imageView;
        }

        // 뷰 제거
        @Override
        public void destroyItem(View container, int position, Object object) {
            // super.destroyItem(container, position, object);
            ((ViewPager) container).removeView((View) object);
        }
    }
}
