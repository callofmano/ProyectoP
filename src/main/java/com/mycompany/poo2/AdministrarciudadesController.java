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
    private void switchToCrearCiudad() throws IOException {
        App.setRoot("crearciudad");
    }
    /*
    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colTipo.setCellValueFactory(new  PropertyValueFactory<>("especie"));
    colDueno.setCellValueFactory(new  PropertyValueFactory<>("dueno"));
    
    listaMascotas.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));
    }
   */
}
