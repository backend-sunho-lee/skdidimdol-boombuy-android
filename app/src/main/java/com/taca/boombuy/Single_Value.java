package com.taca.boombuy;


import com.taca.boombuy.singleton.item_single;
import com.taca.boombuy.vo.VO_Gift_Total_SendernReceiver;
import com.taca.boombuy.netmodel.LonInModel;
import com.taca.boombuy.netmodel.SignUpModel;
import com.taca.boombuy.netmodel.UpdateTokenModel;
import com.taca.boombuy.vo.VO_from_friends_info;
import com.taca.boombuy.vo.VO_from_friends_local_list;
import com.taca.boombuy.vo.VO_giftitem_list;
import com.taca.boombuy.vo.VO_to_friend_info;
import com.taca.boombuy.vo.VO_to_friend_local_list;

import java.util.ArrayList;

/**
 * Created by jimin on 2017-02-06.
 */
public class Single_Value {
    private static Single_Value ourInstance = new Single_Value();

    public static Single_Value getInstance() {
        return ourInstance;
    }

    private Single_Value() {
    }

    // 선물 받을 사람 전화번호부 리스트, VO
    public ArrayList<VO_to_friend_local_list> vo_to_friend_local_lists = new ArrayList<VO_to_friend_local_list>();
    public VO_to_friend_local_list vo_to_friend_local_list;

    // 선물 받을 사람 이름, 전화번호 VO
    public ArrayList<VO_to_friend_info> vo_to_friend_infos = new ArrayList<VO_to_friend_info>();
    public VO_to_friend_info vo_to_friend_info;

    // 선물 보낼 사람들 전화번호부 총 리스트, VO
    public ArrayList<VO_from_friends_local_list> vo_from_friends_local_lists = new ArrayList<VO_from_friends_local_list>();
    public VO_from_friends_local_list vo_from_friends_local_list;


    // 보낸사람 받는사람 한틀에 모아논 리스트
    public ArrayList<VO_Gift_Total_SendernReceiver> vo_gift_total_member = new ArrayList<VO_Gift_Total_SendernReceiver>();
    public VO_Gift_Total_SendernReceiver SenderNReceiver;


    public String toString(int position) {
        return "Single_Value{" +
                "vo_gift_total_member=" + vo_gift_total_member.get(position).getVo_from_friends_local_list().toString() +
                '}';
    }


    @Override
    public String toString() {
        return "Single_Value{" +
                "vo_gift_total_member=" + vo_gift_total_member +
                '}';
    }

    // 선물 보낼 사람들 이름, 전화번호 VO // parent 할때 이거써야해
    public ArrayList<VO_from_friends_info> vo_from_friends_infos = new ArrayList<VO_from_friends_info>();
    public VO_from_friends_info vo_from_friends_info;


    // 선택한 선물 리스트 VO
    public ArrayList<VO_giftitem_list> vo_giftitem_lists = new ArrayList<VO_giftitem_list>();
    public VO_giftitem_list vo_giftitem_list;


    // 전체 선물 리스트 VO
    public ArrayList<VO_giftitem_list> item_arraylist = new ArrayList<VO_giftitem_list>();

    /*// 체크박스 선물 리스트 VO
    public ArrayList<VO_giftitem_list> checkbox_checklist = new ArrayList<VO_giftitem_list>();*/


    // 회원가입 정보
    public SignUpModel signUpModel;

    // 로그인 정보
    public LonInModel lonInModel;

    // 토큰 업데이트 정보(폰번호, 현재 토큰)
    public UpdateTokenModel updateTokenModel;


    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < item_single.getInstance().itemDTOArrayList.size(); i++) {
            total += item_single.getInstance().itemDTOArrayList.get(i).getPrice();
        }
        return total;
    }

    public int devided_master() {
        int total = getTotalPrice();
        int master_price = 0;
        int devided_price = 0;

        devided_price = total / (Single_Value.getInstance().vo_from_friends_infos.size() + 1);
        master_price = total - (devided_price * Single_Value.getInstance().vo_from_friends_infos.size());

        return master_price;
    }

    public int devided_non_master() {
        int total = getTotalPrice();
        int devided_price = 0;

        devided_price = total / (Single_Value.getInstance().vo_from_friends_infos.size() + 1);

        return devided_price;
    }

}