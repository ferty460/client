package com.example.client1.controller;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.BookEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client1.controller.ApplicationController.*;

public class EditAuthorController {

    @FXML
    private TextField authorLastname_field;

    @FXML
    private TextField authorName_field;

    @FXML
    private TextField authorSurname_field;

    private Stage editAuthorStage;
    private AuthorEntity author;
    private int authorId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editAuthorStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(AuthorEntity authorIn, int authorId) {
        this.author = authorIn;
        this.authorId = authorId;

        authorName_field.setText(author.getName());
        authorLastname_field.setText(author.getLastname());
        authorSurname_field.setText(author.getSurname());
    }

    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()) {
            /*author.setId((long) authorId);*/
            author.setName(authorName_field.getText());
            author.setSurname(authorSurname_field.getText());
            author.setLastname(authorLastname_field.getText());

            okClicked = true;
            editAuthorStage.close();
            authorsData.set(authorId, author);
            updateAuthor(author);
        }
    }

    @FXML
    private void handleCancel() {
        editAuthorStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (authorName_field.getText() == null || authorName_field.getText().length() == 0) errorMessage = "Не обнаружено имя автора!\n";
        if (authorLastname_field.getText() == null || authorLastname_field.getText().length() == 0) errorMessage = "Не обнаружено отчество автора!\n";
        if (authorSurname_field.getText() == null || authorSurname_field.getText().length() == 0) errorMessage = "Не обнаружена фамилия автора!\n";

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editAuthorStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public static void updateAuthor(AuthorEntity author) throws IOException {
        http.post("http://localhost:2825/api/v1/author/update", gson.toJson(author).toString());
    }
}
