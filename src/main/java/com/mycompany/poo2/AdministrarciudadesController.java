/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import com.mycompany.poo2.modelo.Ciudad;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

/**
 *
 * @author User
 */
public class AdministrarciudadesController {
    @FXML
    TableView listaCiudades;
    @FXML
    private TableColumn<Ciudad,String> colCodigo;
    @FXML
    private TableColumn<Ciudad,String> colNombre;
    @FXML
    private TableColumn<Ciudad,String> colProvincia;
    @FXML
    private TableColumn<Ciudad,Void> colOpciones;
    @FXML
    private void switchToCrearCiudad() throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearciudad.fxml"));//no tiene el controlador especificado
            CrearciudadController pc = new CrearciudadController();

            fxmlLoader.setController(pc);//se asigna el controlador

            FlowPane root = (FlowPane) fxmlLoader.load();
    
            App.changeRoot(root);

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colProvincia.setCellValueFactory(new  PropertyValueFactory<>("provincia"));
    agregarOpciones();
  
    listaCiudades.getItems().setAll(Ciudad.generarCiudad(App.pathCiudades));
    }
   
    public void switchToEdit(Ciudad ciudad) throws IOException{
        //App.setRoot("primary");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearciudad.fxml"));//no tiene el controlador especificado
            CrearciudadController pc = new CrearciudadController();

            fxmlLoader.setController(pc);//se asigna el controlador

            FlowPane root = (FlowPane) fxmlLoader.load();
            
            pc.llenarCampos(ciudad);
            App.changeRoot(root);

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }

    public void eliminar(Ciudad ciudad){
        ArrayList<Ciudad> lista= new ArrayList<>();
        lista=  Ciudad.generarCiudad(App.pathCiudades);
        /*if(lista.contains(mas)){
            lista.remove(mas);
        }*/
        Ciudad eliminable =null;
        for(Ciudad ciu : lista){
            if(ciu.getCodigo().equals(ciudad.getCodigo())){
                eliminable = ciu;
            }
        }
        lista.remove(eliminable);
        
        try {
            FileWriter writer = new FileWriter(App.pathCiudades);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("id;ciudad;provincia");
            for(Ciudad c:lista ){
                bufferedWriter.write("\n");
                String linea = c.getCodigo() + "," + c.getNombre()+","+c.getProvincia();
                bufferedWriter.write(linea);
                System.out.print(linea);
                
        }
        bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                
        
       }

    //crear botones dinamicos 
    private void agregarOpciones() {

        Callback<TableColumn<Ciudad, Void>, TableCell<Ciudad, Void>> cellFactory = new Callback<TableColumn<Ciudad, Void>, TableCell<Ciudad, Void>>() {
            @Override
            public TableCell<Ciudad, Void> call(final TableColumn<Ciudad, Void> param) {
                TableCell<Ciudad, Void> cell = new TableCell<Ciudad, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el Mascota de la fila
                            Ciudad ciudad = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            //System.out.print(mas.toString());
                            btnEd.setOnAction(e ->{
                                try {
                                    switchToEdit(ciudad);
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            });
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            btnEl.setOnAction(e ->{

                                //eliminar(mas);
                                Alert alert =  new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Eliminar \"" + "\"?");
                                alert.setHeaderText("Eliminar ciudad \"" +  "\"?");
                                alert.setContentText("Seguro desea eliminar esta ciudad:"+ciudad+ "?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if(!result.isPresent() || result.get() != ButtonType.OK) {
                                    try {
                                        switchToMenuPrincipal();
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                } else {
                                    eliminar(ciudad);
                                    try {
                                        switchToMenuPrincipal();
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    
                                }
                                
                            });
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };

        colOpciones.setCellFactory(cellFactory);

    }
}
