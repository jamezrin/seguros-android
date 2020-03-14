package me.android.seguros.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.relaciones.UsuarioConTodo;

public class ActividadLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);

        AppDatabaseWrapper.init(this, "seguros-app");

        final AppDatabase db = AppDatabaseWrapper.get();
        final TextView campoDni = findViewById(R.id.iniciar_sesion_2);
        final TextView campoContrasena = findViewById(R.id.iniciar_sesion_4);

        findViewById(R.id.iniciar_sesion_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioConTodo usuario = db.usuarioDao().findConTodo(
                        campoDni.getText().toString(),
                        campoContrasena.getText().toString()
                );

                if (usuario != null) {
                    Toast.makeText(v.getContext(), "Has iniciado sesión como " + usuario.getUsuario().getNombre() + " " + usuario.getUsuario().getApellidos(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(v.getContext(), "Tipo de usuario: " + usuario.getTipoUsuario().getTipo(), Toast.LENGTH_SHORT).show();

                    Class<? extends AppCompatActivity> claseActividad = mapearTipoUsuarioActividad(
                            usuario.getTipoUsuario());
                    if (claseActividad != null) {
                        Intent intent = new Intent(v.getContext(), claseActividad);
                        intent.putExtra("dni_usuario", usuario.getUsuario().getDni());
                        startActivity(intent);
                    } else {
                        Toast.makeText(v.getContext(), "No entendemos tu tipo de usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Class<? extends AppCompatActivity> mapearTipoUsuarioActividad(TipoUsuario tipoUsuario) {
        switch (tipoUsuario.getId()) {
            case 1:
                return ActividadCliente.class;
            case 2:
                return ActividadVendedor.class;
            case 3:
                return ActividadAdmin.class;
            default:
                return null;
        }
    }
}
