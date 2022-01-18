package com.mycompany.poo2;

import java.io.IOException;

import com.mycompany.poo2.modelo.Mascota;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DetalleMascotaController {

    @FXML
    private Label labelDueno;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelRaza;

    @FXML
    private Button btnRegresar;

    public void llenarDatos(Mascota m){
        labelNombre.setText(m.getNombre());
        labelDueno.setText(m.getDueno().getNombre()+" "+m.getDueno().getApellido());
        labelFecha.setText(m.getFechaNacimiento().toString());
        labelRaza.setText(m.getRaza());
        /*try{
            InputStream  input = getClass().getResource("img/"+p.getRutaImg()).openStream();
            Image img = new Image(input,50,50,false,false);
            ImageView imageView = new ImageView(img);
            btnProd.setGraphic(imageView);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }*/
    }

    public void regresar() throws IOException{
        App.setRoot("secondary");
    }

    
 }
