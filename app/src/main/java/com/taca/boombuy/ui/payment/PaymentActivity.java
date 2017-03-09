package com.taca.boombuy.ui.payment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.taca.boombuy.BaseActivity;
import com.taca.boombuy.R;

import java.net.URISyntaxException;

public class PaymentActivity extends BaseActivity
{
    WebView webView;
    String name;
    int price;
    boolean isChargeFinsh;  // 결제 화면 로딩이 완전히 끝나면 true가 됨.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        name = getIntent().getStringExtra("name");
        price = getIntent().getIntExtra("price", 0);
        if( name.length() == 0 || price == 0 ){
            // 결제 정보 오류
            Toast.makeText(this, "결제 정보가 누락되었습니다. 다시해!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("err", "결제 정보가 누락되었습니다. 다시해!");
            // 결과 돌려주기
            setResult(1001, intent);
            finish();
        }
        webView = (WebView)findViewById(R.id.webView);
        initChargeWeb();
    }
    public void onCharge(View view)
    {
        // 결제 진행
    }
    public void initChargeWeb()
    {
        webView.getSettings().setJavaScriptEnabled(true);
        // 2. 세팅 (성능, 자바스크립트 인터페이스 부분(안드<->웹 통신),
        // 웹 클라이언트, 크롬설정, 스토리지, 로그등등)
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);    // 캐싱 않함
        // WebSQL 데이터베이스를 유효하게 한다
        webView.getSettings().setDatabaseEnabled(true);
        String databasePath = getApplicationContext().getDir("websqldatabase", Context.MODE_PRIVATE).getPath();
        webView.getSettings().setDatabasePath(databasePath);
        // localStorage, sessionStorage를 유효화한다
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSaveFormData(false);       // 입력값 저장 창 않띠움 저장않함
        webView.getSettings().setSavePassword(false);
        // WebView도 컨텐트 프로바이더가 제공하는 컨텐츠를 읽어올 수 있다
        webView.getSettings().setAllowContentAccess(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.addJavascriptInterface(new MyInter(), "my");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if( newProgress == 100 && !isChargeFinsh ){
                    isChargeFinsh = true;   // 결제 페이지가 로딩이 모두 완료되었다.
                    webView.loadUrl("javascript:paynow('"+name+"', "+price+");");
                }
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //super.onPageFinished(view, url);
                hideProgress();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //super.onPageStarted(view, url, favicon);
                showProgress("결제진행중");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("javascript:")) {
                    Intent intent = null;

                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //IntentURI처리
                        Uri uri = Uri.parse(intent.getDataString());

                        startActivity(new Intent(Intent.ACTION_VIEW, uri)); //해당되는 Activity 실행
                        return true;
                    } catch (URISyntaxException ex) {
                        return false;
                    } catch (ActivityNotFoundException e) {
                        if ( intent == null )   return false;

                        // 해당 앱이 설치되었는지 스키마 검사
                        //if ( handleNotFoundPaymentScheme(intent.getScheme()) )  return true; //설치되지 않은 앱에 대해 사전 처리(Google Play이동 등 필요한 처리)

                        String packageName = intent.getPackage();
                        if (packageName != null) { //packageName이 있는 경우에는 Google Play에서 검색을 기본
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            }
        });
        webView.loadUrl("file:///android_asset/index.html");



    }
    public class MyInter
    {
        @JavascriptInterface
        public void showResult(String msg) {
            // 결제 결과 받기
            Toast.makeText(getBaseContext(), "결과 : " + msg,  Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("sec", msg);
            // 결과 돌려주기 응답 코드 : 1
            setResult(1, intent);
            finish();
        }
    }
    private final String APP_SCHEME = "iamportapp";
    private final String ISP_SCHEME = "ispmobile";
    @Override
    protected void onNewIntent(Intent intent) {
        String url = intent.toString();

        if ( url.startsWith(APP_SCHEME) ) {
            // "iamportapp://https://pgcompany.com/foo/bar"와 같은 형태로 들어옴
            String redirectURL = url.substring(APP_SCHEME.length() + "://".length());
            webView.loadUrl(redirectURL);
        }
    }

}
