package me.android.seguros.modelos;

public class TipoSeguro {
    private final int id;
    private String tipo;
    private int borrado;

    public TipoSeguro(int id) {
        this.id = id;
    }

    public TipoSeguro(int id, String tipo, int borrado){
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

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "TipoSeguro{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", borrado=" + borrado +
                '}';
    }
}


