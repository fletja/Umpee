package com.sm64tracker.controller;

import com.sm64tracker.Main;
import com.sm64tracker.model.Course;
import com.sm64tracker.repository.CourseRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private FlowPane courseGrid;

    private final CourseRepository courseRepository = new CourseRepository();

    @FXML
    public void initialize() {
        loadCourses();
    }

    private void loadCourses() {
        List<Course> courses = courseRepository.findAll();
        courseGrid.getChildren().clear();

        for (Course course : courses) {
            Button card = new Button();
            card.getStyleClass().add("course-card");

            Label name = new Label(course.getName());
            name.getStyleClass().add("course-name");

            Label abbreviation = new Label(course.getAbbreviation());
            abbreviation.getStyleClass().add("course-abbr");

            long pbCount = courseRepository.countStarsWithPb(course.getId());
            Label count = new Label(pbCount + " / " + getCourseStarCount(course.getId()) + " PBs");
            count.getStyleClass().add("course-pb-count");

            VBox content = new VBox(6, name, abbreviation, count);
            content.setPrefWidth(220);
            content.setPrefHeight(120);
            content.setFillWidth(true);

            card.setGraphic(content);
            card.setOnAction(event -> openCourseDetail(course));
            courseGrid.getChildren().add(card);
        }
    }

    private long getCourseStarCount(long courseId) {
        return new com.sm64tracker.repository.StarRepository().findByCourseId(courseId).size();
    }

    private void openCourseDetail(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/course-detail-view.fxml"));
            Parent root = loader.load();
            CourseDetailController controller = loader.getController();
            controller.setCourse(course);

            Stage stage = (Stage) courseGrid.getScene().getWindow();
            stage.setScene(new Scene(root, 1200, 800));
            stage.getScene().getStylesheets().add(
                    Main.class.getResource("/css/styles.css").toExternalForm()
            );
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to open course detail screen.", exception);
        }
    }
}
