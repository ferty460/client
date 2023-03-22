package com.example.client1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.BookEntity;
import com.example.client1.entity.PublisherEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.client1.controller.ApplicationController.booksData;
import static com.example.client1.controller.ApplicationController.updateBook;

public class EditBookController {
    @FXML
    private ComboBox<AuthorEntity> authorList;

    @FXML
    private TextField bookChapter_field;

    @FXML
    private TextField bookName_field;

    @FXML
    private ComboBox<PublisherEntity> publisherList;

    @FXML
    private TextField bookYear_field;

    private Stage editBookStage;
    private BookEntity book;
    private int bookId;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editBookStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(BookEntity bookIn, int book_id) {
        this.book = bookIn;
        this.bookId = book_id;

        bookName_field.setText(book.getTitle());
        /*bookAuthor_field.setText(book.getAuthor().getSurname());
        bookPublisher_field.setText(book.getPublishing().getPublisher());*/
        bookYear_field.setText(book.getYear() + "");
        bookChapter_field.setText(book.getKind());
    }

    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()) {
            book.setId((long) bookId);
            book.setTitle(bookName_field.getText());/*
            book.setAuthor(bookAuthor_field.getText());
            book.setPublisher(bookPublisher_field.getText());*/
            book.setYear(Integer.parseInt(bookYear_field.getText()));
            book.setKind(bookChapter_field.getText());

            okClicked = true;
            editBookStage.close();
            booksData.set(bookId, book);
            updateBook(book);
        }
    }

    @FXML
    private void handleCancel() {
        editBookStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (bookName_field.getText() == null || bookName_field.getText().length() == 0) errorMessage = "Не обнаружено название книги!\n";
        /*if (bookAuthor_field.getText() == null || bookAuthor_field.getText().length() == 0) errorMessage = "Не обнаружен автор книги!\n";
        if (bookPublisher_field.getText() == null || bookPublisher_field.getText().length() == 0) errorMessage = "Не обнаружено издание книги!\n";*/
        if (bookYear_field == null || bookYear_field.getText().length() == 0) errorMessage = "Не обнаружен год выпуска книги!\n";
        else {
            try {
                Integer.parseInt(bookYear_field.getText());
            } catch (NumberFormatException e) {
                errorMessage = "Некорректное значение года выпуска книги (должно быть целочисленное)\n";
            }
        }

        if (errorMessage.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editBookStage);
            alert.setTitle("Ошибка заполнения");
            alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}