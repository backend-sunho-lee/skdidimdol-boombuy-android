package com.taca.boombuy.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.taca.boombuy.dto.itemDTO;
import com.taca.boombuy.evt.OTTOBus;
import com.taca.boombuy.modelReq.ReqBbLogIn;
import com.taca.boombuy.modelReq.ReqBbSearchBrand;
import com.taca.boombuy.modelReq.ReqBbSearchItem;
import com.taca.boombuy.modelReq.ReqBbSearchItemId;
import com.taca.boombuy.modelReq.ReqBbSignUp;
import com.taca.boombuy.modelReq.ReqHeader;
import com.taca.boombuy.modelReq.ReqSendFcm;
import com.taca.boombuy.modelReq.ReqUpdateToken;
import com.taca.boombuy.modelRes.ResBbSearchBrand;
import com.taca.boombuy.modelRes.ResBbSearchItem;
import com.taca.boombuy.modelRes.ResBbSearchItemCoupon;
import com.taca.boombuy.modelRes.ResBbSearchItemId;
import com.taca.boombuy.netmodel.FCMModel;
import com.taca.boombuy.netmodel.LonInModel;
import com.taca.boombuy.netmodel.SignUpModel;
import com.taca.boombuy.netmodel.UpdateTokenModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jimin on 2017-01-20.
 */
public class Network {
    private static Network ourInstance = new Network();

    public static Network getInstance() {
        return ourInstance;
    }

    private Network() {
    }

    String url = "http://ec2-35-166-158-25.us-west-2.compute.amazonaws.com:3000/"; // 준범 서버
    //String url = "http://ec2-35-165-170-210.us-west-2.compute.amazonaws.com:3000/"; // 지민 서버




