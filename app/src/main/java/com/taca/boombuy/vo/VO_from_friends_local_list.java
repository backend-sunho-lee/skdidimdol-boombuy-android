package com.taca.boombuy.vo;

/**
 * Created by jimin on 2017-02-01.
 */

public class VO_from_friends_local_list {
    String iv_from_friends_profile;
    String tv_from_friends_local_name;
    String tv_from_friends_local_number;

    public VO_from_friends_local_list(){
    }

    @Override
    public String toString() {
        return "VO_from_friends_local_list{" +
                "iv_from_friends_profile='" + iv_from_friends_profile + '\'' +
                ", tv_from_friends_local_name='" + tv_from_friends_local_name + '\'' +
                ", tv_from_friends_local_number='" + tv_from_friends_local_number + '\'' +
                '}';
    }

    public String getIv_from_friends_profile() {
        return iv_from_friends_profile;
    }

    public void setIv_from_friends_profile(String iv_from_friends_profile) {
        this.iv_from_friends_profile = iv_from_friends_profile;
    }

    public String getTv_from_friends_local_name() {
        return tv_from_friends_local_name;
    }

    public void setTv_from_friends_local_name(String tv_from_friends_local_name) {
        this.tv_from_friends_local_name = tv_from_friends_local_name;
    }

    public String getTv_from_friends_local_number() {
        return tv_from_friends_local_number;
    }

    public void setTv_from_friends_local_number(String tv_from_friends_local_number) {
        this.tv_from_friends_local_number = tv_from_friends_local_number;
    }

    public VO_from_friends_local_list(String iv_from_friends_profile, String tv_from_friends_local_name, String tv_from_friends_local_number) {
        this.iv_from_friends_profile = iv_from_friends_profile;
        this.tv_from_friends_local_name = tv_from_friends_local_name;
        this.tv_from_friends_local_number = tv_from_friends_local_number;
    }
}