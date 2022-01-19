/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;
import com.mycompany.poo2.modelo.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author User
 */
public class CrearconcursoController {
    @FXML Alert alert = new Alert(AlertType.INFORMATION);
    public static ArrayList<Premio> premiosActuales;
    public static boolean isEditing;
    @FXML ComboBox<Especie> cmbEspecie;
    @FXML Alert alert = new Alert(AlertType.INFORMATION);
    @FXML ComboBox<Ciudad> cmbCiudad;
    @FXML ComboBox<Auspiciante> cmbAuspiciantes;
    @FXML TextField txtNombre, txtHora, txtLugar ;
    @FXML Label lbltitulo;
    @FXML TableView listaPremios;
    @FXML private TableColumn<Premio,String>colDescripcion;
    @FXML private TableColumn<Premio,Posicion> colPos;
    @FXML private TableColumn<Premio,String>colAuspiciante ;
<<<<<<< HEAD
    @FXML Button botonGuardar, botonCancelar,botonPremio,botonRegresar;
=======
    @FXML Button botonGuardar, botonCancelar,botonPremio, botonRegresar;
>>>>>>> 3ba1956079290f25cf70ab7579ebb5705bbcd557
    @FXML DatePicker fechaActual,fechaInscripcion,fechaCierre;
    @FXML HBox hboxPremio;

    

    @FXML
    private void switchToAdministarConcursos() throws IOException {
        App.setRoot("concursos");
    }
    @FXML
    private void switchToAnadirPremio() throws IOException {
        //App.setRoot("anadirPremio");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("anadirPremio.fxml"));//no tiene el controlador especificado
            AnadirPremioController apre = new AnadirPremioController();

            fxmlLoader.setController(apre);//se asigna el controlador

            FlowPane root = (FlowPane) fxmlLoader.load();
            
            

            App.changeRoot(root);

    }

       

