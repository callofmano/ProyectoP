/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import java.io.IOException;
import javafx.fxml.FXML;
import java.io.IOException;
import com.mycompany.poo2.modelo.Ciudad;
import com.mycompany.poo2.modelo.DuenoMascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
public class CreardController {
    @FXML
    private TextField txtNombre,txtApellidos,txtCi,txtTelefono,txtDireccion,txtEmail;
    @FXML
    private ComboBox <String> comboCiudad;
    @FXML
    private Button botonGuardar,botonRegresar;
    @FXML
    private void switchToAdministrarDuenos() throws IOException {
        App.setRoot("administrard");
    }
    @FXML
    private void initialize(){
        
        ArrayList <String> ciudades = new ArrayList<>();
        for(Ciudad d: Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(d.getNombre());
            }
        comboCiudad.getItems().addAll(ciudades);
        botonRegresar.setVisible(false);
        }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
        }
    @FXML
    private void guardar(ActionEvent event) throws IOException{
    
    botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
        String nombres=txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String ci= txtCi.getText();
        String email= txtEmail.getText();
        String ciudad_comboBox = comboCiudad.getSelectionModel().getSelectedItem();
        Ciudad ciudad = Ciudad.buscarCiudad(Ciudad.generarCiudad(App.pathCiudades), ciudad_comboBox);
        if(ciudad==null){
                System.out.print("Debe crear esta ciudad primero");
            }
        DuenoMascota dueno = new DuenoMascota(ci,nombres,telefono,ciudad,apellidos);

        try{
            FileWriter writer = new FileWriter(App.pathPersonas,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("\n");
            //id,apellidos,nombres,direccion,telefono,ciudad,email
            String linea = ci+","+apellidos+","+nombres+","+","+direccion+","+telefono+","+ciudad_comboBox+","+email;
            bufferedWriter.write(linea);
            bufferedWriter.close();
            botonRegresar.setVisible(true);
            }
        catch(IOException e1){
            e1.printStackTrace();
            }
        });
    }
}
