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
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;
import me.android.seguros.datos.modelos.relaciones.UsuarioConTipo;

public class ActividadAdmin extends AppCompatActivity {
    private EditText crearUsuariosCampoDni;
    private Spinner gestionarUsuariosSpinner;
    private EditText crearTipoSeguroCampoNombre;
    private Spinner gestionarTiposSeguroSpinner;

    private ArrayAdapter<String> gestionarUsuariosSpinnerAdapter;
    private ArrayAdapter<String> gestionarTiposSeguroSpinnerAdapter;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_admin);
        db = AppDatabaseWrapper.get();

        crearUsuariosCampoDni = findViewById(R.id.editText2);
        gestionarUsuariosSpinner = findViewById(R.id.spinner3);
        crearTipoSeguroCampoNombre = findViewById(R.id.editText);
        gestionarTiposSeguroSpinner = findViewById(R.id.spinner);

        gestionarUsuariosSpinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        gestionarTiposSeguroSpinnerAdapter = new ArrayAdapter<>(
                this,
                R.layout.support_simple_spinner_dropdown_item
        );

        gestionarUsuariosSpinner.setAdapter(gestionarUsuariosSpinnerAdapter);
        gestionarTiposSeguroSpinner.setAdapter(gestionarTiposSeguroSpinnerAdapter);

        actualizarSpinners();

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!crearUsuariosCampoDni.getText().toString().trim().equals("")) {
                    Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                    intent.putExtra("dni_usuario", crearUsuariosCampoDni.getText().toString());
                    startActivity(intent);
                } else {
                    crearUsuariosCampoDni.setError("Este campo es necesario");
                }
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario != null) {
                    db.usuarioDao().delete(usuario);

                    // actualizar el spinner
                    gestionarUsuariosSpinnerAdapter.remove(dniUsuarioSeleccionado);

                    Toast.makeText(v.getContext(), "Se ha borrado el usuario seleccionado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "No se ha podido encontrar a ese usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario != null) {
                    usuario.setIdTipoUsuario(2);
                    db.usuarioDao().update(usuario);

                    Toast.makeText(v.getContext(), "Se ha puesto a " + usuario.getDni() + " como vendedor", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "No se ha podido encontrar a ese usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario != null) {
                    Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                    intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
                    startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "No se ha podido encontrar a ese usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();

                Intent intent = new Intent(v.getContext(), ActividadSeguroUsuario.class);
                intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
                startActivity(intent);
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

                    // actualiza el spinner
                    gestionarTiposSeguroSpinnerAdapter.add(tipoSeguro.getTipo());
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(v.getContext(), "Ya existe ese tipo de seguro", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // borrar tipo de seguro
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoSeguroSeleccionado = (String) gestionarTiposSeguroSpinner.getSelectedItem();
                TipoSeguro tipoSeguro = db.tipoSeguroDao().findByName(tipoSeguroSeleccionado);

                if (tipoSeguro != null) {
                    tipoSeguro.setBorrado(true);
                    db.tipoSeguroDao().update(tipoSeguro);

                    // actualiza el spinner
                    gestionarTiposSeguroSpinnerAdapter.remove(tipoSeguro.getTipo());
                } else {
                    Toast.makeText(v.getContext(), "No se ha podido encontrar ese tipo de seguro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        actualizarSpinners();
    }

    private void actualizarSpinners() {
        gestionarUsuariosSpinnerAdapter.clear();
        gestionarTiposSeguroSpinnerAdapter.clear();

        List<Usuario> usuariosExistentes = db.usuarioDao().getBorrados(false);
        List<TipoSeguro> tiposSeguroExistentes = db.tipoSeguroDao().getBorrados(false);

        for (Usuario usuario : usuariosExistentes) {
            // no listar los administradores
            if (usuario.getIdTipoUsuario() == 3)
                continue;

            gestionarUsuariosSpinnerAdapter.add(usuario.getDni());
        }

        for (TipoSeguro tipoSeguro : tiposSeguroExistentes) {
            gestionarTiposSeguroSpinnerAdapter.add(tipoSeguro.getTipo());
        }
    }
}
