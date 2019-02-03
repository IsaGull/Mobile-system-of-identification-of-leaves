package com.example.pc.quehojaes;


/**
 * Created by Pc on 25/01/2016.
 */

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import net.sourceforge.jtds.jdbc.*;


public class Conexion


{
    String dato;
    public String query2() {

        Log.i("Android", " MySQL Connect Example.");
        Connection conn = null;
        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";

            Class.forName(driver).newInstance();
            //test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;


            // String connString = "jdbc:jtds:sqlserver://192.168.2.104:1433/quehojaes;encrypt=false;user=Pc;password=;instance=SQLEXPRESS;";
           //  String connString = "Data Source=localhost:1433;Initial Catalog=quehojaes;Integrated Security=True";
          //   String connString = "jdbc:jtds:sqlserver://192.168.2.104:1433/quehojaes;";

            String connString = "jdbc:sqlserver://localhost;integratedSecurity=true";

            String username = "Pc";
            String password = "";
            conn = DriverManager.getConnection(connString);
            Log.w("Connection", "open");
            Statement stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("select * from planta where id=1");

            //Print the data to the console
            while (reset.next()) {
                 dato = reset.getString(3);
                Log.w("Data:", reset.getString(3));
//              Log.w("Data",reset.getString(2));

            }
            conn.close();

        } catch (Exception e) {
            Log.w("Error connection", "" + e.getMessage());
        }


        return dato;
    }


}
