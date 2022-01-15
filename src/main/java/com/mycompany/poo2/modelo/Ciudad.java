package com.mycompany.poo2.modelo;

import java.io.*;
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
    
    private String provincia;
    
    public int codigo;

    public Ciudad(String nombre, String provinicia) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.provincia = provinicia;
    }

    @Override
    public String toString() {
        return nombre ;
    }

    public Ciudad() {
    }
    public String getNombre(){
        return nombre;
    }
    public String getProvincia(){
        return provincia;
        }
    public String getCodigo(){
        return Integer.toString(contador);
        }
    public static ArrayList<Ciudad> generarCiudad(String ruta){
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new FileReader(ruta))){
        String line = br.readLine();
        while (line != null){
            String datos[] = line.split(",");
            String nombre = datos[1].strip();
            String provincia = datos[2].strip();
            Ciudad ciudad = new Ciudad(nombre,provincia);
            ciudades.add(ciudad);
            line = br.readLine();
            }
        }catch(IOException e){
                e.printStackTrace();}
        return ciudades;
    }
    
    public static Ciudad buscarCiudad( ArrayList<Ciudad> ciudades, String n){
        Ciudad ciudad = null;
        for(Ciudad c:ciudades){
        if(n.equals(c.getNombre())){
            ciudad= c;
            }
        }
        
        return ciudad;
        }
    
}
