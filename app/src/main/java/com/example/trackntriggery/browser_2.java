package com.example.trackntriggery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class browser_2 extends AppCompatActivity {
    WebView browser;
    EditText link;
    Button btnvisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_2);
        browser = (WebView)findViewById(R.id.webview);
        link = (EditText)findViewById(R.id.edittextlink);
        btnvisit = (Button)findViewById(R.id.ButtonView);
        browser.setWebViewClient(new myWebViewClient());
        btnvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://"+link.getText().toString();
                browser.getSettings().setLoadsImagesAutomatically(true);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.loadUrl(url);
            }
        });
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}