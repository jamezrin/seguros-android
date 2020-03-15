package me.android.seguros.datos;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import me.android.seguros.datos.converters.LocalDateTimeConverter;
import me.android.seguros.datos.daos.SeguroDao;
import me.android.seguros.datos.daos.TipoSeguroDao;
import me.android.seguros.datos.daos.TipoUsuarioDao;
import me.android.seguros.datos.daos.UsuarioDao;
import me.android.seguros.datos.modelos.Seguro;
import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

@Database(entities = {TipoUsuario.class, TipoSeguro.class, Seguro.class, Usuario.class}, version = 1)
@TypeConverters({LocalDateTimeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TipoUsuarioDao tipoUsuarioDao();
    public abstract TipoSeguroDao tipoSeguroDao();
    public abstract UsuarioDao usuarioDao();
    public abstract SeguroDao seguroDao();
}
