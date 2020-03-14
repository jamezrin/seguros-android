package me.android.seguros.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.Usuario;

import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_ADMIN;
import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_CLIENTE;

public class ActividadVendedor extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_vendedor);
        db = AppDatabaseWrapper.get();

        final String dniUsuario = getIntent().getStringExtra("dni_usuario");

        final EditText crearUsuariosCampoDni = findViewById(R.id.vendedor_2);
        final Spinner spinnerUsuarios = findViewById(R.id.vendedor_5);

        final ArrayAdapter<String> spinnerUsuariosAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        spinnerUsuarios.setAdapter(spinnerUsuariosAdapter);

        List<Usuario> usuariosExistentes = db.usuarioDao().getBorrados(false);

        for (Usuario usuario : usuariosExistentes) {
            // solo listar a clientes
            if (usuario.getIdTipoUsuario() != ID_USUARIO_CLIENTE)
                continue;

            spinnerUsuariosAdapter.add(usuario.getDni());
        }

        findViewById(R.id.vendedor_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!crearUsuariosCampoDni.getText().toString().trim().equals("")) {
                    Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                    intent.putExtra("dni_usuario", crearUsuariosCampoDni.getText().toString());
                    startActivity(intent);
                } else {
                    crearUsuariosCampoDni.setError("Este campo es necesario");
                }
            }
        });

        findViewById(R.id.vendedor_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) spinnerUsuarios.getSelectedItem();

                Intent intent = new Intent(v.getContext(), ActividadCrearSeguroUsuario.class);
                intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
                startActivity(intent);
            }
        });

        findViewById(R.id.vendedor_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) spinnerUsuarios.getSelectedItem();

                Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
                startActivity(intent);
            }
        });

        findViewById(R.id.vendedor_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                intent.putExtra("dni_usuario", dniUsuario);
                startActivity(intent);
            }
        });
    }
}
