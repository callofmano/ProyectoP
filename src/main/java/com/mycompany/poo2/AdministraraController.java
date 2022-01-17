/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import java.io.IOException;
import javafx.fxml.FXML;
import com.mycompany.poo2.modelo.Auspiciante;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
/**
 *
 * @author User
 */
public class AdministraraController {
    @FXML
    TableView listaAuspiciantes;
    @FXML
    private TableColumn<Auspiciante,String> colCodigo;
    @FXML
    private TableColumn<Auspiciante,String> colNombre;
    @FXML
    private TableColumn<Auspiciante,String> colTelefono;
    @FXML
    private TableColumn<Auspiciante,String> colCiudad;

    @FXML
    private void switchToCrearAuspiciante() throws IOException {
        App.setRoot("crearauspiciantes");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
    @FXML
    private void initialize(){
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        listaAuspiciantes.getItems().setAll(Auspiciante.cargarAuspiciantes(App.pathAuspiciantes));
        }
}
