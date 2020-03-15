package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import me.android.seguros.datos.modelos.Seguro;
import me.android.seguros.datos.modelos.relaciones.SeguroConTipo;

@Dao
public interface SeguroDao {
    @Query("SELECT * FROM seguros")
    List<Seguro> getAll();

    @Query("SELECT * FROM seguros WHERE id IN (:ids)")
    List<Seguro> loadAllByIds(int[] ids);

    @Query("SELECT * FROM seguros WHERE dni_cliente = :dni")
    List<SeguroConTipo> getAllContratadoConTipo(String dni);

    @Query("SELECT * FROM seguros WHERE id = :id LIMIT 1")
    SeguroConTipo findConTipo(int id);

    @Query("SELECT COUNT(*) FROM seguros")
    int count();

    @Insert
    void insertAll(Seguro... seguros);

    @Update
    void update(Seguro seguro);
}
