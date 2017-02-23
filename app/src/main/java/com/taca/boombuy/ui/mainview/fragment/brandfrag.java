package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.taca.boombuy.R;
import com.taca.boombuy.evt.OTTOBus;
import com.taca.boombuy.modelRes.ResBbSearchBrand;
import com.taca.boombuy.modelRes.ResBbSearchBrandBody;
import com.taca.boombuy.net.Network;
import com.taca.boombuy.ui.mainview.activity.GiftSelectDetailInfoActivity;
import com.taca.boombuy.util.ImageProc;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link brandfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link brandfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class brandfrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public brandfrag() {
    }

    LayoutInflater inflater;
    GridView gridView;
    GridViewAdapter myAdapter;
    ResBbSearchBrand resBbSearchBrand;

    boolean ottoFlag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_brandfrag, container, false);


        Network.getInstance().bb_search_brands(getActivity().getApplicationContext());

        if(!ottoFlag){
            OTTOBus.getInstance().getSearch_brands_bus().register(this);
            ottoFlag = true;
        }

        gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setNumColumns(3);

        myAdapter = new GridViewAdapter();


        return view;
    }

    class ViewHolder{

        @BindView(R.id.brandimg)
        ImageView brandimg;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return resBbSearchBrand.getBody().size();
        }

        @Override
        public ResBbSearchBrandBody getItem(int position) {
            return resBbSearchBrand.getBody().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
  //
            ViewHolder holder;
            convertView = inflater.inflate(R.layout.cell_grid_layout, parent, false);

            holder = new ViewHolder(convertView);

            ImageProc.getInstance().drawImage(getItem(position).getLocation(), holder.brandimg);

            holder.brandimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(), getItem(position).getName(), Toast.LENGTH_SHORT).show();



                    Intent intent = new Intent(getActivity(), GiftSelectDetailInfoActivity.class);

                    startActivity(intent);

                }
            });

            return convertView;
        }
    }

    //// 기본 fragment 생성 틀

    public static brandfrag newInstance(String param1, String param2) {
        brandfrag fragment = new brandfrag();
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

    @Subscribe
    public void FinishLoad(ResBbSearchBrand data){

        resBbSearchBrand = data;
        gridView.setAdapter(myAdapter);
        ((brandfrag.GridViewAdapter)gridView.getAdapter()).notifyDataSetChanged();
    }
}
