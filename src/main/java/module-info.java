module com.mycompany.poo2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.poo2 to javafx.fxml;
    exports com.mycompany.poo2;
}
