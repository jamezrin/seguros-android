package me.android.seguros.actividades;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_ADMIN;
import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_VENDEDOR;

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

        crearUsuariosCampoDni = findViewById(R.id.administracion_2);
        gestionarUsuariosSpinner = findViewById(R.id.administracion_5);
        crearTipoSeguroCampoNombre = findViewById(R.id.administracion_11);
        gestionarTiposSeguroSpinner = findViewById(R.id.administracion_14);

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

        final Button botonBorrarUsuario = findViewById(R.id.administracion_6);
        final Button botonHacerVendedor = findViewById(R.id.administracion_7);
        final Button botonVerUsuario = findViewById(R.id.administracion_8);
        final Button botonCrearSeguro = findViewById(R.id.administracion_9);

        gestionarUsuariosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dniUsuarioSeleccionado = (String) parent.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario == null)
                    return;

                if (usuario.getIdTipoUsuario() == ID_USUARIO_VENDEDOR) {
                    botonCrearSeguro.setEnabled(false);
                    botonHacerVendedor.setEnabled(false);
                } else {
                    botonCrearSeguro.setEnabled(true);
                    botonHacerVendedor.setEnabled(true);
                }

                botonBorrarUsuario.setEnabled(true);
                botonVerUsuario.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                botonCrearSeguro.setEnabled(false);
                botonHacerVendedor.setEnabled(false);
                botonVerUsuario.setEnabled(false);
                botonBorrarUsuario.setEnabled(false);
            }
        });

        actualizarSpinners();

        findViewById(R.id.administracion_3).setOnClickListener(new View.OnClickListener() {
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

        findViewById(R.id.administracion_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario != null) {
                    usuario.setBorrado(true);

                    db.usuarioDao().update(usuario);

                    // actualizar el spinner
                    gestionarUsuariosSpinnerAdapter.remove(dniUsuarioSeleccionado);

                    Toast.makeText(
                            v.getContext(),
                            "Se ha borrado el usuario seleccionado correctamente",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "No se ha podido encontrar a ese usuario",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        findViewById(R.id.administracion_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario != null) {
                    usuario.setIdTipoUsuario(ID_USUARIO_VENDEDOR);

                    db.usuarioDao().update(usuario);

                    botonCrearSeguro.setEnabled(false);
                    botonHacerVendedor.setEnabled(false);

                    Toast.makeText(
                            v.getContext(),
                            "Se ha puesto a " + usuario.getDni() + " como vendedor",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "No se ha podido encontrar a ese usuario",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        findViewById(R.id.administracion_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();
                Usuario usuario = db.usuarioDao().find(dniUsuarioSeleccionado);

                if (usuario != null) {
                    Intent intent = new Intent(v.getContext(), ActividadDatosUsuario.class);
                    intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "No se ha podido encontrar a ese usuario",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        findViewById(R.id.administracion_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dniUsuarioSeleccionado = (String) gestionarUsuariosSpinner.getSelectedItem();

                Intent intent = new Intent(v.getContext(), ActividadCrearSeguroUsuario.class);
                intent.putExtra("dni_usuario", dniUsuarioSeleccionado);
                startActivity(intent);
            }
        });

        findViewById(R.id.administracion_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!crearTipoSeguroCampoNombre.getText().toString().trim().equals("")) {
                    TipoSeguro tipoSeguro = new TipoSeguro();
                    tipoSeguro.setTipo(crearTipoSeguroCampoNombre.getText().toString());
                    tipoSeguro.setBorrado(false);

                    try {
                        db.tipoSeguroDao().insertAll(tipoSeguro);

                        // actualiza el spinner
                        gestionarTiposSeguroSpinnerAdapter.add(tipoSeguro.getTipo());

                        Toast.makeText(
                                v.getContext(),
                                "Tipo de seguro creado correctamente",
                                Toast.LENGTH_SHORT
                        ).show();
                    } catch (SQLiteConstraintException e) {
                        Toast.makeText(
                                v.getContext(),
                                "Ya existe ese tipo de seguro",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                } else {
                    crearTipoSeguroCampoNombre.setError("Este campo es necesario");
                }
            }
        });

        findViewById(R.id.administracion_15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoSeguroSeleccionado = (String) gestionarTiposSeguroSpinner.getSelectedItem();
                TipoSeguro tipoSeguro = db.tipoSeguroDao().findByName(tipoSeguroSeleccionado);

                if (tipoSeguro != null) {
                    tipoSeguro.setBorrado(true);
                    db.tipoSeguroDao().update(tipoSeguro);

                    // actualiza el spinner
                    gestionarTiposSeguroSpinnerAdapter.remove(tipoSeguro.getTipo());

                    Toast.makeText(
                            v.getContext(),
                            "Tipo de seguro eliminado correctamente",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "No se ha podido encontrar ese tipo de seguro",
                            Toast.LENGTH_SHORT
                    ).show();
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
            if (usuario.getIdTipoUsuario() == ID_USUARIO_ADMIN)
                continue;

            gestionarUsuariosSpinnerAdapter.add(usuario.getDni());
        }

        for (TipoSeguro tipoSeguro : tiposSeguroExistentes) {
            gestionarTiposSeguroSpinnerAdapter.add(tipoSeguro.getTipo());
        }
    }
}
