package com.darktalker.cordova.webviewsetting;

import org.apache.cordova.*;
import org.apache.cordova.engine.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Build;
import android.webkit.*;

import android.util.Log;


public class WebviewSetting extends CordovaPlugin {
    private CordovaWebView wv;
    private static final String LOG_TAG = "WebviewSetting";
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        this.wv = webView;
        super.initialize(cordova, webView); 
		setWebviewFlags();
        
    }
	
    private void setWebviewFlags() {
		// needed for sdk 30 - sqlite plugin db access
		cordova.getActivity().runOnUiThread(new Runnable() {
			public void run() {
				CordovaWebViewImpl webViewImpl = (CordovaWebViewImpl) wv;
				SystemWebViewEngine engine = (SystemWebViewEngine) webViewImpl.getEngine();
				WebView webView = (WebView) engine.getView();
				WebSettings settings = webView.getSettings();	
				// needed for sdk 30 - sqlite plugin db access
				settings.setAllowFileAccess(true);
				Log.d(LOG_TAG, "Webview flags setAllowFileAccess set to true");
				settings.setAllowContentAccess(true);
				Log.d(LOG_TAG, "Webview flags setAllowContentAccess set to true");
				settings.setAllowFileAccessFromFileURLs(true);
				Log.d(LOG_TAG, "Webview flags setAllowFileAccessFromFileURLs set to true");
				
			}
		});
    }
	
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if ("set".equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
				public void run() {
					CordovaWebViewImpl webViewImpl = (CordovaWebViewImpl) wv;
					SystemWebViewEngine engine = (SystemWebViewEngine) webViewImpl.getEngine();
					WebView webView = (WebView) engine.getView();
					WebSettings settings = webView.getSettings();	
					// needed for sdk 30 - sqlite plugin db access
					settings.setAllowFileAccess(true);
					Log.d(LOG_TAG, "Webview flags setAllowFileAccess set to true");
					settings.setAllowContentAccess(true);
					Log.d(LOG_TAG, "Webview flags setAllowContentAccess set to true");
					settings.setAllowFileAccessFromFileURLs(true);
					Log.d(LOG_TAG, "Webview flags setAllowFileAccessFromFileURLs set to true");
					
					callbackContext.success();
				}
            });
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }
}
