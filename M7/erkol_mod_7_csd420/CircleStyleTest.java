/*
 * Name: Mirach Erkol
 * Course: CSD420
 * Module: 7
 * Assignment: 7.2
 * Description: This JavaFX program displays four circles and applies
 * an external CSS style sheet. The style class sets white fill and
 * black stroke, and the style IDs set red and green colors.
 * Test code is included to verify the program is set up correctly.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyleTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox hBox = new HBox(10);
        Scene scene = new Scene(hBox, 400, 250);

        scene.getStylesheets().add("mystyle.css");

        Pane pane1 = new Pane();
        Circle circle1 = new Circle(50, 50, 30);
        Circle circle2 = new Circle(150, 50, 30);
        Circle circle3 = new Circle(100, 130, 30);

        circle1.getStyleClass().add("plaincircle");
        circle2.getStyleClass().add("plaincircle");
        circle3.setId("redcircle");

        pane1.getChildren().addAll(circle1, circle2, circle3);

        Pane pane2 = new Pane();
        Circle circle4 = new Circle(100, 100, 30);
        circle4.setId("greencircle");
        pane2.getChildren().add(circle4);

        hBox.getChildren().addAll(pane1, pane2);

        runTests(circle1, circle2, circle3, circle4, pane1, pane2, scene);

        primaryStage.setTitle("Module 7 Assignment 7.2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void runTests(Circle c1, Circle c2, Circle c3, Circle c4,
                          Pane pane1, Pane pane2, Scene scene) {

        if (c1.getRadius() != 30 || c2.getRadius() != 30
                || c3.getRadius() != 30 || c4.getRadius() != 30) {
            throw new AssertionError("Test failed: one or more circles do not have radius 30.");
        }

        if (!c1.getStyleClass().contains("plaincircle")
                || !c2.getStyleClass().contains("plaincircle")) {
            throw new AssertionError("Test failed: plaincircle class not applied correctly.");
        }

        if (!"redcircle".equals(c3.getId())) {
            throw new AssertionError("Test failed: circle3 does not have ID redcircle.");
        }

        if (!"greencircle".equals(c4.getId())) {
            throw new AssertionError("Test failed: circle4 does not have ID greencircle.");
        }

        if (pane1.getChildren().size() != 3 || pane2.getChildren().size() != 1) {
            throw new AssertionError("Test failed: circles were not added to panes correctly.");
        }

        if (scene.getStylesheets().isEmpty()) {
            throw new AssertionError("Test failed: stylesheet was not loaded.");
        }

        System.out.println("Test passed: all circles and CSS assignments are correct.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}