package com.mycompany.poo2;
import com.mycompany.poo2.modelo.Mascota;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;

public class SecondaryController {
    @FXML
    TableView listaMascotas;
    @FXML
    private TableColumn<Mascota,String> colCodigo;
    @FXML
    private TableColumn<Mascota,String> colNombre;
    @FXML
    private TableColumn<Mascota,String> colTipo;
    @FXML
    private TableColumn<Mascota,String> colDueno;
    @FXML
    private TableColumn<Mascota,Void> colAcciones;
    @FXML
    private TableColumn<Mascota,String> colDetalle;
    @FXML
    private TableColumn<Mascota,String> colEliminar;
    @FXML
    private void switchToPrimary() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));//no tiene el controlador especificado
            PrimaryController pc = new PrimaryController();

            fxmlLoader.setController(pc);//se asigna el controlador

            FlowPane root = (FlowPane) fxmlLoader.load();
            
            
            
            App.changeRoot(root);
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
        
    }
    public void switchToEdit(Mascota m) throws IOException{

        //App.setRoot("primary");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));//no tiene el controlador especificado
            PrimaryController pc = new PrimaryController();

            fxmlLoader.setController(pc);//se asigna el controlador

            FlowPane root = (FlowPane) fxmlLoader.load();
            
            
            pc.llenarCampos(m);
            App.changeRoot(root);

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    public void switchToDetalleMascota(Mascota m) throws IOException{

        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("detalleMascota.fxml"));//no tiene el controlador especificado
            DetalleMascotaController dm = new DetalleMascotaController();

            fxmlLoader.setController(dm);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            dm.llenarDatos(m);
            
            
            App.changeRoot(root);
    }

    @FXML
    private void initialize(){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colTipo.setCellValueFactory(new  PropertyValueFactory<>("especie"));
    colDueno.setCellValueFactory(new  PropertyValueFactory<>("dueno"));
    agregarOpciones();
    listaMascotas.getItems().setAll(Mascota.cargarMascotas(App.pathMascotas));
    }



    //crear botones dinamicos 
    private void agregarOpciones() {

        Callback<TableColumn<Mascota, Void>, TableCell<Mascota, Void>> cellFactory = new Callback<TableColumn<Mascota, Void>, TableCell<Mascota, Void>>() {
            @Override
            public TableCell<Mascota, Void> call(final TableColumn<Mascota, Void> param) {
                TableCell<Mascota, Void> cell = new TableCell<Mascota, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el Mascota de la fila
                            Mascota mas = getTableView().getItems().get(getIndex());
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


                            Button btnDet = new Button("Detalle");
                            //System.out.print(mas.toString());
                            btnDet.setOnAction(e ->{
                                try {
                                    switchToDetalleMascota(mas);
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            });
                               
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl,btnDet);
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
    
    private void editarMascota(){

        
    }
}