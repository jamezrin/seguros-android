package me.android.seguros.datos.modelos.relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import me.android.seguros.datos.modelos.Seguro;
import me.android.seguros.datos.modelos.TipoUsuario;
import me.android.seguros.datos.modelos.Usuario;

public class UsuarioConTodo {
    @Embedded
    private Usuario usuario;

    @Relation(parentColumn = "id_tipo_usuario", entityColumn = "id")
    private TipoUsuario tipoUsuario;

    @Relation(parentColumn = "dni", entityColumn = "dni_cliente")
    private List<Seguro> segurosContratados;

    @Relation(parentColumn = "dni", entityColumn = "dni_vendedor")
    private List<Seguro> segurosVendidos;

    public UsuarioConTodo(Usuario usuario, TipoUsuario tipoUsuario, List<Seguro> segurosContratados, List<Seguro> segurosVendidos) {
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
        this.segurosContratados = segurosContratados;
        this.segurosVendidos = segurosVendidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Seguro> getSegurosContratados() {
        return segurosContratados;
    }

    public void setSegurosContratados(List<Seguro> segurosContratados) {
        this.segurosContratados = segurosContratados;
    }

    public List<Seguro> getSegurosVendidos() {
        return segurosVendidos;
    }

    public void setSegurosVendidos(List<Seguro> segurosVendidos) {
        this.segurosVendidos = segurosVendidos;
    }

    @Override
    public String toString() {
        return "UsuarioConTipo{" +
                "usuario=" + usuario +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
