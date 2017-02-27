package com.taca.boombuy.NetRetrofit;

import com.taca.boombuy.database.StorageHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Tacademy on 2017-02-23.
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            // Preference에 cookies를 넣어주는 작업을 수행
            StorageHelper.getInstance().setSet(StorageHelper.SESSION_KEY, cookies);
        }
        return originalResponse;
    }
}
