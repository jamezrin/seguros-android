package me.android.seguros.datos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(foreignKeys = {
        @ForeignKey(entity = Usuario.class, parentColumns = "dni", childColumns = "dni_cliente"),
        @ForeignKey(entity = Usuario.class, parentColumns = "dni", childColumns = "dni_vendedor"),
}, tableName = "seguros")
public class Seguro {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    @ColumnInfo(name = "fecha_baja")
    private LocalDateTime fechaBaja;

    @ColumnInfo(name = "dni_cliente")
    private String dniCliente;

    @ColumnInfo(name = "dni_vendedor")
    private String dniVendedor;

    @ColumnInfo(name = "id_tipo_seguro")
    private int idTipoSeguro;

    @ColumnInfo(name = "borrado")
    private boolean borrado;

    @Ignore
    public Seguro() {

    }

    public Seguro(int id, LocalDateTime fechaAlta, LocalDateTime fechaBaja, String dniCliente, String dniVendedor, int idTipoSeguro, boolean borrado) {
        this.id = id;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.dniCliente = dniCliente;
        this.dniVendedor = dniVendedor;
        this.idTipoSeguro = idTipoSeguro;
        this.borrado = borrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
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

    public int getIdTipoSeguro() {
        return idTipoSeguro;
    }

    public void setIdTipoSeguro(int idTipoSeguro) {
        this.idTipoSeguro = idTipoSeguro;
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
                ", idTipoSeguro=" + idTipoSeguro +
                ", borrado=" + borrado +
                '}';
    }
}
