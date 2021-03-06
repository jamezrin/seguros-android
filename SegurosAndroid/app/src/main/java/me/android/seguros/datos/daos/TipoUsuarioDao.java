package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import me.android.seguros.datos.modelos.TipoUsuario;

@Dao
public interface TipoUsuarioDao {
    @Query("SELECT * FROM tipos_usuario")
    List<TipoUsuario> getAll();

    @Query("SELECT COUNT(*) FROM tipos_usuario")
    int count();

    @Query("SELECT * FROM tipos_usuario WHERE tipo = :tipo LIMIT 1")
    TipoUsuario find(String tipo);

    @Insert
    void insertAll(TipoUsuario... tiposUsuario);

    @Update
    void update(TipoUsuario tipoUsuario);

    @Query("SELECT * FROM tipos_usuario WHERE id = :id")
    TipoUsuario findById(int id);
}
