/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poo2;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author User
 */
public class MenuprincipalController {
    @FXML
    private void switchToAdministarCiudades() throws IOException {
        App.setRoot("administrarciudades");
    }
    @FXML
    private void switchToAdministrarDuenos() throws IOException {
        App.setRoot("administrard");
    }
    @FXML
    private void switchToAdministrarAuspiciantes() throws IOException {
        App.setRoot("administrara");
    }
    @FXML
    private void switchToAdministarConcursos() throws IOException {
        App.setRoot("concursos");
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
