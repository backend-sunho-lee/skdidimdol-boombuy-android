package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResSimpleSendOrders;
import com.taca.boombuy.ui.mainview.activity.SelectedSendOrderActivity;
import com.taca.boombuy.util.ImageProc;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentGiftFrag extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SentGiftFrag() {
    }

    public static SentGiftFrag newInstance(String param1, String param2) {
        SentGiftFrag fragment = new SentGiftFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    LayoutInflater inflater;
    /*MyListAdapter mylistAdapter;
    ListView listView;
    Button restartBtn;*/

    RecyclerView received_gift_recyclerView;
    ReceivedAdapter receivedAdapter;
    GridLayoutManager gridLayoutManager;

    ResSimpleSendOrders resSimpleSendOrders;

    @Override
    public void onStart() {
        super.onStart();

        Call<ResSimpleSendOrders> NetSimpleSendOrders = NetSSL.getInstance().getMemberImpFactory().NetSimpleSendOrders();

        NetSimpleSendOrders.enqueue(new Callback<ResSimpleSendOrders>() {
            @Override
            public void onResponse(Call<ResSimpleSendOrders> call, Response<ResSimpleSendOrders> response) {

                if(response.isSuccessful()){

                    if(response.body() != null && response.body().getResult() != null){

                        resSimpleSendOrders = response.body();
                        Log.i("RES SIMPLE DATA : ", resSimpleSendOrders.toString());

                        received_gift_recyclerView.setAdapter(receivedAdapter);
                        receivedAdapter.notifyDataSetChanged();

                    }else{
                        Log.i("FAIL여기제발", "HERE 11");
                    }
                }else{
                    Log.i("FAIL여기제발", "HERE 22");
                }
            }

            @Override
            public void onFailure(Call<ResSimpleSendOrders> call, Throwable t) {

                t.printStackTrace();
                Log.i("FAIL", t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_received_gift, container, false);

        received_gift_recyclerView = (RecyclerView) view.findViewById(R.id.received_gift_recyclerView);
        received_gift_recyclerView.setNestedScrollingEnabled(false);
        receivedAdapter = new ReceivedAdapter();
        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        received_gift_recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }

    class ReceivedRecyclerViewHolder extends RecyclerView.ViewHolder{
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

        @BindView(R.id.received_gift_cell_senders_count)
        ImageView received_gift_cell_senders_count;

        @BindView(R.id.receivedfrag_linearlayout)
        LinearLayout receivedfrag_linearlayout;


        public ReceivedRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ReceivedAdapter extends RecyclerView.Adapter<ReceivedRecyclerViewHolder>{

        @Override
        public ReceivedRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.custom_receivedgift_cell, null);
            return new ReceivedRecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReceivedRecyclerViewHolder holder, final int position) {


            holder.received_gift_cell_tv_date.setText(resSimpleSendOrders.getResult().get(position).getOrderstime().substring(0, 10).replace("-", "."));

            if(resSimpleSendOrders.getResult().get(position).getState().trim().equals("진행중")){
                holder.received_gift_cell_PayState.setBackgroundResource(R.drawable.ic_progress);
            }else{
                holder.received_gift_cell_PayState.setBackgroundResource(R.drawable.ic_payment);
            }

            switch (resSimpleSendOrders.getResult().get(position).getCnt()){
                case 1:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_1);
                    break;
                case 2:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_2);
                    break;
                case 3:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_3);
                    break;
                case 4:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_4);
                    break;
                case 5:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_5);
                    break;
                case 6:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_6);
                    break;
                case 7:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_7);
                    break;
                case 8:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_8);
                    break;
                case 9:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_9);
                    break;
                default:
                    holder.received_gift_cell_senders_count.setBackgroundResource(R.drawable.ic_plus);
                    break;
            }

            holder.gift_Senders.setText(resSimpleSendOrders.getResult().get(position).getSender() + "외 " + resSimpleSendOrders.getResult().get(position).getCnt() +"명");
            holder.gift_receivedPerson.setText(resSimpleSendOrders.getResult().get(position).getReceiver());

            ImageProc.getInstance().drawImage( resSimpleSendOrders.getResult().get(position).getSenderphoto() ,holder.received_gift_cell_sendMemberProfile);
            ImageProc.getInstance().drawImage( resSimpleSendOrders.getResult().get(position).getReceiverphoto() ,holder.received_gift_cell_receivedMemberProfile);


            holder.receivedfrag_linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), SelectedSendOrderActivity.class);
                    intent.putExtra("oid", resSimpleSendOrders.getResult().get(position).getOid());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return resSimpleSendOrders.getResult().size();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
