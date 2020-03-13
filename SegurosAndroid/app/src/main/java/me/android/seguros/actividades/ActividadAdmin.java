package me.android.seguros.actividades;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import me.android.seguros.R;
import me.android.seguros.datos.AppDatabase;
import me.android.seguros.datos.AppDatabaseWrapper;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.Usuario;

public class ActividadAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_admin);

        final AppDatabase db = AppDatabaseWrapper.get();

        final EditText crearUsuariosCampoDni = findViewById(R.id.editText2);
        final Spinner gestionarUsuariosSpinner = findViewById(R.id.spinner3);
        final EditText crearTipoSeguroCampoNombre = findViewById(R.id.editText);
        final Spinner gestionarTiposSeguroSpinner = findViewById(R.id.spinner);

        final ArrayAdapter<String> gestionarUsuariosSpinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        final ArrayAdapter<String> gestionarTiposSeguroSpinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        List<Usuario> usuariosExistentes = db.usuarioDao().getBorrados(false);
        List<TipoSeguro> tiposSeguroExistentes = db.tipoSeguroDao().getBorrados(false);

        for (Usuario usuario : usuariosExistentes) {
            gestionarUsuariosSpinnerAdapter.add(usuario.getDni());
        }

        for (TipoSeguro tipoSeguro : tiposSeguroExistentes) {
            gestionarTiposSeguroSpinnerAdapter.add(tipoSeguro.getTipo());
        }

        gestionarUsuariosSpinner.setAdapter(gestionarUsuariosSpinnerAdapter);
        gestionarTiposSeguroSpinner.setAdapter(gestionarTiposSeguroSpinnerAdapter);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                intent.putExtra(
                        "dni_usuario",
                        crearUsuariosCampoDni.getText().toString()
                );
                startActivity(intent);
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // añadir tipo de seguro
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipoSeguro tipoSeguro = new TipoSeguro();
                tipoSeguro.setTipo(crearTipoSeguroCampoNombre.getText().toString());
                tipoSeguro.setBorrado(false);

                try {
                    db.tipoSeguroDao().insertAll(tipoSeguro);
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(v.getContext(), "Ya existe ese tipo de seguro", Toast.LENGTH_SHORT).show();
                }

                // actualiza el spinner
                gestionarTiposSeguroSpinnerAdapter.add(tipoSeguro.getTipo());
            }
        });

        // borrar tipo de seguro
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoSeguroSeleccionado = (String) gestionarTiposSeguroSpinner.getSelectedItem();
                TipoSeguro tipoSeguro = db.tipoSeguroDao().findByName(tipoSeguroSeleccionado);

                // por si las moscas
                if (tipoSeguro == null) return;

                tipoSeguro.setBorrado(true);
                db.tipoSeguroDao().update(tipoSeguro);

                // actualiza el spinner
                gestionarTiposSeguroSpinnerAdapter.remove(tipoSeguro.getTipo());
            }
        });
    }
}
