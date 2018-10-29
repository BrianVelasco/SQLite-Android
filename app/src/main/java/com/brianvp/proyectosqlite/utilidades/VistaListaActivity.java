package com.brianvp.proyectosqlite.utilidades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.brianvp.proyectosqlite.R;
import com.brianvp.proyectosqlite.entidades.Usuario;

public class VistaListaActivity extends AppCompatActivity {

    TextView id,nombre,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_lista);

        id  = (TextView) findViewById(R.id.id);
        nombre  = (TextView) findViewById(R.id.nombre);
        tel  = (TextView) findViewById(R.id.telefono);

        Bundle objeto = getIntent().getExtras();
        Usuario user = null;

        if(objeto != null){
            user = (Usuario) objeto.getSerializable("usuario");
            id.setText(user.getId()+"");
            nombre.setText(user.getNombre().toString());
            tel.setText(user.getTelefono().toString());

        }
    }
}
