package com.taca.boombuy.ui.mainview.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.singleton.item_single;
import com.taca.boombuy.vo.VO_from_friends_info;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftDetailInfoActivity extends AppCompatActivity {

    RecyclerView gift_detail_member_recyclerview;
    RecyclerView gift_detail_product_recyclerview;
    /*DetailMemberListAdapter memberListAdapter;
    DetailProductListAdapter productListAdapter;*/

    DetailMemberRecycleAdaper detailMemberRecycleAdaper;
    DetailProductRecycleAdapter detailProductRecycleAdapter;


    GridLayoutManager memberlinearLayoutManager;
    GridLayoutManager productLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("ArrayData", Single_Value.getInstance().vo_gift_total_member.toString());

        Log.i("ArrayPRODUCT", item_single.getInstance().itemDTOArrayList.toString());
        Log.i("ArrayPRODUCT", item_single.getInstance().itemDTOArrayList.size() + "");


        //Log.i("ggg", Single_Value.getInstance().SenderNReceiver.getVO_giftitem_total_list().size()+"");

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);*/

        setContentView(R.layout.activity_gift_detail_info);

        gift_detail_member_recyclerview = (RecyclerView) findViewById(R.id.gift_detail_member_recyclerview);
        gift_detail_product_recyclerview = (RecyclerView) findViewById(R.id.gift_detail_product_recyclerview);


        memberlinearLayoutManager = new GridLayoutManager(this, 1);
        productLinearLayoutManager = new GridLayoutManager(this , 1);

        gift_detail_member_recyclerview.setLayoutManager(memberlinearLayoutManager);
        gift_detail_product_recyclerview.setLayoutManager(productLinearLayoutManager);


        detailMemberRecycleAdaper = new DetailMemberRecycleAdaper();
        detailProductRecycleAdapter = new DetailProductRecycleAdapter();

        gift_detail_member_recyclerview.setAdapter(detailMemberRecycleAdaper);
        gift_detail_product_recyclerview.setAdapter(detailProductRecycleAdapter);

    }


    class MemberViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.received_gift_cell_buyer_profile)
        ImageView received_gift_cell_buyer_profile;

        @BindView(R.id.received_gift_cell_sendName)
        TextView received_gift_cell_sendName;

        @BindView(R.id.received_gift_cell_sendPrice)
        TextView received_gift_cell_sendPrice;

        @BindView(R.id.received_gift_cell_sendPayState)
        ImageView received_gift_cell_sendPayState;

        public MemberViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class DetailMemberRecycleAdaper extends RecyclerView.Adapter<MemberViewHolder>{

        @Override
        public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.recyclerview_custom_buyer_cell, null);

            View view = getLayoutInflater().inflate(R.layout.recyclerview_custom_buyer_cell, null);
            return new MemberViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MemberViewHolder holder, int position) {

            holder.received_gift_cell_sendName.setText(Single_Value.getInstance().SenderNReceiver.getVo_from_friends_local_list().get(position).getName());

            //Bitmap imgbtn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_progress);

            //holder.received_gift_cell_sendPayBtn.setBackgroundResource(R.drawable.ic_progress);


        }

        @Override
        public int getItemCount() {
            return Single_Value.getInstance().SenderNReceiver.getVo_from_friends_local_list().size();
        }
    }

    class DetailMemberListAdapter extends BaseAdapter {


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


            holder.received_gift_cell_sendName.setText(getItem(position).getName());

            return convertView;
        }
    }


    class ProductViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.received_gift_cell_product_img)
        ImageView received_gift_cell_product_img;

        @BindView(R.id.received_gift_cell_product_name)
        TextView received_gift_cell_product_name;

        @BindView(R.id.received_gift_cell_product_price)
        TextView received_gift_cell_product_price;

        public ProductViewHolder(View view) {

            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class DetailProductRecycleAdapter extends RecyclerView.Adapter<ProductViewHolder>{

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.custom_receivedgift_cell_productinfo_cell, null);
            return new ProductViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.product_icon);

            holder.received_gift_cell_product_img.setImageBitmap(bitmap);
            holder.received_gift_cell_product_name.setText(item_single.getInstance().itemDTOArrayList.get(position).getName());
            holder.received_gift_cell_product_price.setText(item_single.getInstance().itemDTOArrayList.get(position).getPrice()+"" );
        }

        @Override
        public int getItemCount() {
            return item_single.getInstance().itemDTOArrayList.size();
        }
    }

    /*class DetailProductListAdapter extends BaseAdapter {

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
    }*/
}
