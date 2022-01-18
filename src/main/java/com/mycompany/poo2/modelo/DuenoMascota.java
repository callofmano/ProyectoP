package com.mycompany.poo2.modelo;

import com.mycompany.poo2.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

public class DuenoMascota extends Persona {

    public String ci;
    
    public DuenoMascota(String ci, String nombre, String telefono, Ciudad ciudad, String apellidos) {
        super(nombre, telefono, ciudad,apellidos);
        this.ci = ci;
    }

    @Override
    public String toString() {
        return  super.toString();
    }

    public String getCi() {
        return ci;
    }
    
    public String getNombre(){
       return super.getNombre();
    }
    
    public String getApellido(){
       return super.getApellido();
    }
    
    public String getTelefono(){
        return super.getTelefono();
    }
    
    public Ciudad getCiudad(){
        return super.getCiudad();
    }
    //AGREGAR ECUALS

    /*    
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
     */
    public static ArrayList<DuenoMascota> cargarDuenos(String ruta) {
        ArrayList<DuenoMascota> duenos = new ArrayList<>();
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {

                String datos[] = line.split(",");
                String ci = datos[0].strip();
                String nombre = datos[2].strip();
                String apellido = datos[1].strip();
                String telefono = datos[4].strip();
                Ciudad ciudad = Ciudad.buscarCiudad(Ciudad.generarCiudad(App.pathCiudades), datos[5].strip());
                DuenoMascota dueno = new DuenoMascota(ci, nombre, telefono, ciudad,apellido);
                duenos.add(dueno);

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return duenos;
    }

    public static DuenoMascota buscarDueno(ArrayList<DuenoMascota> duenos, String id) {
        DuenoMascota dueno = null;
        for (DuenoMascota d : duenos) {
            if (id.equals(d.getCi())) {
                dueno = d;
            }
        }
        return dueno;
    }


}
