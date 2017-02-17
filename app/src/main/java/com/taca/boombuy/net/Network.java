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
import com.taca.boombuy.evt.OTTOBus;
import com.taca.boombuy.netmodel.FCMModel;
import com.taca.boombuy.netmodel.LonInModel;
import com.taca.boombuy.netmodel.ReqBbLogIn;
import com.taca.boombuy.netmodel.ReqBbSearchItem;
import com.taca.boombuy.netmodel.ReqBbSignUp;
import com.taca.boombuy.netmodel.ReqHeader;
import com.taca.boombuy.netmodel.ReqSendFcm;
import com.taca.boombuy.netmodel.ReqUpdateToken;
import com.taca.boombuy.netmodel.ResBbSearchItem;
import com.taca.boombuy.netmodel.SignUpModel;
import com.taca.boombuy.netmodel.UpdateTokenModel;

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

    ///////////////////////////////////////////////////////////////////////////
    // 통신 큐
    RequestQueue requestQueue;

    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
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
                            "http://ec2-35-165-170-210.us-west-2.compute.amazonaws.com:3000/sendFcm",
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
                            "http://ec2-35-165-170-210.us-west-2.compute.amazonaws.com:3000/bb_Signup",
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
                            "http://ec2-35-165-170-210.us-west-2.compute.amazonaws.com:3000/bb_Login",
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
                            "http://ec2-35-165-170-210.us-west-2.compute.amazonaws.com:3000/bb_Update_token",
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

    public void bb_search_items(Context context){

        ReqBbSearchItem reqBbSearchItem = new ReqBbSearchItem();
        ReqHeader header = new ReqHeader();
        header.setCode("아이템 총 출력");
        reqBbSearchItem.setHeader(header);

        try {
            JSONObject json = new JSONObject(new Gson().toJson(reqBbSearchItem));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://ec2-35-165-170-210.us-west-2.compute.amazonaws.com:3000/bb_Search_Items",
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.i("RESULT" , response.toString());

                            ResBbSearchItem resBbSearchItem = new Gson().fromJson(response.toString(), ResBbSearchItem.class);

                            Log.i("RESULT RES", resBbSearchItem.toString() );

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
        } catch(Exception e){

            e.printStackTrace();
        }


    }
}
