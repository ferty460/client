package com.example.client1;

import com.example.client1.controller.ApplicationController;
import com.example.client1.controller.EditAuthorController;
import com.example.client1.controller.EditBookController;
import com.example.client1.controller.EditPublisherController;
import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.BookEntity;
import com.example.client1.entity.PublisherEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        VBox mainApp = (VBox) fxmlLoader.load();
        Scene scene = new Scene(mainApp, 600, 400);
        stage.setTitle("LibraryApp");
        stage.setScene(scene);

        ApplicationController controller = fxmlLoader.getController();
        stage.show();
    }

    public static boolean showBookEditDialog(BookEntity bookObj, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("editBook.fxml"));
            VBox page = (VBox) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Книга");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            EditBookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj, id);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showAuthorEditDialog(AuthorEntity authorObj, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("editAuthor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Автор");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            EditAuthorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(authorObj, id);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showPublisherEditDialog(PublisherEntity publisherObj, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("editPublisher.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Издательство");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            EditPublisherController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(publisherObj, id);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}