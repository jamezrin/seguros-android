package me.android.seguros.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.List;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.Seguro;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.Usuario;

import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_VENDEDOR;

public class ActividadCrearSeguroUsuario extends AppCompatActivity {
    private Usuario usuarioActual = null;
    private AppDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear_seguro_usuario);
        db = AppDatabaseWrapper.get();

        final EditText campoCliente = findViewById(R.id.crear_seguro_2);
        final Spinner spinnerVendedor = findViewById(R.id.crear_seguro_4);
        final Spinner spinnerTipoSeguro = findViewById(R.id.crear_seguro_6);

        String dniUsuario = getIntent().getStringExtra("dni_usuario");
        campoCliente.setText(dniUsuario);

        usuarioActual = db.usuarioDao().find(dniUsuario);

        final ArrayAdapter<String> spinnerVendedorAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        spinnerVendedor.setAdapter(spinnerVendedorAdapter);

        List<Usuario> usuarios = db.usuarioDao().getBorrados(false);
        for (Usuario usuario : usuarios) {
            if (usuario.getIdTipoUsuario() != ID_USUARIO_VENDEDOR)
                continue;

            spinnerVendedorAdapter.add(usuario.getDni());
        }

        final ArrayAdapter<String> spinnerTipoSeguroAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        spinnerTipoSeguro.setAdapter(spinnerTipoSeguroAdapter);

        List<TipoSeguro> tiposSeguros = db.tipoSeguroDao().getBorrados(false);
        for (TipoSeguro tipoSeguro : tiposSeguros) {
            spinnerTipoSeguroAdapter.add(tipoSeguro.getTipo());
        }

        findViewById(R.id.crear_seguro_7).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String dniVendedorSeleccionado = (String) spinnerVendedor.getSelectedItem();
                if (dniVendedorSeleccionado == null) {
                    Toast.makeText(
                            v.getContext(),
                            "Es necesario especificar un vendedor",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                String tipoSeguroSeleccionado = (String) spinnerTipoSeguro.getSelectedItem();
                if (tipoSeguroSeleccionado == null) {
                    Toast.makeText(
                            v.getContext(),
                            "Es necesario especificar un tipo de seguro",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                Usuario vendedor = db.usuarioDao().find(dniVendedorSeleccionado);
                TipoSeguro tipoSeguro = db.tipoSeguroDao().findByName(tipoSeguroSeleccionado);

                Seguro seguro = new Seguro();
                seguro.setDniCliente(usuarioActual.getDni());
                seguro.setDniVendedor(vendedor.getDni());
                seguro.setFechaAlta(LocalDateTime.now());
                seguro.setIdTipoSeguro(tipoSeguro.getId());
                seguro.setBorrado(false);

                db.seguroDao().insertAll(seguro);

                Toast.makeText(
                        v.getContext(),
                        "Un nuevo seguro ha sido dado de alta correctamente",
                        Toast.LENGTH_SHORT
                ).show();

                ActividadCrearSeguroUsuario.this.finish();
            }
        });

        findViewById(R.id.crear_seguro_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActividadCrearSeguroUsuario.this.finish();
            }
        });
    }
}
