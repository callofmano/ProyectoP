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

    }

    public void regresar() throws IOException{
        App.setRoot("secondary");
    }

    
 }
