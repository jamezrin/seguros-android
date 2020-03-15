package me.android.seguros.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private AppDatabase db = null;

    private ArrayAdapter<String> spinnerUsuariosAdapter = null;

    private String dniUsuario = null;
    private EditText crearUsuariosCampoDni = null;
    private Spinner spinnerUsuarios = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_vendedor);
        db = AppDatabaseWrapper.get();

        dniUsuario = getIntent().getStringExtra("dni_usuario");
        crearUsuariosCampoDni = findViewById(R.id.vendedor_2);
        spinnerUsuarios = findViewById(R.id.vendedor_5);

        spinnerUsuariosAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        spinnerUsuarios.setAdapter(spinnerUsuariosAdapter);

        actualizarSpinners();

        findViewById(R.id.vendedor_3).setOnClickListener(new BotonCrearUsuarioListener());

        findViewById(R.id.vendedor_6).setOnClickListener(new BotonCrearSeguroListener());

        findViewById(R.id.vendedor_7).setOnClickListener(new BotonVerUsuarioListener());

        findViewById(R.id.vendedor_8).setOnClickListener(new BotonVerDatosListener());
    }

    @Override
    protected void onResume() {
        super.onResume();

        actualizarSpinners();
    }

    private void actualizarSpinners() {
        spinnerUsuariosAdapter.clear();

        List<Usuario> usuariosExistentes = db.usuarioDao().getBorrados(false);

        for (Usuario usuario : usuariosExistentes) {
            // solo listar a clientes
            if (usuario.getIdTipoUsuario() != ID_USUARIO_CLIENTE)
                continue;

            spinnerUsuariosAdapter.add(usuario.getDni());
        }
    }

    private class BotonVerDatosListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
            intent.putExtra("dni_usuario", dniUsuario);
            intent.putExtra("accion_crear", false);
            startActivity(intent);
        }
    }

    private class BotonVerUsuarioListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String dniUsuarioSeleccionado = (String) spinnerUsuarios.getSelectedItem();

            Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
            intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
            intent.putExtra("accion_crear", false);
            startActivity(intent);
        }
    }

    private class BotonCrearSeguroListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String dniUsuarioSeleccionado = (String) spinnerUsuarios.getSelectedItem();

            Intent intent = new Intent(v.getContext(), ActividadCrearSeguroUsuario.class);
            intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
            startActivity(intent);
        }
    }

    private class BotonCrearUsuarioListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!crearUsuariosCampoDni.getText().toString().trim().equals("")) {
                Usuario usuario = db.usuarioDao().find(crearUsuariosCampoDni.getText().toString());

                if (usuario == null) {
                    Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                    intent.putExtra("dni_usuario", crearUsuariosCampoDni.getText().toString());
                    intent.putExtra("accion_crear", true);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "Ya existe un usuario con ese DNI",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            } else {
                crearUsuariosCampoDni.setError("Este campo es necesario");
            }
        }
    }
}
