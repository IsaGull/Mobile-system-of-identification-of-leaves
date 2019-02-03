package com.example.pc.quehojaes;

import android.os.Environment;
import android.util.Log;

import java.io.FileReader;
import java.text.DecimalFormat;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import static java.lang.Math.exp;

/**
 * Created by Isa on 4/3/2016.
 */
public class RNA
{
    //region Variable RNA manual
    double [][] PesosCapaOculta = new double[4][8] ;  // 4 nodos de la capa oculpa con 7 pesos + bias c/u
    double [] CapaOculta = new double[4];           // capa oculta de 4 neuronas
    double [][] PesosCapaSalida = new double[6][5] ; // 6 nodos de la capa de salida con 4 pesos + bias c/u
    double [] CapaSalida = new double[6];           //capa de salida de 6 neuronas
    String [] Plantas = new String[6];
    //endregion


    Double[] EntradaHU = new Double[7];
    String ArchivoEntrenamiento= Environment.getExternalStorageDirectory().getPath()+ "/ConjuntoEntrenamientoFINAL.arff";
    String Resultado = "";
    double probaFinal =0;

    public RNA (Double[] Entrada)

    {
        this.EntradaHU = Entrada ;
        PrediccionWeka();
       // LLenarPesos();
        //CalculoNeuronas();
        //ImprimirResultados();
    }

