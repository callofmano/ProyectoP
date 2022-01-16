/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;
import java.io.IOException;
import javafx.fxml.FXML;
import java.io.IOException;

import com.mycompany.poo2.modelo.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
/**
 *
 * @author User
 */
public class CrearauspiciantesController {
    @FXML
    private TextField txtNombre,txtDireccion,txtTelefono,txtEmail,txtWebPage,txtApellidos;
    @FXML
    private Button botonGuardar;
    @FXML
    private ComboBox <String>comboCiudad;
    @FXML
    private void switchToAdministrarAuspiciantes() throws IOException {
        App.setRoot("administrara");
    }

    @FXML
    private void initialize(){
        ArrayList <String> ciudades = new ArrayList<>();
        for(Ciudad d: Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(d.getNombre());
            }
        comboCiudad.getItems().addAll(ciudades);
    }

    @FXML

    private void guardar(ActionEvent event) throws IOException{
        botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String  email = txtEmail.getText();
        String webPage = txtWebPage.getText();
        String ciudad_comboBox = comboCiudad.getSelectionModel().getSelectedItem();
        Ciudad ciudad = Ciudad.buscarCiudad(Ciudad.generarCiudad(App.pathCiudades), ciudad_comboBox);
        if(ciudad==null){
            System.out.print("Debe crear esta ciudad primero");
        }
        Auspiciante auspiciante = new Auspiciante(email,webPage,nombre,telefono,ciudad,apellidos);

        try{
            FileWriter writer = new FileWriter("archivos/auspiciantes",true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("\n");
        }
        catch( IOException e1){
            e1.printStackTrace();
        }


        });
    }
}
