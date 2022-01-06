package com.mycompany.poo2.modelo;

import com.mycompany.poo2.modelo.Persona;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import  java.lang.Class;
import java.util.ArrayList;

public class DuenoMascota extends Persona{
    
    public String ci;

    public DuenoMascota(String ci, String nombre, String telefono, Ciudad ciudad) {
        super(nombre, telefono, ciudad);
        this.ci = ci;
    }

    @Override
    public String toString() {
        return "DuenoMascota{" +super.toString()+ "ci=" + ci + '}';
    }

    //AGREGAR ECUALS

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DuenoMascota other = (DuenoMascota) obj;
        if (!Objects.equals(this.ci, other.ci)) {
            return false;
        }
        return true;
    }
    
    public static DuenoMascota buscarDueno(String id,String ruta){
    DuenoMascota dueno = null;
    InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new InputStreamReader(input)))
        {
        br.readLine();
        String line = br.readLine();
        while (line != null){
            String datos[]= line.split(",");
            String ci = datos[0].strip();
            String nombre= datos[2].strip();
            String telefono =datos[4].strip();
            Ciudad ciu = Ciudad.valueOf(datos[5].strip());
            if (ci.equals(id)){
                dueno = new DuenoMascota(ci,nombre,telefono,ciu);
                }
            }
        }catch(IOException e){
                e.printStackTrace();}
        return dueno;
        }
    
    public static ArrayList<DuenoMascota> cargarDuenos(string ruta){
        ArrayList<DuenoMascota> duenos = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new InputStreamReader(input))){
            String line = br.readLine();
            br.readLine();
            while (line != null){
                
            
                }
            }catch(IOException e){
                e.printStackTrace();}
    }
    
    
    
    
    
    
    
    
    
    
    }




