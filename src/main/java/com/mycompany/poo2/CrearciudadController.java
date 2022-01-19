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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author User
 */
public class CrearciudadController {
    @FXML
    Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtcodigo;
    @FXML
    private ComboBox <String> comboProvincia;
    @FXML
    private Button botonGuardar,botonRegresar; 
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
        botonRegresar.setVisible(false);
        alert.setHeaderText("Esto es un dialogo de información");
        alert.setContentText("Usted ha creado una ciudad");
        }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
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
            botonRegresar.setVisible(true);
            alert.showAndWait();
            }
        catch(IOException e1){
            e1.printStackTrace();
            }
        });
    }
    
    @FXML
    private void guardar(ActionEvent event,Ciudad ciudad){
        if(ciudad==null){
            botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
                String nombre = txtNombre.getText();
                int codigo = Integer.valueOf(txtcodigo.getText());
                String provincia = (String) comboProvincia.getSelectionModel().getSelectedItem();

                Ciudad ciu = new Ciudad(codigo,nombre,provincia);
                try{
                    FileWriter writer = new FileWriter(App.pathCiudades,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("\n");
                    String linea = ciu.getCodigo() + "," + ciu.getNombre()+","+ciu.getProvincia();
                    bufferedWriter.write(linea);
                    System.out.print(linea);
                    bufferedWriter.close();
                    alert.showAndWait();
                    botonRegresar.setVisible(true);

                    }
        catch(IOException er){
            er.printStackTrace();
        }
        });
        }else{
            //System.out.println("prueba de EDITAR //////////////////////////////--------");
            
            //codigo para guardar lo editado en una variable
            String nombre = txtNombre.getText();
            int codigo = Integer.valueOf(txtcodigo.getText());
            String provincia = (String) comboProvincia.getSelectionModel().getSelectedItem();

            Ciudad ciu = new Ciudad(codigo,nombre,provincia);
                
                ArrayList<Ciudad> lista= Ciudad.generarCiudad(App.pathCiudades);
                Boolean aux= false;
                Ciudad editable= null;
                for(Ciudad ciud : lista){
                    if(ciud.getCodigo().equals(ciudad.getCodigo())){
                        aux=true;
                        editable=ciud;
                    }
                }
                if(aux){
                    
                    lista.add(lista.indexOf(editable),ciu);
                    lista.remove(editable);
                }

                try {
                    FileWriter writer = new FileWriter(App.pathCiudades);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("id,apellidos,nombres,direccion,telefono,ciudad,email,,,,,,,");
                    for(Ciudad d:lista ){
                        bufferedWriter.write("\n");
                        String linea = ciu.getCodigo() + "," + ciu.getNombre()+","+ciu.getProvincia();
                        bufferedWriter.write(linea);
                        System.out.print(linea);
                        
                }
                bufferedWriter.close();
                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }

                System.out.println(lista);
                alert.showAndWait();
                    botonRegresar.setVisible(true);
        }
    }

    public void llenarCampos(Ciudad ciudad){
        txtNombre.setText(ciudad.getNombre());
        txtcodigo.setText(ciudad.getCodigo());
        ArrayList <String> provincias = new ArrayList<>();
        for (Ciudad c:Ciudad.generarCiudad(App.pathCiudades)){
            provincias.add(c.getProvincia());
        }
        comboProvincia.getItems().addAll(provincias);
        comboProvincia.getSelectionModel().select(provincias.indexOf(ciudad.getProvincia()));

        botonGuardar.setOnAction(event-> {
            guardar(event,ciudad);
        });
    }
}
