package com.mycompany.poo2;
import com.mycompany.poo2.modelo.Mascota;
import com.mycompany.poo2.modelo.Persona;

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

    public void eliminar(Mascota mas){
        ArrayList<Mascota> lista= new ArrayList<>();
        lista=  mas.cargarMascotas(App.pathMascotas);
        /*if(lista.contains(mas)){
            lista.remove(mas);
        }*/
        Mascota eliminable =null;
        for(Mascota ms : lista){
            if(ms.getCodigo().equals(mas.getCodigo())){
                eliminable = ms;
            }
        }
        lista.remove(eliminable);
        
        try {
            FileWriter writer = new FileWriter(App.pathMascotas);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("id;nombre;tipo;raza;fecha_nac;foto;id_dueno");
            for(Mascota m:lista ){
                bufferedWriter.write("\n");
                String[] date=m.getFechaNacimiento().toString().split("-");
                String linea = m.getCodigo()+";"+m.getNombre()+";"+m.getEspecie().toString()+";"+m.getRaza()+";"+date[2]+"/"+date[1]+"/"+date[0]+";"+"png"+";"+m.getDueno().getCi();
                bufferedWriter.write(linea);
                System.out.print(linea);
                
        }
        bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                
        
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
                            btnEl.setOnAction(e ->{

                                //eliminar(mas);
                                Alert alert =  new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("eliminar \"" + "\"?");
                                alert.setHeaderText("eliminar mascota \"" +  "\"?");
                                alert.setContentText("Seguro desea eliminar esta mascota?");
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
    
    private void editarMascota(){

        
    }
}