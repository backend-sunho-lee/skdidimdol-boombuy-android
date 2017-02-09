package com.taca.boombuy;

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

    // 선물 보낼 사람들 전화번호부 리스트, VO
    public ArrayList<VO_from_friends_local_list> vo_from_friends_local_lists = new ArrayList<VO_from_friends_local_list>();
    public VO_from_friends_local_list vo_from_friends_local_list;

    // 선물 보낼 사람들 이름, 전화번호 VO
    public ArrayList<VO_from_friends_info> vo_from_friends_infos = new ArrayList<VO_from_friends_info>();
    public VO_from_friends_info vo_from_friends_info;

    // 선택한 선물 리스트 VO
    public ArrayList<VO_giftitem_list> vo_giftitem_lists = new ArrayList<VO_giftitem_list>();
    public VO_giftitem_list vo_giftitem_list;

    // 전체 선물 리스트 VO
    public ArrayList<VO_giftitem_list> item_arraylist = new ArrayList<VO_giftitem_list>();

    // 체크박스 선물 리스트 VO
    public ArrayList<VO_giftitem_list> checkbox_checklist = new ArrayList<VO_giftitem_list>();


}
