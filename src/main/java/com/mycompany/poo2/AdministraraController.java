/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.mycompany.poo2.modelo.Auspiciante;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
/**
 *
 * @author User
 */
public class AdministraraController {
    @FXML
    TableView listaAuspiciantes;
    @FXML
    private TableColumn<Auspiciante,String> colCodigo;
    @FXML
    private TableColumn<Auspiciante,String> colNombre;
    @FXML
    private TableColumn<Auspiciante,String> colTelefono;
    @FXML
    private TableColumn<Auspiciante,String> colCiudad;
    @FXML
    private TableColumn<Auspiciante,Void> colAcciones;

    @FXML
    private void switchToCrearAuspiciante() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearauspiciantes.fxml"));//no tiene el controlador especificado
        CrearauspiciantesController pc = new CrearauspiciantesController();
        fxmlLoader.setController(pc);//se asigna el controlador
        FlowPane root = (FlowPane) fxmlLoader.load();
        App.changeRoot(root);
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }

    @FXML
    private void initialize(){
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        agregarOpciones();
        listaAuspiciantes.getItems().setAll(Auspiciante.cargarAuspiciantes(App.pathAuspiciantes));
        }

        public void switchToEdit(Auspiciante m) throws IOException{

            //App.setRoot("primary");
    
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearauspiciantes.fxml"));//no tiene el controlador especificado
                CrearauspiciantesController pc = new CrearauspiciantesController();
    
                fxmlLoader.setController(pc);//se asigna el controlador
    
                FlowPane root = (FlowPane) fxmlLoader.load();
                
                
                pc.llenarCampos(m);
                App.changeRoot(root);
    
            }catch(IOException ex){
                ex.printStackTrace();
            } 
        }

        
            public void eliminar(Auspiciante mas){
                ArrayList<Auspiciante> lista= new ArrayList<>();
                lista=  mas.cargarAuspiciantes(App.pathMascotas);
                /*if(lista.contains(mas)){
                    lista.remove(mas);
                }*/
                Auspiciante eliminable =null;
                for(Auspiciante ms : lista){
                    if(ms.getCodigo().equals(mas.getCodigo())){
                        eliminable = ms;
                    }
                }
                lista.remove(eliminable);
                
                try {
                    FileWriter writer = new FileWriter(App.pathMascotas);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    for(Auspiciante m:lista ){
                        bufferedWriter.write("\n");
                        //String foto = Mascota.buscadordeFotos(m.getNombre(), m.cargarImagener("com/mycompany/poo2/files/"));
                        String linea = m.getCodigo()+","+m.getNombre()+","+m.getApellido()+","+m.getTelefono()+","+m.getCiudad()+","+m.getEmail()+","+m.getWebpage();
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

        Callback<TableColumn<Auspiciante, Void>, TableCell<Auspiciante, Void>> cellFactory = new Callback<TableColumn<Auspiciante, Void>, TableCell<Auspiciante, Void>>() {
            @Override
            public TableCell<Auspiciante, Void> call(final TableColumn<Auspiciante, Void> param) {
                TableCell<Auspiciante, Void> cell = new TableCell<Auspiciante, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el Mascota de la fila
                            Auspiciante mas = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            //System.out.print(mas.toString());
                            btnEd.setOnAction(e ->{
                                try {
                                    switchToEdit(mas);
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
                                alert.setHeaderText("Eliminar mascota \"" +  "\"?");
                                alert.setContentText("Seguro desea eliminar esta mascota:"+mas+ "?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if(!result.isPresent() || result.get() != ButtonType.OK) {
                                    try {
                                        switchToMenuPrincipal();
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                } else {
                                    eliminar(mas);
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
        colAcciones.setCellFactory(cellFactory);
    }
}
