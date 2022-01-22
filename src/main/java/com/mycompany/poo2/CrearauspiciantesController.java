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
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
/**
 *
 * @author User
 */
public class CrearauspiciantesController {
    @FXML
    Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private TextField txtNombre,txtTelefono,txtEmail,txtWebPage,txtApellidos;
    @FXML
    private Button botonGuardar,botonRegresar;
    @FXML
    private ComboBox <String>comboCiudad;
    @FXML
    private Label lbltitulo;

    @FXML
    private void switchToAdministrarAuspiciantes() throws IOException {
        App.setRoot("administrara");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
        }
    @FXML
    private void initialize(){
        ArrayList <String> ciudades = new ArrayList<>();
        for(Ciudad d: Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(d.getNombre());
            }
        comboCiudad.getItems().addAll(ciudades);
        botonRegresar.setVisible(false);
        alert.setHeaderText("Esto es un dialogo de información");
        alert.setContentText("Usted ha creado un auspiciante");
    }

    @FXML

    private void guardar(ActionEvent event) throws IOException{
        botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
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
            FileWriter writer = new FileWriter(App.pathAuspiciantes,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("\n");
            String linea = auspiciante.getCodigo()+","+ auspiciante.getNombre()+","+auspiciante.getApellido()+","+auspiciante.getTelefono()+","+auspiciante.getCiudad()+","+auspiciante.getEmail()+","+auspiciante.getWebpage();
            bufferedWriter.write(linea);
            bufferedWriter.close();
            alert.showAndWait();
            botonRegresar.setVisible(true);
        }
        catch( IOException e1){
            e1.printStackTrace();
        }
        });
    }

    @FXML
    private void guardar(ActionEvent event,Auspiciante dueno){
        if(dueno==null){
            botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
                String nombre = txtNombre.getText();
                String apellido = txtApellidos.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();
                Ciudad ciudad = null;
                String webpage = txtWebPage.getText();
                for(Ciudad c: Ciudad.generarCiudad(App.pathCiudades)){
                    if(comboCiudad.getSelectionModel().getSelectedItem().equals(c.getNombre())){
                        ciudad = c;
                    }
                }
                Auspiciante due = new Auspiciante(email, webpage, nombre, telefono, ciudad, apellido);
                try{
                    FileWriter writer = new FileWriter(App.pathPersonas,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("\n");
                    String linea = due.getCodigo() + "," + due.getNombre()+","+due.getApellido()+","+due.getTelefono()+","+due.getCiudad().getNombre()+","+due.getEmail()+","+due.getWebpage();
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
            String apellido = txtApellidos.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String webpage = txtWebPage.getText();
            Ciudad ciudad = null;
            for(Ciudad c: Ciudad.generarCiudad(App.pathCiudades)){
                if(comboCiudad.getSelectionModel().getSelectedItem().equals(c.getNombre())){
                    ciudad = c;
                }
            }
            Auspiciante due = new Auspiciante(email, webpage, nombre, telefono, ciudad, apellido);
                
                ArrayList<Auspiciante> lista= Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);
                Boolean aux= false;
                Auspiciante editable= null;
                for(Auspiciante duen : lista){
                    if(duen.getCodigo().equals(dueno.getCodigo())){
                        aux=true;
                        editable=duen;
                    }
                }
                if(aux){
                    
                    lista.add(lista.indexOf(editable),due);
                    lista.remove(editable);
                }

                try {
                    FileWriter writer = new FileWriter(App.pathPersonas);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    for(Auspiciante d:lista ){
                        bufferedWriter.write("\n");
                        String linea = due.getCodigo() + "," + due.getNombre()+","+due.getApellido()+","+due.getTelefono()+","+due.getCiudad().getNombre()+","+due.getEmail()+","+due.getWebpage();
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

    public void llenarCampos(Auspiciante dueno){
        txtNombre.setText(dueno.getNombre());
        txtApellidos.setText(dueno.getApellido());
        txtTelefono.setText(dueno.getTelefono());
        txtEmail.setText(dueno.getEmail());
        txtWebPage.setText(dueno.getWebpage());
        lbltitulo.setText("EDITAR AUSPICIANTES");
        ArrayList <String> ciudades = new ArrayList<>();
        for (Ciudad c:Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(c.getNombre());
        }
        comboCiudad.getItems().addAll(ciudades);
        comboCiudad.getSelectionModel().select(ciudades.indexOf(dueno.getCiudad()));

        botonGuardar.setOnAction(event-> {
            guardar(event,dueno);
        });
    }
}
