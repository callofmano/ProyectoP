package com.mycompany.poo2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.mycompany.poo2.modelo.DuenoMascota;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DetalleDuenosController {
    
    @FXML
    private Label lblNom;

    @FXML
    private Label lblapellido;

    @FXML
    private Label lbldireccion;

    @FXML
    private Label lbltelefono;

    @FXML
    private Label lblciudad;

    @FXML
    private Label lblci;

    @FXML
    private Label lblemail;

    @FXML
    private Button botonRegresar;

    public void llenarCampos(DuenoMascota dueno){
        lblNom.setText(dueno.getNombre());
        lblapellido.setText(dueno.getApellido());
        lblci.setText(dueno.getCi());
        lbldireccion.setText(dueno.getDireccion());
        lblciudad.setText(dueno.getCiudad().getNombre());
        lblemail.setText(dueno.getEmail());
        lbltelefono.setText(dueno.getTelefono());
    }

    public void regresar() throws IOException{
        App.setRoot("administrard");
    }
}
