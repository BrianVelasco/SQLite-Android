package com.brianvp.proyectosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button registrar,consultar, spinner,listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conexion= new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        registrar = (Button) findViewById(R.id.reg_usuario);
        consultar = (Button) findViewById(R.id.cons_usuario);
        spinner = (Button) findViewById(R.id.cons_spinner);
        listView = (Button) findViewById(R.id.cons_spinner);

    }

    public void registrar (View v){
        Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
        startActivity(intent);
    }
    public void buscar(View v){
        Intent intent = new Intent(MainActivity.this , BuscarActivity.class);
        startActivity(intent);
    }

    public void irSpinner(View v){
        Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
        startActivity(intent);
    }
    public void irLista(View v){
        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(intent);
    }
    public void rMascota(View v){
        Intent intent = new Intent(MainActivity.this, RMascotaActivity.class);
        startActivity(intent);
    }
    public void vMascota(View v){
        Intent intent = new Intent(MainActivity.this, VerMascotasActivity.class);
        startActivity(intent);
    }
}
