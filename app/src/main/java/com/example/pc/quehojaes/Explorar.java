package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import weka.classifiers.functions.MultilayerPerceptron;

/**
 * Created by Pc on 16/01/2016.
 */
public class Explorar extends Activity implements View.OnClickListener {


    Button btnExplorar;
    String Seleccion;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explorar);

        btnExplorar =(Button)findViewById(R.id.btnExplorar);

        String[] country={"Yerbabuena", "Toronjil", "Albahaca","Espinaca","Menta","Cilantro"};

        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, country);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Seleccion = (String) adapterView.getItemAtPosition(position);
                //
                // Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });

    }


    public void onClick(View v2)

    {
        btnExplorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                finish();
                Intent i10 = new Intent(getBaseContext(), Descripcion.class);
                i10.putExtra("seleccion", Seleccion);
                //Toast.makeText(getBaseContext(), Seleccion, Toast.LENGTH_SHORT).show();
                startActivity(i10);
            }
        });




    }
}
