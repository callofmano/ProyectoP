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
    @FXML TextField txtDescripcion,txtDescripcion1,txtDescripcion2;
    @FXML ComboBox<String> cmbAuspiciante,cmbAuspiciante1,cmbAuspiciante2;
    @FXML Button botonGuardar, botonCancelar,botonRegresar;
//int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 }; 
    

@FXML private void initialize(){
    ArrayList <String> auspiciante = new ArrayList<>();
    for (Auspiciante a: Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
        auspiciante.add(a.getEmail());
        }
    Posicion posicionArray[] = new Posicion[] {Posicion.valueOf("PRIMERO"),Posicion.valueOf("SEGUNDO"),Posicion.valueOf("TERCERO")};
    cmbAuspiciante.getItems().addAll(auspiciante);
    cmbAuspiciante1.getItems().addAll(auspiciante);
    cmbAuspiciante2.getItems().addAll(auspiciante);


    botonRegresar.setVisible(false);
    botonGuardar.setOnAction(event-> {
        guardar(event);
    });
    }


    @FXML
    private void guardar(ActionEvent event){

        botonGuardar.setOnMouseClicked((MouseEvent ev) ->{

            String auspiciante = cmbAuspiciante.getSelectionModel().getSelectedItem();
            Auspiciante ausSelected=null;
            for(Auspiciante a: Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
                if(a.getEmail().equals(auspiciante)){
                    ausSelected=a;
                }
            }
            String descripcion = txtDescripcion.getText();
            //Premio premio = new Premio(posicion,descripcion,ausSelected);
            Premio nuevopremio = new Premio(Posicion.valueOf("PRIMERO"),descripcion,ausSelected);
            System.out.println(nuevopremio.toString());

            ArrayList<Premio> premios = new ArrayList<>();
            premios.add(nuevopremio);
//PARA 2DO LUGAR
            auspiciante = cmbAuspiciante1.getSelectionModel().getSelectedItem();
            ausSelected=null;
            for(Auspiciante a: Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
                if(a.getEmail().equals(auspiciante)){
                    ausSelected=a;
                }
            }
            descripcion = txtDescripcion1.getText();
            Premio nuevopremio2 = new Premio(Posicion.valueOf("SEGUNDO"),descripcion,ausSelected);
            premios.add(nuevopremio2);

            //para 3er lugar 

            auspiciante = cmbAuspiciante2.getSelectionModel().getSelectedItem();
            ausSelected=null;
            for(Auspiciante a: Auspiciante.cargarAuspiciantes(App.pathAuspiciantes)){
                if(a.getEmail().equals(auspiciante)){
                    ausSelected=a;
                }
            }
            descripcion = txtDescripcion2.getText();
            Premio nuevopremio3 = new Premio(Posicion.valueOf("TERCERO"),descripcion,ausSelected);
            premios.add(nuevopremio3);

            try {
                regresarCrear(premios);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });
    }



    private void regresarCrear(ArrayList<Premio> premios ) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearconcurso.fxml"));//no tiene el controlador especificado
            CrearconcursoController apre = new CrearconcursoController();

            fxmlLoader.setController(apre);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            apre.setTablaPremios(premios);

            App.changeRoot(root);

    }


    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }



}
