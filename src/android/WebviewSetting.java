package com.darktalker.cordova.webviewsetting;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Build;

import android.util.Log;

public class WebviewSetting extends CordovaPlugin {
    private CordovaWebView webView;
    private static final String LOG_TAG = "WebviewSetting";
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        Log.d(LOG_TAG, "Webview flags initialized");
        this.webView = webView;
        super.initialize(cordova, webView); 
        
    }
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if ("set".equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {

					final WebSettings settings = webView.getSettings();
					
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
