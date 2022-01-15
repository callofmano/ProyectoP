/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import java.io.IOException;
import java.util.*;
import java.io.*;
import com.mycompany.poo2.modelo.Ciudad;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import javafx.fxml.FXML;
import com.mycompany.poo2.modelo.Ciudad;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class CrearciudadController {
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox <String> comboProvincia;
    @FXML
    private Button botonGuardar; 
    @FXML 
    private void switchToAdministarCiudades() throws IOException {
        App.setRoot("administrarciudades");
    }
    @FXML
    private void initialize(){
        
        ArrayList <String> ciudades = new ArrayList<>();
        for(Ciudad d: Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(d.getProvincia());
            }
        comboProvincia.getItems().addAll(ciudades);
        
        }
    
    @FXML
    private void guardar(ActionEvent event){
    
    botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
        
        String nombreCiudad = txtNombre.getText();
        String provincia = comboProvincia.getSelectionModel().getSelectedItem();
        Ciudad ciudad = new Ciudad(nombreCiudad,provincia);
        try{
            FileWriter writer = new FileWriter(App.pathCiudades,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("\n");
            String linea = ciudad.getCodigo()+","+ciudad.getNombre()+","+ciudad.getProvincia();
            bufferedWriter.write(linea);
            bufferedWriter.close();
            }
        catch(IOException e1){
            e1.printStackTrace();
            }
        });
    }
    
}
