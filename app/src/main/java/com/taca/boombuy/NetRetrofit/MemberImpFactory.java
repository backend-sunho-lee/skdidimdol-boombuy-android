package com.taca.boombuy.NetRetrofit;

import com.taca.boombuy.Resmodel.ResBasic;
import com.taca.boombuy.Resmodel.ResItemDetail;
import com.taca.boombuy.Resmodel.ResItems;
import com.taca.boombuy.Resmodel.ResSearchBrands;
import com.taca.boombuy.Resmodel.ResSearchProfile;
import com.taca.boombuy.networkmodel.LoginDTO;
import com.taca.boombuy.networkmodel.SignUpDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * 회원관련 모든 API 구현
 * 1. 카카오톡 로그인 및 최초 회원등록_
 * 2. 로컬 회원 최초 가입_
 * 3. 로컬 회원 로그인_
 * 4. 로컬 회원 정보 변경_
 * 5. 회원 탈퇴하기_
 * 6. 회원 로그아웃_
 * 7. 회원 상세 정보 보기_
 * 8. 회원 본인 모든 기록 보기_
 * 9. 회원 본인 점수 등록_
 * 10. 회원 본인 점수 수정_
 * 11. 회원 본인 점수 삭제_
 */

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
    Call<ResSearchProfile> NetSearchProfile();


    // 전체 상품 목록 조회
    @GET("/items")
    Call<ResItems> NetSearchItems();


    // 브랜드 전체 목록 조회
    @GET("/brands")
    Call<ResSearchBrands> NetSearchBrands();

    // 하나의 브랜드 상품 전체 조회
    @GET("/items/brand/{bid}")
    Call<ResItems> NetSearchBrandItem(@Path("bid") int bid);


    // 상품권 전체 목록 조회
    @GET("/items/voucher")
    Call<ResItems> NetSearchCoupon();

    // 하나의 상품 상세 조회
    @GET("/items/{iid}")
    Call<ResItemDetail> NetSearchItemDetail(@Path("iid") int iid);


    // 친구 목록 전송
    @PUT("/friends")
    Call<ResBasic> NetSendContacts(@Body ArrayList<String> list);




}


















