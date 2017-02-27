package com.taca.boombuy.NetRetrofit;

import android.content.Context;
import android.util.Log;

import com.taca.boombuy.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tacademy on 2017-02-20.
 */
public class NetSSL {
    private static NetSSL ourInstance = new NetSSL();
    public static NetSSL getInstance() {
        return ourInstance;
    }
    private NetSSL() {
    }
    ////////////////////////////////////////////////////////////////////////
    //SSLSocketFactory
    //X509TrustManager
    //HostnameVerifier
    SSLSocketFactory sslSocketFactory;
    X509TrustManager x509TrustManager;
    HostnameVerifier hostnameVerifier;
    OkHttpClient     client;
    ////////////////////////////////////////////////////////////////////////
    Retrofit         retrofit;
    ////////////////////////////////////////////////////////////////////////
    // Context 때문에 생성 방식을 변경함.
    public void launch(Context context)
    {
        if(sslSocketFactory==null && x509TrustManager==null && hostnameVerifier==null){
            try {
                sslSocketFactory = getSslSocketFactory(context);
                hostnameVerifier = getHostnameVerifier();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 세션 처리를 위한 수정 ================================================================
            OkHttpClient.Builder clientTmp = new OkHttpClient.Builder();
            clientTmp.sslSocketFactory(sslSocketFactory, x509TrustManager);
            clientTmp.hostnameVerifier(hostnameVerifier);
            clientTmp.interceptors().add(new AddCookiesInterceptor());
            clientTmp.interceptors().add(new ReceivedCookiesInterceptor());
            client = clientTmp.build();
            // ====================================================================================
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://ec2-52-78-52-228.ap-northeast-2.compute.amazonaws.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
    ////////////////////////////////////////////////////////////////////////
    MemberImpFactory memberImpFactory;

    public MemberImpFactory getMemberImpFactory() {
        if( memberImpFactory == null ){
            memberImpFactory = retrofit.create(MemberImpFactory.class);
        }
        return memberImpFactory;
    }
    ////////////////////////////////////////////////////////////////////////
    // HostnameVerifier
    private HostnameVerifier getHostnameVerifier()
    {
        return new HostnameVerifier(){
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
    }
    ////////////////////////////////////////////////////////////////////////
    // SSLSocketFactory
    private SSLSocketFactory getSslSocketFactory(Context context)
            throws CertificateException, KeyStoreException, IOException,
            NoSuchAlgorithmException, KeyManagementException
    {
        // 1 단계 인증 X.509를 사용하는 인증서 팩토리 객체 생성
        CertificateFactory cf   = CertificateFactory.getInstance("X.509");
        // 2. pem 을 읽어서 인증서 생성
        Certificate ca          = null;
        try {
            InputStream caInput     = context.getResources().openRawResource(R.raw.mysite_cert);
            ca = cf.generateCertificate(caInput);
            caInput.close();
            Log.i("FT", "인증서 로드 완료");
        } catch (Exception e) {
            Log.i("FT", "ERR:"+e.getMessage());
        }
        // 3. keystore 에 설정, 안드로이드 BKS, JKS를 사용할수도 있음
        KeyStore keyStore       = KeyStore.getInstance("BKS");
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);
        // 4.신뢰매니저생성후 키스토어 설정
        String tmAlgorithm      = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmAlgorithm);
        tmf.init(keyStore);

        // 신뢰 매니저들 획득
        TrustManager[] trustManagers = getTrustManagers(tmf.getTrustManagers());

        // SSL 획득
        SSLContext sslContext   = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, null);
        return sslContext.getSocketFactory();
    }
    // 래핑처리 (TrustManager의 wrapping)
    private TrustManager[] getTrustManagers(TrustManager[] trustManagers)
    {
        // 래핑이 되기 전에 원본
        x509TrustManager = (X509TrustManager)trustManagers[0];
        // 체크후 리턴
        return new TrustManager[]{
                // 인증서를 클라이언트단와 서버단 체크를 해서 다시 넣어준다
                new X509TrustManager(){
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        if( x509Certificates != null && x509Certificates.length > 0 ){
                            x509Certificates[0].checkValidity();
                        }else{
                            x509TrustManager.checkClientTrusted(x509Certificates, s);
                        }
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        if( x509Certificates != null && x509Certificates.length > 0 ){
                            x509Certificates[0].checkValidity();
                        }else{
                            x509TrustManager.checkClientTrusted(x509Certificates, s);
                        }
                    }
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return x509TrustManager.getAcceptedIssuers();
                    }
                }
        };
    }
}

















