package vostore.karaoquebar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Essa classe é utilizada como tela inicial. Possui uma animação e faz transição após 3 segundos para a ValidarCPF
 */
public class Maps extends AppCompatActivity {


    private Button btn_home,btn_voltar;
    private LocationManager lm;
    private Location location;
    private double longitude = -25.5598719;
    private double latitude = -54.5616859;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_maps);

        // android.support.v7.app.ActionBar bar = getSupportActionBar();
        // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
        Bundle extras = getIntent().getExtras();
        mWebView = (WebView) findViewById(R.id.site);

        //Recebendo informação de outra Activity
        if (null != getIntent()) {
            /** Pegamos o VALOR_1**/
            final String site = getIntent().getStringExtra("site");


            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(false);
            mWebView.loadUrl("            https://www.google.com/maps/place/Karaokê+Bar/@-25.4284121,-49.2806439,17z/data=!3m1!4b1!4m5!3m4!1s0x94dce40c6c793e9f:0x3af0a52edd7bc490!8m2!3d-25.4284121!4d-49.27845525");
            mWebView.setWebViewClient(new HelloWebViewClient());

        }
    }
    @Override
    public void onBackPressed () {

        Intent intent = new Intent(Maps.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private class HelloWebViewClient extends WebViewClient {


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
