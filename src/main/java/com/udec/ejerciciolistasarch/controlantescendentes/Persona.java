/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.ejerciciolistasarch.controlantescendentes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

/**
 *
 * @author Leidy Torres
 */
public class Persona implements Serializable{
   
    private String nombre;
    private String cedula;
    private short edad;
    private String genero;
    private  List <Antecedentes> antecedentes = new ArrayList<>();

    public Persona(String nombre, String cedula, short edad, String genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
    }

    public List<Antecedentes> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<Antecedentes> antecedentes) {
        this.antecedentes = antecedentes;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
