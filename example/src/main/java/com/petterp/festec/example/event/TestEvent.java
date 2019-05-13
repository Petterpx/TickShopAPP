package com.petterp.festec.example.event;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;
import android.widget.Toast;

import com.petterp.latte_core.delegates.web.event.Event;

/**
 *
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
