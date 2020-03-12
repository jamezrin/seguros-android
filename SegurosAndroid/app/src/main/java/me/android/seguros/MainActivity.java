package me.android.seguros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.android.seguros.actividades.ActividadAdmin;
import me.android.seguros.actividades.ActividadLogin;
import me.android.seguros.datos.AppDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String NOMBRE_SQLITE_DB = "seguros-app";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, NOMBRE_SQLITE_DB).build();

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
