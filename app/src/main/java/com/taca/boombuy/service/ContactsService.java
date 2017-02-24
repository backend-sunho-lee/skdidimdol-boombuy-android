package com.taca.boombuy.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

import com.taca.boombuy.database.StorageHelper;

import java.util.ArrayList;

public class ContactsService extends Service {
    ArrayList<String> cc = new ArrayList<String>();
    final int BUFFER_MAX_CNT = 10;

    public ContactsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 백그라운드에서 서비스 단으로 전화번호를 수집하여 서버로 전송한다.
        // 언제 끝날지 모르니까. 다 끝나면 -> 방송을 통해 내 앱한테 통보를 하고
        // 이것을 리시버를 통해 받아서 인지하여 다음 작업을 할 수 있게 구동한다.
        new ContactsThread().execute(new String[]{""});

        return super.onStartCommand(intent, flags, startId);
    }

    // 비동기 쓰레드 클래스 작성
    public class ContactsThread extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            getAddressEx();
            return null;
        }
    }

    public void getAddressEx() {
        if (StorageHelper.getInstance().getBoolean(this, "notFirst"))
            return;

        // 1. 주소록 처리 객체 획득
        ContentResolver cr = this.getContentResolver();
        // 2. 쿼리
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        // 3. 키 획득
        int idIdx = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int idName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        // 4. 커서 초기 위치로 이동
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) { // 마지막이 아니면
            // 추출!!
            String id = cursor.getString(idIdx); // 주소록 한개의 아이디
            String name = cursor.getString(idName); // 주소록 한개의 이름
            Log.i("CC", id + "," + name);
            // 전화번호 추출
            Cursor subCursor =
                    cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
            subCursor.moveToFirst();
            while (!subCursor.isAfterLast()) {
                int i = subCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String tel = subCursor.getString(i).replaceAll("-", "");
                /////////////////전화번호 통일//////////////////////////////////////////////////////////////////////////
                if (tel.length() == 10) {
                    tel = tel.substring(0, 3) + "-"
                            + tel.substring(3, 6) + "-"
                            + tel.substring(6);
                } else if (tel.length() > 8) {
                    tel = tel.substring(0, 3) + "-"
                            + tel.substring(3, 7) + "-"
                            + tel.substring(7);
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                Log.i("CC", "tel" + ":" + tel);
                // 전번 1세트 등록
                //cc.add(new AddressModel(uid, name, tel));
                // 데이터를 수집 -> 10(임의의 수)개가 채워지면 -> 서버 전송 -> 비운다
                // 10개 되었냐??
                if (cc.size() == BUFFER_MAX_CNT) {
                    // 서버 전송@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    //NetworkTEST.getInstance().sendAddress(this, cc);
                    // 버퍼 비우기
                    cc.clear();
                }
                subCursor.moveToNext();
            }
            subCursor.close();
            // ===========================================================================================

            // 다음 row로 이동
            cursor.moveToNext();
        }
        cursor.close();
        // 수집 활동이 끝났는데 버퍼에 8개가 남은 것 같다 -> 서버 전송 -> 비운다
        if (cc.size() > 0) {
            // 서버 전송@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            //NetworkTEST.getInstance().sendAddress(this, cc);
            // 버퍼 비우기
            cc.clear();
        }

        StorageHelper.getInstance().setBoolean(this, "notFirst", true);
        // 전송 : { header:{code:AD}, body:[{uid:xx, name:xx, tel:xx}] }
        // 응답 : { code:1, msg:"ok" }
    }
}
