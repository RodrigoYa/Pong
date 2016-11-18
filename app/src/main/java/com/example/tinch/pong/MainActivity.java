package com.example.tinch.pong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {
    Button btnJugar,btnOpciones;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJugar = (Button)findViewById(R.id.btnJugar);
        btnOpciones = (Button)findViewById(R.id.btnOpciones);

        //para ir al activity de las opciones del juego
        btnJugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent jugar = new Intent(MainActivity.this,ActivityOpcionesJuego.class);
                startActivity(jugar);
            }

        });

        //para ir al activity de las opciones
        btnOpciones.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent opciones = new Intent(MainActivity.this, Opciones.class);
                startActivity(opciones);
            }
        });
    }

}
