package com.mycompany.poo2.modelo;

import com.mycompany.poo2.modelo.Mascota;
import com.mycompany.poo2.modelo.Premio;
import java.time.LocalDate;
import java.util.*;

public class Concurso {
    
    private static int contador = 1;
    
    public int codigo ;
    
    private String nombre ;
    
    private LocalDate fecha;
    
    private String hora;
    
    private LocalDate fechaInsc;
    
    private LocalDate fechaCierre;
    
    private Ciudad ciudad;
    
    private String lugar;

    public ArrayList<Mascota> inscritos;
    
    private ArrayList<Premio> premios;
    
    private ArrayList<Mascota> ganadores;
    
    
    
    
    //creando contructor que se use en el main para pre generar los datos que deben estar cargados

    public Concurso(String nombre, LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Mascota> inscritos, ArrayList<Premio> premios, ArrayList<Mascota> ganadores) {
        codigo = contador++;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaInsc = fechaInsc;
        this.fechaCierre = fechaCierre;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.inscritos = inscritos;
        this.premios = premios;
        this.ganadores = ganadores;
    }

    
    //generando constructor sin mascotas inscritas ni ganadores

    public Concurso(String nombre, LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios) {
        codigo = contador++;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaInsc = fechaInsc;
        this.fechaCierre = fechaCierre;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.premios = premios;
    }

    
    
//getters
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public LocalDate getFechaInsc() {
        return fechaInsc;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public String getLugar() {
        return lugar;
    }

    public ArrayList<Mascota> getInscritos() {
        return inscritos;
    }

    public ArrayList<Premio> getPremios() {
        return premios;
    }

    public ArrayList<Mascota> getGanadores() {
        return ganadores;
    }
    
    //to strings

    @Override
    public String toString() {
        return "Concurso{" + "codigo=" + codigo + ", nombre=" + nombre + ", fecha=" + fecha + ", hora=" + hora + ", fechaInsc=" + fechaInsc + ", fechaCierre=" + fechaCierre + ", ciudad=" + ciudad + ", lugar=" + lugar + ", inscritos=" + inscritos + ", premios=" + premios + ", ganadores=" + ganadores + '}';
    }
       
    
    public String nombreyfechas(){
        return "codigo: "+codigo+" Nombre del concurso: "+nombre+" Fecha inicio inscripciones "+fechaInsc+" Fecha cierre de Inscripciones "+fechaCierre;
    }
    
    
    
    

    

    
    

   
    
    
}
