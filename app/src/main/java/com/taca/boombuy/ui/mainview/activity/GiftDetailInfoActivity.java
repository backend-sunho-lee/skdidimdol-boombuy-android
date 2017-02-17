package com.taca.boombuy.ui.mainview.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.vo.VO_from_friends_info;
import com.taca.boombuy.vo.VO_giftitem_list;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftDetailInfoActivity extends AppCompatActivity {

    ListView gift_detail_member_listview;
    ListView gift_detail_product_listview;
    DetailMemberListAdapter memberListAdapter;
    DetailProductListAdapter productListAdapter;

    View gift_detail_footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("ArrayData", Single_Value.getInstance().vo_gift_total_member.toString());

        Log.i("ggg", Single_Value.getInstance().SenderNReceiver.getVO_giftitem_total_list().size()+"");

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);*/

        setContentView(R.layout.activity_gift_detail_info);

        gift_detail_footer = getLayoutInflater().inflate(R.layout.activity_gift_detail_footer, null);

        gift_detail_member_listview = (ListView) findViewById(R.id.gift_detail_member_listview);
        gift_detail_product_listview = (ListView) gift_detail_footer.findViewById(R.id.gift_detail_product_listview);

        memberListAdapter = new DetailMemberListAdapter();
        productListAdapter = new DetailProductListAdapter();

        gift_detail_member_listview.setAdapter(memberListAdapter);
        gift_detail_product_listview.setAdapter(productListAdapter);

        gift_detail_member_listview.addFooterView(gift_detail_product_listview);

    }


    class DetailMemberListAdapter extends BaseAdapter {

        class MemberViewHolder {
            @BindView(R.id.received_gift_cell_buyer_profile)
            ImageView received_gift_cell_buyer_profile;

            @BindView(R.id.received_gift_cell_sendName)
            TextView received_gift_cell_sendName;

            @BindView(R.id.received_gift_cell_sendPrice)
            TextView received_gift_cell_sendPrice;

            @BindView(R.id.received_gift_cell_sendPayBtn)
            ImageButton received_gift_cell_sendPayBtn;

            public MemberViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        @Override
        public int getCount() {
            return Single_Value.getInstance().SenderNReceiver.getVo_from_friends_local_list().size();
        }

        @Override
        public VO_from_friends_info getItem(int position) {
            return Single_Value.getInstance().SenderNReceiver.getVo_from_friends_local_list().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MemberViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.custom_receivedgift_cell_buyer_listview_cell, null);
                holder = new MemberViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (MemberViewHolder) convertView.getTag();
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_basic_profile);
            holder.received_gift_cell_buyer_profile.setImageBitmap(bitmap);

            holder.received_gift_cell_sendName.setText(getItem(position).getName());

            Bitmap imgbtn = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_progress);

            holder.received_gift_cell_sendPayBtn.setImageBitmap(imgbtn);

            return convertView;
        }
    }

    class DetailProductListAdapter extends BaseAdapter {

        class ProductViewHolder {

            @BindView(R.id.received_gift_cell_product_img)
            ImageView received_gift_cell_product_img;

            @BindView(R.id.received_gift_cell_product_name)
            TextView received_gift_cell_product_name;

            @BindView(R.id.received_gift_cell_product_price)
            TextView received_gift_cell_product_price;

            public ProductViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        @Override
        public int getCount() {
            return Single_Value.getInstance().SenderNReceiver.getVO_giftitem_total_list().size();
        }

        @Override
        public VO_giftitem_list getItem(int position) {
            return Single_Value.getInstance().SenderNReceiver.getVO_giftitem_total_list().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ProductViewHolder holder;

            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.custom_receivedgift_cell_productinfo_cell, null);
                holder = new ProductViewHolder(convertView);
                convertView.setTag(holder);
            }else{

                holder = (ProductViewHolder)convertView.getTag();
            }

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.product_icon);

            holder.received_gift_cell_product_img.setImageBitmap(bitmap);
            holder.received_gift_cell_product_name.setText(getItem(position).getProduct_title_cell());
            holder.received_gift_cell_product_price.setText(getItem(position).getProduct_price_cell());

            return convertView;

        }
    }
}
