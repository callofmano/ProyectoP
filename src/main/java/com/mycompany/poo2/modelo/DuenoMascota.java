package com.mycompany.poo2.modelo;

import com.mycompany.poo2.App;
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
    
    public String getCi(){
        return ci;
        }
    //AGREGAR ECUALS
    //probando git
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
    public static ArrayList<DuenoMascota> cargarDuenos(String ruta){
        ArrayList<DuenoMascota> duenos = new ArrayList<>();
        int intentos =0;
        InputStream input = Persona.class.getClassLoader().getResourceAsStream(ruta);
        try(BufferedReader br =new BufferedReader(new InputStreamReader(input))){
            String line = br.readLine();
            while (line != null){
                if(intentos>=1){
                    String datos[] = line.split(",");
                    String ci = datos[0].strip();
                    String nombre= datos[2].strip();
                    String telefono =datos[4].strip();
                    Ciudad ciudad = Ciudad.buscarCiudad(Ciudad.generarCiudad(App.pathCiudades),datos[5].strip());
                    DuenoMascota dueno = new DuenoMascota(ci,nombre,telefono,ciudad);
                    duenos.add(dueno);
                    }
                intentos++;
                }
            }catch(IOException e){
                e.printStackTrace();}
        return duenos;
        }
    
       public static DuenoMascota buscarDueno(ArrayList<DuenoMascota> duenos, String id){
          DuenoMascota dueno = null;
          for(DuenoMascota d: duenos){
             if(id.equals(d.getCi())) {
              dueno= d;
                }  
            }
        return dueno;
        }
      
    }




