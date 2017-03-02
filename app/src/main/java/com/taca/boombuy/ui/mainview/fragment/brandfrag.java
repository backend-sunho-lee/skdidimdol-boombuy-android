package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;
import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResSearchBrands;
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.networkmodel.BrandDTO;
import com.taca.boombuy.util.ImageProc;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    ResSearchBrands resSearchBrands;
    boolean ottoFlag = false;

    Button btn1 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_brandfrag, container, false);

        Call<ResSearchBrands> NetSearchBrands = NetSSL.getInstance().getMemberImpFactory().NetSearchBrands();
        NetSearchBrands.enqueue(new Callback<ResSearchBrands>() {
            @Override
            public void onResponse(Call<ResSearchBrands> call, Response<ResSearchBrands> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResult() != null) {

                        OttoBus.getInstance().getSearchBrands_Bus().post(response.body());

                    } else {
                        Log.i("RESPONSE RESULT 1: ", response.message());
                    }
                } else {
                    Log.i("RESPONSE RESULT 2 : ", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResSearchBrands> call, Throwable t) {

            }
        });


        if (!ottoFlag) {
            OttoBus.getInstance().getSearchBrands_Bus().register(this);
            ottoFlag = true;
        }

        gridView = (GridView) view.findViewById(R.id.gridview);
        //gridView.setNumColumns(3);

        myAdapter = new GridViewAdapter();


        return view;
    }

    class ViewHolder {

        @BindView(R.id.brandimg)
        ImageView brandimg;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            Log.i("Y","개수" + resSearchBrands.getResult().size());
            return resSearchBrands.getResult().size();
        }

        @Override
        public BrandDTO getItem(int position) {
            return resSearchBrands.getResult().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //
            ViewHolder holder;
            if( convertView == null) {
                convertView = inflater.inflate(R.layout.cell_grid_layout, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            ImageProc.getInstance().drawImage(getItem(position).getLocation(), holder.brandimg);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment brandSelectfrag = new brandSelectfrag();
                    Bundle bundle = new Bundle();


                    bundle.putInt("bid", getItem(position).getBid());

                    Log.i("bid brandfrag : ", getItem(position).getBid() + "");
                    brandSelectfrag.setArguments(bundle);


                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.container_Frag, brandSelectfrag);

                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            });
            return convertView;
        }
    }


    public void test(){

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
    public void FinishLoad(ResSearchBrands data) {

        resSearchBrands = data;
        gridView.setAdapter(myAdapter);
        ((brandfrag.GridViewAdapter) gridView.getAdapter()).notifyDataSetChanged();
        OttoBus.getInstance().getSearchBrands_Bus().unregister(this);
    }
}
