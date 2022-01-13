package com.mycompany.poo2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
