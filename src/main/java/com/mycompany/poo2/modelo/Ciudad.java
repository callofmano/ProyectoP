package com.mycompany.poo2.modelo;

import java.io.InputStream;
import com.mycompany.poo2.modelo.Persona;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import  java.lang.Class;
import java.util.ArrayList;

public class Ciudad {
    
    private static int contador = 1 ;
    
    private String nombre;
    
    private String provinicia;
    
    public String codigo;

    public Ciudad(String nombre, String provinicia,String codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provinicia = provinicia;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "nombre=" + nombre +'}';
    }

    public Ciudad() {
    }

    public static ArrayList<Ciudad> generarCiudad(String ruta, String n){
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new InputStreamReader(input))){
        String line = br.readLine();
        while (line != null){
            String datos[] = line.split(",");
            String nombre = datos[1].strip();
            String provincia = datos[2].strip();
            String codigo = datos[0].strip();
            Ciudad ciudad = new Ciudad(nombre,provincia,codigo);
            ciudades.add(ciudad);
            }
        }catch(IOException e){
                e.printStackTrace();}
        return ciudades;
    }
    
    
}
