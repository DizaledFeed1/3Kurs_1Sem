module org.example.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires kernel;
    requires styled.xml.parser;
    requires layout;
    requires java.desktop;


    opens org.example.lab2 to javafx.fxml;
    exports org.example.lab2;
}