package com.taca.boombuy.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

import com.taca.boombuy.Single_Value;
import com.taca.boombuy.vo.VO_from_friends_local_list;
import com.taca.boombuy.vo.VO_to_friend_local_list;

/**
 * Created by jimin on 2017-02-06.
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
    }

    // 특정 키워드 포함하는 전화번호부 검색하여 리스트에 담음
    public void getPhoneNumber(Context context, String keyword) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID, // 연락처 ID -> 사진 정보 가져오는데 사용
                ContactsContract.CommonDataKinds.Phone.NUMBER,        // 연락처
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}; // 연락처 이름.

        String[] selectionArgs = null;

        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";

        Cursor contactCursor = context.getContentResolver().query(uri, projection, null, selectionArgs, sortOrder);

        //ArrayList<Contact> contactlist = new ArrayList<Contact>();

        if (contactCursor.moveToFirst()) {
            do {
                String phonenumber = contactCursor.getString(1).replaceAll("-", "");
                if (phonenumber.length() == 10) {
                    phonenumber = phonenumber.substring(0, 3) + "-"
                            + phonenumber.substring(3, 6) + "-"
                            + phonenumber.substring(6);
                } else if (phonenumber.length() > 8) {
                    phonenumber = phonenumber.substring(0, 3) + "-"
                            + phonenumber.substring(3, 7) + "-"
                            + phonenumber.substring(7);
                }

                /*Log.i("확인", "연락처 사용자 ID : " + contactCursor.getLong(0));
                Log.i("확인", "연락처 사용자 이름 : " + contactCursor.getString(2));
                Log.i("확인", "연락처 사용자 번호 : " + phonenumber);*/
                if (contactCursor.getString(2).contains(keyword)) {
                    Single_Value.getInstance().vo_to_friend_local_list = new VO_to_friend_local_list();
                    Single_Value.getInstance().vo_to_friend_local_list.setIv_to_friend_profile("");
                    Single_Value.getInstance().vo_to_friend_local_list.setTv_to_friend_local_name(contactCursor.getString(2));
                    Single_Value.getInstance().vo_to_friend_local_list.setTv_to_friend_local_number(phonenumber);
                    Single_Value.getInstance().vo_to_friend_local_lists.add(Single_Value.getInstance().vo_to_friend_local_list);

                    Single_Value.getInstance().vo_from_friends_local_list = new VO_from_friends_local_list();
                    Single_Value.getInstance().vo_from_friends_local_list.setIv_from_friends_profile("");
                    Single_Value.getInstance().vo_from_friends_local_list.setTv_from_friends_local_name(contactCursor.getString(2));
                    Single_Value.getInstance().vo_from_friends_local_list.setTv_from_friends_local_number(phonenumber);
                    Single_Value.getInstance().vo_from_friends_local_lists.add(Single_Value.getInstance().vo_from_friends_local_list);
                }
            } while (contactCursor.moveToNext());
        }
    }

    public String getMyPhoneNum(Context context){
        TelephonyManager telemamanger;
        telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telemamanger.getLine1Number();
    }
}
