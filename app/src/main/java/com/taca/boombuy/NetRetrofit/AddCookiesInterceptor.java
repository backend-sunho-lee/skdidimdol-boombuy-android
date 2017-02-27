package com.taca.boombuy.NetRetrofit;

import com.taca.boombuy.database.StorageHelper;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tacademy on 2017-02-23.
 */

public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        // Preference에서 cookies를 가져오는 작업을 수행

        Set<String> preferences = StorageHelper.getInstance().getSet(StorageHelper.SESSION_KEY);
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }
        // Web,Android,iOS 구분을 위해 User-Agent세팅
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android");
        return chain.proceed(builder.build());
    }
}
