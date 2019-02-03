package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pc on 21/01/2016.
 */
public class Descripcion extends Activity implements View.OnClickListener
{

    TextView Miplanta;
    TextView NombreCientifico;
    TextView Familia;
    TextView Usos;
    TextView Nervadura;
    TextView Forma;
    TextView Descripcion;
    TextView Clima;
    TextView Borde;
    ImageView Imagen;
    Button btnVolver;

    //region datos bd
    //Albahaca
    public static final String COLUMN_NOMBRECIENTIFICOal = "Ocimum tenuiflorum";
    public static final String COLUMN_FAMILIAal = "Menta";
    public static final String COLUMN_USOSal = "Mejora la memoria, Baja la fiebre, Mejora el dolor de Garganta y la salud emocional.";
    public static final String COLUMN_CLIMAal = "Calido";
    public static final String COLUMN_TIPODENERVADURAal = "Curvinervia";
    public static final String COLUMN_FORMAal = "Eliptica";
    public static final String COLUMN_BORDEal = "Lisa";
    public static final String COLUMN_DESCRIPCIONGENERALal = "Planta de origen de Asia, puede alcanzar una altura de 60 cm";

    //Cilantro
    public static final String COLUMN_NOMBRECIENTIFICOc = "Coriandrum sativ";
    public static final String COLUMN_FAMILIAc = "Apiaceas";
    public static final String COLUMN_USOSc = "Antiespasmodica, estomacales, platos de cocina latinoamericana.";
    public static final String COLUMN_CLIMAc = "Templado";
    public static final String COLUMN_TIPODENERVADURAc = "Radial";
    public static final String COLUMN_FORMAc = "Trifoliada";
    public static final String COLUMN_BORDEc = "Dentada";
    public static final String COLUMN_DESCRIPCIONGENERALc = "Origen del sureste asiatico, alcanza unos 40 a 60 cm, multiples usos en cocina latinoamericana china e indu";

    //Espinaca
    public static final String COLUMN_NOMBRECIENTIFICOe = "Spinacia oleracea";
    public static final String COLUMN_FAMILIAe = "Amarantáceas";
    public static final String COLUMN_USOSe = "Rica en Vitamina A, E, Hierro. Reduce la presion arterial, fortifica huesos y piel";
    public static final String COLUMN_CLIMAe = "Frio";
    public static final String COLUMN_TIPODENERVADURAe = "Penninervia";
    public static final String COLUMN_FORMAe = "Ovalada";
    public static final String COLUMN_BORDEe = "Lisa";
    public static final String COLUMN_DESCRIPCIONGENERALe = "Compuestas en su mayoria por agua, posee bajos contenidos de hidratos de carbono y grasas. Alto contenudo en fibra como muchas verduras, puede alcanzar unos 15 cm de largo";

    //Toronjil
    public static final String COLUMN_NOMBRECIENTIFICOt = "Melissa officinalis";
    public static final String COLUMN_FAMILIAt = "Lamiaceas";
    public static final String COLUMN_USOSt = "Muy utilizada en dentrificos, por sus propiedades antisepticas y aromaticas. Ayuda a relajar y disminuir las palpitaciones de origen nervioso. Tambien se utiliza como repelente de zancudos";
    public static final String COLUMN_CLIMAt = "Calido";
    public static final String COLUMN_TIPODENERVADURAt = "Penninervia";
    public static final String COLUMN_FORMAt = "Acorazonada";
    public static final String COLUMN_BORDEt = "Dentada";
    public static final String COLUMN_DESCRIPCIONGENERALt = "Nativa del sur de europa. Apreciada por su fuerte aroma de limon. Utilizada en perfumeria, infusiones y tranquilizante natural.";

    //Yerbabuena
    public static final String COLUMN_NOMBRECIENTIFICOy = "Mentha spicata";
    public static final String COLUMN_FAMILIAy = "Lamiaceae";
    public static final String COLUMN_USOSy = "Propiedades antisepticas, antiespasmodicas, analgesico, antiinflamatorio y estimulante. Alivia cefalea tensional, disminuye niveles de estres y mejora el mal aliento";
    public static final String COLUMN_CLIMAy = "Templado";
    public static final String COLUMN_TIPODENERVADURAy = "Penninervia";
    public static final String COLUMN_FORMAy = "Lanceolada";
    public static final String COLUMN_BORDEy = "Lisa";
    public static final String COLUMN_DESCRIPCIONGENERALy = "Es una hierba aromatica muy empleada en gastronomia y perfumes por su aroma intenso y fresco";

    //Menta
    public static final String COLUMN_NOMBRECIENTIFICOm = "Mentha sativa";
    public static final String COLUMN_FAMILIAm = "Lamiacea";
    public static final String COLUMN_USOSm = "Es tonica y estimulante, sirve para disminuir problemas estomacales porque favorece la secrecion biliar, es rica en Mentol y da uso un uso gastronomico";
    public static final String COLUMN_CLIMAm = "Fresco y Humedo";
    public static final String COLUMN_TIPODENERVADURAm = "Penninervia";
    public static final String COLUMN_FORMAm = "Acorazonada";
    public static final String COLUMN_BORDEm = "Lisa";
    public static final String COLUMN_DESCRIPCIONGENERALm = "Puede alcanzar los 25 cm de alto, se cria en huertas con suele muy humedo";

