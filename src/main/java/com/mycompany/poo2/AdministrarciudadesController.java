/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import com.mycompany.poo2.modelo.Ciudad;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

/**
 *
 * @author User
 */
public class AdministrarciudadesController {
    @FXML
    TableView listaCiudades;
    @FXML
    private TableColumn<Ciudad,String> colCodigo;
    @FXML
    private TableColumn<Ciudad,String> colNombre;
    @FXML
    private TableColumn<Ciudad,String> colProvincia;
    @FXML
    private void switchToCrearCiudad() throws IOException {
        App.setRoot("crearciudad");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colProvincia.setCellValueFactory(new  PropertyValueFactory<>("provincia"));
  
    listaCiudades.getItems().setAll(Ciudad.generarCiudad(App.pathCiudades));
    }
   
}
