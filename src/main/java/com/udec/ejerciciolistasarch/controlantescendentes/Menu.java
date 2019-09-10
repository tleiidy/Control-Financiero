/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.ejerciciolistasarch.controlantescendentes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leidy Torres
 */
public class Menu implements Serializable{
    private Scanner teclado = new Scanner(System.in);
    List<Persona> persona = new ArrayList<Persona>();
    
    
    public Menu() {
        menu();
    }
    
    
    public void menu(){
        String respuesta;
        do{
            System.out.println("*** M E N Ú *** ");
            System.out.println("-- Escoja una opción -- ");
            System.out.println("* 1. Registrar Persona* ");
            System.out.println("* 2. Registrar Antecedentes * ");
            System.out.println("* 3. Consultar * ");
            System.out.println("* 4. Actualizar * ");
            System.out.println("* 5. Eliminar * ");
            System.out.println("* 6. SALIR * ");
            int opcion = teclado.nextInt();
            switch (opcion){
                case 1: datosPersona();
                    break;
                case 2: datosAntecedentes();
                    break;
                case 3: mostrar();
                    break;
                case 4: //actualizar();
                    break;
                case 5: //eliminar();
                    break;
            }System.out.print("Desea volver al menú? (si/no):");
            respuesta = teclado.next();
            
        }while (respuesta.equalsIgnoreCase("si"));
    }
    
    public void datosPersona(){
        System.out.println("Nombre: ");
        String nombre = teclado.next();
        System.out.println("Cédula: ");
        String cedula = teclado.next();
        System.out.println("Edad: ");
        short edad = teclado.nextShort();
        System.out.println("Genero: ");
        String genero = teclado.next();
        Persona per = new Persona(nombre,cedula,edad,genero);
        guardarDatos(per);
    }
    
    public void datosAntecedentes(){
        System.out.println("Codigo Antecedente: ");
        int id = teclado.nextInt();
        System.out.println("Fecha: ");
        String fecha = teclado.next();
        System.out.println("Estado: ");
        String estado = teclado.next();
        System.out.println("Tipo: ");
        String tipo = teclado.next();
        System.out.println("Descripción: ");
        String descripcion = teclado.next();
        System.out.println("Ingrese el numero de cedula de la persona: ");
        String cc = teclado.next();
        Antecedentes an = new Antecedentes(id,fecha,estado,tipo,descripcion);
        lectura(an,cc);
    }
    
