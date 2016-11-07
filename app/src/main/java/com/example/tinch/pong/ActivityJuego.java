package com.example.tinch.pong;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class ActivityJuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // ponemos el Juego como view
        SharedPreferences sharedPreferences = getSharedPreferences("Dificultad",Context.MODE_PRIVATE);
        String dificultad = sharedPreferences.getString("dificultad","Normal");

        Juego game = new Juego(this,getIntent().getExtras().getBoolean("modo"),dificultad);
        setContentView(game);
    }
}
