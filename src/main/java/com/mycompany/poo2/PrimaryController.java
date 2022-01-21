package com.mycompany.poo2;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mycompany.poo2.modelo.DuenoMascota;
import com.mycompany.poo2.modelo.Mascota;
import com.mycompany.poo2.modelo.Especie;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


public class PrimaryController {

    @FXML
    Alert alert = new Alert(AlertType.INFORMATION);


    String editType;

    String nombre;
    String nacimiento;
    String raza;
    private Stage thistage;
    @FXML
    private Button botonBuscar;
    @FXML
    private RadioButton perro,gato;
    @FXML
    private Button botonGuardar,botonRegresar;
    @FXML
    private ImageView imageCrear;
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
        ArrayList<String> fotos = Mascota.cargarImagener("com/mycompany/poo2/files/");
        for (DuenoMascota d: DuenoMascota.cargarDuenos(App.pathPersonas)){
            duenos.add(d.getNombre());
            }
        comboDueno.getItems().addAll(duenos);
        botonRegresar.setVisible(false);
        alert.setTitle("Dialogo de información");
        alert.setHeaderText("Esto es un dialogo de información");
        alert.setContentText("Usted ha creado una mascota");
        Mascota m = null;
        botonGuardar.setOnAction(event-> {
            guardar(event,m);
        });
        }

    @FXML
    private void comboimagen (){
        String foto = comboFoto.getValue();
        InputStream input = null;
        try{
            input = App.class.getResource("files/" + foto).openStream();
            Image image = new Image(input,100,100,false,false);
            imageCrear.setImage(image);
        } catch (Exception ex){
            ex.getMessage();
        }
    }

    @FXML
    private void procesaArchivo(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");
            fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("All Images","*.*"),
            new ExtensionFilter("PNG", "*.png"),
            new ExtensionFilter("JPG", "*.jpg"));

            // Obtener la imagen seleccionada
            File imgFile = fileChooser.showOpenDialog(null);

            // Mostrar la imagen
            if (imgFile != null){
                Image imagen = new Image("file:" + imgFile.getAbsolutePath());
                imageCrear.setImage(imagen);
                //copiar la imagen
                Path from = Paths.get(imgFile.toURI());
                Path to = Paths.get("archivos/" + imgFile.getName());
                Files.copy(from, to);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
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
    private void guardar(ActionEvent event,Mascota mas){
        if(mas==null){
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
                String foto = comboFoto.getSelectionModel().getSelectedItem();
                Mascota m = new Mascota(txtNombre.getText(),txtRaza.getText().toLowerCase(),nacimiento,foto,especie,dueno);
                try{
                    FileWriter writer = new FileWriter(App.pathMascotas,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("\n");
                    String linea = m.getCodigo()+";"+m.getNombre()+";"+e+";"+txtRaza.getText()+";"+dateEscribir+";"+foto+";"+dueno.getCi();
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
            System.out.println("prueba de EDITAR //////////////////////////////-------------------------------------------------------------------");
            
            //codigo para guardar lo editado en una variable
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
                String foto = comboFoto.getSelectionModel().getSelectedItem();
                LocalDate nacimiento = LocalDate.parse(date);
                for (DuenoMascota d: DuenoMascota.cargarDuenos(App.pathPersonas)){
                if(d.getNombre().equals(comboDueno.getSelectionModel().getSelectedItem())){
                    dueno= d;
                    System.out.print(dueno.getCi());
                    }}
                  //public Mascota(String nombre, Especie especie, LocalDate fechaNacimiento, String foto, int codigo, String raza,DuenoMascota dueno)  
                Mascota m = new Mascota(txtNombre.getText(),especie,nacimiento,foto,Integer.valueOf(mas.getCodigo()),txtRaza.getText().toLowerCase(),dueno);
                

                ArrayList<Mascota> lista= new ArrayList<>();
                lista=  mas.cargarMascotas(App.pathMascotas);
                Boolean aux= false;
                Mascota editable= null;
                for(Mascota ms : lista){
                    if(ms.getCodigo().equals(mas.getCodigo())){

                        aux=true;
                        editable=ms;
                    }
                }
                if(aux){
                    
                    lista.add(lista.indexOf(editable),m);
                    lista.remove(editable);
                }

                try {
                    FileWriter writer = new FileWriter(App.pathMascotas);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("id;nombre;tipo;raza;fecha_nac;foto;id_dueno");
                    for(Mascota mx:lista ){
                        bufferedWriter.write("\n");
                        String[] dateWriting=mx.getFechaNacimiento().toString().split("-");
                        String linea = mx.getCodigo()+";"+mx.getNombre()+";"+mx.getEspecie().toString()+";"+mx.getRaza()+";"+dateWriting[2]+"/"+dateWriting[1]+"/"+dateWriting[0]+";"+"png"+";"+mx.getDueno().getCi();
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
    public void llenarCampos(Mascota m) throws IOException{
        txtNombre.setText(m.getNombre());
        if(m.getEspecie()==Especie.PERRO){
            perro.setSelected(true);
        }else{
            gato.setSelected(true);
        }
        txtNacimiento.setText(m.getFechaNacimiento().toString());
        txtRaza.setText(m.getRaza());
        ArrayList <String> duenos = new ArrayList<>();
        for (DuenoMascota d: DuenoMascota.cargarDuenos(App.pathPersonas)){
            duenos.add(d.getNombre());
            }
        comboDueno.getItems().addAll(duenos);
        DuenoMascota d =m.getDueno();
        comboDueno.getSelectionModel().select(duenos.indexOf(d.getNombre()));
        InputStream input = App.class.getResource("files/"+m.getFoto()).openStream();
            Image img = new Image(input,50,50,false,false);
        imageCrear.setImage(img);
        botonGuardar.setOnAction(event-> {
            guardar(event,m);
        });
    }

    
    



    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
}
