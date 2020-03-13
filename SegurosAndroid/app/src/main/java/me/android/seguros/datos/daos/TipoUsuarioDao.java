package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import me.android.seguros.datos.modelos.TipoUsuario;

@Dao
public interface TipoUsuarioDao {
    @Query("SELECT * FROM tipos_usuario")
    List<TipoUsuario> getAll();

    @Query("SELECT COUNT(*) FROM tipos_usuario")
    int count();

    @Insert
    void insertAll(TipoUsuario... users);

    @Delete
    void delete(TipoUsuario user);
}
