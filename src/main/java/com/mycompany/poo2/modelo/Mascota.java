package com.mycompany.poo2.modelo;
import com.mycompany.poo2.App;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class Mascota {
    
    private static int contador=1;
    
    private String nombre;
    
    private Especie especie;
    
    private LocalDate fechaNacimiento;
    
    private String foto;
    
    public int codigo;
    
    private String raza;

    private DuenoMascota dueno;

    public Mascota(String nombre, String raza, LocalDate fechaNacimiento, String foto,int codigo, Especie especie, DuenoMascota dueno) {
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.codigo = codigo;
        this.especie = especie;
        this.dueno = dueno;
    }

    public String getNombre(){
        return nombre;
    }
    
    public int getCodigo(){
        return codigo;
    }

    public DuenoMascota getDueno(){
        return dueno;
    }
    
    public Especie getEspecie(){
        return especie;
    }
    
    
    
    @Override
    public String toString() {
        return "Mascota{ codigo "+ codigo+ "nombre=" + nombre +", dueno=" + dueno + '}';
    }
    
    public static ArrayList<Mascota> cargarMascotas(String ruta){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new InputStreamReader(input)))
        {
            int intentos =0;
            String line = br.readLine();
            while (line != null){
                if(intentos>=1){
                    String[] datos = line.split(";");
                    String nombre = datos[1].strip();
                    int codigo = Integer.valueOf(datos[0].strip());
                    Especie e = Especie.valueOf(datos[2].strip());
                    String linea2 [] = datos[4].split("-");
                    LocalDate nacimiento = LocalDate.of(Integer.valueOf(linea2[0].strip()), Integer.valueOf(linea2[1].strip()), Integer.valueOf(linea2[2].strip()));
                    String foto = datos[5].strip();
                    String raza = datos[3].strip();
                    DuenoMascota dueno = DuenoMascota.buscarDueno(DuenoMascota.cargarDuenos(App.pathPersonas), datos[6].strip());
                    Mascota m = new Mascota(nombre,raza,nacimiento,foto,codigo,e,dueno);
                    mascotas.add(m);
                }
                intentos++;
                    }
            }    catch(IOException e){
                e.printStackTrace();}
        System.out.print(mascotas);
        return mascotas;
        }
    

    
    
    
    
    
}
