package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import com.example.pc.quehojaes.Huclase;

/**
 * Created by Pc on 25/02/2016.
 */

public class Tratamiento extends Activity implements View.OnClickListener{

    ImageView img;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    Button btnIdentifica;
    TextView txtm1;
    TextView txtm2;
    TextView txtm3;
    TextView txtm4;
    TextView txtm5;
    TextView txtm6;
    TextView txtm7;
    TextView txPrediccion;
    Bitmap bitmap;
    Bitmap bitmapGris;
    double [][] ArregloHu = new double[7][];
    Huclase MomentoHu = new Huclase();
    String [] stra = new String[7];
    String resultado ="";

    // Aqui va a entrar una vez cargado el onCreate
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            if (status == LoaderCallbackInterface.SUCCESS )
            {
                try
                {
                    Grises();
                    Suavisado();
                    Binarizacion();
                    Canny();
                    Contornos();
                    Hu();
                }
                catch (Exception ex)
                {Volver();}


            } else {
                super.onManagerConnected(status);
            }
        }
    };
    //*********************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tratamiento);
        init();
    }

    public void init() {
        img = (ImageView) findViewById(R.id.Original);
        img2 = (ImageView) findViewById(R.id.Grises);
        img3 = (ImageView) findViewById(R.id.Suavisado);
        img4 = (ImageView) findViewById(R.id.Binarizado);
        img5 = (ImageView) findViewById(R.id.Canny);
        img6 = (ImageView) findViewById(R.id.Findcontour);
        txtm1 = (TextView) findViewById(R.id.textView31);
        txtm2 = (TextView) findViewById(R.id.textView32);
        txtm3 = (TextView) findViewById(R.id.textView33);
        txtm4 = (TextView) findViewById(R.id.textView34);
        txtm5 = (TextView) findViewById(R.id.textView35);
        txtm6 = (TextView) findViewById(R.id.textView36);
        txtm7 = (TextView) findViewById(R.id.textView37);
        txPrediccion = (TextView) findViewById(R.id.tvPrediccion);
        btnIdentifica = (Button) findViewById(R.id.btnIdentifica);

        Intent intent = getIntent();
        bitmap = (Bitmap) intent.getParcelableExtra("ImagenTratamiento");
        Original();

    }

    public void Original()
    {

        img.setImageBitmap(bitmap);
    }

    public void Grises()
    {
        Mat imagenOriginal;
        imagenOriginal = new Mat();
        Utils.bitmapToMat(bitmap, imagenOriginal);
        Mat gris= new Mat(imagenOriginal.width() ,imagenOriginal.height(),imagenOriginal.type());
        Imgproc.cvtColor(imagenOriginal, gris, Imgproc.COLOR_RGB2GRAY);
        Bitmap bitmapa = Bitmap.createBitmap(gris.cols(),gris.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(gris, bitmapa);
        img2.setImageBitmap(bitmapa);


    }

    public void Suavisado()
    {

        Mat imagenOriginal;
        imagenOriginal = new Mat();
        Utils.bitmapToMat(bitmap, imagenOriginal);
        Mat gris= new Mat(imagenOriginal.width() ,imagenOriginal.height(),imagenOriginal.type());
        Imgproc.cvtColor(imagenOriginal, gris, Imgproc.COLOR_RGB2GRAY);
        org.opencv.core.Size s = new Size(3, 3);
        Imgproc.GaussianBlur(gris, gris, s, 2);
        Bitmap bitmapa2 = Bitmap.createBitmap(gris.cols(),gris.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(gris,bitmapa2);
        img3.setImageBitmap(bitmapa2);


    }

    public void Binarizacion(){

        Mat imagenOriginal;
        imagenOriginal = new Mat();

        Mat binario;
        binario = new Mat();

        Utils.bitmapToMat(bitmap, imagenOriginal);
        Mat gris= new Mat(imagenOriginal.width() ,imagenOriginal.height(),imagenOriginal.type());
        Imgproc.cvtColor(imagenOriginal, gris, Imgproc.COLOR_RGB2GRAY);
        org.opencv.core.Size s = new Size(3,3);
        Imgproc.GaussianBlur(gris, gris, s, 2);

        Imgproc.threshold(gris, binario, 100, 255, Imgproc.THRESH_BINARY);

        Bitmap bitmapa3 = Bitmap.createBitmap(binario.cols(),binario.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(binario, bitmapa3);
        img4.setImageBitmap(bitmapa3);


    }

    public void Canny(){

        Mat imagenOriginal;
        imagenOriginal = new Mat();

        Mat binario;
        binario = new Mat();

        Mat Canny;
        Canny = new Mat();

        Utils.bitmapToMat(bitmap, imagenOriginal);
        Mat gris= new Mat(imagenOriginal.width() ,imagenOriginal.height(),imagenOriginal.type());
        Imgproc.cvtColor(imagenOriginal, gris, Imgproc.COLOR_RGB2GRAY);
        org.opencv.core.Size s = new Size(3,3);
        Imgproc.GaussianBlur(gris, gris, s, 2);

        Imgproc.threshold(gris, binario, 100, 255, Imgproc.THRESH_BINARY);
        Imgproc.Canny(binario, Canny, 100, 100 * 2);

        Bitmap bitmapa4 = Bitmap.createBitmap(Canny.cols(),Canny.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(Canny, bitmapa4);
        img5.setImageBitmap(bitmapa4);



    }

    public void Contornos()
    {

        Mat imagenOriginal;
        imagenOriginal = new Mat();

        Mat binario;
        binario = new Mat();

        Mat Canny;
        Canny = new Mat();

        Utils.bitmapToMat(bitmap, imagenOriginal);
        Mat gris= new Mat(imagenOriginal.width() ,imagenOriginal.height(),imagenOriginal.type());
        Imgproc.cvtColor(imagenOriginal, gris, Imgproc.COLOR_RGB2GRAY);
        org.opencv.core.Size s = new Size(3,3);
        Imgproc.GaussianBlur(gris, gris, s, 2);

        Imgproc.threshold(gris, binario, 100, 255, Imgproc.THRESH_BINARY);
        Imgproc.Canny(binario, Canny, 100, 100 * 2);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        Mat hierarcy = new Mat();

        Imgproc.findContours(Canny, contours, hierarcy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(Canny, contours, -1, new Scalar(Math.random() * 255, Math.random() * 255, Math.random() * 255));

        Bitmap bitmapa5 = Bitmap.createBitmap(Canny.cols(),Canny.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(Canny, bitmapa5);
        img6.setImageBitmap(bitmapa5);

    }

    public void Hu()
    {

        Mat imagenOriginal;
        imagenOriginal = new Mat();
        Mat binario;
        binario = new Mat();
        Mat Canny;
        Canny = new Mat();

        Utils.bitmapToMat(bitmap, imagenOriginal);
        Mat gris = new Mat(imagenOriginal.width(), imagenOriginal.height(), imagenOriginal.type());
        Imgproc.cvtColor(imagenOriginal, gris, Imgproc.COLOR_RGB2GRAY);

        org.opencv.core.Size s = new Size(3, 3);
        Imgproc.GaussianBlur(gris, gris, s, 2);

        Imgproc.threshold(gris, binario, 100, 255, Imgproc.THRESH_BINARY);
        Imgproc.Canny(binario, Canny, 100, 100*2);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarcy = new Mat();
        Imgproc.findContours(Canny, contours, hierarcy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(Canny, contours, -1, new Scalar(Math.random()* 255, Math.random()* 255, Math.random() * 255));


       Moments MomentoCentral = new Moments();
        Mat hu= new Mat();
        //declara una matriz
        MomentoCentral = Imgproc.moments(contours.get(0), false);
        // Se obtiene el momento central de la lista contours
        // en la posición cero
        Imgproc.HuMoments(MomentoCentral, hu);
        // se ejecuta el metodo de Hu, se guardan en matriz hu

        // Recorre Matriz HU, para leer los 7 momentosHu.
        for ( int row=0; row<hu.rows(); row++)
        {
            for (int col=0; col<hu.cols(); col++)
            {
                ArregloHu[row]=hu.get(row, col);
            }
        }

        //Recorre el arreglo anterior para pasarlo al DropDownList
        Double[] ArreRNA = new Double[7];
        for (int i=0; i<ArregloHu.length; i++)
        {
            stra[i] =  Double.toString(ArregloHu[i][0]);
            ArreRNA[i] = ArregloHu[i][0];
        }

        //Muestro momentoshu en la ventana
        txtm1.setText(stra[0]);
        txtm2.setText(stra[1]);
        txtm3.setText(stra[2]);
        txtm4.setText(stra[3]);
        txtm5.setText(stra[4]);
        txtm6.setText(stra[5]);
        txtm7.setText(stra[6]);

       if((ArreRNA[0]==0.0)&&(ArreRNA[1]==0.0)&&(ArreRNA[2]==0.0)&&(ArreRNA[3]==0.0)&&(ArreRNA[4]==0.0)&&(ArreRNA[5]==0.0)&&(ArreRNA[6]==0.0))
       {
            Toast.makeText(getApplicationContext(), "Intente de nuevo", Toast.LENGTH_SHORT).show();
            Log.d("TRTAMIENTOO", "HU igual 0");
           Volver();
       }
       else
       {
           //escribo datos en archivo
           escribirFicheroMemoriaExterna();
           //evaluo en red neuronal
           RNA modeloRed= new RNA (ArreRNA);
           resultado = modeloRed.Resultado();
           txPrediccion.setText(modeloRed.Proba());
       }
    }


    public void escribirFicheroMemoriaExterna()
    {

        String dato;
        dato="*********"+stra[0] +" "+ stra[1]+" "+ stra[2]+" "+stra[3]+" "+ stra[4]+" "+stra[5]+" "+ stra[6];
        File file;
        FileOutputStream outputStream;
        try
        {
            file = new File(Environment.getExternalStorageDirectory().getPath()+"/Archivo.txt");

            if (file.exists())
            {
                FileInputStream fin = new FileInputStream(file);
                InputStreamReader archivo = new InputStreamReader(fin);
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea!=null)
                {
                    todo=todo+linea+" ";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                dato = dato + todo;

                outputStream = new FileOutputStream(file);
                outputStream.write(dato.getBytes());
                outputStream.close();
            }
            else
            {
                file.createNewFile();
                outputStream = new FileOutputStream(file);
                outputStream.write(dato.getBytes());
                outputStream.close();
            }
        } catch (Exception ex)
        {
            Log.e("Error", "Error al escribir fichero a memoria Externa");
        }
    }


    public void onClick(View v)
    {
        btnIdentifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i2 = new Intent(getBaseContext(), Descripcion.class);
                i2.putExtra("seleccion",resultado);
                startActivity(i2);
            }
        });
    }

    public void Volver ()
    {
        finish();
        onBackPressed();
    }

    public void onResume()
    {
        super.onResume();

        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11,this,mLoaderCallback);
    }
}
