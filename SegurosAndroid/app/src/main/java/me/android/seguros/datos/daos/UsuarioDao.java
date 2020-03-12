package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import me.android.seguros.datos.modelos.Usuario;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuarios WHERE dni IN (:dnis)")
    List<Usuario> loadAllByDNIs(String[] dnis);

    @Query("SELECT COUNT(*) FROM usuarios")
    int count();

    @Insert
    void insertAll(Usuario... users);

    @Delete
    void delete(Usuario user);
}
