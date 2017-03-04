package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taca.boombuy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        receivedItemAdapter = new ReceivedItemAdapter();
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        receivedRecyclerView.setNestedScrollingEnabled(false);
        receivedRecyclerView.setLayoutManager(gridLayoutManager);
        receivedRecyclerView.setAdapter(receivedItemAdapter);

        return view;
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

        }

        @Override
        public int getItemCount() {
            return 5;
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