    public void guardarDatos(Persona persona){
       FileWriter Archivos = null;
        try{
            Archivos = new FileWriter("Archivos/Archivo.txt", true);
            File archivo2 = new File("Archivos/Registros.txt");
            ArrayList<Persona> lista2 = new ArrayList();
            if(archivo2.exists()){
                ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(archivo2));
                lista2 = (ArrayList<Persona>) objectInput.readObject();
            }
            datosRepetidos(lista2, persona);
            lista2.add(persona);
            ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(archivo2));
            escritura.writeObject(lista2);
            escritura.close();
        }catch(FileNotFoundException ex){
            System.out.println("No se encontro el archivo!!");    
        }catch(IOException ex){
            System.out.println("Error en el archivo!!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void lectura(Antecedentes an, String cedula){
        try(FileInputStream fis=new FileInputStream("Archivos/Registros.txt")){
            try {
                ObjectInputStream entrada = new ObjectInputStream(fis);
                Object actual = null;
                ArrayList<Persona> lista = null;
                Persona persona = null;
                while((actual = entrada.readObject()) != null){
                    lista = (ArrayList<Persona>) actual;
                    for (int i = 0; i < lista.size(); i++) {
                        if(lista.get(i).getCedula().equals(cedula)){
                            lista.get(i).getAntecedentes().add(an);
                            guardarDatos(persona = (Persona)lista.get(i));
                        }
                    }
                }
                entrada.close();
            } catch (Exception e) {
            }
        }catch(IOException ex){
            System.out.println("Error");
        }
    }
    
    public void mostrar(){
        try(FileInputStream fis=new FileInputStream("Archivos/Registros.txt")){
            try {
                ObjectInputStream entrada = new ObjectInputStream(fis);
                Object actual = null;
                ArrayList<Persona> lista = null;
                while((actual = entrada.readObject()) != null){
                    lista = (ArrayList<Persona>) actual;
                    for(int i = 0;i<lista.size();++i){
                        System.out.println("Nombre: "+lista.get(i).getNombre());
                        System.out.println("Cedula: "+lista.get(i).getCedula());
                        System.out.println("Edad: "+lista.get(i).getEdad());
                        System.out.println("Genero: "+lista.get(i).getGenero());
                        for(int j = 0;j<lista.get(i).getAntecedentes().size();++j){
                            System.out.println("Codigo Antecedente: "+lista.get(i).getAntecedentes().get(j).getId());
                            System.out.println("Fecha: "+lista.get(i).getAntecedentes().get(j).getFecha());
                            System.out.println("Estado: "+lista.get(i).getAntecedentes().get(j).getEstado());
                            System.out.println("Tipo: "+lista.get(i).getAntecedentes().get(j).getTipo());
                            System.out.println("Descripcion: "+lista.get(i).getAntecedentes().get(j).getDescripcion());
                        }
                    }      
                }
                entrada.close();
            } catch (Exception e) {
            }
        }catch(IOException ex){
            System.out.println("Error");
        }
    }
    
    public Persona datosRepetidos(ArrayList<Persona> lista,Persona persona){
        ArrayList<Persona> prueba = new ArrayList<>(lista);
        try {
            for (Persona lista1 : prueba) {
                if(lista1.getCedula()== persona.getCedula()){
                    lista.remove(lista1);
                }
            }
        } catch (Exception e) {
        }
        return persona;
    }

    public void actualizar(){
        System.out.println("Ingrese la cedula ");
        Long cedula = teclado.nextLong();
        teclado.nextLine();
        short error = 1;
        ArrayList<Persona> lista = new ArrayList();
        for(int i = 0; i<lista.size(); i++){
            if(lista.get(i).getCedula().equals(cedula)){
                error = 0;
                System.out.println("Nombre: ");
                lista.get(i).setNombre(teclado.nextLine());
                teclado.nextLine();
                System.out.println("Edad: ");
                lista.get(i).setEdad(teclado.nextShort());
                teclado.nextLine();
            }
        }
        if(error == 1){
            System.out.println("No se encontro el registro");
        }
        File miFile = new File("Archivo/Registros.txt");
            ObjectOutputStream oos;
        try {    
            oos = new ObjectOutputStream(new FileOutputStream(miFile));
            oos.writeObject(persona);
            oos.close();
        } catch (IOException e) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, e);
        }
    
    }
    public void eliminar(){
        System.out.println("Ingrese la cedula de la persona a la cual desea eliminar un antecedente:");
        String cedula = teclado.next();
        List<Antecedentes> antecedentes = new ArrayList();
        short error = 1;  
        ArrayList<Persona> lista = new ArrayList();
        for(int j = 0; j<lista.size(); j++){
            if(lista.get(j).getCedula().equals(cedula)){
                error = 0;
                System.out.println("Se encontro el registro");
                antecedentes = lista.get(j).getAntecedentes();
                for(int i=0; i<antecedentes.size();i++){
                    if(antecedentes.get(i).getEstado().equals("negativo")){
                        System.out.println("Antecedente"+(i+1));
                        System.out.println("Fecha: " + antecedentes.get(i).getFecha());
                        System.out.println("Estado: " + antecedentes.get(i).getEstado());
                        System.out.println("Tipo: " + antecedentes.get(i).getTipo());
                        System.out.println("Descripcion: " + antecedentes.get(i).getDescripcion());
                        
                    }
                }
                System.out.println("Ingrese el numero del antecedente");
                int indexElim = teclado.nextInt();
                if(antecedentes.get(indexElim-1).getTipo().equals("positivo")){
                    System.out.println("el antecedente no se puede eliminar por que es positivo");
                }else{
                    antecedentes.remove(indexElim-1);
                    System.out.println("Se ha eliminado el registro con exito");
                }
                lista.get(indexElim).setAntecedentes(antecedentes);
            }
         }
        if(error == 1){
            System.out.println("No se encontro el registro");
        }
          File miFile = new File("Archivo/Registros.txt");
            ObjectOutputStream oos;
        try {    
            oos = new ObjectOutputStream(new FileOutputStream(miFile));
            oos.writeObject(persona);
            oos.close();
        } catch (IOException e) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public List<Persona> getpersona() {
        return persona;
    }

    public void setListaPersonas(List<Persona> persona) {
        this.persona = persona;
    }
    
}
