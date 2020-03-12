package me.android.seguros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.android.seguros.actividades.ActividadAdmin;
import me.android.seguros.actividades.ActividadLogin;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    public static final String NOMBRE_SQLITE_DB = "seguros-app";

    private void rellenarEntidadesDefecto(AppDatabase db) {
        if (db.tipoSeguroDao().count() == 0) {
            db.tipoSeguroDao().insertAll(
                    new TipoSeguro(1, "Vida", false),
                    new TipoSeguro(2, "Vivienda", false),
                    new TipoSeguro(3, "Coche", false)
            );
        }

        if (db.tipoUsuarioDao().count() == 0) {
            db.tipoUsuarioDao().insertAll(
                    new TipoUsuario(1, "Cliente", false),
                    new TipoUsuario(2, "Vendedor", false),
                    new TipoUsuario(3, "Admin", false)
            );
        }

        if (db.usuarioDao().count() == 0) {
            db.usuarioDao().insertAll(
                    new Usuario("00000000A", "Pepe", "Martinez", "Calle X", "600000000", "pass1", 3, false),
                    new Usuario("00000000B", "Pepita", "Martin", "Calle Y", "600000001", "pass2", 2, false),
                    new Usuario("00000000C", "Pepón", "Marín", "Calle Z", "600000002", "pass3", 1, false)
            );
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, NOMBRE_SQLITE_DB)
                .allowMainThreadQueries()
                .build();

        rellenarEntidadesDefecto(db);

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
