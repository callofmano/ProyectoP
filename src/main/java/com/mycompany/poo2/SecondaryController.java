package com.mycompany.poo2;
import com.mycompany.poo2.modelo.Mascota;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

public class SecondaryController {
    @FXML
    TableView listaMascotas;
    @FXML
    private TableColumn<Mascota,String> colCodigo;
    @FXML
    private TableColumn<Mascota,String> colNombre;
    @FXML
    private TableColumn<Mascota,String> colTipo;
    @FXML
    private TableColumn<Mascota,String> colDueno;
    @FXML
    private TableColumn<Mascota,String> colAcciones;
    @FXML
    private TableColumn<Mascota,String> colDetalle;
    @FXML
    private TableColumn<Mascota,String> colEliminar;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
    public void switchToEdit() throws IOException{

        App.setRoot("primary");
        
    }
    public void switchToDetalleMascota() throws IOException{

        App.setRoot("detalleMascota");
        
    }

    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colTipo.setCellValueFactory(new  PropertyValueFactory<>("especie"));
    colDueno.setCellValueFactory(new  PropertyValueFactory<>("dueno"));
    colAcciones.setCellValueFactory(
        new PropertyValueFactory<Mascota,String>("button")
    );
    colDetalle.setCellValueFactory(
        new PropertyValueFactory<Mascota,String>("detalle")
    );
    colEliminar.setCellValueFactory(
        new PropertyValueFactory<Mascota,String>("eliminar")
    );
    listaMascotas.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));
    }
}