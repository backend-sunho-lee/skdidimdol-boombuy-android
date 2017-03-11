package com.taca.boombuy.ui.mainview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.taca.boombuy.NetRetrofit.NetSSL;
import com.taca.boombuy.R;
import com.taca.boombuy.Resmodel.ResItems;
import com.taca.boombuy.networkmodel.ItemDTO;
import com.taca.boombuy.singleton.item_single;
import com.taca.boombuy.ui.mainview.activity.GiftSelectDetailInfoActivity;
import com.taca.boombuy.util.ImageProc;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class totalfrag extends Fragment {

    ResItems resItems;
    //ResBbSearchItem resBbSearchItem = new ResBbSearchItem();

    CustomListAdapter listAdapter;
    ListView listView;

    ImageView circleImageView;

    int page_num = 1;
    int cur_page_num;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //OttoBus.getInstance().getSearchItems_Bus().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_totalfrag, container, false);

        listAdapter = null;



        circleImageView = (ImageView) rootView.findViewById(R.id.fab);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });*/

        listView = (ListView) rootView.findViewById(R.id.listview);
        listAdapter = new CustomListAdapter();

        getTotalItems(1);
        //listView.setAdapter(listAdapter);
        return rootView;
    }

    class ViewHolder {

        CheckBox lv_checkbox;
        ImageView lv_imageview;
        TextView lv_pname;
        TextView lv_pprice;
        Button lv_detailinfo;
    }


    // 상품 리스트 어뎁터
    class CustomListAdapter extends BaseAdapter {
/*
        private LayoutInflater layoutInflater;

        public CustomListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }*/

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

        ViewHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_listview_cell, null);

                holder = new ViewHolder();




                holder.lv_checkbox = (CheckBox) convertView.findViewById(R.id.lv_checkbox);


                holder.lv_imageview = (ImageView) convertView.findViewById(R.id.lv_imageview);
                holder.lv_pname = (TextView) convertView.findViewById(R.id.lv_pname);
                holder.lv_pprice = (TextView) convertView.findViewById(R.id.lv_pprice);
                holder.lv_detailinfo = (Button) convertView.findViewById(R.id.lv_detailinfo);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Log.i("IMAGE URL", getItem(position).getLocation());

            ImageProc.getInstance().drawImage(getItem(position).getLocation(), holder.lv_imageview);

            holder.lv_pname.setText(getItem(position).getName());
            // pcontent
            holder.lv_pprice.setText(getItem(position).getPrice() + "원");

            holder.lv_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    //buttonView.setTag(position, "checked"); // 태ㅔ그를 이용해서 체크된것들의 값들을 가져올수있나?
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
/*
*//*
                        Single_Value.getInstance().vo_giftitem_list = new VO_giftitem_list();
                        Single_Value.getInstance().vo_giftitem_list.setProduct_imageView_cell(getItem(position).getProduct_imageView_cell());
                        Single_Value.getInstance().vo_giftitem_list.setProduct_title_cell(getItem(position).getProduct_title_cell());
                        Single_Value.getInstance().vo_giftitem_list.setProduct_price_cell(getItem(position).getProduct_price_cell());
                        Single_Value.getInstance().vo_giftitem_lists.add(Single_Value.getInstance().vo_giftitem_list);
*//*
                        Collections.reverse(Single_Value.getInstance().vo_giftitem_lists);*/

                        // 준범]] giftStorage 파트
                        // 체크한것들은 저장해야지

                        // 선택한 곳
                        Toast.makeText(getActivity(), "선택", Toast.LENGTH_SHORT).show();

                    } else {

                        item_single.getInstance().itemDTOArrayList.remove(item_single.getInstance().itemDTO);
                        Toast.makeText(getActivity(), position + "번째 선택 취소", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            holder.lv_detailinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), GiftSelectDetailInfoActivity.class);
                    ItemDTO item = getItem(position);
                    intent.putExtra("item", item);
                    startActivity(intent);
                }
            });

            // 마지막 체크
            if (position == getCount() - 1) {
                // 최종 페이지라면 더이상 목록이 없습니다.등 메세지 처리 하면 됨.
                // 아니라면 다은 페이지를 가져온다.
                //Toast.makeText(getActivity(), "마지막", Toast.LENGTH_SHORT).show();
                Log.i("UI", "마지막");
                if (page_num == cur_page_num) {
                    page_num++;
                    // 통신
                    getTotalItems(getCount());
                }
            }

            return convertView;
        }
    }

    public void getTotalItems(final int getCount) {
        Call<ResItems> NetSearchItems = NetSSL.getInstance().getMemberImpFactory().NetSearchItems(page_num, 20);
        NetSearchItems.enqueue(new Callback<ResItems>() {
            @Override
            public void onResponse(Call<ResItems> call, Response<ResItems> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResult() != null) {
                        cur_page_num = page_num;
                        //OttoBus.getInstance().getSearchItems_Bus().post(response.body());
                        FinishLoad(response.body(), getCount);
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
    }

    public void FinishLoad(ResItems data, int getCount) {
        if (page_num == 1) {
            resItems = data;
        } else {
            resItems.getResult().addAll(data.getResult());
        }

        listView.setAdapter(listAdapter);
        listView.setSelection(getCount -1);
        ((totalfrag.CustomListAdapter) listView.getAdapter()).notifyDataSetChanged();

    }
}