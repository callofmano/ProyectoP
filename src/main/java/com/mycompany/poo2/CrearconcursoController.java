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
import java.time.LocalDate;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author User
 */
public class CrearconcursoController {
    public static ArrayList<Premio> premiosActuales;
    public static boolean isEditing;
    @FXML ComboBox<Especie> cmbEspecie;
    @FXML ComboBox<Ciudad> cmbCiudad;
    @FXML ComboBox<Auspiciante> cmbAuspiciantes;
    @FXML TextField txtNombre, txtHora, txtLugar ;
    @FXML TableView listaPremios;
    @FXML private TableColumn<Premio,String>colDescripcion;
    @FXML private TableColumn<Premio,Posicion> colPos;
    @FXML private TableColumn<Premio,String>colAuspiciante ;
    @FXML Button botonGuardar, botonCancelar,botonPremio;
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
                //alert.showAndWait();
                

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
}
