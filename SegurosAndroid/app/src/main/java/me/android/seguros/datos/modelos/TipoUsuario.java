package me.android.seguros.datos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipos_usuario")
public class TipoUsuario {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "tipo")
    private String tipo;

    @ColumnInfo(name = "borrado")
    private boolean borrado;

    public TipoUsuario(int id) {
        this.id = id;
    }

    public TipoUsuario(int id, String tipo, boolean borrado) {
        this.id = id;
        this.tipo = tipo;
        this.borrado = borrado;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", borrado=" + borrado +
                '}';
    }
}
