/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;
import com.mycompany.poo2.modelo.DuenoMascota;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Optional;
/**
 *
 * @author User
 */
public class AdministrardController {
    @FXML
    TableView listaDuenos;
    @FXML
    private TableColumn<DuenoMascota,String> colCodigo;
    @FXML
    private TableColumn<DuenoMascota,String> colNombre;
    @FXML
    private TableColumn<DuenoMascota,String> colApellidos;
    @FXML
    private TableColumn<DuenoMascota,String> colTelefono;
    @FXML
    private TableColumn<DuenoMascota,String> colCiudad;
    @FXML
    private TableColumn<DuenoMascota,Void> colOpciones;
    @FXML
    private void switchToCreard() throws IOException {
        App.setRoot("creard");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("ci"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colApellidos.setCellValueFactory(new  PropertyValueFactory<>("apellido"));
    colTelefono.setCellValueFactory(new  PropertyValueFactory<>("telefono"));
    colCiudad.setCellValueFactory(new  PropertyValueFactory<>("ciudad"));
    agregarOpciones();
    listaDuenos.getItems().setAll(DuenoMascota.cargarDuenos(App.pathPersonas));
    }

    public void switchToEdit(DuenoMascota dueno) throws IOException{
        //App.setRoot("primary");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("creard.fxml"));//no tiene el controlador especificado
            CreardController pc = new CreardController();

            fxmlLoader.setController(pc);//se asigna el controlador

            FlowPane root = (FlowPane) fxmlLoader.load();
            
            pc.llenarCampos(dueno);
            App.changeRoot(root);

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }

    public void eliminar(DuenoMascota dueno){
        ArrayList<DuenoMascota> lista= new ArrayList<>();
        lista=  dueno.cargarDuenos(App.pathPersonas);
        /*if(lista.contains(mas)){
            lista.remove(mas);
        }*/
        DuenoMascota eliminable =null;
        for(DuenoMascota due : lista){
            if(due.getCi().equals(dueno.getCi())){
                eliminable = due;
            }
        }
        lista.remove(eliminable);
        
        try {
            FileWriter writer = new FileWriter(App.pathPersonas);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("id;nombre;tipo;raza;fecha_nac;foto;id_dueno");
            for(DuenoMascota d:lista ){
                bufferedWriter.write("\n");
                String linea = d.getCi() + "," + d.getApellido()+","+d.getNombre()+","+d.getDireccion()+","+d.getCiudad().getNombre()+","+d.getEmail();
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

        Callback<TableColumn<DuenoMascota, Void>, TableCell<DuenoMascota, Void>> cellFactory = new Callback<TableColumn<DuenoMascota, Void>, TableCell<DuenoMascota, Void>>() {
            @Override
            public TableCell<DuenoMascota, Void> call(final TableColumn<DuenoMascota, Void> param) {
                TableCell<DuenoMascota, Void> cell = new TableCell<DuenoMascota, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el Mascota de la fila
                            DuenoMascota dueno = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            //System.out.print(mas.toString());
                            btnEd.setOnAction(e ->{
                                try {
                                    switchToEdit(dueno);
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
                                alert.setHeaderText("Eliminar dueño \"" +  "\"?");
                                alert.setContentText("Seguro desea eliminar este dueño:"+dueno+ "?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if(!result.isPresent() || result.get() != ButtonType.OK) {
                                    try {
                                        switchToMenuPrincipal();
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                } else {
                                    eliminar(dueno);
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