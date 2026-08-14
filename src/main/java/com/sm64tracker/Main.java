package com.sm64tracker;

import com.sm64tracker.database.DatabaseInitializer;
import com.sm64tracker.service.SeedDataLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseInitializer.initialize();
        SeedDataLoader.seedIfNeeded();

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/main-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(
                    Objects.requireNonNull(Main.class.getResource("/css/styles.css")).toExternalForm()
            );

            stage.setTitle("SM64 Star PB Tracker");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load the main JavaFX view.", exception);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
