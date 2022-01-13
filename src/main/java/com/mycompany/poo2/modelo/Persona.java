package com.mycompany.poo2.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marti
 */
public abstract class Persona {
    
    private String nombre;
    
    private String telefono;
    
    private Ciudad ciudad;
    
    private String apellidos;

    public Persona(String nombre, String telefono, Ciudad ciudad, String apellidos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.apellidos = apellidos;
    }

    public void modNomb(String newNombre){
        this.nombre = newNombre;
    }
    
    public void modTel(String newTelefono){
        this.telefono = newTelefono;
    }
    public void modCiudad(Ciudad newCiudad){
        this.ciudad = newCiudad;
    }
    
    public Persona() {
    }

    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellidos;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public Ciudad getCiudad(){
        return ciudad;
    }
    
    @Override
    public String toString() {
        return nombre +" " + apellidos;
    }
    
    
    
}
