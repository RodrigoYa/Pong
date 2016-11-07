package com.example.tinch.pong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityOpcionesJuego extends AppCompatActivity {

    Button btn1v1, btnVsPC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_juego);
        btn1v1 = (Button)findViewById(R.id.btn1v1);
        btnVsPC = (Button)findViewById(R.id.btnVsPC);

        btn1v1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent jugar = new Intent(ActivityOpcionesJuego.this,ActivityJuego.class);
                jugar.putExtra("modo",true);
                startActivity(jugar);
            }

        });
        btnVsPC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent jugar = new Intent(ActivityOpcionesJuego.this,ActivityJuego.class);
                jugar.putExtra("modo",false);
                startActivity(jugar);            }

        });
    }
}
