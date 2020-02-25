package com.example.actcatalogodefrutas.servicio;

public class Fruta {
    public enum Tipo{
        TROPICAL, DELBOSQUE, CITRICA, SECA
    }

    private String nombre;
    private String descripcion;
    private Tipo tipo;

    public Fruta(String nombre, String descripcion, Tipo tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
