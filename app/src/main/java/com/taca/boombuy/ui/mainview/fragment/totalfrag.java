package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.vo.VO_giftitem_list;

import java.util.Collections;

public class totalfrag extends Fragment {


    CustomListAdapter listAdapter;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_totalfrag, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });

        listView = (ListView) rootView.findViewById(R.id.listview);

        listAdapter = new CustomListAdapter(getActivity());
        listView.setAdapter(listAdapter);


        return rootView;
    }

    class ViewHolder {

        CheckBox lv_checkbox;
        ImageView lv_imageview;
        TextView lv_pname;
        TextView lv_pcontent;
        TextView lv_pprice;
    }


    // 상품 리스트 어뎁터
    class CustomListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public CustomListAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return Single_Value.getInstance().item_arraylist.size();
        }

        @Override
        public VO_giftitem_list getItem(int position) {
            return Single_Value.getInstance().item_arraylist.get(position);
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
                holder.lv_pcontent = (TextView) convertView.findViewById(R.id.lv_pcontent);
                holder.lv_pprice = (TextView) convertView.findViewById(R.id.lv_pprice);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.lv_imageview.setImageBitmap(getItem(position).getProduct_imageView_cell());
            holder.lv_pname.setText(getItem(position).getProduct_title_cell());
            // pcontent
            holder.lv_pprice.setText(getItem(position).getProduct_price_cell());

            holder.lv_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        Collections.reverse(Single_Value.getInstance().vo_giftitem_lists); // 새로운 데이터를 리스트의 앞에 추가 해야하므로 리버스한 후 추가 후 다시 리버스

                        Single_Value.getInstance().vo_giftitem_list = new VO_giftitem_list();
                        Single_Value.getInstance().vo_giftitem_list.setProduct_imageView_cell(getItem(position).getProduct_imageView_cell());
                        Single_Value.getInstance().vo_giftitem_list.setProduct_title_cell(getItem(position).getProduct_title_cell());
                        Single_Value.getInstance().vo_giftitem_list.setProduct_price_cell(getItem(position).getProduct_price_cell());
                        Single_Value.getInstance().vo_giftitem_lists.add(Single_Value.getInstance().vo_giftitem_list);

                        Collections.reverse(Single_Value.getInstance().vo_giftitem_lists);

                        // 선택한 곳
                        Toast.makeText(getActivity(), "선택", Toast.LENGTH_SHORT).show();

                    } else {
                        Single_Value.getInstance().vo_giftitem_lists.remove(getItem(position));
                        // 선택 x
                        Toast.makeText(getActivity(), position + "번째 선택 취소", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i("FRAG : ", getItem(position).toString());

                    Toast.makeText(getContext(), getItem(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }
}