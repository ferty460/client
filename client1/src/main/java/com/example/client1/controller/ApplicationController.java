package com.example.client1.controller;


import com.example.client1.Application;
import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.BookEntity;
import com.example.client1.entity.PublisherEntity;
import com.example.client1.utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ApplicationController {
    public static String api = "http://localhost:2825/api/v1/book/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public static ObservableList<AuthorEntity> authorsData = FXCollections.observableArrayList();
    public static ObservableList<PublisherEntity> publishersData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

    @FXML
    private TableView<BookEntity> tableBooks;

    @FXML
    private TableColumn<BookEntity, String> bookId;

    @FXML
    private TableColumn<BookEntity, String> bookAuthor;

    @FXML
    private TableColumn<BookEntity, String> bookChapter;

    @FXML
    private TableColumn<BookEntity, String> bookName;

    @FXML
    private TableColumn<BookEntity, String> bookPublisher;

    @FXML
    private TableColumn<BookEntity, String> bookYear;

    @FXML
    private TableView<AuthorEntity> tableAuthors;

    @FXML
    private TableColumn<AuthorEntity, String> authorId;

    @FXML
    private TableColumn<AuthorEntity, String> authorName;

    @FXML
    private TableColumn<AuthorEntity, String> authorSurname;

    @FXML
    private TableColumn<AuthorEntity, String> authorLastname;

    @FXML
    private TableView<PublisherEntity> tablePublishers;

    @FXML
    private TableColumn<PublisherEntity, String> publisherId;

    @FXML
    private TableColumn<PublisherEntity, String> publisherName;

    @FXML
    private TableColumn<PublisherEntity, String> publisherCity;

    @FXML
    private void initialize() throws Exception {
        getDataBooks();
        getDataAuthors();
        getDataPublishers();
        updateBookTable();
        updateAuthorTable();
        updatePublisherTable();
    }

    private void updateBookTable() throws Exception {
        bookName.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
        bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        bookChapter.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("kind"));
        tableBooks.setItems(booksData);
    }

    private void updatePublisherTable() throws Exception {
        publisherName.setCellValueFactory(new PropertyValueFactory<PublisherEntity, String>("publisher"));
        publisherCity.setCellValueFactory(new PropertyValueFactory<PublisherEntity, String>("city"));
        tablePublishers.setItems(publishersData);
    }

    private void updateAuthorTable() throws Exception {
        authorName.setCellValueFactory(new PropertyValueFactory<AuthorEntity, String>("name"));
        authorSurname.setCellValueFactory(new PropertyValueFactory<AuthorEntity, String>("surname"));
        authorLastname.setCellValueFactory(new PropertyValueFactory<AuthorEntity, String>("lastname"));
        tableAuthors.setItems(authorsData);
    }

    @FXML
    private void click_newAuthor() throws IOException {
        AuthorEntity tempAuthor = new AuthorEntity();
        authorsData.add(tempAuthor);
        Application.showAuthorEditDialog(tempAuthor, authorsData.size() - 1);
    }

    @FXML
    private void click_editAuthor() {
        AuthorEntity selectedPerson = tableAuthors.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            Application.showAuthorEditDialog(selectedPerson, authorsData.indexOf(selectedPerson));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный автор");
            alert.setContentText("Пожалуйста, выберите автора в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_removeAuthor() throws IOException {
        AuthorEntity selectedAuthor = tableAuthors.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            System.out.println(selectedAuthor.getId());
            System.out.println(http.delete("http://localhost:2825/api/v1/author/", selectedAuthor.getId()));
            authorsData.remove(selectedAuthor);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_newBook() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        Application.showBookEditDialog(tempBook, booksData.size() - 1);
    }

    @FXML
    private void click_editBook() {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Application.showBookEditDialog(selectedBook, booksData.indexOf(selectedBook));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранное издательство");
            alert.setContentText("Пожалуйста, выберите издательство в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_removeBook() throws IOException {
        BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            System.out.println(selectedPerson.getId());
            System.out.println(http.delete(api, selectedPerson.getId()));
            booksData.remove(selectedPerson);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_newPublisher() throws IOException {
        PublisherEntity tempPublisher = new PublisherEntity();
        publishersData.add(tempPublisher);
        Application.showPublisherEditDialog(tempPublisher, publishersData.size() - 1);
    }

    @FXML
    private void click_editPublisher() {
        PublisherEntity selectedPerson = tablePublishers.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            Application.showPublisherEditDialog(selectedPerson, publishersData.indexOf(selectedPerson));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранное издательство");
            alert.setContentText("Пожалуйста, выберите издательство в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void click_removePublisher() throws IOException {
        PublisherEntity selectedPublisher = tablePublishers.getSelectionModel().getSelectedItem();
        if (selectedPublisher != null) {
            System.out.println(selectedPublisher.getId());
            System.out.println(http.delete("http://localhost:2825/api/v1/publisher/", selectedPublisher.getId()));
            publishersData.remove(selectedPublisher);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Отсутствует выбранный пользователь");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице");
            alert.showAndWait();
        }
    }

    public static void getDataBooks() throws Exception {
        String res = http.get(api, "all");
        System.out.println(res);
        JsonObject base = gson.fromJson(res, JsonObject.class);

        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    public static void getDataAuthors() throws Exception {
        String res = http.get("http://localhost:2825/api/v1/author/", "all");
        JsonObject base = gson.fromJson(res, JsonObject.class);

        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            AuthorEntity newAuthor = gson.fromJson(dataArr.get(i).toString(), AuthorEntity.class);
            authorsData.add(newAuthor);
        }
    }

    public static void getDataPublishers() throws Exception {
        String res = http.get("http://localhost:2825/api/v1/publisher/", "all");
        JsonObject base = gson.fromJson(res, JsonObject.class);

        JsonArray dataArr = base.getAsJsonArray("data");
        for (int i = 0; i < dataArr.size(); i++) {
            PublisherEntity newPublisher = gson.fromJson(dataArr.get(i).toString(), PublisherEntity.class);
            publishersData.add(newPublisher);
        }
    }

}