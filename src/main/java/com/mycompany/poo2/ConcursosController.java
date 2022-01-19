/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.poo2;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import com.mycompany.poo2.modelo.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ConcursosController {
    @FXML TableView listaConcursos;
    @FXML private TableColumn<Concurso,String> colCodigo;
    @FXML private TableColumn<Concurso,String> colNombre;
    @FXML private TableColumn<Concurso,String> colFecha;
    @FXML private TableColumn<Concurso,String> colCiudad;


@FXML
private void initialize (){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colFecha.setCellValueFactory(new  PropertyValueFactory<>("fecha"));
    colCiudad.setCellValueFactory(new  PropertyValueFactory<>("ciudad"));
    listaConcursos.getItems().setAll(Concurso.cargarConcursos(App.pathConcursos));
    }

    @FXML
    private void switchToCrearConcursos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearconcurso.fxml"));//no tiene el controlador especificado
            CrearconcursoController apre = new CrearconcursoController();

            fxmlLoader.setController(apre);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            
            

            App.changeRoot(root);
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }

}
