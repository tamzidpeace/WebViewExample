package com.example.arafat.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar myProgressBar = findViewById(R.id.my_progress_bar);
        final ImageView myImageView = findViewById(R.id.my_image_view);
        myWebView = findViewById(R.id.my_web_view);
        final Button search = findViewById(R.id.search);
        final EditText webAddress = findViewById(R.id.web_address);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String web_address = "https://www." + webAddress.getText().toString();
                myWebView.loadUrl(web_address);
            }
        });

        myProgressBar.setMax(100);


        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(
                new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        myProgressBar.setProgress(newProgress);
                    }

                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                    }

                    @Override
                    public void onReceivedIcon(WebView view, Bitmap icon) {
                        super.onReceivedIcon(view, icon);
                        myImageView.setImageBitmap(icon);
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack())
            myWebView.goBack();
        else
            finish();
    }
}
