package com.taca.boombuy.vo;

/**
 * Created by jimin on 2017-02-01.
 */

public class VO_to_friend_local_list {
    String iv_to_friend_profile;
    String tv_to_friend_local_name;
    String tv_to_friend_local_number;

    @Override
    public String toString() {
        return "VO_to_friend_local_list{" +
                "iv_to_friend_profile='" + iv_to_friend_profile + '\'' +
                ", tv_to_friend_local_name='" + tv_to_friend_local_name + '\'' +
                ", tv_to_friend_local_number='" + tv_to_friend_local_number + '\'' +
                '}';
    }

    public VO_to_friend_local_list() {
    }

    public String getIv_to_friend_profile() {
        return iv_to_friend_profile;
    }

    public void setIv_to_friend_profile(String iv_to_friend_profile) {
        this.iv_to_friend_profile = iv_to_friend_profile;
    }

    public String getTv_to_friend_local_name() {
        return tv_to_friend_local_name;
    }

    public void setTv_to_friend_local_name(String tv_to_friend_local_name) {
        this.tv_to_friend_local_name = tv_to_friend_local_name;
    }

    public String getTv_to_friend_local_number() {
        return tv_to_friend_local_number;
    }

    public void setTv_to_friend_local_number(String tv_to_friend_local_number) {
        this.tv_to_friend_local_number = tv_to_friend_local_number;
    }
}