    Planta Albahaca = new Planta(COLUMN_NOMBRECIENTIFICOal, COLUMN_FAMILIAal, COLUMN_USOSal, COLUMN_CLIMAal, COLUMN_TIPODENERVADURAal, COLUMN_FORMAal, COLUMN_BORDEal, COLUMN_DESCRIPCIONGENERALal);
    Planta Cilantro = new Planta(COLUMN_NOMBRECIENTIFICOc, COLUMN_FAMILIAc, COLUMN_USOSc, COLUMN_CLIMAc, COLUMN_TIPODENERVADURAc, COLUMN_FORMAc, COLUMN_BORDEc, COLUMN_DESCRIPCIONGENERALc);
    Planta Espinaca = new Planta(COLUMN_NOMBRECIENTIFICOe, COLUMN_FAMILIAe, COLUMN_USOSe, COLUMN_CLIMAe, COLUMN_TIPODENERVADURAe, COLUMN_FORMAe, COLUMN_BORDEe, COLUMN_DESCRIPCIONGENERALe);
    Planta Toronjil = new Planta(COLUMN_NOMBRECIENTIFICOt, COLUMN_FAMILIAt, COLUMN_USOSt, COLUMN_CLIMAt, COLUMN_TIPODENERVADURAt, COLUMN_FORMAt, COLUMN_BORDEt, COLUMN_DESCRIPCIONGENERALt);
    Planta YerbaBuena = new Planta(COLUMN_NOMBRECIENTIFICOy, COLUMN_FAMILIAy, COLUMN_USOSy, COLUMN_CLIMAy, COLUMN_TIPODENERVADURAy, COLUMN_FORMAy, COLUMN_BORDEy, COLUMN_DESCRIPCIONGENERALy);
    Planta Menta = new Planta(COLUMN_NOMBRECIENTIFICOm, COLUMN_FAMILIAm, COLUMN_USOSm, COLUMN_CLIMAm, COLUMN_TIPODENERVADURAm, COLUMN_FORMAm, COLUMN_BORDEm, COLUMN_DESCRIPCIONGENERALm);
    //endregion



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descripcion);

        //region find text view
        Miplanta = (TextView) findViewById(R.id.tvTitulo);
        NombreCientifico = (TextView) findViewById(R.id.tvCientifico);
        Familia = (TextView) findViewById(R.id.tvFamilia);
        Usos = (TextView) findViewById(R.id.tvUsos);
        Forma = (TextView) findViewById(R.id.tvForma);
        Nervadura = (TextView) findViewById(R.id.tvNervadura);
        Clima = (TextView) findViewById(R.id.tvClima);
        Borde = (TextView) findViewById(R.id.tvBorde);
        Descripcion = (TextView) findViewById(R.id.tvDescripcionGeneral);
        Imagen = (ImageView) findViewById(R.id.ivImagen);
        btnVolver = (Button) findViewById(R.id.btnVolver);
        //endregion

        //region insert BD
        DbHandler miBd = new DbHandler(this,null,null,1);
        if(miBd.imprimirPlanta("Mentha spicata")==null)
        {
            miBd.agregarPlanta(Albahaca);
            miBd.agregarPlanta(Cilantro);
            miBd.agregarPlanta(Espinaca);
            miBd.agregarPlanta(Toronjil);
            miBd.agregarPlanta(YerbaBuena);
            miBd.agregarPlanta(Menta);
        }
        //endregion

        //region leer seleccion
        String planta=null;
        Intent intent = getIntent();
        if (intent.getExtras().getString("seleccion")!=null)
        {
            planta = (String) getIntent().getExtras().getString("seleccion");
        }
        //endregion

        //region buscar seleccion en bd
        Planta miPlanta = new Planta();

        if (planta.equals("Yerbabuena") )
        {
            miPlanta= miBd.imprimirPlanta("Mentha spicata");
            Miplanta.setText("Yerbabuena");
            Imagen.setImageResource(R.drawable.hierbabuena);
        }

        if (planta.equals("Toronjil") )
        {
            miPlanta= miBd.imprimirPlanta("Melissa officinalis");
            Miplanta.setText("Toronjil");
            Imagen.setImageResource(R.drawable.toronjil);
        }

        if (planta.equals("Cilantro" ))
        {
            miPlanta= miBd.imprimirPlanta("Coriandrum sativ");
            Miplanta.setText("Cilantro");
            Imagen.setImageResource(R.drawable.cilantro);
        }

        if (planta.equals( "Menta" ))
        {
            miPlanta= miBd.imprimirPlanta("Mentha sativa");
            Miplanta.setText("Menta");
            Imagen.setImageResource(R.drawable.menta);
        }

        if (planta.equals( "Espinaca" ))
        {
            miPlanta= miBd.imprimirPlanta("Spinacia oleracea");
            Miplanta.setText("Espinaca");
            Imagen.setImageResource(R.drawable.espinaca);
        }

        if (planta.equals( "Albahaca" ))
        {
            miPlanta= miBd.imprimirPlanta("Ocimum tenuiflorum");
            Miplanta.setText("Albahaca");
            Imagen.setImageResource(R.drawable.albahaca);
        }
        //endregion

        //region Mostrar datos de bd en pantalla
        NombreCientifico.setText(miPlanta.getNombreCientifico());
        Familia.setText(miPlanta.getFamilia());
        Usos.setText(miPlanta.getUsos());
        Clima.setText(miPlanta.getClima());
        Forma.setText(miPlanta.getForma());
        Nervadura.setText(miPlanta.getTipoNervadura());
        Borde.setText(miPlanta.getBorde());
        Descripcion.setText(miPlanta.getDescripcionGeneral());
        //endregion
    }



    public void onClick(View v2)

    {
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                finish();
               // Intent i10 = new Intent(getBaseContext(), MainActivity.class);
              //  startActivity(SingletonMAIN.getInstance().i10);
                onBackPressed();
            }
        });
    }




}
