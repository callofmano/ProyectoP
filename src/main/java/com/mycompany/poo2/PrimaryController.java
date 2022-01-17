package com.mycompany.poo2;

import java.io.IOException;
import java.util.*;
import java.io.*;
import com.mycompany.poo2.modelo.DuenoMascota;
import com.mycompany.poo2.modelo.Mascota;
import com.mycompany.poo2.modelo.Especie;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PrimaryController {

    @FXML
    Alert alert = new Alert(AlertType.INFORMATION);
    String editType;

    String nombre;
    String nacimiento;
    String raza;
    private Stage thistage;
    @FXML
    private RadioButton perro,gato;
    @FXML
    private Button botonGuardar,botonRegresar;
    @FXML
    private ComboBox <String> comboDueno, comboFoto;
    @FXML
    private TextField txtNombre, txtNacimiento, txtRaza;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void initialize(){
    
        ArrayList <String> duenos = new ArrayList<>();
        for (DuenoMascota d: DuenoMascota.cargarDuenos(App.pathPersonas)){
            duenos.add(d.getNombre());
            }
        comboDueno.getItems().addAll(duenos);
        botonRegresar.setVisible(false);
        alert.setTitle("Dialogo de información");
        alert.setHeaderText("Esto es un dialogo de información");
        alert.setContentText("Usted ha creado una mascota");

        }

    @FXML
    private void comboboxEvent (ActionEvent e){
        Object evt =e.getSource();
            if(evt.equals(comboDueno)){
                //System.out.print(comboDueno.getSelectionModel().getSelectedItem());
            }
        //return dueno;
        }
    @FXML
    private void guardar(ActionEvent event){
        
        botonGuardar.setOnMouseClicked((MouseEvent ev) ->{
                Especie especie =null;
                String e = null;
                if(gato.isSelected()){
                    especie = Especie.valueOf(gato.getText().strip().toUpperCase()); 
                    e=gato.getText().strip().toLowerCase();
                    }
                else if (perro.isSelected()){
                    especie = Especie.valueOf(perro.getText().strip().toUpperCase());
                    e = perro.getText().strip().toLowerCase();
                    }
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DuenoMascota dueno = null;
                String date = txtNacimiento.getText();
                String dates[]= date.split("-");
                String dateEscribir= dates[2]+"/"+dates[1]+"/"+dates[0];
                System.out.print(date);
                LocalDate nacimiento = LocalDate.parse(date);
                for (DuenoMascota d: DuenoMascota.cargarDuenos(App.pathPersonas)){
                if(d.getNombre().equals(comboDueno.getSelectionModel().getSelectedItem())){
                    dueno= d;
                    System.out.print(dueno.getCi());
                    }}
                Mascota m = new Mascota(txtNombre.getText(),txtRaza.getText().toLowerCase(),nacimiento,"png",especie,dueno);
                try{
                    FileWriter writer = new FileWriter(App.pathMascotas,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("\n");
                    String linea = m.getCodigo()+";"+m.getNombre()+";"+e+";"+txtRaza.getText()+";"+dateEscribir+";"+"png"+";"+dueno.getCi();
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

        


    }


    
    



    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
}
