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

    public Persona(String nombre, String telefono, Ciudad ciudad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
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

    
    
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + '}';
    }
    
    
    
}
