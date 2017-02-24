package com.taca.boombuy.evt;

import com.squareup.otto.Bus;

/**
 * Created by Tacademy on 2017-02-24.
 */
public class OttoBus {
    private static OttoBus ourInstance = new OttoBus();

    public static OttoBus getInstance() {
        return ourInstance;
    }

    private OttoBus() {
    }

    //회원가입 버스
    Bus SignUp_Bus = new Bus();

    // 로그인버스
    Bus Login_Bus = new Bus();

    // 프로필 조회 버스
    Bus SearchProfile_Bus = new Bus();

    // 전체상품 조회 버스
    Bus SearchItems_Bus = new Bus();

    // 전체 브랜드 조회 버스
    Bus SearchBrands_Bus = new Bus();

    // 하나의 브랜드 상품 전체 조회 버스
    Bus SearchBrandItem_Bus = new Bus();

    // 하나의 상품 상세정보 조회 버스
    Bus SearchItemDetail_Bus = new Bus();

    // 친구 전화번호 목록 전송 버스
    Bus SendContacts_Bus = new Bus();

    public Bus getSendContacts_Bus() {
        return SendContacts_Bus;
    }

    public void setSendContacts_Bus(Bus sendContacts_Bus) {
        SendContacts_Bus = sendContacts_Bus;
    }

    public Bus getSearchItemDetail_Bus() {
        return SearchItemDetail_Bus;
    }

    public void setSearchItemDetail_Bus(Bus searchItemDetail_Bus) {
        SearchItemDetail_Bus = searchItemDetail_Bus;
    }

    public Bus getSearchBrandItem_Bus() {
        return SearchBrandItem_Bus;
    }

    public void setSearchBrandItem_Bus(Bus searchBrandItem_Bus) {
        SearchBrandItem_Bus = searchBrandItem_Bus;
    }

    public Bus getSearchBrands_Bus() {
        return SearchBrands_Bus;
    }

    public void setSearchBrands_Bus(Bus searchBrands_Bus) {
        SearchBrands_Bus = searchBrands_Bus;
    }

    public Bus getSearchProfile_Bus() {
        return SearchProfile_Bus;
    }

    public void setSearchProfile_Bus(Bus searchProfile_Bus) {
        SearchProfile_Bus = searchProfile_Bus;
    }

    public Bus getSearchItems_Bus() {
        return SearchItems_Bus;
    }

    public void setSearchItems_Bus(Bus searchItems_Bus) {
        SearchItems_Bus = searchItems_Bus;
    }

    public Bus getLogin_Bus() {
        return Login_Bus;
    }

    public void setLogin_Bus(Bus login_Bus) {
        Login_Bus = login_Bus;
    }

    public static OttoBus getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(OttoBus ourInstance) {
        OttoBus.ourInstance = ourInstance;
    }

    public Bus getSignUp_Bus() {
        return SignUp_Bus;
    }

    public void setSignUp_Bus(Bus signUp_Bus) {
        SignUp_Bus = signUp_Bus;
    }
}
