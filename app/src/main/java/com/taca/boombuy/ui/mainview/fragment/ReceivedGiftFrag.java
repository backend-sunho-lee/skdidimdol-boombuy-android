package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResReceivedItem;
import com.taca.boombuy.util.ImageProc;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReceivedGiftFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReceivedGiftFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceivedGiftFrag extends Fragment {

    @BindView(R.id.receivedRecyclerView)
    RecyclerView receivedRecyclerView;

    ReceivedItemAdapter receivedItemAdapter;
    GridLayoutManager gridLayoutManager;

    ResReceivedItem resReceivedItem;

    @Override
    public void onStart() {
        super.onStart();

        //Call<> NetReceivedItem = NetSSL.getInstance().getMemberImpFactory().NetReceivedItem();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sent_gift, container, false);
        ButterKnife.bind(this, view);

        receivedRecyclerView = (RecyclerView) view.findViewById(R.id.receivedRecyclerView);

        receivedItemAdapter = new ReceivedItemAdapter();
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        receivedRecyclerView.setNestedScrollingEnabled(false);
        receivedRecyclerView.setLayoutManager(gridLayoutManager);
        //receivedRecyclerView.setAdapter(receivedItemAdapter);

        getReceivedItems(1);

        return view;
    }

    public void getReceivedItems(final int getItemCount){

        Call<ResReceivedItem> NetReceivedItem = NetSSL.getInstance().getMemberImpFactory().NetReceivedItem();
        NetReceivedItem.enqueue(new Callback<ResReceivedItem>() {
            @Override
            public void onResponse(Call<ResReceivedItem> call, Response<ResReceivedItem> response) {

                if(response.isSuccessful()){

                    if(response.body() != null && response.body().getResult() != null){

                        Log.i("MY RESULT", response.body().getResult().toString());
                        FinishLoad(response.body());

                    }else{

                        Log.i("FFFF", response.body().getError().toString());
                    }

                }else{

                    Log.i("FFFFffffff", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResReceivedItem> call, Throwable t) {

                t.printStackTrace();
                Log.i("hey", "err" + t.getMessage());
            }
        });

    }

    public void FinishLoad(ResReceivedItem data){

        resReceivedItem = data;
        receivedRecyclerView.setAdapter(receivedItemAdapter);

        //(receivedRecyclerView.getAdapter()).notifyDataSetChanged();

    }

    class ReceivedItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.gift_received_date)
        TextView gift_received_date;

        @BindView(R.id.gift_using_statement)
        ImageView gift_using_statement;

        @BindView(R.id.gift_sent_profile)
        ImageView gift_sent_profile;

        @BindView(R.id.gift_sent_people)
        TextView gift_sent_people;

        @BindView(R.id.gift_sent_product_img)
        ImageView gift_sent_product_img;

        @BindView(R.id.gift_sent_product_name)
        TextView gift_sent_product_name;

        /*@BindView(R.id.gift_barcode)
        ImageView gift_barcode;*/

        public ReceivedItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ReceivedItemAdapter extends RecyclerView.Adapter<ReceivedItemViewHolder>{

        @Override
        public ReceivedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.custom_received_item_recycler_cell, null);
            return new ReceivedItemViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReceivedItemViewHolder holder, int position) {

            // 값
            holder.gift_received_date.setText(resReceivedItem.getResult().get(position).getTime());
            holder.gift_sent_people.setText(resReceivedItem.getResult().get(position).getSenders());
            holder.gift_sent_product_name.setText(resReceivedItem.getResult().get(position).getItem_name());

            ImageProc.getInstance().drawImage(resReceivedItem.getResult().get(position).getItem_photo(), holder.gift_sent_product_img);
            ImageProc.getInstance().drawImage(resReceivedItem.getResult().get(position).getLocation(), holder.gift_sent_profile);
            //ImageProc.getInstance().drawImage(, holder.gift_using_statement);



         /*   // 마지막 체크 페이징
            if (position == getItemCount() - 1) {
                // 최종 페이지라면 더이상 목록이 없습니다.등 메세지 처리 하면 됨.
                // 아니라면 다은 페이지를 가져온다.
                //Toast.makeText(getActivity(), "마지막", Toast.LENGTH_SHORT).show();
                Log.i("UI", "마지막");
                if (page_num == cur_page_num) {
                    page_num++;
                    // 통신
                    getReceivedItems(getItemCount());
                }
            }
*/
        }

        @Override
        public int getItemCount() {
            return resReceivedItem.getResult().size();
        }
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReceivedGiftFrag() {

    }

    public static ReceivedGiftFrag newInstance(String param1, String param2) {
        ReceivedGiftFrag fragment = new ReceivedGiftFrag();
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
