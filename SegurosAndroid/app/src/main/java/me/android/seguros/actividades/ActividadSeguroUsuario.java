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
    private String dniUsuario = null;
    private int idSeguro = -1;
    private SeguroConTipo seguroConTipo = null;

    private EditText campoTipo = null;
    private EditText campoNumeroPoliza = null;
    private EditText campoFechaAlta = null;
    private EditText campoVendedor = null;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_seguro_usuario);
        db = AppDatabaseWrapper.get();

        dniUsuario = getIntent().getStringExtra("dni_usuario");
        idSeguro = getIntent().getIntExtra("id_seguro", -1);
        if (dniUsuario == null || idSeguro == -1) return;

        seguroConTipo = db.seguroDao().findConTipo(idSeguro);

        campoTipo = findViewById(R.id.informacion_seguro_2);
        campoNumeroPoliza = findViewById(R.id.informacion_seguro_4);
        campoFechaAlta = findViewById(R.id.informacion_seguro_6);
        campoVendedor = findViewById(R.id.informacion_seguro_8);

        campoTipo.setText(seguroConTipo.getTipoSeguro().getTipo());
        campoNumeroPoliza.setText(seguroConTipo.getSeguro().getId() + "");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        campoFechaAlta.setText(seguroConTipo.getSeguro().getFechaAlta().format(dateTimeFormatter));

        campoVendedor.setText(seguroConTipo.getSeguro().getDniVendedor());

        findViewById(R.id.informacion_seguro_9).setOnClickListener(new BotonDarDeBajaSeguroListener());

        findViewById(R.id.informacion_seguro_10).setOnClickListener(new BotonVolverAtrasListener());
    }

    private class BotonDarDeBajaSeguroListener implements View.OnClickListener {
        @RequiresApi(api = Build.VERSION_CODES.O)
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
    }

    private class BotonVolverAtrasListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ActividadSeguroUsuario.this.finish();
        }
    }
}
