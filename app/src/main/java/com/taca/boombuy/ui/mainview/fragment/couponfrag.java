package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.taca.boombuy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link couponfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link couponfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class couponfrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    LayoutInflater inflater;

    ListView listview;
    CouponListViewAdapter couponAdapter;

    String []data = {"AAA","BBB","CCC"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflater = inflater;
        View view =  inflater.inflate(R.layout.fragment_couponfrag, container, false);


        listview = (ListView) view.findViewById(R.id.listview);
        couponAdapter = new CouponListViewAdapter();
        listview.setAdapter(couponAdapter);

        return view;
    }

    class CouponViewHolder{

        @BindView(R.id.lv_pname)
        TextView lv_pname;

        public CouponViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class CouponListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public String getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CouponViewHolder couponViewHolder;
            convertView = inflater.inflate(R.layout.custom_listview_cell, parent, false);

            couponViewHolder = new CouponViewHolder(convertView);

            couponViewHolder.lv_pname.setText(getItem(position));

            return convertView;
        }
    }


















    public couponfrag() {
    }

    public static couponfrag newInstance(String param1, String param2) {
        couponfrag fragment = new couponfrag();
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
        void onFragmentInteraction(Uri uri);
    }
}
