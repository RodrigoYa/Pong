package com.example.tinch.pong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class Opciones extends AppCompatActivity {

    Button btnVolver;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        btnVolver = (Button)findViewById(R.id.btnVolver);
        spinner = (Spinner) findViewById(R.id.spinner);
        // crea el adaptador para poder utilizar el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dificultades, android.R.layout.simple_spinner_item);
        // especifica el layout del spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // seteamos el adaptador al spinner
        spinner.setAdapter(adapter);

        //para volver al main activity y poner lo seleccionado en el shared preferences
        btnVolver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dificultad",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("dificultad",spinner.getSelectedItem().toString());
                editor.commit();
                Intent jugar = new Intent(Opciones.this,MainActivity.class);
                startActivity(jugar);
            }

        });

    }
}
