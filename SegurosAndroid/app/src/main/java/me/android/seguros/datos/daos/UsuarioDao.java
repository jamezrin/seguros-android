package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import me.android.seguros.datos.modelos.TipoSeguro;
import me.android.seguros.datos.modelos.Usuario;
import me.android.seguros.datos.modelos.relaciones.UsuarioConTipo;
import me.android.seguros.datos.modelos.relaciones.UsuarioConTodo;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuarios JOIN tipos_seguro ON usuarios.id_tipo_usuario = tipos_seguro.id AND tipos_seguro.tipo = :tipo")
    List<Usuario> getAllUserType(String tipo);

    @Query("SELECT * FROM usuarios WHERE dni IN (:dnis)")
    List<Usuario> loadAllByDNIs(String[] dnis);

    @Query("SELECT * FROM usuarios WHERE dni = :dni AND contrasena = :contrasena LIMIT 1")
    UsuarioConTodo findConTodo(String dni, String contrasena);

    @Query("SELECT * FROM usuarios WHERE dni = :dni LIMIT 1")
    UsuarioConTodo findConTodo(String dni);

    @Query("SELECT * FROM usuarios WHERE dni = :dni LIMIT 1")
    Usuario find(String dni);

    @Query("SELECT * FROM usuarios WHERE borrado = :borrado")
    List<Usuario> getBorrados(boolean borrado);

    @Query("SELECT COUNT(*) FROM usuarios")
    int count();

    @Insert
    void insertAll(Usuario... usuarios);

    @Update
    void update(Usuario usuario);

    @Query("SELECT * FROM usuarios WHERE dni = :dni LIMIT 1")
    UsuarioConTipo findConTipo(String dni);
}
