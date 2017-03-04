package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResItems;
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.networkmodel.ItemDTO;
import com.taca.boombuy.singleton.item_single;
import com.taca.boombuy.ui.mainview.activity.GiftSelectDetailInfoActivity;
import com.taca.boombuy.util.ImageProc;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    boolean ottoflag = false;

    ResItems resItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        this.inflater = inflater;
        View view =  inflater.inflate(R.layout.fragment_couponfrag, container, false);
        couponAdapter = new CouponListViewAdapter();

        Call<ResItems> NetSearchCoupon = NetSSL.getInstance().getMemberImpFactory().NetSearchCoupon();
        NetSearchCoupon.enqueue(new Callback<ResItems>() {
            @Override
            public void onResponse(Call<ResItems> call, Response<ResItems> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResult() != null) {

                        OttoBus.getInstance().getSearchCoupons_Bus().post(response.body());

                    } else {
                        Log.i("RESPONSE RESULT 1: ", response.message());
                    }
                } else {
                    Log.i("RESPONSE RESULT 2 : ", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResItems> call, Throwable t) {

            }
        });





        listview = (ListView) view.findViewById(R.id.listview);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });

        return view;
    }

    class CouponViewHolder{

        @BindView(R.id.lv_pname)
        TextView lv_pname;

        @BindView(R.id.lv_checkbox)
        CheckBox lv_checkbox;

        @BindView(R.id.lv_imageview)
        ImageView lv_imageview;

        @BindView(R.id.lv_pprice)
        TextView lv_pprice;

        @BindView(R.id.lv_detailinfo)
        Button lv_detailinfo;


        public CouponViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class CouponListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return resItems.getResult().size();
        }

        @Override
        public ItemDTO getItem(int position) {
            return resItems.getResult().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            CouponViewHolder couponViewHolder;
            convertView = inflater.inflate(R.layout.custom_listview_cell, parent, false);

            couponViewHolder = new CouponViewHolder(convertView);

            couponViewHolder.lv_pname.setText(getItem(position).getName());
            ImageProc.getInstance().drawImage(getItem(position).getLocation(), couponViewHolder.lv_imageview);
            couponViewHolder.lv_pprice.setText(getItem(position).getPrice()+"원");


            couponViewHolder.lv_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item_single.getInstance().itemDTO = new ItemDTO(
                            getItem(position).getId(),
                            getItem(position).getBid(),
                            getItem(position).getName(),
                            getItem(position).getPrice(),
                            getItem(position).getDetail(),
                            getItem(position).getLocation()
                    );
                    if (isChecked) {
                        Collections.reverse(item_single.getInstance().itemDTOArrayList); // 새로운 데이터를 리스트의 앞에 추가 해야하므로 리버스한 후 추가 후 다시 리버스
                        item_single.getInstance().itemDTOArrayList.add(item_single.getInstance().itemDTO);
                        Collections.reverse(item_single.getInstance().itemDTOArrayList);

                        // 선택한 곳
                        Toast.makeText(getActivity(), "선택", Toast.LENGTH_SHORT).show();

                    } else {
                        item_single.getInstance().itemDTOArrayList.remove(item_single.getInstance().itemDTO);
                        Toast.makeText(getActivity(), position + "번째 선택 취소", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            couponViewHolder.lv_detailinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GiftSelectDetailInfoActivity.class);
                    ItemDTO item = resItems.getResult().get(position);
                    intent.putExtra("item", item);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }


    @Subscribe
    public void FinishLoad(ResItems data){


        resItems = data;
        listview.setAdapter(couponAdapter);
        ((couponfrag.CouponListViewAdapter)listview.getAdapter()).notifyDataSetChanged();

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

        OttoBus.getInstance().getSearchCoupons_Bus().register(this);
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
