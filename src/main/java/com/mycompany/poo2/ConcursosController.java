/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.poo2;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;


public class ConcursosController {
    @FXML
    private void switchToCrearConcursos() throws IOException {
        App.setRoot("crearconcurso");
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }

}
