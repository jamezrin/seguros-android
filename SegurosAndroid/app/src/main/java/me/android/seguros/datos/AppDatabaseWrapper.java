package me.android.seguros.datos;

import android.content.Context;

import androidx.room.Room;

import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

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

    public static AppDatabase get() {
        if (db == null) {
            throw new RuntimeException("No se ha inicializado la base de datos. Hay que a llamar init()");
        }

        return db;
    }
}
