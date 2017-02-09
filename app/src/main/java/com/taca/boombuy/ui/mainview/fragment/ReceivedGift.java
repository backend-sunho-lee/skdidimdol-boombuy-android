package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.adapter.BaseExpandableAdapter;
import com.taca.boombuy.vo.VO_from_friends_local_list;
import com.taca.boombuy.vo.VO_giftitem_group_info;
import com.taca.boombuy.vo.VO_giftitem_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ReceivedGift extends Fragment {

    BaseExpandableListAdapter baseExpandableListAdapter;
    ExpandableListView expandableListView;

    ArrayList<VO_giftitem_group_info> parent;
    HashMap<VO_giftitem_group_info, ArrayList<VO_giftitem_list>> child;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReceivedGift() {
    }

    public static ReceivedGift newInstance(String param1, String param2) {
        ReceivedGift fragment = new ReceivedGift();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_received_gift, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_listview);


        prepareData();

        baseExpandableListAdapter = new BaseExpandableAdapter(getActivity(), parent, child);
        expandableListView.setAdapter(baseExpandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            int lastClickedPosition = 0;

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                // 선택 한 groupPosition의 펼침/닫힘 상태 체크
                Boolean isExpand = (!expandableListView.isGroupExpanded(groupPosition));

                // 이 전에 열려있던 group 닫기
                expandableListView.collapseGroup(lastClickedPosition);

                if (isExpand) {
                    expandableListView.expandGroup(groupPosition);
                }
                lastClickedPosition = groupPosition;
                return true;
            }
        });


        return view;
    }


    public void prepareData() {


        parent = new ArrayList<VO_giftitem_group_info>();
        child = new HashMap<VO_giftitem_group_info, ArrayList<VO_giftitem_list>>();

        VO_giftitem_group_info parentTemp = new VO_giftitem_group_info();
        ArrayList<VO_from_friends_local_list> sendMember = new ArrayList<VO_from_friends_local_list>();


        // 날짜
        parentTemp.setDate("2017-02-09");

        // 보내는 사람 틀
        for(int i=0; i<Single_Value.getInstance().vo_from_friends_infos.size(); i++){
            sendMember.add(Single_Value.getInstance().vo_from_friends_local_lists.get(i));
        }

        // 받는 사람
        parentTemp.setReceivedPerson(Single_Value.getInstance().vo_to_friend_infos.get(0).getName());


        //VO_from_friends_local_list
        // 보내는 사람 다수
        parentTemp.setSendPeople(sendMember);

        // 보내는 상품 목록 정보
        parentTemp.setTotalProductInfo(Single_Value.getInstance().vo_giftitem_lists);
        parentTemp.setPay_state("승인");

        parent.add(parentTemp);

        ArrayList<VO_giftitem_list> childTemp = new ArrayList<VO_giftitem_list>();
        childTemp.addAll(Single_Value.getInstance().vo_giftitem_lists);
        child.put(parentTemp, childTemp);




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
