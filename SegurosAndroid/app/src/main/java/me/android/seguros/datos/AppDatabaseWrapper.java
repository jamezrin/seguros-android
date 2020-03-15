package me.android.seguros.datos;

import android.content.Context;

import androidx.room.Room;

import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_ADMIN;
import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_CLIENTE;
import static me.android.seguros.datos.modelos.Usuario.ID_USUARIO_VENDEDOR;

public class AppDatabaseWrapper {
    private static AppDatabase db;

    public static void init(Context context, String dbName) {
        db = Room.databaseBuilder(context,
                AppDatabase.class, dbName)
                .allowMainThreadQueries()
                .build();

        if (db.tipoSeguroDao().count() == 0) {
            db.tipoSeguroDao().insertAll(
                    new TipoSeguro(1, "Vida", false),
                    new TipoSeguro(2, "Vivienda", false),
                    new TipoSeguro(3, "Coche", false)
            );
        }

        if (db.tipoUsuarioDao().count() == 0) {
            db.tipoUsuarioDao().insertAll(
                    new TipoUsuario(ID_USUARIO_CLIENTE, "Cliente", false),
                    new TipoUsuario(ID_USUARIO_VENDEDOR, "Vendedor", false),
                    new TipoUsuario(ID_USUARIO_ADMIN, "Admin", false)
            );
        }

        if (db.usuarioDao().count() == 0) {
            db.usuarioDao().insertAll(
                    new Usuario("00000000A", "Pepe", "Martinez", "Calle X", "600000000", "pass1", ID_USUARIO_ADMIN, false),
                    new Usuario("00000000B", "Pepita", "Martin", "Calle Y", "600000001", "pass2", ID_USUARIO_VENDEDOR, false),
                    new Usuario("00000000C", "Pepón", "Marín", "Calle Z", "600000002", "pass3", ID_USUARIO_CLIENTE, false)
            );
        }
    }

    public static AppDatabase get() {
        if (db == null) {
            throw new RuntimeException("No se ha inicializado la base de datos. Hay que a llamar init()");
        }

        return db;
    }
}
