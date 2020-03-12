package me.android.seguros.modelos.relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import me.android.seguros.modelos.TipoUsuario;
import me.android.seguros.modelos.Usuario;

public class UsuarioConTipo {
    @Embedded
    private Usuario usuario;

    @Relation(parentColumn = "id", entityColumn = "id_tipo_usuario")
    private TipoUsuario tipoUsuario;

    public UsuarioConTipo(Usuario usuario, TipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
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

    @Override
    public String toString() {
        return "UsuarioConTipo{" +
                "usuario=" + usuario +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
