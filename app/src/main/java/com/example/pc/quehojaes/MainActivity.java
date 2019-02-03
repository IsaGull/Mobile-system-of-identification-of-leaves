package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;



public class MainActivity extends ActionBarActivity implements View.OnClickListener, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    public Button imgButtonExplorar;
    public Button imgButtonCamara;
    public Button imgButtonGaleria;
    public TextView texto;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //texto = (TextView) findViewById(R.id.textView2);
        imgButtonGaleria = (Button) findViewById(R.id.button);
        imgButtonCamara = (Button) findViewById(R.id.button2);
        imgButtonExplorar = (Button) findViewById(R.id.button3);
    }

    //region BUSCADOR
    //Metodos a sobreescribir obligador por el buscador*********************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.buscador, menu);
        MenuItem searchItem = menu.findItem(R.id.menu3_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Buscador activado", Toast.LENGTH_SHORT).show();
        return true;
    }
    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Buscador desactivado", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
       // texto.setText("Escribiendo texto...\n\n" + s);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
       // texto.setText("Texto a buscar\n\n" + s);
        String hoja ="";
        if (s.equals("Albahaca")||s.equals("albahaca") )
        {
            hoja="Albahaca";
            VerDescripcion(hoja);
            return false;
        }
        if (s.equals("Hierbabuena")||s.equals("hierbabuena") ||s.equals("HierbaBuena")||s.equals("yerbabuena")||s.equals("Yerbabuena")||s.equals("YerbaBuena") )
        {
            hoja="Yerbabuena";
            VerDescripcion(hoja);
            return false;
        }
        if (s.equals("Toronjil")||s.equals("toronjil") )
        {
            hoja="Toronjil";
            VerDescripcion(hoja);
            return false;
        }
        if (s.equals("Espinaca")||s.equals("espinaca") )
        {
            hoja="Espinaca";
            VerDescripcion(hoja);
            return false;
        }
        if (s.equals("Menta")||s.equals("menta") )
        {
            hoja="Menta";
            VerDescripcion(hoja);
            return false;
        }
        if (s.equals("Cilantro")||s.equals("cilantro") )
        {
            hoja="Cilantro";
            VerDescripcion(hoja);
            return false;
        }

        Toast.makeText(getApplicationContext(), "No existen coincidencias", Toast.LENGTH_LONG).show();
        return false;
    }

    public void VerDescripcion (String hoja)
    {
        Intent i10 = new Intent(getBaseContext(), Descripcion.class);
        i10.putExtra("seleccion", hoja);
        startActivity(i10);
    }
    //endregion




    //**********************************************************************************************

     //Segun el boton que presione en la pantalla principal, ira a galeria, camara o explorar.
    public void onClick(View v)
    {
        if (v.getId() == R.id.button3   )
        {
            Conexion con = new Conexion();
            //Si se presiona boton de explorar
            imgButtonExplorar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getBaseContext(), Explorar.class);
                    startActivity(i);
                     }
            });
        }

        if (v.getId() == R.id.button2  )
        {
            //Si se presiona boton de Camara
            imgButtonCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i2, 0);
                }
            });

        }

        //
        if (v.getId() == R.id.button  )
        {
            //Si se presiona boton de Galeria
            imgButtonGaleria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i3, 1);
                }
            });
        }
    }

    //**********************************************************************************************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        try {

        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==Activity.RESULT_OK && requestCode==0)
        {
            Bundle ext=data.getExtras();
            bmp=(Bitmap)ext.get("data");

            Intent intent= new Intent(getBaseContext(),Camara.class);
            intent.putExtra("Imagen",bmp);
            startActivity(intent);

        }

        if(resultCode==Activity.RESULT_OK && requestCode==1)
        {
            Uri uri=data.getData();
            String[] projection={MediaStore.Images.Media.DATA};

            Cursor cursor=getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex=cursor.getColumnIndex(projection[0]);
            String filePath=cursor.getString(columnIndex);
            cursor.close();

            Bitmap Imagen= BitmapFactory.decodeFile(filePath);
           // Drawable d=new BitmapDrawable(Imagen);


            Intent intent= new Intent(getBaseContext(),Camara.class);
            intent.putExtra("ImagenGaleria",scaleDownBitmap(Imagen,150,getBaseContext()));
            startActivity(intent);

        }
        }
        catch (Exception ex)
        {}

    }

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context)
    {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }


}


