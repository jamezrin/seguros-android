package me.android.seguros.datos.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import me.android.seguros.datos.modelos.TipoSeguro;

@Dao
public interface TipoSeguroDao {
    @Query("SELECT * FROM tipos_seguro")
    List<TipoSeguro> getAll();

    @Query("SELECT * FROM tipos_seguro WHERE borrado = :borrado")
    List<TipoSeguro> getBorrados(boolean borrado);

    @Query("SELECT * FROM tipos_seguro WHERE tipo = :tipoSeguro")
    TipoSeguro findByName(String tipoSeguro);

    @Query("SELECT * FROM tipos_seguro WHERE id = :id")
    TipoSeguro findById(int id);

    @Query("SELECT COUNT(*) FROM tipos_seguro")
    int count();

    @Insert
    void insertAll(TipoSeguro... tiposSeguro);

    @Update
    void update(TipoSeguro tipoSeguro);
}
