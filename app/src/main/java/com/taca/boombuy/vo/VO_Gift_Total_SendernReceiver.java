package com.taca.boombuy.vo;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-14.
 */

public class VO_Gift_Total_SendernReceiver {

    // 선택한 상품 정보
    ArrayList<VO_giftitem_list> VO_giftitem_total_list;

    // 받는사람 1명에 대한 정보
    VO_to_friend_info vo_to_friend_info;

    // 보내는 사람 여려명에 대한정>보니깐
    ArrayList<VO_from_friends_info> vo_from_friends_local_list;


    public ArrayList<VO_giftitem_list> getVO_giftitem_total_list() {
        return VO_giftitem_total_list;
    }

    public void setVO_giftitem_total_list(ArrayList<VO_giftitem_list> VO_giftitem_total_list) {
        this.VO_giftitem_total_list = VO_giftitem_total_list;
    }

    public VO_to_friend_info getVo_to_friend_info() {
        return vo_to_friend_info;
    }

    public void setVo_to_friend_info(VO_to_friend_info vo_to_friend_info) {
        this.vo_to_friend_info = vo_to_friend_info;
    }

    public ArrayList<VO_from_friends_info> getVo_from_friends_local_list() {
        return vo_from_friends_local_list;
    }

    public void setVo_from_friends_local_list(ArrayList<VO_from_friends_info> vo_from_friends_local_list) {
        this.vo_from_friends_local_list = vo_from_friends_local_list;
    }


    @Override
    public String toString() {
        return "VO_Gift_Total_SendernReceiver{" +
                "VO_giftitem_total_list=" + VO_giftitem_total_list +
                ", vo_to_friend_info=" + vo_to_friend_info +
                ", vo_from_friends_local_list=" + vo_from_friends_local_list +
                '}';
    }
}
