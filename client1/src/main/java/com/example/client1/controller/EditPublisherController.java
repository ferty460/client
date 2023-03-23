package com.example.client1.controller;

import com.example.client1.entity.PublisherEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client1.controller.ApplicationController.*;
import static com.example.client1.controller.ApplicationController.gson;

public class EditPublisherController {
    @FXML
    private TextField publisherName_field;

    @FXML
    private TextField publisherCity_field;

    private Stage editPublisherStage;
    private PublisherEntity publisher;
    private int publisherId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editPublisherStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(PublisherEntity publisherIn, int publisherId) {
        this.publisher = publisherIn;
        this.publisherId = publisherId;

        publisherName_field.setText(publisher.getPublisher());
        publisherCity_field.setText(publisher.getCity());
    }

    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()) {
            publisher.setId((long) publisherId);
            publisher.setPublisher(publisherName_field.getText());
            publisher.setCity(publisherCity_field.getText());

            okClicked = true;
            editPublisherStage.close();
            publishersData.set(publisherId, publisher);
            updatePublisher(publisher);
        }
    }

    @FXML
    private void handleCancel() {
        editPublisherStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (publisherName_field.getText() == null || publisherName_field.getText().length() == 0) errorMessage = "Не обнаружено наименование издательства!\n";
        if (publisherCity_field.getText() == null || publisherCity_field.getText().length() == 0) errorMessage = "Не обнаружен город!\n";

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editPublisherStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public static void updatePublisher(PublisherEntity author) throws IOException {
        http.post("http://localhost:2825/api/v1/publisher/update", gson.toJson(author).toString());
    }
}
