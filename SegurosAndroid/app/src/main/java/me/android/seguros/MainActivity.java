package me.android.seguros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import me.android.seguros.actividades.ActividadAdmin;
import me.android.seguros.actividades.ActividadLogin;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabaseWrapper.init(this, "seguros-app");

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActividadAdmin.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActividadLogin.class);
                startActivity(intent);
            }
        });
    }
}
