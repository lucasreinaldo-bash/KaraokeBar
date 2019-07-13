package vostore.karaoquebar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import java.text.Normalizer;
import java.util.regex.Pattern;


public class SiteLetras extends AppCompatActivity {


    private WebView mWebView;
    private Button voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_letra);

        // android.support.v7.app.ActionBar bar = getSupportActionBar();
        // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
        Bundle extras = getIntent().getExtras();
        mWebView = (WebView) findViewById(R.id.site);
        voltar = findViewById(R.id.text_voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Recebendo informação de outra Activity
        if (null != getIntent()) {
            /** Pegamos o VALOR_1**/
            final String site = getIntent().getStringExtra("site");


            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(false);
            mWebView.loadUrl(""+site);
//            Toast.makeText(this, ""+site, Toast.LENGTH_SHORT).show();
            mWebView.setWebViewClient(new SiteLetras.HelloWebViewClient());

        }
    }
    @Override
    public void onBackPressed () {

        Intent intent = new Intent(SiteLetras.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }

    public class HelloWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            //progressBar.setVisibility(view.GONE);
        }

    }


    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event)
    { //if back key is pressed
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }
}
