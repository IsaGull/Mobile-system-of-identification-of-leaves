package com.example.pc.quehojaes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pc on 01/04/2016.
 */
public class DbHandler extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plantas.db";
    private static final String TABLE_PLANTA = "planta";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRECIENTIFICO = "nombrec";
    public static final String COLUMN_FAMILIA = "familia";
    public static final String COLUMN_USOS = "usos";
    public static final String COLUMN_CLIMA = "clima";
    public static final String COLUMN_TIPODENERVADURA = "tipon";
    public static final String COLUMN_FORMA = "forma";
    public static final String COLUMN_BORDE = "borde";
    public static final String COLUMN_DESCRIPCIONGENERAL = "descripciong";







    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_PLANTA + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRECIENTIFICO + " TEXT, " +
                COLUMN_FAMILIA + " TEXT, " +
                COLUMN_USOS + " TEXT, " +
                COLUMN_CLIMA + " TEXT, " +
                COLUMN_TIPODENERVADURA + " TEXT, " +
                COLUMN_FORMA + " TEXT, " +
                COLUMN_BORDE + " TEXT, " +
                COLUMN_DESCRIPCIONGENERAL + " TEXT " +
                ");";

        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void agregarPlanta(Planta planta)

    {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRECIENTIFICO, planta.getNombreCientifico());
        values.put(COLUMN_FAMILIA, planta.getFamilia());
        values.put(COLUMN_USOS, planta.getUsos());
        values.put(COLUMN_CLIMA, planta.getClima());
        values.put(COLUMN_TIPODENERVADURA, planta.getTipoNervadura());
        values.put(COLUMN_FORMA, planta.getForma());
        values.put(COLUMN_BORDE, planta.getBorde());
        values.put(COLUMN_DESCRIPCIONGENERAL, planta.getDescripcionGeneral());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PLANTA, null, values);
        db.close();

    }

    public Planta imprimirPlanta(String nombre)

    {
        String query = "SELECT * FROM " + TABLE_PLANTA + " WHERE nombrec='"+nombre+"'";
        SQLiteDatabase db = getWritableDatabase();
        Planta miPlanta = new Planta();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if (c.moveToFirst())
        {

            miPlanta.setNombreCientifico(c.getString(1));
            miPlanta.setFamilia(c.getString(2));
            miPlanta.setUsos(c.getString(3));
            miPlanta.setClima(c.getString(4));
            miPlanta.setTipoNervadura(c.getString(5));
            miPlanta.setForma(c.getString(6));
            miPlanta.setBorde(c.getString(7));
            miPlanta.setDescripcionGeneral(c.getString(8));
            c.close();
            db.close();
            return miPlanta;
        }

        c.close();
        db.close();
        return miPlanta=null;

    }


}
