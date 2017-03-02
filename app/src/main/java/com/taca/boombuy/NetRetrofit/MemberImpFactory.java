package com.taca.boombuy.NetRetrofit;

import com.taca.boombuy.Reqmodel.ReqChangeToken;
import com.taca.boombuy.Reqmodel.ReqSendContacts;
import com.taca.boombuy.Resmodel.ResBasic;
import com.taca.boombuy.Resmodel.ResFriendList;
import com.taca.boombuy.Resmodel.ResItemDetail;
import com.taca.boombuy.Resmodel.ResItems;
import com.taca.boombuy.Resmodel.ResMyProfile;
import com.taca.boombuy.Resmodel.ResSearchBrands;
import com.taca.boombuy.networkmodel.GiftDTO;
import com.taca.boombuy.networkmodel.LoginDTO;
import com.taca.boombuy.networkmodel.SignUpDTO;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;


public interface MemberImpFactory {
    // 로컬 회원 최초 가입 --------------------------------------------------------------------------
    @POST("users")
    Call<ResBasic> NetSignUp(@Body SignUpDTO signUpDTO);
    // 세션 사용 -----------------------------------------------------------------------------------

    // 로그인
    @POST("/auth/local/login")
    Call<ResBasic> NetLogin(@Body LoginDTO loginDTO);

    // 세션 아웃 -----------------------------------------------------------------------------------
    /*  @GET("/auth/logout")
    Call<ResAll> logout();*/
    // 회원 정보 요청 --------------------------------------------------------------------------------
    // /users/:uid

    // 내 정보 조회
    @GET("/users/me")
    Call<ResMyProfile> NetMyProfile();

    // 내 토큰 변경
    @PUT("/auth/local/token")
    Call<ResBasic> NetChaneToken(@Body ReqChangeToken reqChangeToken);

    // 회원 탈퇴
    @DELETE("/users/me")
    Call<ResBasic> NetWithdrawal();

    // 친구 전화번호 전체 전송
    @POST("/friends")
    Call<ResBasic> NetSendContacts(@Body ReqSendContacts reqSendContacts);

    // 전체 상품 목록 조회
    @GET("/items")
    Call<ResItems> NetSearchItems();

    // 브랜드 전체 목록 조회
    @GET("/brands")
    Call<ResSearchBrands> NetSearchBrands();

    // 하나의 브랜드 상품 전체 조회
    @GET("/brands/{bid}")
    Call<ResItems> NetSearchBrandItem(@Path("bid") int bid);

    // 상품권 전체 목록 조회
    @GET("/items/voucher")
    Call<ResItems> NetSearchCoupon();

    // 하나의 상품 상세 조회
    @GET("/items/{iid}")
    Call<ResItemDetail> NetSearchItemDetail(@Path("iid") int iid);

    // 친구 목록 전송
    /*@POST("/friends")
    Call<ResBasic> NetSendContacts(@Body String list);*/
    @GET("/friends")
    Call<ResFriendList> NetSearchFriendList();

        // 주문 정보 전송
    @POST("/orders")
    Call<ResBasic> NetOrders(@Body GiftDTO giftDTO);

    // 파일(사진) 전송
    @Multipart
    @PUT("/users/me")
    Call<ResBasic> NetChangeImage(@PartMap Map<String, RequestBody> params);
}
