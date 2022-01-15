/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;
import com.mycompany.poo2.modelo.DuenoMascota;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class AdministrardController {
    @FXML
    TableView listaDuenos;
    @FXML
    private TableColumn<DuenoMascota,String> colCodigo;
    @FXML
    private TableColumn<DuenoMascota,String> colNombre;
    @FXML
    private TableColumn<DuenoMascota,String> colApellidos;
    @FXML
    private TableColumn<DuenoMascota,String> colTelefono;
    @FXML
    private TableColumn<DuenoMascota,String> colCiudad;
    @FXML
    private void switchToCreard() throws IOException {
        App.setRoot("creard");
    }

    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("ci"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colApellidos.setCellValueFactory(new  PropertyValueFactory<>("apellido"));
    colTelefono.setCellValueFactory(new  PropertyValueFactory<>("telefono"));
    colCiudad.setCellValueFactory(new  PropertyValueFactory<>("ciudad"));
    listaDuenos.getItems().setAll(DuenoMascota.cargarDuenos(App.pathPersonas));
    }

}
