package com.example.mssqlserverconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView connectStatus,connectView;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectStatus = findViewById(R.id.connectStatus);
        btnConnect=findViewById(R.id.btnConnect);
        connectView=findViewById(R.id.connectView);


        btnConnect.setOnClickListener(v -> {
            try {
                CheckConnection();
                connectStatus.setText("Connection Successfull");
                Toast.makeText(this, "MS SQL Database connect successfully", Toast.LENGTH_SHORT).show();
            }catch(Error e) {
                connectStatus.setText("Connection Failed!!");
            }

        });

    }
    public void CheckConnection()
    {
        try {
            if(ConnectionClass.con==null)
            {
                new ConnectionClass().setConnection();
            }

            if(ConnectionClass.con!=null)
            {
                Statement stmt=ConnectionClass.con.createStatement();
                String sql="select *from Test_Table";
                ResultSet rs=stmt.executeQuery(sql);
                Log.e("ASK","-------------------");
                int i=0;
                while (rs.next()){
                    String data=rs.getString("C1");
                    Log.e("ASK", data);
                   connectView.setText(data);
                }

                Log.e("ASK","---------------------");
                Toast.makeText(this, "Query executed successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Connection to server failed!!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            Log.e("ASK",e.getMessage());

        }
    }
}

