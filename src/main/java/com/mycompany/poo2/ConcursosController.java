/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.poo2;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import com.mycompany.poo2.modelo.*;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
public class ConcursosController {
    @FXML Button botonEnviarInvitaciones;
    @FXML TableView listaConcursos;
    @FXML private TableColumn<Concurso,String> colCodigo;
    @FXML private TableColumn<Concurso,String> colNombre;
    @FXML private TableColumn<Concurso,String> colFecha;
    @FXML private TableColumn<Concurso,String> colCiudad;
    @FXML private TableColumn<Concurso,Void> colOpciones;
    @FXML Alert alert = new Alert(AlertType.INFORMATION);

@FXML
private void initialize (){
    colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colNombre.setCellValueFactory(new  PropertyValueFactory<>("nombre"));
    colFecha.setCellValueFactory(new  PropertyValueFactory<>("fecha"));
    colCiudad.setCellValueFactory(new  PropertyValueFactory<>("ciudad"));
    listaConcursos.getItems().setAll(Concurso.cargarConcursos(App.pathConcursos));
    alert.setTitle("Dialogo de información");
    alert.setHeaderText("Esto es un dialogo de información");
    alert.setContentText("Usted ha enviado las invitaciones");
    agregarOpciones();
    }

    public void switchToEdit(Concurso con) throws IOException{
        //App.setRoot("primary");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearconcurso.fxml"));//no tiene el controlador especificado
            CrearconcursoController pc = new CrearconcursoController();

            fxmlLoader.setController(pc);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            
            pc.llenarCampos(con);
            App.changeRoot(root);

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void switchToCrearConcursos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearconcurso.fxml"));//no tiene el controlador especificado
            CrearconcursoController apre = new CrearconcursoController();

            fxmlLoader.setController(apre);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            
            

            App.changeRoot(root);
    }



    private void agregarOpciones() {

        Callback<TableColumn<Concurso, Void>, TableCell<Concurso, Void>> cellFactory = new Callback<TableColumn<Concurso, Void>, TableCell<Concurso, Void>>() {
            @Override
            public TableCell<Concurso, Void> call(final TableColumn<Concurso, Void> param) {
                TableCell<Concurso, Void> cell = new TableCell<Concurso, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //para saber si es actual o no
                            Concurso con = getTableView().getItems().get(getIndex());
                            if(con.getFechaCierre().compareTo(LocalDate.now())>=0  &&  LocalDate.now().compareTo(con.getFechaInsc())>=0){
                                //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el Concurso de la fila
                            Concurso mas = getTableView().getItems().get(getIndex());
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


                            Button btnEliminar = new Button("Eliminar");
                            //System.out.print(mas.toString());
                            btnEliminar.setOnAction(e ->{
                            });
                               
                            //boton eliminar
                            Button btnInscritos = new Button("Cons.Inscritos");
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnInscritos,btnEliminar);
                            btnInscritos.setOnAction(e ->{

                                //eliminar(mas);
                                Alert alert =  new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("MASCOTAS INSCRITAS \"" + "\"?");
                                alert.setHeaderText("MASCOTAS INSCRITAS \"" +  "\"?");
                                alert.setContentText("Estas son las mascotas inscritas");
                                Optional<ButtonType> result = alert.showAndWait();
                                
                            });
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                            }else{
                                                                //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el Concurso de la fila
                            Concurso mas = getTableView().getItems().get(getIndex());
                            //boton editar


                            Button btnGanadores = new Button("Cons.Ganadores");
                            //System.out.print(mas.toString());
                            btnGanadores.setOnAction(e ->{
                            });
                               
                            Button btnInscritos = new Button("Cons.Inscritos");
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnInscritos,btnGanadores);
                            btnInscritos.setOnAction(e ->{

                                //eliminar(mas);
                                Alert alert =  new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("MASCOTAS INSCRITAS \"" + "\"?");
                                alert.setHeaderText("MASCOTAS INSCRITAS \"" +  "\"?");
                                alert.setContentText("Estas son las mascotas inscritas");
                                Optional<ButtonType> result = alert.showAndWait();
                                
                            });
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                            }
                        }
                    }
                };
                return cell;
            }
        };

        colOpciones.setCellFactory(cellFactory);

    }



    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }


    @FXML 
    private void enviarInvitaciones(ActionEvent event, Concurso concurso){
    botonEnviarInvitaciones.setOnMouseClicked((MouseEvent ev) ->{
    ArrayList<DuenoMascota> duenos = DuenoMascota.cargarDuenos(App.pathPersonas);
    for(DuenoMascota d : duenos){
        String correo = d.getEmail();
        String cuerpo = "Su mascota Sr(a)" +" " +d.getNombre()+d.getApellido() +" "+"ha sido invitada al siguiente concuso:"+" "+concurso.getNombre()+"/n"+"Fecha:"+" "+concurso.getFecha()+"/n"+"Hora:"+" "+concurso.getHora()+"/n"+"Ciudad"+" "+concurso.getCiudad().toString()+"/n"+"Lugar"+" "+concurso.getLugar()+"/n"+"Premios"+" "+concurso.getPremios().toString(); 
        Correo.enviarConGMail(correo,"Ha sido invitado al siguiente concurso"+concurso.getNombre(),cuerpo);
    }
    alert.showAndWait();
    });
    }
}
