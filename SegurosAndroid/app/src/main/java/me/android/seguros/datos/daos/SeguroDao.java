package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import me.android.seguros.datos.modelos.Seguro;

@Dao
public interface SeguroDao {
    @Query("SELECT * FROM seguros")
    List<Seguro> getAll();

    @Query("SELECT * FROM seguros WHERE id IN (:ids)")
    List<Seguro> loadAllByIds(int[] ids);

    @Query("SELECT COUNT(*) FROM seguros")
    int count();

    @Insert
    void insertAll(Seguro... users);

    @Delete
    void delete(Seguro user);
}