    @FXML
    private void initialize(){
        ArrayList <Ciudad> ciudades = new ArrayList<>();
        for(Ciudad d: Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(d);
            }
        Especie especieArray[] = new Especie[] {Especie.valueOf("PERRO"),Especie.valueOf("GATO"),Especie.valueOf("TODOS")};
        cmbCiudad.getItems().addAll(ciudades);
        cmbEspecie.getItems().addAll(especieArray);

        ArrayList<Auspiciante> auspiciantes = new ArrayList<>();
        for(Auspiciante aus:Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
            auspiciantes.add(aus);
        }
        cmbAuspiciantes.getItems().addAll(auspiciantes);
<<<<<<< HEAD
        alert.setTitle("Dialogo de información");
        alert.setHeaderText("Esto es un dialogo de información");
        alert.setContentText("Usted ha creado un concurso");
        botonRegresar.setVisible(false);
=======
        botonRegresar.setVisible(false);
    
>>>>>>> 3ba1956079290f25cf70ab7579ebb5705bbcd557
        
        

        botonPremio.setOnAction(event-> {
            try {
                switchToAnadirPremio();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        
        //setTablaPremios();
        
        /*
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("pos"));
        colAuspiciante.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colPos.setCellValueFactory(new PropertyValueFactory<>("auspiciante"));
        listaPremios.getItems().setAll(Premio.cargarPremios(App.pathPremios));
        */
    }

    

    @FXML
    private void guardar(ActionEvent event){

        botonGuardar.setOnMouseClicked((MouseEvent ev) -> { 
        String nombre = txtNombre.getText();
        LocalDate fechaA = fechaActual.getValue();
        System.out.println(fechaA);
        LocalDate fechaI = fechaInscripcion.getValue();
        LocalDate fechaC = fechaCierre.getValue();
        String hora = txtHora.getText();
        String lugar = txtLugar.getText();
        Ciudad ciudad = cmbCiudad.getSelectionModel().getSelectedItem();
        Especie dirigiadoA = cmbEspecie.getSelectionModel().getSelectedItem();

        //public Concurso(String nombre, LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios) {
            ArrayList<Premio> premios = new ArrayList<>();
            
            //creando el objeto

            Concurso conc = new Concurso(nombre, dirigiadoA, fechaA, hora, fechaI, fechaC, ciudad, lugar, premios);
            System.out.println(conc);



            //escribiendo en el doc 
            try{
                FileWriter writer = new FileWriter(App.pathConcursos,true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("\n");
                //codigo;nombre;fecha;hora;fechainsc;fechacierre;ciudad;lugar;(inscritos)1-2-3-4-5;stringsdepremios;(ganadores)2-4-7
                String linea = conc.codigo+";"+nombre+";"+dirigiadoA.toString()+";"+fechaA.toString()+";"+hora+";"+fechaI.toString()+";"+fechaC.toString()+";"+ciudad.getCodigo()+";"+lugar+";"+";"+listaPremios.getItems().toString()+";";
                bufferedWriter.write(linea);
                System.out.print(linea);
                bufferedWriter.close();
                alert.showAndWait();
                botonRegresar.setVisible(true);
                }
    catch(IOException er){
        er.printStackTrace();
    }


        });
        }

        public void setTablaPremios(ArrayList<Premio> p){
            colPos.setCellValueFactory(
            new PropertyValueFactory<Premio,Posicion>("pos")
        );
        colDescripcion.setCellValueFactory(
            new PropertyValueFactory<Premio,String>("descripcion")
        );
        colAuspiciante.setCellValueFactory(
            new PropertyValueFactory<Premio,String>("auspiciante")
        );
        listaPremios.getItems().setAll(p);
        }

<<<<<<< HEAD

        @FXML
        private void switchToMenuPrincipal() throws IOException{
            App.setRoot("menuprincipal");
        }






=======
        @FXML
    private void guardar(ActionEvent event,Concurso dueno){
        if(dueno==null){
            botonGuardar.setOnMouseClicked((MouseEvent ev) -> { 
                String nombre = txtNombre.getText();
                LocalDate fechaA = fechaActual.getValue();
                System.out.println(fechaA);
                LocalDate fechaI = fechaInscripcion.getValue();
                LocalDate fechaC = fechaCierre.getValue();
                String hora = txtHora.getText();
                String lugar = txtLugar.getText();
                Ciudad ciudad = cmbCiudad.getSelectionModel().getSelectedItem();
                Especie dirigiadoA = cmbEspecie.getSelectionModel().getSelectedItem();
        
                //public Concurso(String nombre, LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios) {
                    ArrayList<Premio> premios = new ArrayList<>();
                    
                    //creando el objeto
        
                    Concurso conc = new Concurso(nombre, dirigiadoA, fechaA, hora, fechaI, fechaC, ciudad, lugar, premios);
                    System.out.println(conc);
        
        
        
                    //escribiendo en el doc 
                    try{
                        FileWriter writer = new FileWriter(App.pathConcursos,true);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        bufferedWriter.write("\n");
                        //codigo;nombre;fecha;hora;fechainsc;fechacierre;ciudad;lugar;(inscritos)1-2-3-4-5;stringsdepremios;(ganadores)2-4-7
                        String linea = conc.codigo+";"+nombre+";"+dirigiadoA.toString()+";"+fechaA.toString()+";"+hora+";"+fechaI.toString()+";"+fechaC.toString()+";"+ciudad.getCodigo()+";"+lugar+";"+";"+listaPremios.getItems().toString()+";";
                        bufferedWriter.write(linea);
                        System.out.print(linea);
                        bufferedWriter.close();
                        //alert.showAndWait();
                        
        
                        }
            catch(IOException er){
                er.printStackTrace();
            }
        });
        }else{
            //System.out.println("prueba de EDITAR //////////////////////////////--------");
            
            //codigo para guardar lo editado en una variable
            String nombre = txtNombre.getText();
                LocalDate fechaA = fechaActual.getValue();
                System.out.println(fechaA);
                LocalDate fechaI = fechaInscripcion.getValue();
                LocalDate fechaC = fechaCierre.getValue();
                String hora = txtHora.getText();
                String lugar = txtLugar.getText();
                Ciudad ciudad = cmbCiudad.getSelectionModel().getSelectedItem();
                Especie dirigiadoA = cmbEspecie.getSelectionModel().getSelectedItem();
        
                //public Concurso(String nombre, LocalDate fecha, String hora, LocalDate fechaInsc, LocalDate fechaCierre, Ciudad ciudad, String lugar, ArrayList<Premio> premios) {
                    ArrayList<Premio> premios = new ArrayList<>();
                    
                    //creando el objeto
        
                    Concurso conc = new Concurso(nombre, dirigiadoA, fechaA, hora, fechaI, fechaC, ciudad, lugar, premios);
                    System.out.println(conc);
                
                ArrayList<Concurso> lista= Concurso.cargarConcursos(App.pathConcursos);
                Boolean aux= false;
                Concurso editable= null;
                for(Concurso duen : lista){
                    if(String.valueOf(duen.getCodigo()).equals(String.valueOf(dueno.getCodigo()))){
                        aux=true;
                        editable=duen;
                    }
                }
                if(aux){
                    
                    lista.add(lista.indexOf(editable),conc);
                    lista.remove(editable);
                }

                try {
                    FileWriter writer = new FileWriter(App.pathConcursos);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("codigo;nombre;especie;fecha;hora;fechainsc;fechacierre;ciudad;lugar;(inscritos)1-2-3-4-5;stringsdepremios;(ganadores)2-4-7");
                    for(Concurso d:lista ){
                        bufferedWriter.write("\n");
                        String linea = d.codigo+";"+nombre+";"+dirigiadoA.toString()+";"+fechaA.toString()+";"+hora+";"+fechaI.toString()+";"+fechaC.toString()+";"+ciudad.getCodigo()+";"+lugar+";"+";"+listaPremios.getItems().toString()+";";
                        bufferedWriter.write(linea);
                        System.out.print(linea);
                        
                }
                bufferedWriter.close();
                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }

                System.out.println(lista);
                    alert.showAndWait();
                    botonRegresar.setVisible(true);
        }
    }

        public void llenarCampos(Concurso con){
            lbltitulo.setText("EDITAR CONCURSO");
            txtNombre.setText(con.getNombre());
            txtHora.setText(con.getHora());
            txtLugar.setText(con.getLugar());
            cmbEspecie.getSelectionModel().select(con.getDirigido());
            fechaActual.setValue(LocalDate.parse(con.getFecha()));
            fechaCierre.setValue(con.getFechaCierre());
            fechaInscripcion.setValue(con.getFechaCierre());
            cmbCiudad.getSelectionModel().select(con.getCiudad());
            /*ArrayList<Auspiciante> auspiciantes = new ArrayList<>();
            for(Auspiciante aus:Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
            auspiciantes.add(aus);
            }
            ArrayList<Premio> premio = con.getPremios();
            cmbAuspiciantes.getSelectionModel().select();*/

            botonGuardar.setOnAction(event-> {
                guardar(event,con);
            });
        }
>>>>>>> 3ba1956079290f25cf70ab7579ebb5705bbcd557
}
