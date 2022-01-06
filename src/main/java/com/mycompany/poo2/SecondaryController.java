package com.mycompany.poo2;
import com.mycompany.poo2.modelo.Mascota;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.uitl.ArrayList;
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
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colTipo.setCellValueFactory(new  PropertyValueFactory<>("especie"));
    colDueno.setCellValueFactory(new  PropertyValueFactory<>("dueno"));
    
    listaMascotas.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));
    }
    
}