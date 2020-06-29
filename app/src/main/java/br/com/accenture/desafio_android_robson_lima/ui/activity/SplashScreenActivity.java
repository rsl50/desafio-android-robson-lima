package br.com.accenture.desafio_android_robson_lima.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import br.com.accenture.desafio_android_robson_lima.BuildConfig;
import br.com.accenture.desafio_android_robson_lima.R;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Desafio Android - Robson Lima";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setTitle(TITULO_APPBAR);

        TextView labelVersion = findViewById(R.id.label_version);
        labelVersion.setText(BuildConfig.VERSION_NAME);

        showSplashScreen();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.go_up, R.anim.go_down);
    }

    private void showSplashScreen() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMainScreen();
            }
        }, 2000);
    }

    private void showMainScreen() {
        Intent intent = new Intent(SplashScreenActivity.this, HeroesListActivity.class);
        ActivityOptions option = ActivityOptions.makeCustomAnimation(SplashScreenActivity.this, R.anim.go_up, R.anim.go_down);
        startActivity(intent, option.toBundle());
    }
}
