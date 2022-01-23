package com.mycompany.poo2.modelo;

import com.mycompany.poo2.App;
import com.mycompany.poo2.modelo.Ciudad;
import com.mycompany.poo2.modelo.Persona;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.lang.Class;
import java.util.ArrayList;

public class Auspiciante extends Persona{
    
    private static int contador = 1 ;
    
    public int codigo ;
    
    private String email;
    
    private String webpage;

    public Auspiciante(String email, String webpage, String nombre, String telefono, Ciudad ciudad, String apellidos) {
        super(nombre, telefono, ciudad,apellidos);
        this.codigo = contador++ ;
        this.email = email;
        this.webpage = webpage;
    }
    public Auspiciante(String email, String webpage, String nombre, String telefono, Ciudad ciudad, String apellidos, int codigo) {
        super(nombre, telefono, ciudad,apellidos);
        this.codigo = codigo;
        this.email = email;
        this.webpage = webpage;
    }

    public Auspiciante() {
    }

    public String getCodigo() {
        return Integer.toString(codigo);
    }
    public Ciudad getCiudad(){
        return super.getCiudad();
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static ArrayList<Auspiciante> cargarAuspiciantes (String ruta){
    ArrayList<Auspiciante> auspiciantes = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
        String linea = br.readLine();
        
        linea = br.readLine();
        while (linea != null) {
            
            String datos[]= linea.split(",");
            String nombre = datos[1];
            String apellidos = datos[2];
            String telefono = datos[3];
            Ciudad ciudad = Ciudad.buscarCiudad(Ciudad.generarCiudad(App.pathCiudades), datos[4].strip());
            String email = datos[5];
            String webpage= datos[6];
            int codigo = Integer.valueOf(datos[0]);
            Auspiciante auspiciante= new Auspiciante(email,webpage,nombre,telefono,ciudad,apellidos,Integer.parseInt(datos[0]));
            contador = codigo +1;
            auspiciantes.add(auspiciante);
            linea = br.readLine();
            }
    }
    catch(IOException e){
        e.printStackTrace();
    }
    return auspiciantes;
    }

    public static Auspiciante buscarAuspiciante(String nombre)
    {   
        Auspiciante auspiciante=null;
        for (Auspiciante a: cargarAuspiciantes(App.pathAuspiciantes)){
            if(a.getNombre().equals(nombre)){
                auspiciante=a;
                }
            }
        return auspiciante;
    }
}
