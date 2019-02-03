package com.example.pc.quehojaes;

import android.os.Environment;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.Utils;

/**
 * Created by Isa on 4/21/2016.
 */
public class SingletonMLP {

    private static SingletonMLP ourInstance = null;

    MultilayerPerceptron mlp = new MultilayerPerceptron();
    String ArchivoEntrenamiento= Environment.getExternalStorageDirectory().getPath()+ "/ConjuntoEntrenamientoFINAL.arff";


    public static SingletonMLP getInstance()
    {
        if (ourInstance==null)
        {
            ourInstance  = new SingletonMLP();
        }
        return ourInstance;
    }

    private SingletonMLP()
    {
        try
        {
            Log.d("SINGLETON ENTRADA","entra en constructor");
            FileReader trainreader  = new FileReader(ArchivoEntrenamiento);
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);

            mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 4"));
            mlp.buildClassifier(train);
            Log.d("SINGLETON SALIDA", "sale en constructor");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
