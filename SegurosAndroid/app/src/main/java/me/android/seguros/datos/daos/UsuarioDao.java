package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.Usuario;
import me.android.seguros.datos.modelos.relaciones.UsuarioConTodo;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuarios WHERE dni IN (:dnis)")
    List<Usuario> loadAllByDNIs(String[] dnis);

    @Query("SELECT * FROM usuarios WHERE dni = :dni AND contrasena = :contrasena LIMIT 1")
    UsuarioConTodo findUserConTodo(String dni, String contrasena);

    @Query("SELECT * FROM usuarios WHERE borrado = :borrado")
    List<Usuario> getBorrados(boolean borrado);

    @Query("SELECT COUNT(*) FROM usuarios")
    int count();

    @Insert
    void insertAll(Usuario... users);

    @Delete
    void delete(Usuario user);
}
