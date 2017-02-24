package com.taca.boombuy.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.taca.boombuy.Reqmodel.ReqHeader;
import com.taca.boombuy.Reqmodel.ReqSendContacts;
import com.taca.boombuy.Resmodel.ResBasic;
import com.taca.boombuy.Resmodel.ResItemDetail;
import com.taca.boombuy.Resmodel.ResItems;
import com.taca.boombuy.Resmodel.ResSearchBrands;
import com.taca.boombuy.Resmodel.ResSearchProfile;
import com.taca.boombuy.evt.OttoBus;
import com.taca.boombuy.networkmodel.LoginDTO;
import com.taca.boombuy.networkmodel.SignUpDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tacademy on 2017-02-24.
 */
public class NetWork {
    private static NetWork ourInstance = new NetWork();

    public static NetWork getInstance() {
        return ourInstance;
    }

    private NetWork() {
    }


    public RequestQueue requestQueue;

    public RequestQueue getRequestQueue(Context context) {

        if(requestQueue == null){

            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    // 회원가입 //////////////////////////////////////////////////////////////////////////////////////////
    public void NetSignUp(Context context, SignUpDTO signUpDTO){

/*
        ReqSignUp reqSignUp = new ReqSignUp();

        ReqHeader header = new ReqHeader("회원 가입");
        reqSignUp.setHeader(header);
        reqSignUp.setBody(signUpDTO);

        Log.i("회원가입 입력값 :" , reqSignUp.toString());
*/
        Log.i("회원가입 값 :", signUpDTO.toString());

        try {
            JSONObject json = new JSONObject(new Gson().toJson(signUpDTO));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com/users",
                    json,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            ResBasic resBasic = new Gson().fromJson(response.toString(), ResBasic.class);

                            Log.i("response data :" , response.toString());
                            OttoBus.getInstance().getSignUp_Bus().post(resBasic);

                            Log.i("RES SUCCES :", "회원가입 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Log.i("RES Fail :", "회원가입 Volley 실패" + error.getMessage());

                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // 회원 로그인 ///////////////////////////////////////////////////////////////////////////////////////
    public void NetLogin(Context context, LoginDTO loginDTO){

        /*ReqLogin reqLogin = new ReqLogin();

        ReqHeader header = new ReqHeader("로그인");
        reqLogin.setHeader(header);
        reqLogin.setBody(loginDTO);*/

        try {
            JSONObject json = new JSONObject(new Gson().toJson(loginDTO));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com/auth/local/login",
                    json,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            ResBasic resBasic = new Gson().fromJson(response.toString(), ResBasic.class);

                            OttoBus.getInstance().getLogin_Bus().post(resBasic);

                            Log.i("RES SUCCES :", "로그인 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.i("RES Fail :", "로그인 Volley 실패" + error.getMessage());

                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // 회원 프로필 조회 //////////////////////////////////////////////////////////////////////////////////
    public void NetSearchProfile(Context context, String phone){

        /*ReqSearchProfile reqSearchProfile = new ReqSearchProfile();

        ReqHeader header = new ReqHeader("회원 프로필 조회");
        reqSearchProfile.setHeader(header);
        reqSearchProfile.setPhone(phone);*/

        try {
            JSONObject json = new JSONObject(new Gson().toJson(phone));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "",
                    json,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            ResSearchProfile resSearchProfile = new Gson().fromJson(response.toString(), ResSearchProfile.class);
                            OttoBus.getInstance().getSearchProfile_Bus().post(resSearchProfile);
                            Log.i("RES SUCCES :", "회원정보 조회 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "회원정보 조회 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // 상품 전체 조회 ////////////////////////////////////////////////////////////////////////////////////
    public void NetSearchItems(Context context){


        try {
            //JSONObject json = new JSONObject(new Gson().toJson(reqSearchProfile));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com/items",
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            ResItems resItems = new Gson().fromJson(response.toString(), ResItems.class);
                            OttoBus.getInstance().getSearchItems_Bus().post(resItems);
                            Log.i("RES SUCCES :", "상품 전체 조회 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "상품 전체 조회 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void NetSearchBrands(Context context){

        try {
            //JSONObject json = new JSONObject(new Gson().toJson(reqSearchProfile));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com/brands",
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            ResSearchBrands resSearchBrands = new Gson().fromJson(response.toString(), ResSearchBrands.class);
                            OttoBus.getInstance().getSearchBrands_Bus().post(resSearchBrands);
                            Log.i("RES SUCCES :", "상품 전체 조회 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "상품 전체 조회 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // 하나의 브랜드 상품 전체 조회 //////////////////////////////////////////////////////////////////////
    public void NetSearchBrandItem(Context context, int bid){

        try {
            //JSONObject json = new JSONObject(new Gson().toJson(reqSearchProfile));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "" + bid,
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            ResItems resItems = new Gson().fromJson(response.toString(), ResItems.class);
                            OttoBus.getInstance().getSearchBrandItem_Bus().post(resItems);
                            Log.i("RES SUCCES :", "브랜드선택 상품 전체 조회 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "브랜드선택 상품 전체 조회 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void NetSearchCoupon(Context context){

        try {
            //JSONObject json = new JSONObject(new Gson().toJson(reqSearchProfile));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com/items/voucher",
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            ResItems resItems = new Gson().fromJson(response.toString(), ResItems.class);
                            OttoBus.getInstance().getSearchCoupons_Bus().post(resItems);
                            Log.i("RES SUCCES :", "상품권 상품 전체 조회 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "상품권 상품 전체 조회 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 하나의 상품 상세정보 조회 /////////////////////////////////////////////////////////////////////////
    public void NetSearchItemDetail(Context context, int iid){

        try {
            //JSONObject json = new JSONObject(new Gson().toJson(reqSearchProfile));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com/items/:"+iid ,
                    null,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            ResItemDetail itemDetail = new Gson().fromJson(response.toString(), ResItemDetail.class);
                            OttoBus.getInstance().getSearchItemDetail_Bus().post(itemDetail);
                            Log.i("RES SUCCES :", "하나의 상품 상세정보 조회 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "하나의 상품 상세정보 조회 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // 친구 목록 배열 전송 ///////////////////////////////////////////////////////////////////////////////
    public void NetSendContacts(Context context, ArrayList<String> list){

        ReqSendContacts reqSendContacts = new ReqSendContacts();
        ReqHeader header = new ReqHeader("내 친구전화번호 목록 배열전송");
        reqSendContacts.setHeader(header);
        reqSendContacts.setBody(list);

        try {
            JSONObject json = new JSONObject(new Gson().toJson(reqSendContacts));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "",
                    json,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {

                            ResBasic resBasic = new Gson().fromJson(response.toString(), ResBasic.class);
                            OttoBus.getInstance().getSendContacts_Bus().post(resBasic);
                            Log.i("RES SUCCES :", "친구 목록 배열 전송 됐다.");
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("RES Fail :", "친구 목록 배열 전송 실패" + error.getMessage());
                        }
                    });
            getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void temp(Context context){

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getPostParams() throws AuthFailureError {
                return super.getPostParams();
            }
        };
    }
}
