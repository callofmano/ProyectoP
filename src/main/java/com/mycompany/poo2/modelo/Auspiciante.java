package com.mycompany.poo2.modelo;


import com.mycompany.poo2.modelo.Ciudad;
import com.mycompany.poo2.modelo.Persona;


public class Auspiciante extends Persona{
    
    private static int contador = 1 ;
    
    public int codigo ;
    
    private String email;
    
    private String webpage;

    public Auspiciante(String email, String webpage, String nombre, String telefono, Ciudad ciudad, String apellidos) {
        super(nombre, telefono, ciudad,apellidos);
        codigo = contador++ ;
        this.email = email;
        this.webpage = webpage;
    }

    public Auspiciante() {
    }

    
    
    

    @Override
    public String toString() {
        return "Auspiciante{" +super.toString()+ "codigo=" + codigo + ", email=" + email + ", webpage=" + webpage + '}';
    }

    
    
    
    
    
    
}
