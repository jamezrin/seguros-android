package me.android.seguros.modelos.relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import me.android.seguros.modelos.Seguro;
import me.android.seguros.modelos.TipoSeguro;

public class SeguroConTipo {
    @Embedded
    private Seguro seguro;

    @Relation(parentColumn = "id", entityColumn = "id_tipo_seguro")
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
        return "SeguroConTipo{" +
                "seguro=" + seguro +
                ", tipoSeguro=" + tipoSeguro +
                '}';
    }
}
