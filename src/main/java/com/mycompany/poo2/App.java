package com.mycompany.poo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
//probando maykoll 
/**
 * JavaFX App
 */
public class App extends Application {
    public static String pathPersonas = "archivo/duenosP5.csv";
    public static String pathMascotas = "archivo/mascotas.csv";
    public static String pathCiudades = "archivo/ciudades.csv";
    public static String pathAuspiciantes= "archivo/auspiciantes.csv";
    public static String pathConcursos = "archivo/concursos.csv";
    public static String pathPremios = "archivo/premios.csv";
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menuprincipal"), 640, 740);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }
    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}