    //region Calculo manual RNA
    public void LLenarPesos ()
    {
            //region MATRIZ DE PESOS DE LA CAPA OCULTA. 4 nodos con 7 pesos + bias c/u
            //PESOS NODO 6
            PesosCapaOculta [0][7]=3.1375969831468855;    //BIAS
            PesosCapaOculta [0][0]=0.6577060690672721;    //peso momento1
            PesosCapaOculta [0][1]=-15.147700873013008;    //peso momento2
            PesosCapaOculta [0][2]=11.223301546408942;    //peso momento3
            PesosCapaOculta [0][3]=6.687166502176408;    //peso momento4
            PesosCapaOculta [0][4]=0.7593613514756565;    //peso momento5
            PesosCapaOculta [0][5]=2.524835574945995;    //peso momento6
            PesosCapaOculta [0][6]=-1.4717710773383688;    //peso momento7
            //PESOS NODO 7
            PesosCapaOculta [1][7]=-3.896274993818635;    //BIAS
            PesosCapaOculta [1][0]=-17.76027794648122;    //peso momento1
            PesosCapaOculta [1][1]=17.754719707150016;    //peso momento2
            PesosCapaOculta [1][2]=-0.7909072321783309;    //peso momento3
            PesosCapaOculta [1][3]=-5.805454573980339;    //peso momento4
            PesosCapaOculta [1][4]=-0.8058043849856391;    //peso momento5
            PesosCapaOculta [1][5]=-2.034315287353827;    //peso momento6
            PesosCapaOculta [1][6]=2.075454378031683;    //peso momento7
            //PESOS NODO 8
            PesosCapaOculta [2][7]=-2.718557769400005;    //BIAS
            PesosCapaOculta [2][0]=-2.241556012583629;    //peso momento1
            PesosCapaOculta [2][1]=8.356927867968883;    //peso momento2
            PesosCapaOculta [2][2]=-2.385804407441001;    //peso momento3
            PesosCapaOculta [2][3]=-1.0115077213252344;    //peso momento4
            PesosCapaOculta [2][4]=0.2899198389517331;    //peso momento5
            PesosCapaOculta [2][5]=2.592594509290893;    //peso momento6
            PesosCapaOculta [2][6]=1.5936168102184922;    //peso momento7
            //PESOS NODO 9
            PesosCapaOculta [3][7]=6.045352333294905;    //BIAS
            PesosCapaOculta [3][0]=5.529801530858435;    //peso momento1
            PesosCapaOculta [3][1]=-0.0833888434556068;    //peso momento2
            PesosCapaOculta [3][2]=-4.593864547308531;    //peso momento3
            PesosCapaOculta [3][3]=10.512075867790035;    //peso momento4
            PesosCapaOculta [3][4]=0.8444351492243972;    //peso momento5
            PesosCapaOculta [3][5]=11.018701593337003;    //peso momento6
            PesosCapaOculta [3][6]=-2.8606775042487866;    //peso momento7
            //endregion

            //region MATRIZ DE PESOS DE LA CAPA DE SALIDA. 6 nodos con 4 pesos + bias c/u
            //PESOS NODO 0
            PesosCapaSalida [0][4] =   -1.7938535792851382; //BIAS
            PesosCapaSalida [0][0] = -8.256893008740418; //peso nodo 6
            PesosCapaSalida [0][1] = -3.401282730307199; //peso nodo 7
            PesosCapaSalida [0][2] = -2.3958487194259663; //peso nodo 8
            PesosCapaSalida [0][3] = 3.607088220098716; //peso nodo 9
            //PESOS NODO 1
            PesosCapaSalida [1][4] =  -6.2646443786220125; //BIAS
            PesosCapaSalida [1][0] = -4.81663052083588; //peso nodo 6
            PesosCapaSalida [1][1] =  3.260250279816809; //peso nodo 7
            PesosCapaSalida [1][2] = 4.528417586938928; //peso nodo 8
            PesosCapaSalida [1][3] = 2.346411718177676; //peso nodo 9
            //PESOS NODO 2
            PesosCapaSalida [2][4] = -3.1895696368075606; //BIAS
            PesosCapaSalida [2][0] = -5.4988545171958725; //peso nodo 6
            PesosCapaSalida [2][1] = 4.236412020923799; //peso nodo 7
            PesosCapaSalida [2][2] = -4.204334606310604; //peso nodo 8
            PesosCapaSalida [2][3] = 2.088176584331885; //peso nodo 9
            //PESOS NODO 3
            PesosCapaSalida [3][4] = -4.691079864136918; //BIAS
            PesosCapaSalida [3][0] = 4.008592443703192; //peso nodo 6
            PesosCapaSalida [3][1] =  -10.851613228131653; //peso nodo 7
            PesosCapaSalida [3][2] = -7.253586917953027; //peso nodo 8
            PesosCapaSalida [3][3] = 4.667135937379946; //peso nodo 9
            //PESOS NODO 4
            PesosCapaSalida [4][4] = -6.490180365361504; //BIAS
            PesosCapaSalida [4][0] =  6.087031106290519; //peso nodo 6
            PesosCapaSalida [4][1] = 7.469292988339815; //peso nodo 7
            PesosCapaSalida [4][2] = -2.824513686829861; //peso nodo 8
            PesosCapaSalida [4][3] = -13.298346137411471; //peso nodo 9
            //PESOS NODO 5
            PesosCapaSalida [5][4] = 6.401209271293727; //BIAS
            PesosCapaSalida [5][0] = -5.024428472550255; //peso nodo 6
            PesosCapaSalida [5][1] = -6.698829688434393; //peso nodo 7
            PesosCapaSalida [5][2] = -6.00737843971762; //peso nodo 8
            PesosCapaSalida [5][3] = -7.070277442881396; //peso nodo 9
            //endregion

            //region Arreglo tipos plantas
            //Llenar tipos de planta
            Plantas[0]="Toronjil";
            Plantas[1]="HierbaBuena";
            Plantas[2]="Albahaca";
            Plantas[3]="Espinaca";
            Plantas[4]="Menta";
            Plantas[5]="Cilantro";
            //endregion
    }


    public void CalculoNeuronas ()
    {
        //Sumatoria de los valores de entrada Hu por los pesos de la capa oculta.
        for (int i=0; i<EntradaHU.length; i++)
        {
            for (int j=0; j<CapaOculta.length; j++)
            {
                CapaOculta[j]=EntradaHU[i]*PesosCapaOculta[j][i]+CapaOculta[j];
            }
        }
        //Sumar BIAS y Aplicar funcion sigmoide a la capa oculta
        for (int j=0; j<CapaOculta.length; j++)
        {
            CapaOculta[j]=CapaOculta[j]+PesosCapaOculta[j][7];
            CapaOculta[j]=1/(1+exp(-CapaOculta[j]));
        }
        //Sumatoria de las neuronas de la capa oculta (4) por los pesos de la capa de salida.
        for (int i=0; i<CapaOculta.length; i++)
        {
            for (int j=0; j<CapaSalida.length; j++)
            {
                CapaSalida[j]=CapaOculta[i]*PesosCapaSalida[j][i]+CapaSalida[j];
            }
        }
        //Sumar BIAS y Aplicar funcion sigmoide capa de salida
        for (int j=0; j<CapaSalida.length; j++)
        {
            CapaSalida[j]= CapaSalida[j]+PesosCapaSalida[j][4];
            CapaSalida[j]=1/(1+exp(-CapaSalida[j]));
        }
    }


