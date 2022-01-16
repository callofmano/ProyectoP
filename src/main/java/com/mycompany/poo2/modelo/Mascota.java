package com.mycompany.poo2.modelo;

import com.mycompany.poo2.App;
import com.mycompany.poo2.PrimaryController;
import com.mycompany.poo2.SecondaryController;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Mascota {

    private static int contador = 1;

    private String nombre;

    private Especie especie;

    private LocalDate fechaNacimiento;

    private String foto;

    public int codigo;

    private String raza;

    private DuenoMascota dueno;

    public Button button;
    public Button detalle;
    public Button eliminar;

    public Mascota(String nombre, String raza, LocalDate fechaNacimiento, String foto, Especie especie, DuenoMascota dueno) {   //ESTE CONSTRUCTOR SIRVE PARA CREAR
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.codigo = contador++;
        this.especie = especie;
        this.dueno = dueno;
        this.button = new Button("Editar");
        this.detalle = new Button("Detalle");
        this.eliminar = new Button("Eliminar");

        /*detalle.setOnAction((ActionEvent ev)->{
            TextInputDialog dialogo1 = new TextInputDialog();
            dialogo1.setTitle("PROBANDO123 FUNCIONANDO ****");
        });*/
        setteoBotones();   //carga los botones y su funcionalidad
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
        this.button = new Button("Editar");
        this.detalle = new Button("Detalle");
        this.eliminar = new Button("Eliminar");
        setteoBotones();
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


    


    public Button getDetalle() {
        return detalle;
    }

    public void setDetalle(Button detalle) {
        this.detalle = detalle;
    }

    public Button getEliminar() {
        return eliminar;
    }

    public void setEliminar(Button eliminar) {
        this.eliminar = eliminar;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Mascota{ codigo " + codigo + "nombre=" + nombre + ", dueno=" + dueno + '}';
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
               
                String linea2[] = datos[4].split("/");
                LocalDate nacimiento = LocalDate.of(Integer.valueOf(linea2[2].strip()), Integer.valueOf(linea2[1].strip()), Integer.valueOf(linea2[0].strip()));
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

    public void setteoBotones(){
        button.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                System.out.println(codigo+","+nombre);

                SecondaryController sc = new SecondaryController();

                    try {
                        sc.switchToEdit();                  //con esto se carga la pantalla de crear mascota, que será reutilizada para editar 
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }   
            }
            
        });


        detalle.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                System.out.println(codigo+","+nombre);

                SecondaryController sc = new SecondaryController();

                    try {
                        sc.switchToDetalleMascota();                  //con esto se carga la pantalla de detalle mascota
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }   
            }
            
        });

        eliminar.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                System.out.println(codigo+","+nombre);

                SecondaryController sc = new SecondaryController();

                    //TODO AÑADIR PANTALLA PARA ELIMINAR   
            }
            
        });

    }

}
