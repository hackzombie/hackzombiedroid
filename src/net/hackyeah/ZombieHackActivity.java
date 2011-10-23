package net.hackyeah;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.socialize.ui.SocializeUI;


public class ZombieHackActivity extends Activity {
	
	WebView mWebView;
	final String baseURL = "http://hackyeah.net/"; //"http://192.168.1.132:3000/"; 
	final String defaultResource = "mocks/list.html";
	
	final String consumerKey = "4a9e2e68-9f41-4072-aa3e-f3a172edfbb7";
	final String consumerSecret = "fd5ebf35-6c92-4558-b092-8bd7e0615d98";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(baseURL + defaultResource);
        mWebView.setWebViewClient(new ZeroHackWebViewClient());
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private class ZeroHackWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	if (url.startsWith(baseURL)) {
        		view.loadUrl(url);
            	return true;
        	}
        	else {
        		return false;
        	}
        }
    }
    
    
    public void openShare() {
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.main_refresh:
        	mWebView.loadUrl(baseURL + defaultResource);
            return true;
        case R.id.main_share:    
			SocializeUI.getInstance().setSocializeCredentials(consumerKey, consumerSecret);
			//SocializeUI.getInstance().setFacebookAppId(facebookAppId);
			SocializeUI.getInstance().showCommentView(ZombieHackActivity.this, mWebView.getUrl());
			return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}