    public void ImprimirResultados ()
    {
        Log.d("RESULTADO ", " \n" );;
        Log.d("RESULTADO ", " \n" );
        Log.d("RESULTADO ", " \n" );
        Double Mayor = 0.00;
        for (int j=0; j<CapaSalida.length; j++)
        {
            Log.d("Neurona de Salida ", Double.toString(CapaSalida[j])+" "+Plantas[j]+"\n" );
            //VALOR MAYOR
            if (CapaSalida[j]>Mayor)
            {
                Mayor=CapaSalida[j];
                Resultado = Plantas[j];
            }
        }
        Log.d("RESULTADO ", " \n" );
        Log.d("MAYOR ", Double.toString(Mayor)+" "+Resultado );
        //VALOR MAYOR
    }

    //endregion


    public void PrediccionWeka ()
    {
        try
        {
            FileReader trainreader = new FileReader(ArchivoEntrenamiento);
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);

            Instances test = HacerInstancia();
            test.setClassIndex(test.numAttributes() - 1);

            // MultilayerPerceptron mlp = new MultilayerPerceptron();
           /* mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 4"));
            mlp.buildClassifier(train);*/
            MultilayerPerceptron mlp = SingletonMLP.getInstance().mlp;

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(mlp, test);

            double clsLabel = mlp.classifyInstance(test.instance(0));
            test.instance(0).setClassValue(clsLabel);

            double[] probabilidad = new double[6];
            probabilidad=mlp.distributionForInstance(test.instance(0));

            Resultado =test.classAttribute().value((int) clsLabel);
            probaFinal = probabilidad[(int)clsLabel];

            Log.d("entrenaISAA", "prueba");
            Log.d("SALIDAA", clsLabel + "  " + Resultado );
            Log.d("PROBABILIDAD", String.valueOf(probabilidad[0]) +"  "+ String.valueOf(probabilidad[1]) +"  "+ String.valueOf(probabilidad[2]) +"  "+ String.valueOf(probabilidad[3]) +"  "+ String.valueOf(probabilidad[4]) +"  "+ String.valueOf(probabilidad[5]));
            trainreader.close();
            //testreader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Instances HacerInstancia ()
    {
        Attribute m1 = new Attribute("Momento1");
        Attribute m2 = new Attribute("Momento2");
        Attribute m3 = new Attribute("Momento3");
        Attribute m4 = new Attribute("Momento4");
        Attribute m5 = new Attribute("Momento5");
        Attribute m6 = new Attribute("Momento6");
        Attribute m7 = new Attribute("Momento7");

        FastVector fvClassVal = new FastVector(6);
        fvClassVal.addElement("Toronjil");
        fvClassVal.addElement("Yerbabuena");
        fvClassVal.addElement("Albahaca");
        fvClassVal.addElement("Espinaca");
        fvClassVal.addElement("Menta");
        fvClassVal.addElement("Cilantro");
        Attribute Class=new Attribute("especies", fvClassVal);

        FastVector fvWekaAttributes = new FastVector(8);
        fvWekaAttributes.addElement(m1);
        fvWekaAttributes.addElement(m2);
        fvWekaAttributes.addElement(m3);
        fvWekaAttributes.addElement(m4);
        fvWekaAttributes.addElement(m5);
        fvWekaAttributes.addElement(m6);
        fvWekaAttributes.addElement(m7);
        fvWekaAttributes.addElement(Class);

        Instances dataset = new Instances("planta",fvWekaAttributes, 0);

        double [] attValues = new double[dataset.numAttributes()];
        attValues[0]=EntradaHU[0];
        attValues[1]=EntradaHU[1];
        attValues[2]=EntradaHU[2];
        attValues[3]=EntradaHU[3];
        attValues[4]=EntradaHU[4];
        attValues[5]=EntradaHU[5];
        attValues[6]=EntradaHU[6];

        Instance i1 = new Instance(1.0, attValues);

        dataset.add(i1);

        return dataset;

    }



    public String Resultado ()
    {
        return Resultado;
    }
    public String Proba ()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        return Resultado+" "+df.format(probaFinal*100)+"%";
    }









}
