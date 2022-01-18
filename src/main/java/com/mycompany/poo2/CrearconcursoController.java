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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.control.Alert.AlertType;
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
    @FXML ComboBox<Especie> cmbEspecie;
    @FXML ComboBox<Ciudad> cmbCiudad;
    @FXML TextField txtNombre, txtHora, txtLugar ;
    @FXML TableView listaPremios;
    @FXML private TableColumn<Premio,String>colDescripcion;
    @FXML private TableColumn<Premio,Posicion> colPos;
    @FXML private TableColumn<Premio,Auspiciante>colAuspiciante ;
    @FXML Button botonGuardar, botonCancelar;
    @FXML DatePicker fechaActual,fechaInscripcion,fechaCierre;


    @FXML
    private void switchToAdministarConcursos() throws IOException {
        App.setRoot("concursos");
    }
    @FXML
    private void switchToAnadirPremio() throws IOException {
        App.setRoot("anadirPremio");
    }

    @FXML
    private void initialize(){
        ArrayList <Ciudad> ciudades = new ArrayList<>();
        for(Ciudad d: Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(d);
            }
        Especie especieArray[] = new Especie[] {Especie.valueOf("PERRO"),Especie.valueOf("GATO")};
        cmbCiudad.getItems().addAll(ciudades);
        cmbEspecie.getItems().addAll(especieArray);
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
        String lugar = txtHora.getText();
        Ciudad ciudad = cmbCiudad.getSelectionModel().getSelectedItem();
        Especie dirigiadoA = cmbEspecie.getSelectionModel().getSelectedItem();
        ArrayList <Premio> premios = Premio.cargarPremios(App.pathPremios);

        });
        }


}
