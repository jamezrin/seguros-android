package me.android.seguros.datos.modelos.relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import me.android.seguros.datos.modelos.Seguro;
import me.android.seguros.datos.modelos.TipoSeguro;

public class SeguroConTipo {
    @Embedded
    private Seguro seguro;

    @Relation(parentColumn = "id_tipo_seguro", entityColumn = "id")
    private TipoSeguro tipoSeguro;

    public SeguroConTipo(Seguro seguro, TipoSeguro tipoSeguro) {
        this.seguro = seguro;
        this.tipoSeguro = tipoSeguro;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    @Override
    public String toString() {
        return String.format("%s (codigo %d)",
                tipoSeguro.getTipo(),
                seguro.getId()
        );
    }
}
