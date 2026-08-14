package com.sm64tracker.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import com.sm64tracker.Main;
import com.sm64tracker.model.Course;
import com.sm64tracker.model.PersonalBest;
import com.sm64tracker.model.Star;
import com.sm64tracker.repository.PersonalBestRepository;
import com.sm64tracker.service.PersonalBestService;
import com.sm64tracker.util.TimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StarDetailController {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    @FXML
    private Label starNameLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label currentPbLabel;

    @FXML
    private TextField timeInputField;

    @FXML
    private Label statusLabel;

    @FXML
    private VBox historyContainer;

    @FXML
    private Label firstPbLabel;

    @FXML
    private Label currentPbSummaryLabel;

    @FXML
    private Label improvementLabel;

    private Course course;
    private Star star;
    private final PersonalBestRepository personalBestRepository = new PersonalBestRepository();
    private final PersonalBestService personalBestService = new PersonalBestService(personalBestRepository);

    public void setStar(Star star, Course course) {
        this.star = star;
        this.course = course;
        starNameLabel.setText(star.getName());
        courseNameLabel.setText(course.getName());
        refreshView();
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/course-detail-view.fxml"));
            Parent root = loader.load();
            CourseDetailController controller = loader.getController();
            controller.setCourse(course);

            Stage stage = (Stage) starNameLabel.getScene().getWindow();
            stage.setScene(new Scene(root, 1200, 800));
            stage.getScene().getStylesheets().add(
                    Main.class.getResource("/css/styles.css").toExternalForm()
            );
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to return to course details.", exception);
        }
    }

    @FXML
    private void submitTime() {
        try {
            long timeInMs = TimeFormatter.parseToMilliseconds(timeInputField.getText());
            var result = personalBestService.submitTime(star.getId(), timeInMs);

            if (result.isNewPb()) {
                statusLabel.setText("New PB recorded.");
                statusLabel.getStyleClass().remove("alert-error");
                statusLabel.getStyleClass().add("alert-label");
            } else {
                statusLabel.setText("Time was not faster than the current PB.");
                statusLabel.getStyleClass().remove("alert-label");
                statusLabel.getStyleClass().add("alert-error");
            }

            timeInputField.clear();
            refreshView();
        } catch (IllegalArgumentException exception) {
            statusLabel.setText(exception.getMessage());
            statusLabel.getStyleClass().remove("alert-label");
            statusLabel.getStyleClass().add("alert-error");
        }
    }

    private void refreshView() {
        List<PersonalBest> history = personalBestService.getProgression(star.getId());
        historyContainer.getChildren().clear();

        if (history.isEmpty()) {
            currentPbLabel.setText("Current PB: No PB");
            currentPbSummaryLabel.setText("No PB");
            firstPbLabel.setText("No PB");
            improvementLabel.setText("No PB");
            return;
        }

        List<PersonalBest> sorted = history.stream()
                .sorted(Comparator.comparing(PersonalBest::getAchievedAt))
                .toList();

        PersonalBest current = sorted.stream().min(Comparator.comparingLong(PersonalBest::getTimeInMs)).orElse(sorted.getFirst());
        currentPbLabel.setText("Current PB: " + TimeFormatter.formatFromMilliseconds(current.getTimeInMs()));
        currentPbSummaryLabel.setText(TimeFormatter.formatFromMilliseconds(current.getTimeInMs()));

        PersonalBest first = sorted.getFirst();
        firstPbLabel.setText(TimeFormatter.formatFromMilliseconds(first.getTimeInMs()));

        long improvement = first.getTimeInMs() - current.getTimeInMs();
        improvementLabel.setText("-" + TimeFormatter.formatFromMilliseconds(improvement));

        for (PersonalBest pb : sorted) {
            HBox row = new HBox(18);
            row.setPadding(new Insets(6, 0, 6, 0));

            Label time = new Label(TimeFormatter.formatFromMilliseconds(pb.getTimeInMs()));
            time.getStyleClass().add("history-time");

            Label date = new Label(pb.getAchievedAt().format(DATE_FORMATTER));
            date.getStyleClass().add("history-date");

            row.getChildren().addAll(time, date);
            historyContainer.getChildren().add(row);
        }
    }
}
