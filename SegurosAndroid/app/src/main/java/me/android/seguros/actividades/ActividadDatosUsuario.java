package me.android.seguros.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_CLIENTE;

public class ActividadDatosUsuario extends AppCompatActivity {
    private Usuario usuarioActual = null;
    private AppDatabase db = null;
    private EditText campoDni = null;
    private EditText campoNombre = null;
    private EditText campoApellidos = null;
    private EditText campoDireccion = null;
    private EditText campoTelefono = null;
    private EditText campoContrasena = null;
    private EditText campoTipoUsuario = null;
    private String dniUsuario = null;
    private boolean accionCrear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_datos_usuario);
        db = AppDatabaseWrapper.get();

        campoDni = findViewById(R.id.datos_usuario_2);
        campoNombre = findViewById(R.id.datos_usuario_4);
        campoApellidos = findViewById(R.id.datos_usuario_6);
        campoDireccion = findViewById(R.id.datos_usuario_8);
        campoTelefono = findViewById(R.id.datos_usuario_10);
        campoContrasena = findViewById(R.id.datos_usuario_12);
        campoTipoUsuario = findViewById(R.id.datos_usuario_14);

        dniUsuario = getIntent().getStringExtra("dni_usuario");

        campoDni.setText(dniUsuario);

        accionCrear = getIntent().getBooleanExtra("accion_crear", false);

        if (!accionCrear) {
            usuarioActual = db.usuarioDao().find(dniUsuario);
            campoNombre.setText(usuarioActual.getNombre());
            campoApellidos.setText(usuarioActual.getApellidos());
            campoDireccion.setText(usuarioActual.getDireccion());
            campoTelefono.setText(usuarioActual.getTelefono());
            campoContrasena.setText(usuarioActual.getContrasena());
        } else {
            assert dniUsuario != null;
            usuarioActual = new Usuario(dniUsuario);
            usuarioActual.setIdTipoUsuario(ID_USUARIO_CLIENTE);
        }

        TipoUsuario tipoUsuario = db.tipoUsuarioDao().findById(usuarioActual.getIdTipoUsuario());
        campoTipoUsuario.setText(tipoUsuario.getTipo());

        findViewById(R.id.datos_usuario_15).setOnClickListener(new BotonGuardarCambiosListener());

        findViewById(R.id.datos_usuario_16).setOnClickListener(new BotonVolverAtrasListener());
    }

    private class BotonGuardarCambiosListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            usuarioActual.setNombre(campoNombre.getText().toString());
            usuarioActual.setApellidos(campoApellidos.getText().toString());
            usuarioActual.setDireccion(campoDireccion.getText().toString());
            usuarioActual.setTelefono(campoTelefono.getText().toString());
            usuarioActual.setContrasena(campoContrasena.getText().toString());
            usuarioActual.setBorrado(false);

            if (accionCrear) {
                db.usuarioDao().insertAll(usuarioActual);
                Toast.makeText(
                        v.getContext(),
                        "Se ha creado el usuario correctamente",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                db.usuarioDao().update(usuarioActual);
                Toast.makeText(
                        v.getContext(),
                        "Se ha actualizado el usuario correctamente",
                        Toast.LENGTH_SHORT
                ).show();
            }

            // sale de la actividad
            ActividadDatosUsuario.this.finish();
        }
    }

    private class BotonVolverAtrasListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // sale de la actividad
            ActividadDatosUsuario.this.finish();
        }
    }
}
