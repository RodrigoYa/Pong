package com.example.tinch.pong;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class ActivityJuego extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //obtenemos el shared preferences para indicar la dificultad
        SharedPreferences sharedPreferences = getSharedPreferences("Dificultad",Context.MODE_PRIVATE);
        String dificultad = sharedPreferences.getString("dificultad","Normal");

        // ponemos el Juego como view
        Juego game = new Juego(this,getIntent().getExtras().getBoolean("modo"),dificultad);
        setContentView(game);



    }

}
