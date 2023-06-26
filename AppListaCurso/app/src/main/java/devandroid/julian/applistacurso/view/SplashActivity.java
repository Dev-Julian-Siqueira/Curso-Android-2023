package devandroid.julian.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import devandroid.julian.applistacurso.R;
import devandroid.julian.applistacurso.database.ListaVipDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cumutarTelaSplash();


    }

    private void cumutarTelaSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ListaVipDB db = new ListaVipDB(SplashActivity.this);

                Intent telaPrincipal = new Intent(
                        SplashActivity.this,
                        MainActivity.class);

                startActivity(telaPrincipal);
                finish();

            }
        },TIME_OUT_SPLASH);


    }
}