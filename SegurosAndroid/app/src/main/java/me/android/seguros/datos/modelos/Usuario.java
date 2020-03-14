package me.android.seguros.datos.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "usuarios")
public class Usuario {
    public static final int ID_USUARIO_ADMIN = 3,
            ID_USUARIO_VENDEDOR = 2,
            ID_USUARIO_CLIENTE = 1;

    @PrimaryKey
    @ColumnInfo(name = "dni")
    @NonNull
    private String dni;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "direccion")
    private String direccion;

    @ColumnInfo(name = "telefono")
    private String telefono;

    @ColumnInfo(name = "contrasena")
    private String contrasena;

    @ColumnInfo(name = "id_tipo_usuario")
    private int idTipoUsuario;

    @ColumnInfo(name = "borrado")
    private boolean borrado;

    @Ignore
    public Usuario(@NotNull String dni) {
        this.dni = dni;
    }

    public Usuario(@NotNull String dni, String nombre, String apellidos, String direccion, String telefono, String contrasena, int idTipoUsuario, boolean borrado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.idTipoUsuario = idTipoUsuario;
        this.borrado = borrado;
    }

    @NotNull
    public String getDni() {
        return dni;
    }

    public void setDni(@NonNull String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", idTipoUsuario=" + idTipoUsuario +
                ", borrado=" + borrado +
                '}';
    }
}