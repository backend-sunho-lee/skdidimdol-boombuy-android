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
import com.taca.boombuy.netmodel.FCMModel;
import com.taca.boombuy.netmodel.ReqHeader;
import com.taca.boombuy.netmodel.ReqSendFcm;

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
    // 통신 API
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
}
