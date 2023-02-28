module edu.bip.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires okhttp3;
    requires static lombok;
    requires annotations;


    exports com.example.client1;
    opens com.example.client1 to javafx.fxml;
    exports com.example.client1.controller;
    opens com.example.client1.controller to javafx.fxml;
    exports com.example.client1.entity;
    opens com.example.client1.entity to com.google.gson;
    exports com.example.client1.response;
    opens com.example.client1.response to com.google.gson;
}