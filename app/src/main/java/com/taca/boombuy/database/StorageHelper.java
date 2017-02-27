package com.taca.boombuy.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * 앱 구동간 필요에 의해서 저장하는 저장소 기능
 */
public class StorageHelper {
    // 저장소 메인키
    //public static final String STORAGE_KEY   = "pref";


    public static final String STORAGE_KEY   = "pref";
    public static final String SESSION_KEY   = "cookies";


    private static StorageHelper ourInstance = new StorageHelper();
    public static StorageHelper getInstance() {
        return ourInstance;
    }
    private StorageHelper() {
    }
    // ------------------- 저장 타입별 기능 제공 ----------------------
    //static{
        // NDK로 만들어는 lib, o등등 파일을(c/c++라이브러리) 로드할대 사용
        // libXXXXXX.o
        // System.loadLibrary("xxxx.o");
    //}
    public void setString(Context context, String key, String value)
    {
        // 저장소 획득
        SharedPreferences.Editor editor
                = context.getSharedPreferences(STORAGE_KEY, 0).edit();
        // 저장
        editor.putString(key, value);
        // 커밋
        editor.commit();
    }
    public String getString(Context context, String key)
    {
        return
        context.getSharedPreferences(STORAGE_KEY, 0).getString(key, "");
    }


    public void setBoolean(Context context, String key, boolean value)
    {
        // 저장소 획득
        SharedPreferences.Editor editor
                = context.getSharedPreferences(STORAGE_KEY, 0).edit();
        // 저장
        editor.putBoolean(key, value);
        // 커밋
        editor.commit();
    }
    public boolean getBoolean(Context context, String key)
    {
        return
        context.getSharedPreferences(STORAGE_KEY, 0).getBoolean(key, false);
    }

    public void setInt(Context context, String key, int value)
    {
        // 저장소 획득
        SharedPreferences.Editor editor
                = context.getSharedPreferences(STORAGE_KEY, 0).edit();
        // 저장
        editor.putInt(key, value);
        // 커밋
        editor.commit();
    }
    public int getInt(Context context, String key)
    {
        return
                context.getSharedPreferences(STORAGE_KEY, 0).getInt(key, 0);
    }

    // retro 에 사용되는 부분 /////////////////////////////////////////////////

    Context context;
    public void setContext(Context context) {
        this.context = context;
    }
    // ------------------- 저장 타입별 기능 제공 ----------------------
    //static{
    // NDK로 만들어는 lib, o등등 파일을(c/c++라이브러리) 로드할대 사용
    // libXXXXXX.o
    // System.loadLibrary("xxxx.o");
    //}
    // 세션 유지를 위해 쿠키 집합을 보관 =============================================================
    public void setSet(String key, Set<String> value)
    {
        // 저장소 획득
        SharedPreferences.Editor editor = context.getSharedPreferences(STORAGE_KEY, 0).edit();
        // 저장
        Log.i("RF", "저장쿠키["+value.toString()+"]");
        editor.putStringSet(key, value);
        // 커밋
        editor.commit();
    }
    public Set<String> getSet(String key)
    {
        Set<String> s = context.getSharedPreferences(STORAGE_KEY, 0).getStringSet(key, new HashSet<String>());
        Log.i("RF", "획득쿠키["+s.toString()+"]");
        return s;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
}













