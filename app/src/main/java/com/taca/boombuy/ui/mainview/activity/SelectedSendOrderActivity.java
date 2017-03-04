package com.taca.boombuy.ui.mainview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResSelectedSendOrder;
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.util.ImageProc;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedSendOrderActivity extends AppCompatActivity {

    ResSelectedSendOrder resSelectedSendOrder;
    @Override
    protected void onStart() {
        super.onStart();
        OttoBus.getInstance().getSelectSendOrder_Bus().register(this);

    }

    RecyclerView sender_recyclerview;
    SenderRecyclerAdapter senderRecyclerAdapter;

    RecyclerView product_recyclerview;
    ProductRecyclerAdapter productRecyclerAdapter;


    GridLayoutManager gridLayoutManager;
    GridLayoutManager gridLayoutManager2;

    @BindView(R.id.received_gift_cell_tv_date)
    TextView received_gift_cell_tv_date;
    @BindView(R.id.received_gift_cell_PayState)
    ImageView received_gift_cell_PayState;
    @BindView(R.id.received_gift_cell_sendMemberProfile)
    ImageView received_gift_cell_sendMemberProfile;
    @BindView(R.id.received_gift_cell_receivedMemberProfile)
    ImageView received_gift_cell_receivedMemberProfile;
    @BindView(R.id.gift_Senders)
    TextView gift_Senders;
    @BindView(R.id.gift_receivedPerson)
    TextView gift_receivedPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_send_order);
        ButterKnife.bind(this);

        sender_recyclerview = (RecyclerView) findViewById(R.id.sender_recyclerview);
        product_recyclerview = (RecyclerView) findViewById(R.id.product_recyclerview);

        senderRecyclerAdapter = new SenderRecyclerAdapter();
        productRecyclerAdapter = new ProductRecyclerAdapter();

        gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        gridLayoutManager2 = new GridLayoutManager(this ,1);
        gridLayoutManager2.setOrientation(OrientationHelper.VERTICAL);

        sender_recyclerview.setLayoutManager(gridLayoutManager);
        sender_recyclerview.setNestedScrollingEnabled(false);

        product_recyclerview.setLayoutManager(gridLayoutManager2);
        product_recyclerview.setNestedScrollingEnabled(false);


        int oid = getIntent().getIntExtra("oid", 0);
        Call<ResSelectedSendOrder> NetSelectSendOrder = NetSSL.getInstance().getMemberImpFactory().NetSelectSendOrder(oid);
        NetSelectSendOrder.enqueue(new Callback<ResSelectedSendOrder>() {
            @Override
            public void onResponse(Call<ResSelectedSendOrder> call, Response<ResSelectedSendOrder> response) {

                if(response.isSuccessful()){

                    if(response.body() != null && response.body().getResult() != null){

                        Log.i("성공했다: ", response.body().getResult().toString());

                        OttoBus.getInstance().getSelectSendOrder_Bus().post(response.body());


                    }else{
                        Log.i("실패했어 제이슨안맞음 :", response.message());
                    }
                }else{
                    Log.i("실패했어 틀안맞 :", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResSelectedSendOrder> call, Throwable t) {

                t.printStackTrace();
                Log.i("통신실패", t.getMessage());
            }
        });

    }

    @Subscribe
    public void FinishLoad(ResSelectedSendOrder data){
        resSelectedSendOrder = data;
        sender_recyclerview.setAdapter(senderRecyclerAdapter);
        product_recyclerview.setAdapter(productRecyclerAdapter);
        senderRecyclerAdapter.notifyDataSetChanged();
        productRecyclerAdapter.notifyDataSetChanged();
        // 어뎁터 2개

        // 최상단 order 간략부분 데이터 삽입
        received_gift_cell_tv_date.setText(resSelectedSendOrder.getResult().getOrders().getOrderstime().trim().substring(0,10).replace("-", "."));

        if(resSelectedSendOrder.getResult().getOrders().getState().trim().equals("진행중")){
            received_gift_cell_PayState.setBackgroundResource(R.drawable.ic_progress);
        }else{
            received_gift_cell_PayState.setBackgroundResource(R.drawable.ic_payment);
        }

        gift_Senders.setText(resSelectedSendOrder.getResult().getOrders().getSender() + "외 " + resSelectedSendOrder.getResult().getOrders().getCnt() +"명");
        gift_receivedPerson.setText(resSelectedSendOrder.getResult().getOrders().getReceiver());

        ImageProc.getInstance().drawImage(resSelectedSendOrder.getResult().getOrders().getReceiverphoto(), received_gift_cell_receivedMemberProfile);
        ImageProc.getInstance().drawImage(resSelectedSendOrder.getResult().getOrders().getSenderphoto(), received_gift_cell_sendMemberProfile);

        OttoBus.getInstance().getSelectSendOrder_Bus().unregister(this);
    }

    class SenderRecyclerViewholder extends RecyclerView.ViewHolder{

        @BindView(R.id.received_gift_cell_buyer_profile)
        ImageView received_gift_cell_buyer_profile;

        @BindView(R.id.received_gift_cell_sendName)
        TextView received_gift_cell_sendName;

        @BindView(R.id.received_gift_cell_sendPrice)
        TextView received_gift_cell_sendPrice;

        @BindView(R.id.received_gift_cell_sendPayBtn)
        ImageView received_gift_cell_sendPayBtn;

        public SenderRecyclerViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SenderRecyclerAdapter extends RecyclerView.Adapter<SenderRecyclerViewholder>{

        @Override
        public SenderRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_receivedgift_cell_buyer_listview_cell, parent, false);
            return new SenderRecyclerViewholder(itemView);
        }

        @Override
        public void onBindViewHolder(SenderRecyclerViewholder holder, int position) {

            holder.received_gift_cell_sendName.setText(resSelectedSendOrder.getResult().getSettlements().get(position).getName());
            holder.received_gift_cell_sendPrice.setText(String.format("%,3d",resSelectedSendOrder.getResult().getSettlements().get(position).getCost())+" 원");

            if(resSelectedSendOrder.getResult().getSettlements().get(position).getState().trim().equals("대기")){
                holder.received_gift_cell_sendPayBtn.setBackgroundResource(R.drawable.ic_progress);
            }else{
                holder.received_gift_cell_sendPayBtn.setBackgroundResource(R.drawable.ic_payment);
            }

            ImageProc.getInstance().drawImage(resSelectedSendOrder.getResult().getSettlements().get(position).getLocation(), holder.received_gift_cell_buyer_profile);
            
            holder.received_gift_cell_sendPayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(SelectedSendOrderActivity.this, "결제 진행 모듈 실행", Toast.LENGTH_SHORT).show();
                    
                }
            });
        }

        @Override
        public int getItemCount() {
            return resSelectedSendOrder.getResult().getSettlements().size();
        }
    }

    class ProductRecyclerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.received_gift_cell_product_img)
        ImageView received_gift_cell_product_img;

        @BindView(R.id.received_gift_cell_product_name)
        TextView received_gift_cell_product_name;

        @BindView(R.id.received_gift_cell_product_price)
        TextView received_gift_cell_product_price;

        public ProductRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerViewHolder> {

        @Override
        public ProductRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_receivedgift_cell_productinfo_cell, parent, false);
            return new ProductRecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ProductRecyclerViewHolder holder, int position) {
            ImageProc.getInstance().drawImage(resSelectedSendOrder.getResult().getCarts().get(position).getLocation(), holder.received_gift_cell_product_img);
            holder.received_gift_cell_product_name.setText(resSelectedSendOrder.getResult().getCarts().get(position).getName());
            holder.received_gift_cell_product_price.setText(String.format("%,3d",resSelectedSendOrder.getResult().getCarts().get(position).getPrice())+"원/1개");
        }

        @Override
        public int getItemCount() {
            return resSelectedSendOrder.getResult().getCarts().size();
        }
    }

}
