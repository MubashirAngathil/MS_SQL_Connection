package com.example.mssqlserverconnection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public static Connection con;
    public void setConnection()
    {
        try{
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ip="192.168.159.22";
            String ConnURL="jdbc:jtds:sqlserver://"+ip+";instance=DESKTOP-305V0Pl;user=test;password=test;databasename=TestDatabase;";
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(ConnURL);
            Log.e("ASK","Connection Called");
        }catch (Exception e){
            Log.e("ASK","EXCEPTION"+e.getMessage());
        }
    }
}
