module com.mycompany.poo2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.poo2 to javafx.fxml;
     opens com.mycompany.poo2.modelo to javafx.base;
    exports com.mycompany.poo2;
}
