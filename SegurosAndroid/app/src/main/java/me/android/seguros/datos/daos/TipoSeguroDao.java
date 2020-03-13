package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import me.android.seguros.datos.modelos.TipoSeguro;

@Dao
public interface TipoSeguroDao {
    @Query("SELECT * FROM tipos_seguro")
    List<TipoSeguro> getAll();

    @Query("SELECT COUNT(*) FROM tipos_seguro")
    int count();

    @Insert
    void insertAll(TipoSeguro... users);

    @Delete
    void delete(TipoSeguro user);
}
