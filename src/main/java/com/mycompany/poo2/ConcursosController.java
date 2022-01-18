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


public class ConcursosController {
    @FXML
    private void switchToCrearConcursos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearconcurso.fxml"));//no tiene el controlador especificado
            CrearconcursoController apre = new CrearconcursoController();

            fxmlLoader.setController(apre);//se asigna el controlador

            VBox root = (VBox) fxmlLoader.load();
            
            

            App.changeRoot(root);
    }
    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menuprincipal");
    }

}
