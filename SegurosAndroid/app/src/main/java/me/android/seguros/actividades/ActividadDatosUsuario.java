package me.android.seguros.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.Usuario;

public class ActividadDatosUsuario extends AppCompatActivity {
    private String dniUsuario = null;
    private Usuario usuarioActual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_datos_usuario);
        final AppDatabase db = AppDatabaseWrapper.get();

        final EditText campoDni = findViewById(R.id.campo_dni_dash);
        final EditText campoNombre = findViewById(R.id.campo_nombre_dash);
        final EditText campoApellidos = findViewById(R.id.campo_apellidos_dash);
        final EditText campoDireccion = findViewById(R.id.campo_direccion_dash);
        final EditText campoTelefono = findViewById(R.id.campo_telefono_dash);
        final EditText campoContrasena = findViewById(R.id.campo_contrasena_dash);

        final Button botonGuardar = findViewById(R.id.boton_guardar_cambios_dash);
        final Button botonBorrar = findViewById(R.id.boton_volver_dash);

        dniUsuario = getIntent().getStringExtra("dni_usuario");
        campoDni.setText(dniUsuario);

        usuarioActual = db.usuarioDao().find(dniUsuario);

        if (usuarioActual != null) {
            campoNombre.setText(usuarioActual.getNombre());
            campoApellidos.setText(usuarioActual.getApellidos());
            campoDireccion.setText(usuarioActual.getDireccion());
            campoTelefono.setText(usuarioActual.getTelefono());
            campoContrasena.setText(usuarioActual.getContrasena());
        }

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean usuarioExistente = (usuarioActual != null);

                if (!usuarioExistente) {
                    usuarioActual = new Usuario(dniUsuario);
                }

                usuarioActual.setNombre(campoNombre.getText().toString());
                usuarioActual.setApellidos(campoApellidos.getText().toString());
                usuarioActual.setDireccion(campoDireccion.getText().toString());
                usuarioActual.setTelefono(campoTelefono.getText().toString());
                usuarioActual.setContrasena(campoContrasena.getText().toString());
                usuarioActual.setBorrado(false);

                if (usuarioExistente) {
                    db.usuarioDao().update(usuarioActual);
                } else {
                    db.usuarioDao().insertAll(usuarioActual);
                }

                // sale de la actividad
                ActividadDatosUsuario.this.finish();
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sale de la actividad
                ActividadDatosUsuario.this.finish();
            }
        });
    }
}
