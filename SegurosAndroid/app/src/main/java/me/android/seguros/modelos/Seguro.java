package me.android.seguros.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.OffsetDateTime;

@Entity(foreignKeys = {
        @ForeignKey(entity = Usuario.class, parentColumns = "dni", childColumns = "dniCliente"),
        @ForeignKey(entity = Usuario.class, parentColumns = "dni", childColumns = "dniVendedor"),
})
public class Seguro {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "fecha_alta")
    private OffsetDateTime fechaAlta;

    @ColumnInfo(name = "fecha_baja")
    private OffsetDateTime fechaBaja;

    @ColumnInfo(name = "dni_cliente")
    private String dniCliente;

    @ColumnInfo(name = "dni_vendedor")
    private String dniVendedor;

    @Embedded
    private TipoSeguro tipoSeguro;

    @ColumnInfo(name = "borrado")
    private boolean borrado;

    public Seguro(int id) {
        this.id = id;
    }

    public Seguro(int id, OffsetDateTime fechaAlta, OffsetDateTime fechaBaja, String dniCliente, String dniVendedor, TipoSeguro tipoSeguro, boolean borrado) {
        this.id = id;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.dniCliente = dniCliente;
        this.dniVendedor = dniVendedor;
        this.tipoSeguro = tipoSeguro;
        this.borrado = borrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OffsetDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(OffsetDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public OffsetDateTime getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(OffsetDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDniVendedor() {
        return dniVendedor;
    }

    public void setDniVendedor(String dniVendedor) {
        this.dniVendedor = dniVendedor;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", dniCliente='" + dniCliente + '\'' +
                ", dniVendedor='" + dniVendedor + '\'' +
                ", tipoSeguro=" + tipoSeguro +
                ", borrado=" + borrado +
                '}';
    }
}
