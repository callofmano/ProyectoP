package com.mycompany.poo2.modelo;

import com.mycompany.poo2.App;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Mascota {

    private static int contador = 1;

    private String nombre;

    private Especie especie;

    private LocalDate fechaNacimiento;

    private String foto;

    private int codigo;

    private String raza;

    private DuenoMascota dueno;



    public Mascota(String nombre, String raza, LocalDate fechaNacimiento, String foto, Especie especie, DuenoMascota dueno) {   //ESTE CONSTRUCTOR SIRVE PARA CREAR
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.codigo = contador++;
        this.especie = especie;
        this.dueno = dueno;


        /*detalle.setOnAction((ActionEvent ev)->{
            TextInputDialog dialogo1 = new TextInputDialog();
            dialogo1.setTitle("PROBANDO123 FUNCIONANDO ****");
        });*/
           //carga los botones y su funcionalidad
    }

    

    public Mascota(String nombre, Especie especie, LocalDate fechaNacimiento, String foto, int codigo, String raza,
            DuenoMascota dueno) {       //CONTRUCTOR PARA HACER CONSULTAS INCLUYENDO EL CODIGO 
        this.nombre = nombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.codigo = codigo;
        this.raza = raza;
        this.dueno = dueno;

        
    }



    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return Integer.toString(codigo);
    }

    public DuenoMascota getDueno() {
        return dueno;
    }

    public Especie getEspecie() {
        return especie;
    }

    public String getFoto(){
        return foto;
    }
    




    public String getRaza() {
        return raza;
    }



    public void setRaza(String raza) {
        this.raza = raza;
    }



    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }



    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



    @Override
    public String toString() {
        return "Mascota{ codigo " + codigo + "nombre=" + nombre + ", dueno=" + dueno + " fecha"+fechaNacimiento.toString()+" raza"+raza+'}';
    }

    public static ArrayList<Mascota> cargarMascotas(String ruta) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String line = br.readLine();//escapar cabecera
            line = br.readLine();
            while (line != null) {

                String[] datos = line.split(";");
                
                String nombre = datos[1].strip();
                int codigo = Integer.valueOf(datos[0].strip());
                Especie e = Especie.valueOf(datos[2].strip().toUpperCase());
               
                String linea2[] = datos[4].split("-");
                LocalDate nacimiento = LocalDate.of(Integer.valueOf(linea2[0].strip()), Integer.valueOf(linea2[1].strip()), Integer.valueOf(linea2[2].strip()));
                String foto = datos[5].strip();
                String raza = datos[3].strip();
            
                DuenoMascota dueno = DuenoMascota.buscarDueno(DuenoMascota.cargarDuenos(App.pathPersonas), datos[6].strip());
                //Mascota(String nombre, Especie especie, LocalDate fechaNacimiento, String foto, int codigo, String raza,DuenoMascota dueno)
                Mascota m = new Mascota(nombre,e,nacimiento,foto,codigo,raza,dueno);
                contador=codigo+1;            //sirve para que despues de leer el archivo, se puedan crear nuevos objetos id 
                


                mascotas.add(m);
        
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mascotas;
    }
    public static ArrayList<String> cargarImagener(String ruta ){

        ArrayList<String> fotos = new ArrayList<>();
    
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line = br.readLine();
                while (line != null) {
                    fotos.add(line.strip());
                    line = br.readLine();
                    }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return fotos;
    }
    
    
    /*public static String buscadordeFotos(String nombreMascota, ArrayList<String> listaFotos){
        String enviar="Noexiste";
        for(String fotos: listaFotos){
            String lista[] = fotos.split(".");
            if(lista[0].equals(nombreMascota)){
                enviar= fotos;
                }
            }
        return enviar;
    }*/


}
