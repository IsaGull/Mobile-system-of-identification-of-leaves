package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by UnaAhi on 26/06/2016.
 */
public class SingletonMAIN extends Activity
{


    private static SingletonMAIN ourInstance = null;
    Intent i10 = null;


    public static SingletonMAIN getInstance()
    {
        if (ourInstance==null)
        {
            ourInstance  = new SingletonMAIN();
        }
        return ourInstance;
    }

    private SingletonMAIN() {



    }


}
