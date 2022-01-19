package com.mycompany.poo2;

import java.io.IOException;
import java.io.InputStream;

import com.mycompany.poo2.modelo.Mascota;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView imageView;

    public void llenarDatos(Mascota m){
        labelNombre.setText(m.getNombre());
        labelDueno.setText(m.getDueno().getNombre()+" "+m.getDueno().getApellido());
        labelFecha.setText(m.getFechaNacimiento().toString());
        labelRaza.setText(m.getRaza());

        InputStream input = null;
        try{
            input = App.class.getResource("files/"+m.getNombre()+".png").openStream();
            Image img = new Image(input,50,50,false,false);
            imageView.setImage(img);
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void regresar() throws IOException{
        App.setRoot("secondary");
    }

    
 }
