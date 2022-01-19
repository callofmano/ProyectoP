package com.mycompany.poo2.modelo;

import com.mycompany.poo2.App;
import com.mycompany.poo2.modelo.Mascota;
import com.mycompany.poo2.modelo.Premio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.io.*;
public class Concurso {
    
    private static int contador = 1;

    private Especie dirigido;
    
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

    public Concurso(int codigo,String nombre,Especie dirigido ,LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Mascota> inscritos, ArrayList<Premio> premios, ArrayList<Mascota> ganadores) {
        this.codigo = codigo;
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
        this.dirigido = dirigido;
    }

    
    //generando constructor sin mascotas inscritas ni ganadores

    public Concurso(int codigo, String nombre,Especie dirigido , LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaInsc = fechaInsc;
        this.fechaCierre = fechaCierre;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.premios = premios;
        this.dirigido = dirigido;
    }
    public Concurso(String nombre,Especie dirigido ,LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Mascota> inscritos, ArrayList<Premio> premios, ArrayList<Mascota> ganadores) {
        this.codigo = contador++;
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
        this.dirigido = dirigido;
    }

    
    //generando constructor sin mascotas inscritas ni ganadores

    public Concurso(String nombre,Especie dirigido , LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaInsc = fechaInsc;
        this.fechaCierre = fechaCierre;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.premios = premios;
        this.dirigido = dirigido;
    }
    
    
//getters
    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha.toString();
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

    public Especie getDirigido() {
        return dirigido;
    }

    
    //to strings

    public static int getContador() {
        return contador;
    }


    public static void setContador(int contador) {
        Concurso.contador = contador;
    }


    public void setDirigido(Especie dirigido) {
        this.dirigido = dirigido;
    }


    public int getCodigo() {
        return codigo;
    }


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public void setHora(String hora) {
        this.hora = hora;
    }


    public void setFechaInsc(LocalDate fechaInsc) {
        this.fechaInsc = fechaInsc;
    }


    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }


    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }


    public void setLugar(String lugar) {
        this.lugar = lugar;
    }


    public void setInscritos(ArrayList<Mascota> inscritos) {
        this.inscritos = inscritos;
    }


    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }


    public void setGanadores(ArrayList<Mascota> ganadores) {
        this.ganadores = ganadores;
    }


    @Override
    public String toString() {
        return "Concurso{" + "codigo=" + codigo + ", nombre=" + nombre + ", fecha=" + fecha + ", hora=" + hora + ", fechaInsc=" + fechaInsc + ", fechaCierre=" + fechaCierre + ", ciudad=" + ciudad + ", lugar=" + lugar + ", inscritos=" + inscritos + ", premios=" + premios + ", ganadores=" + ganadores + '}';
    }
       
    
    public String nombreyfechas(){
        return "codigo: "+codigo+" Nombre del concurso: "+nombre+" Fecha inicio inscripciones "+fechaInsc+" Fecha cierre de Inscripciones "+fechaCierre;
    }
    
    public static ArrayList<Concurso> cargarConcursos(String ruta)
    {
      ArrayList<Concurso> concursos = new ArrayList<>();  
      try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
        String line = br.readLine();
        line= br.readLine();
        while(line != null){
            String datos[] = line.split(";");
            int codigo = Integer.valueOf(datos[0]);
            String nombre = datos [1];
            Especie especie = Especie.valueOf(datos[2]);
            String fechaA[] = datos[3].split("-");
            LocalDate fechaActual = LocalDate.of(Integer.valueOf(fechaA[0]),Integer.valueOf(fechaA[1]),Integer.valueOf(fechaA[2]));
            String hora = datos[4];
            String fechaI[] = datos[5].split("-");
            LocalDate fechaInscripcion = LocalDate.of(Integer.valueOf(fechaI[0]),Integer.valueOf(fechaI[1]),Integer.valueOf(fechaI[2]));
            String fechaC[] = datos[6].split("-");
            LocalDate fechaCierre = LocalDate.of(Integer.valueOf(fechaC[0]),Integer.valueOf(fechaC[1]),Integer.valueOf(fechaC[2]));
            Ciudad ciudad = Concurso.buscarciudad(Ciudad.generarCiudad(App.pathCiudades),datos[7]);
            String lugar = datos[8];
            ArrayList<Premio> premios = new ArrayList<>();
            //,Especie dirigido , LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios
            if(datos[9].equals(" ") && datos[11].equals(" ")){
                Concurso concurso = new Concurso(codigo, nombre,especie,fechaActual,hora,fechaInscripcion,fechaCierre,ciudad,lugar,premios);
                contador=codigo+1;
                concursos.add(concurso);
                }
            else{
            String[] inscritos = datos[9].split("-"); 
            String [] ganadores = datos[11].split("-");
            ArrayList<Mascota> mascotasInscritas = new ArrayList<>();
            ArrayList<Mascota> mascotasGanadoras = new ArrayList<>();
            ArrayList<Mascota> mascotas = Mascota.cargarMascotas(App.pathMascotas);
            for(String s : inscritos){
                for(Mascota m: mascotas){
                    if(m.getCodigo().equals(s)){
                        mascotasInscritas.add(m);
                        }
                    }
                }
            for(String g : ganadores){
                for(Mascota m: mascotas){
                    if(m.getCodigo().equals(g)){
                        mascotasGanadoras.add(m);
                            }
                        }
                    }
            //String nombre,Especie dirigido ,LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Mascota> inscritos, ArrayList<Premio> premios, ArrayList<Mascota> ganadores
            Concurso concurso = new Concurso (codigo,nombre,especie,fechaActual,hora,fechaInscripcion,fechaCierre,ciudad,lugar,mascotasInscritas,premios,mascotasGanadoras);
            contador=codigo+1;
            concursos.add(concurso);     
            } 
            line= br.readLine();          



         } }catch(IOException e){
        e.printStackTrace();
        }
    System.out.println(concursos);
    return concursos;
    }
    public static Ciudad buscarciudad( ArrayList<Ciudad> ciudades, String co){
            Ciudad ciudad = null;
            for(Ciudad c:ciudades){
            if(co.equals(c.getCodigo())){
                ciudad= c;
                }
            }
            
            return ciudad;
            }
    

    

    
    

   
    
    
}
