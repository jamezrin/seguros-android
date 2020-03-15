package me.android.seguros.actividades;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.relaciones.SeguroConTipo;

public class ActividadSeguroUsuario extends AppCompatActivity {
    private AppDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_seguro_usuario);
        db = AppDatabaseWrapper.get();

        final String dniUsuario = getIntent().getStringExtra("dni_usuario");
        final int idSeguro = getIntent().getIntExtra("id_seguro", -1);
        if (dniUsuario == null || idSeguro == -1) return;

        final SeguroConTipo seguroConTipo = db.seguroDao().findConTipo(idSeguro);

        final EditText campoTipo = findViewById(R.id.informacion_seguro_2);
        final EditText campoNumeroPoliza = findViewById(R.id.informacion_seguro_4);
        final EditText campoFechaAlta = findViewById(R.id.informacion_seguro_6);
        final EditText campoVendedor = findViewById(R.id.informacion_seguro_8);

        campoTipo.setText(seguroConTipo.getTipoSeguro().getTipo());
        campoNumeroPoliza.setText(seguroConTipo.getSeguro().getId() + "");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        campoFechaAlta.setText(seguroConTipo.getSeguro().getFechaAlta().format(dateTimeFormatter));

        campoVendedor.setText(seguroConTipo.getSeguro().getDniVendedor());

        findViewById(R.id.informacion_seguro_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguroConTipo.getSeguro().setBorrado(true);
                seguroConTipo.getSeguro().setFechaBaja(LocalDateTime.now());

                db.seguroDao().update(seguroConTipo.getSeguro());

                Toast.makeText(
                        v.getContext(),
                        "Seguro dado de baja correctamente",
                        Toast.LENGTH_SHORT
                ).show();

                ActividadSeguroUsuario.this.finish();
            }
        });

        findViewById(R.id.informacion_seguro_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActividadSeguroUsuario.this.finish();
            }
        });
    }
}
