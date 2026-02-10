/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.ENT;

import java.sql.Blob;
import javafx.scene.image.Image;

/**
 *
 * @author march
 */
public class ClProductos {
    private int id;
    private String nombre;
    private String descripcion;
    private int valor;

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
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public ClProductos() {
    }

    
    public ClProductos(int id, String nombre, String descripcion, int valor) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setValor(valor);
    }
    public ClProductos(String nombre, String descripcion, int valor) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setValor(valor);
    }
    
}