    ///////////////////////////////////////////////////////////////////////////
    // 통신 큐
    RequestQueue requestQueue;

    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
    }



    public void bb_search_item_coupon(Context context, int bid){

        ReqBbSearchItemId reqBbSearchItemCoupon = new ReqBbSearchItemId();
        ReqHeader header = new ReqHeader();

        header.setCode("상품 Bid 값으로 상품정보 검색");
        reqBbSearchItemCoupon.setHeader(header);
        reqBbSearchItemCoupon.setBody(bid);

        try {
            JSONObject json = new JSONObject(new Gson().toJson(reqBbSearchItemCoupon));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url + "bb_search_item_coupon",
                        json,
                        new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {


                                Log.i("COUPON RES" , response.toString());
                                ResBbSearchItemCoupon resBbSearchItemCoupon = new Gson().fromJson(response.toString(), ResBbSearchItemCoupon.class);

                                OTTOBus.getInstance().getSearch_items_coupon_bus().post(resBbSearchItemCoupon);

                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("COUPON RES FAIL", "why : " + error.getMessage());
                            }
                        });

            getRequestQueue(context).add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    // 상품번호를 통해 상품 하나의 상세정보 가져오는 volley
    public ArrayList<itemDTO> bb_search_item_Id(Context context, int id) {

        ReqBbSearchItemId reqBbSearchItemId = new ReqBbSearchItemId();
        ReqHeader header = new ReqHeader();

        header.setCode("상품 Id 값으로 상품정보 검색");
        reqBbSearchItemId.setHeader(header);
        reqBbSearchItemId.setBody(id);

        try {
            JSONObject json = new JSONObject(new Gson().toJson(reqBbSearchItemId));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                    Request.Method.POST,
                    url + "bb_search_item_Id",
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.i("RESPONSE :", response.toString());
                            ResBbSearchItemId resBbSearchItemId = new Gson().fromJson(response.toString(), ResBbSearchItemId.class);
                            Log.i("SELECT FROM ID : ", resBbSearchItemId.toString());

                            OTTOBus.getInstance().getSelected_item_detail_bus().post(resBbSearchItemId);


                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            error.printStackTrace();
                            Log.i("SELECT FROM ID 위치", "실패" + error.getMessage());
                        }
                    }
            );

            getRequestQueue(context).add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 전역변수에 형태 정해주고 return list
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // FCM 전송
    public void sendFcm(Context context, ArrayList<FCMModel> cc) {

        // 전송 : { header:{code:AD}, body:[{token:xx, content:xx}] }
        // 응답 : { code:1, msg:"ok" }
        // 1. 파라미터 구성
        ReqSendFcm reqSendFcm = new ReqSendFcm();
        ReqHeader reqHeader = new ReqHeader();
        reqSendFcm.setHeader(reqHeader);
        reqSendFcm.setBody(cc);
        // 2. 요청객체 준비
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            url + "sendFcm",
                            new JSONObject(new Gson().toJson(reqSendFcm)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // 4. 응답처리
                                    Log.i("RES", response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }

    // users 회원가입
    public void bb_Signup(Context context, SignUpModel signUpModel) {
        // 전송 : { header:{code:AD}, body:[{phone:xx, password:xx, name:xx, token:xx, profile:xx}] }
        // 응답 : { code:1, msg:"ok" }
        // 1. 파라미터 구성
        ReqBbSignUp reqBbSignUp = new ReqBbSignUp();
        ReqHeader reqHeader = new ReqHeader();
        reqBbSignUp.setHeader(reqHeader);
        reqBbSignUp.setBody(signUpModel);
        // 2. 요청객체 준비
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            url + "bb_Signup",
                            new JSONObject(new Gson().toJson(reqBbSignUp)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // 4. 응답처리
                                    Log.i("RES", "bb_Signup" + response.toString());
                                    // 이벤트 발생 등록
                                    OTTOBus.getInstance().getSign_up_bus().post(response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }

    // users 로그인
    public void bb_Login(Context context, LonInModel lonInModel) {
        // 전송 : { header:{code:AD}, body:[{uid:xx, name:xx, tel:xx}] }
        // 응답 : { code:1, msg:"ok" }
        // 1. 파라미터 구성
        ReqBbLogIn reqBbLogIn = new ReqBbLogIn();
        ReqHeader reqHeader = new ReqHeader();
        reqBbLogIn.setHeader(reqHeader);
        reqBbLogIn.setBody(lonInModel);
        // 2. 요청객체 준비
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            url + "bb_Login",
                            new JSONObject(new Gson().toJson(reqBbLogIn)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // 4. 응답처리
                                    Log.i("RES", "bb_Login" + response.toString());
                                    // 이벤트 발생 등록
                                    OTTOBus.getInstance().getSign_in_bus().post(response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }

    // users token 업데이트
    public void bb_Update_token(Context context, UpdateTokenModel updateTokenModel) {
        // 전송 : { header:{code:AD}, body:[{uid:xx, name:xx, tel:xx}] }
        // 응답 : { code:1, msg:"ok" }
        // 1. 파라미터 구성
        ReqUpdateToken reqUpdateToken = new ReqUpdateToken();
        ReqHeader reqHeader = new ReqHeader();
        reqUpdateToken.setHeader(reqHeader);
        reqUpdateToken.setBody(updateTokenModel);
        // 2. 요청객체 준비
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            url + "bb_Update_token",
                            new JSONObject(new Gson().toJson(reqUpdateToken)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // 4. 응답처리
                                    Log.i("RES", "bb_Update_token" + response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

        }
    }

    public void bb_search_items(Context context) {

        ReqBbSearchItem reqBbSearchItem = new ReqBbSearchItem();
        ReqHeader header = new ReqHeader();
        header.setCode("아이템 총 출력");
        reqBbSearchItem.setHeader(header);

        try {
            JSONObject json = new JSONObject(new Gson().toJson(reqBbSearchItem));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url + "bb_Search_Items",
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.i("RESULT", response.toString());

                            ResBbSearchItem resBbSearchItem = new Gson().fromJson(response.toString(), ResBbSearchItem.class);

                            Log.i("RESULT RES", resBbSearchItem.toString());

                            OTTOBus.getInstance().getSearch_items_bus().post(resBbSearchItem);

                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.i("RESULT FAIL", "실패:");
                        }
                    }
            );


            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void bb_search_brands(Context context) {

        ReqBbSearchBrand reqBbSearchBrand = new ReqBbSearchBrand();

        ReqHeader header = new ReqHeader();
        header.setCode("브랜드 총 출력");
        reqBbSearchBrand.setHeader(header);

        try {
            JSONObject json = new JSONObject(new Gson().toJson(reqBbSearchBrand));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url + "bb_Search_Brands",
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.i("BRAND RESPONSE", response.toString());

                            ResBbSearchBrand resBbSearchBrand = new Gson().fromJson(response.toString(), ResBbSearchBrand.class);

                            OTTOBus.getInstance().getSearch_brands_bus().post(resBbSearchBrand);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.i("실패", error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}