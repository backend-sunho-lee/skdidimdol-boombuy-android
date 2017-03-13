package com.taca.boombuy.ui.mainview.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class brandSelectfrag extends Fragment {

    public brandSelectfrag() {
        // Required empty public constructor
    }

    ResItems resItems;

    RecyclerView selectedbrand_recyclerview;
    RecyclerAdapter recyclerAdapter;

    GridLayoutManager gridLayoutManager;
    int bid = 0;

    int page_num = 1;
    int cur_page_num;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selectbrandsitem, container, false);

        selectedbrand_recyclerview = (RecyclerView) view.findViewById(R.id.selectedbrand_recyclerview);
        recyclerAdapter = new RecyclerAdapter();

        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        selectedbrand_recyclerview.setLayoutManager(gridLayoutManager);
        selectedbrand_recyclerview.setNestedScrollingEnabled(false);

        CircleImageView fab = (CircleImageView) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        Bundle bundle = getArguments();
        Log.i("선택된 프레그먼트 ", bid + "제발");
        bid = bundle.getInt("bid");
        Log.i("브랜드번호 : ", bid + "");
        getSelectedbrands(1);

        return view;
    }

    public void getSelectedbrands(final int getItemCount) {
        Call<ResItems> NetSearchBrandItem = NetSSL.getInstance().getMemberImpFactory().NetSearchBrandItem(bid, page_num, 20);
        NetSearchBrandItem.enqueue(new Callback<ResItems>() {
            @Override
            public void onResponse(Call<ResItems> call, Response<ResItems> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResult() != null) {
                        cur_page_num = page_num;
                        Log.i("DATA", response.body().toString());
                        FinishLoad(response.body(), getItemCount);
                    } else {

                        Log.i("RESPONSE RESULT 1: ", response.message());

                    }
                } else {
                    Log.i("RESPONSE RESULT 2 : ", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResItems> call, Throwable t) {
                Log.i("FAILURE : ", t.getMessage());
            }
        });
    }

    public void FinishLoad(ResItems data, int getItemCount) {
        /*resItems = data;
        selectedbrand_recyclerview.setNestedScrollingEnabled(false);
        selectedbrand_recyclerview.setAdapter(recyclerAdapter);*/


        selectedbrand_recyclerview.setNestedScrollingEnabled(false);

        if (page_num == 1) {
            resItems = data;
            for(ItemDTO it : item_single.getInstance().itemDTOArrayList){
                for(ItemDTO newIt : resItems.getResult()){
                    if( it.getId()== newIt.getId() ){
                        newIt.setChecked(true);
                    }
                }
            }
            selectedbrand_recyclerview.setAdapter(recyclerAdapter);
            selectedbrand_recyclerview.scrollToPosition(getItemCount-1);
        } else {
            resItems.getResult().addAll(data.getResult());
            selectedbrand_recyclerview.setAdapter(recyclerAdapter);
            selectedbrand_recyclerview.scrollToPosition(getItemCount-1);
            (selectedbrand_recyclerview.getAdapter()).notifyDataSetChanged();
        }

    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lv_checkbox)
        CheckBox lv_checkbox;
        @BindView(R.id.lv_imageview)
        ImageView lv_imageview;
        @BindView(R.id.lv_pname)
        TextView lv_pname;
        @BindView(R.id.lv_pprice)
        TextView lv_pprice;
        @BindView(R.id.lv_detailinfo)
        Button lv_detailinfo;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


        @Override
        public int getItemCount() {
            return resItems.getResult().size();
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.custom_listview_cell, null);
            return new RecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

            ImageProc.getInstance().drawImage(resItems.getResult().get(position).getLocation(), holder.lv_imageview);

            holder.lv_detailinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                }
            });

            holder.lv_pname.setText(resItems.getResult().get(position).getName());
            holder.lv_pprice.setText(resItems.getResult().get(position).getPrice() + "원");

            holder.lv_detailinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), GiftSelectDetailInfoActivity.class);
                    ItemDTO item = resItems.getResult().get(position);
                    intent.putExtra("item", item);
                    startActivity(intent);
                }
            });


            holder.lv_checkbox.setOnCheckedChangeListener(new MyBrandSelectedCheck(position));
            holder.lv_checkbox.setChecked(resItems.getResult().get(position).isChecked());

            /*holder.lv_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    item_single.getInstance().itemDTO = new ItemDTO(
                            resItems.getResult().get(position).getId(),
                            resItems.getResult().get(position).getBid(),
                            resItems.getResult().get(position).getName(),
                            resItems.getResult().get(position).getPrice(),
                            resItems.getResult().get(position).getDetail(),
                            resItems.getResult().get(position).getLocation(),false
                    );
                    if (isChecked) {
                        Collections.reverse(item_single.getInstance().itemDTOArrayList); // 새로운 데이터를 리스트의 앞에 추가 해야하므로 리버스한 후 추가 후 다시 리버스
                        item_single.getInstance().itemDTOArrayList.add(item_single.getInstance().itemDTO);
                        Collections.reverse(item_single.getInstance().itemDTOArrayList);

                    } else {
                        item_single.getInstance().itemDTOArrayList.remove(item_single.getInstance().itemDTO);
                    }
                }

            });*/

            // 마지막 체크
            if (position == getItemCount() - 1) {
                // 최종 페이지라면 더이상 목록이 없습니다.등 메세지 처리 하면 됨.
                // 아니라면 다은 페이지를 가져온다.
                //Toast.makeText(getActivity(), "마지막", Toast.LENGTH_SHORT).show();
                Log.i("UI", "마지막");
                if (page_num == cur_page_num) {
                    page_num++;
                    // 통신
                    getSelectedbrands(getItemCount());
                }
            }

        }

    }


    class MyBrandSelectedCheck implements  CompoundButton.OnCheckedChangeListener
    {
        ItemDTO itemDTO;
        int position;
        public MyBrandSelectedCheck(int position){
            this.position = position;
            itemDTO = resItems.getResult().get(position);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                Collections.reverse(item_single.getInstance().itemDTOArrayList); // 새로운 데이터를 리스트의 앞에 추가 해야하므로 리버스한 후 추가 후 다시 리버스
                boolean isDup = false;
                for(ItemDTO it : item_single.getInstance().itemDTOArrayList){
                    if( it.getId() == this.itemDTO.getId() ){
                        isDup = true;
                        break;
                    }
                }

                if(!isDup)
                    item_single.getInstance().itemDTOArrayList.add(this.itemDTO);
                Collections.reverse(item_single.getInstance().itemDTOArrayList);

                this.itemDTO.setChecked(true);


            } else {
                this.itemDTO.setChecked(false);
                item_single.getInstance().itemDTOArrayList.remove(this.itemDTO);

            }
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();

    }


}