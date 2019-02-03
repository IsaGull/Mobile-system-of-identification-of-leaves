package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



/**
 * Created by Pc on 16/01/2016.
 */

public class Camara extends Activity implements View.OnClickListener  {

    Button btnIdentificar;
    ImageView img;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camara);
        init();
    }

    public void init()
    {

        btnIdentificar =(Button)findViewById(R.id.botonIdentificar);
        img =(ImageView)findViewById(R.id.imagenCamara);

        Intent intent = getIntent();

        if (intent.getParcelableExtra("Imagen")!=null)
        {
            bitmap = (Bitmap) intent.getParcelableExtra("Imagen");
            img.setImageBitmap(bitmap);
        }

        if (intent.getParcelableExtra("ImagenGaleria")!=null)
        {

            bitmap = (Bitmap) intent.getParcelableExtra("ImagenGaleria");
            img.setImageBitmap(bitmap);
        }

    }


    public void onClick(View v)
    {
        btnIdentificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i2 = new Intent(getBaseContext(), Tratamiento.class);
                i2.putExtra("ImagenTratamiento",bitmap);
                startActivity(i2);
            }
        });
    }
}
