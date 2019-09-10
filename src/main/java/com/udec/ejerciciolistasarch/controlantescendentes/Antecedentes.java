/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.ejerciciolistasarch.controlantescendentes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Leidy Torres
 */
public class Antecedentes implements Serializable{
    
    private int id;
    private String fecha;
    private String estado;
    private String tipo;
    private String descripcion;

    public Antecedentes(int id,String fecha, String estado, String tipo, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
   
    
}
