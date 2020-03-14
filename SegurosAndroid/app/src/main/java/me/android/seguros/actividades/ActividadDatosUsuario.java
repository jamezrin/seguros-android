package me.android.seguros.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.Usuario;

public class ActividadDatosUsuario extends AppCompatActivity {
    private Usuario usuarioActual = null;
    private AppDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_datos_usuario);
        db = AppDatabaseWrapper.get();

        final EditText campoDni = findViewById(R.id.datos_usuario_2);
        final EditText campoNombre = findViewById(R.id.datos_usuario_4);
        final EditText campoApellidos = findViewById(R.id.datos_usuario_6);
        final EditText campoDireccion = findViewById(R.id.datos_usuario_8);
        final EditText campoTelefono = findViewById(R.id.datos_usuario_10);
        final EditText campoContrasena = findViewById(R.id.datos_usuario_12);

        final String dniUsuario = getIntent().getStringExtra("dni_usuario");
        campoDni.setText(dniUsuario);

        usuarioActual = db.usuarioDao().find(dniUsuario);

        if (usuarioActual != null) {
            campoNombre.setText(usuarioActual.getNombre());
            campoApellidos.setText(usuarioActual.getApellidos());
            campoDireccion.setText(usuarioActual.getDireccion());
            campoTelefono.setText(usuarioActual.getTelefono());
            campoContrasena.setText(usuarioActual.getContrasena());
        }

        findViewById(R.id.datos_usuario_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean usuarioExistente = (usuarioActual != null);

                if (!usuarioExistente) {
                    assert dniUsuario != null;
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
                    Toast.makeText(
                            v.getContext(),
                            "Se ha actualizado el usuario correctamente",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    usuarioActual.setIdTipoUsuario(1);
                    db.usuarioDao().insertAll(usuarioActual);
                    Toast.makeText(
                            v.getContext(),
                            "Se ha creado el usuario correctamente",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                // sale de la actividad
                ActividadDatosUsuario.this.finish();
            }
        });

        findViewById(R.id.datos_usuario_14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sale de la actividad
                ActividadDatosUsuario.this.finish();
            }
        });
    }
}
