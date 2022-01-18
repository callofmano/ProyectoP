package com.mycompany.poo2.modelo;
import com.mycompany.poo2.modelo.*;
import java.lang.reflect.Array;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Premio {
    private Posicion pos;
    
    private String descripcion;
    
    private Auspiciante auspiciante;
    
    

    public Premio(Posicion pos, String descripcion, Auspiciante auspiciante) {
        this.pos = pos;
        this.descripcion = descripcion;
        this.auspiciante = auspiciante;
    }

    public Posicion getPos() {
        return pos;
    }

    public void setPos(Posicion pos) {
        this.pos = pos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    public void setAuspiciante(Auspiciante auspiciante) {
        this.auspiciante = auspiciante;
    }



    @Override
    public String toString() {
        return "Premio{" + "pos=" + pos + ", descripcion=" + descripcion + ", auspiciante=" + auspiciante + '}';
    }
    
    public static ArrayList<Premio> cargarPremios(String ruta){
        ArrayList<Premio> premios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line = br.readLine();
            while (line != null) {
                String datos []=line.split(",");
                Posicion posicion = Posicion.valueOf(datos[0]);
                Auspiciante auspiciante= Auspiciante.buscarAuspiciante(datos[1]);
                String des = datos[2];
        
                }
            }   
            catch(IOException e){
            e.printStackTrace();
            }

        return premios; 
    }

    
}
