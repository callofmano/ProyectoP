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
    public Ciudad(int codigo, String nombre, String provinicia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provinicia;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return nombre + Integer.toString(codigo);
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
        return Integer.toString(codigo);
        }
    public static ArrayList<Ciudad> generarCiudad(String ruta){
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new FileReader(ruta))){
        String line = br.readLine();
        while (line != null){
            String datos[] = line.split(",");
            int codigo =   Integer.valueOf(datos[0].strip());
            //System.out.println(codigo);
            String nombre = datos[1].strip();
            String provincia = datos[2].strip();
            Ciudad ciudad = new Ciudad(codigo, nombre,provincia);
            //System.out.println(ciudad.getCodigo());
            contador=codigo+1; 
            ciudades.add(ciudad);
            System.out.print(ciudad);
            line = br.readLine();
            }
        }catch(IOException e){
                e.printStackTrace();}
        //imprimir(ciudades);
        return ciudades;
    }
    public static void imprimir(ArrayList<Ciudad> c) {
        for(Ciudad ciu:c){
            System.out.println(ciu.getCodigo());
        }


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
