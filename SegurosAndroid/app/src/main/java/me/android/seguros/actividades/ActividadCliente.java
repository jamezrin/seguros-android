package me.android.seguros.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.Usuario;
import me.android.seguros.datos.modelos.relaciones.SeguroConTipo;

import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_ADMIN;

public class ActividadCliente extends AppCompatActivity {
    private AppDatabase db = null;

    private Usuario usuarioActual = null;
    private ArrayAdapter<SeguroConTipo> segurosSpinnerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_cliente);
        db = AppDatabaseWrapper.get();

        final String dniUsuario = getIntent().getStringExtra("dni_usuario");
        usuarioActual = db.usuarioDao().find(dniUsuario);

        final Spinner segurosSpinner = findViewById(R.id.cliente_2);
        segurosSpinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        segurosSpinner.setAdapter(segurosSpinnerAdapter);

        actualizarSpinners();

        findViewById(R.id.cliente_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeguroConTipo seguroSeleccionado = (SeguroConTipo) segurosSpinner.getSelectedItem();

                if (seguroSeleccionado != null) {
                    Intent intent = new Intent(v.getContext(), ActividadSeguroUsuario.class);
                    intent.putExtra("dni_usuario", dniUsuario);
                    intent.putExtra("id_seguro", seguroSeleccionado.getSeguro().getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "No se ha podido encontrar a ese seguro",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        findViewById(R.id.cliente_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                intent.putExtra("dni_usuario", dniUsuario);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        actualizarSpinners();
    }

    private void actualizarSpinners() {
        segurosSpinnerAdapter.clear();

        List<SeguroConTipo> segurosContratados = db.seguroDao().getAllContratadoConTipo(usuarioActual.getDni());
        for (SeguroConTipo seguro : segurosContratados) {
            if (seguro.getSeguro().isBorrado())
                continue;

            segurosSpinnerAdapter.add(seguro);
        }
    }
}
