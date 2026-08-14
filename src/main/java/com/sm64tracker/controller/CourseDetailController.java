package com.sm64tracker.controller;

import java.io.IOException;
import java.util.List;

import com.sm64tracker.Main;
import com.sm64tracker.model.Course;
import com.sm64tracker.model.Star;
import com.sm64tracker.repository.PersonalBestRepository;
import com.sm64tracker.repository.StarRepository;
import com.sm64tracker.util.TimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseDetailController {
    @FXML
    private Label courseTitleLabel;

    @FXML
    private VBox starListContainer;

    private Course course;
    private final StarRepository starRepository = new StarRepository();
    private final PersonalBestRepository personalBestRepository = new PersonalBestRepository();

    public void setCourse(Course course) {
        this.course = course;
        courseTitleLabel.setText(course.getName().toUpperCase());
        renderStars();
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/main-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) starListContainer.getScene().getWindow();
            stage.setScene(new Scene(root, 1200, 800));
            stage.getScene().getStylesheets().add(
                    Main.class.getResource("/css/styles.css").toExternalForm()
            );
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to return to course selection screen.", exception);
        }
    }

    private void renderStars() {
        starListContainer.getChildren().clear();
        List<Star> stars = starRepository.findByCourseId(course.getId());

        for (Star star : stars) {
            HBox row = new HBox(12);
            row.getStyleClass().add("star-row");
            row.setPrefWidth(900);
            row.setPadding(new Insets(10, 12, 10, 12));

            Label nameLabel = new Label(star.getName());
            nameLabel.setWrapText(true);
            nameLabel.setMaxWidth(700);
            nameLabel.getStyleClass().add("star-name");

            Label pbLabel = new Label();
            pbLabel.getStyleClass().add("star-pb");

            Long currentPbMs = personalBestRepository.findCurrentPbTimeMs(star.getId()).orElse(null);
            if (currentPbMs == null) {
                pbLabel.setText("No PB");
                pbLabel.getStyleClass().clear();
                pbLabel.getStyleClass().add("star-pb-empty");
            } else {
                pbLabel.setText(TimeFormatter.formatFromMilliseconds(currentPbMs));
            }

            row.getChildren().addAll(nameLabel, pbLabel);
            HBox.setHgrow(nameLabel, javafx.scene.layout.Priority.ALWAYS);

            row.setOnMouseClicked(event -> openStarDetail(star));
            starListContainer.getChildren().add(row);
        }
    }

    private void openStarDetail(Star star) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/star-detail-view.fxml"));
            Parent root = loader.load();
            StarDetailController controller = loader.getController();
            controller.setStar(star, course);

            Stage stage = (Stage) starListContainer.getScene().getWindow();
            stage.setScene(new Scene(root, 1200, 800));
            stage.getScene().getStylesheets().add(
                    Main.class.getResource("/css/styles.css").toExternalForm()
            );
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to open star detail screen.", exception);
        }
    }
}
