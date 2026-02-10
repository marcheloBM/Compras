package com.example.march.compras;

/**
 * Created by march on 21-11-2017.
 */

public class ClProductos {
    int id;
    String nombre;
    String descripcion;
    int Valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }

    @Override
    public String toString() {
        return id +" nombre=" + nombre +" descripcion=" + descripcion +" Valor=" + Valor ;
    }

    public ClProductos() {
    }

    public ClProductos(int id, String nombre, String descripcion, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        Valor = valor;
    }
}
