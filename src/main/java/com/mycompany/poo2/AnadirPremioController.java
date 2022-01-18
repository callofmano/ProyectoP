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
import javafx.scene.input.MouseEvent;
/**
 *
 * @author User
 */
public class AnadirPremioController {
    @FXML
    Alert alert = new Alert(AlertType.INFORMATION);
    @FXML TextField txtDescripcion;
    @FXML ComboBox<Auspiciante> cmbAuspiciante;
    @FXML ComboBox<Posicion> cmbPosicion; 
    @FXML Button botonGuardar, botonCancelar,botonRegresar;
//int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 }; 
    

@FXML private void initialize(){
    ArrayList <Auspiciante> auspiciante = new ArrayList<>();
    for (Auspiciante a: Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
        auspiciante.add(a);
        }
    Posicion posicionArray[] = new Posicion[] {Posicion.valueOf("PRIMERO"),Posicion.valueOf("SEGUNDO"),Posicion.valueOf("TERCERO")};
    cmbAuspiciante.getItems().addAll(auspiciante);
    cmbPosicion.getItems().addAll(posicionArray);
    alert.setTitle("Dialogo de información");
    alert.setHeaderText("Esto es un dialogo de información");
    alert.setContentText("Usted ha creado un premio");
    botonRegresar.setVisible(false);

    }


    @FXML
    private void guardar(ActionEvent event){

        botonGuardar.setOnMouseClicked((MouseEvent ev) ->{

            Posicion posicion = cmbPosicion.getSelectionModel().getSelectedItem();
            Auspiciante auspiciante = cmbAuspiciante.getSelectionModel().getSelectedItem();
            String descripcion = txtDescripcion.getText();
            Premio premio = new Premio(posicion,descripcion,auspiciante);
            try{
                FileWriter writer = new FileWriter(App.pathPremios,true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                
                String linea = posicion.toString()+","+auspiciante.getNombre()+","+descripcion;
                bufferedWriter.write(linea);
                bufferedWriter.write("\n");
                bufferedWriter.close();
                alert.showAndWait();
                botonRegresar.setVisible(true);


            }catch(IOException e){
                e.printStackTrace();
            }






        });
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }



}
