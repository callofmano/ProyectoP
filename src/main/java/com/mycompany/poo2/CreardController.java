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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
public class CreardController {
    @FXML
    Alert alert = new Alert(AlertType.INFORMATION);
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
        alert.setHeaderText("Esto es un dialogo de información");
        alert.setContentText("Usted ha creado un dueño");
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
        DuenoMascota dueno = new DuenoMascota(ci,nombres,telefono,email,ciudad,apellidos, direccion);

        try{
            FileWriter writer = new FileWriter(App.pathPersonas,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("\n");
            //id,apellidos,nombres,direccion,telefono,ciudad,email
            String linea = ci+","+apellidos+","+nombres+","+","+direccion+","+telefono+","+ciudad_comboBox+","+email;
            bufferedWriter.write(linea);
            bufferedWriter.close();
            alert.showAndWait();
            botonRegresar.setVisible(true);
            }
        catch(IOException e1){
            e1.printStackTrace();
            }
        });
    }

    @FXML
    private void guardar(ActionEvent event,DuenoMascota dueno){
        if(dueno==null){
            botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
                String nombre = txtNombre.getText();
                String apellido = txtApellidos.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String ci = txtCi.getText();
                String email = txtEmail.getText();
                Ciudad ciudad = null;
                for(Ciudad c: Ciudad.generarCiudad(App.pathCiudades)){
                    if(comboCiudad.getSelectionModel().getSelectedItem().equals(c.getNombre())){
                        ciudad = c;
                    }
                }
                DuenoMascota due = new DuenoMascota(ci, nombre, telefono, email, ciudad, apellido, direccion);
                try{
                    FileWriter writer = new FileWriter(App.pathPersonas,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("\n");
                    String linea = due.getCi() + "," + due.getApellido()+","+due.getNombre()+","+due.getDireccion()+","+due.getCiudad().getNombre()+","+due.getEmail();
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
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String ci = txtCi.getText();
            String email = txtEmail.getText();
            Ciudad ciudad = null;
            for(Ciudad c: Ciudad.generarCiudad(App.pathCiudades)){
                if(comboCiudad.getSelectionModel().getSelectedItem().equals(c.getNombre())){
                    ciudad = c;
                }
            }
            DuenoMascota due = new DuenoMascota(ci, nombre, telefono, email, ciudad, apellido, direccion);
                
                ArrayList<DuenoMascota> lista= DuenoMascota.cargarDuenos(App.pathPersonas);
                Boolean aux= false;
                DuenoMascota editable= null;
                for(DuenoMascota duen : lista){
                    if(duen.getCi().equals(dueno.getCi())){
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
                    bufferedWriter.write("id,apellidos,nombres,direccion,telefono,ciudad,email,,,,,,,");
                    for(DuenoMascota d:lista ){
                        bufferedWriter.write("\n");
                        String linea = d.getCi() + "," + d.getApellido()+","+d.getNombre()+","+d.getDireccion()+","+d.getTelefono()+","+d.getCiudad().getNombre()+","+d.getEmail();
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


    public void llenarCampos(DuenoMascota dueno){
        txtNombre.setText(dueno.getNombre());
        txtApellidos.setText(dueno.getApellido());
        txtCi.setText(dueno.getCi());
        txtDireccion.setText(dueno.getDireccion());
        txtTelefono.setText(dueno.getTelefono());
        txtEmail.setText(dueno.getEmail());
        ArrayList <String> ciudades = new ArrayList<>();
        for (Ciudad c:Ciudad.generarCiudad(App.pathCiudades)){
            ciudades.add(c.getNombre());
        }
        comboCiudad.getItems().addAll(ciudades);
        comboCiudad.getSelectionModel().select(ciudades.indexOf(dueno.getCiudad().getNombre()));

        botonGuardar.setOnAction(event-> {
            guardar(event,dueno);
        });
    }
}
