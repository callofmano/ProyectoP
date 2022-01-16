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
public class AdministraraController {
    @FXML
    private void switchToCrearAuspiciante() throws IOException {
        App.setRoot("crearauspiciantes");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }
}
