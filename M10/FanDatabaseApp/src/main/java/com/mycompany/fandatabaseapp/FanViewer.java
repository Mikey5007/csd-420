package com.mycompany.fandatabaseapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FanViewer extends Application {

    private final TextField idField = new TextField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField favoriteTeamField = new TextField();

    private final DBHelper db = new DBHelper();

    @Override
    public void start(Stage stage) {
        Label idLabel = new Label("ID:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label favoriteTeamLabel = new Label("Favorite Team:");

        Button displayButton = new Button("Display");
        Button updateButton = new Button("Update");

        displayButton.setOnAction(e -> displayFan());
        updateButton.setOnAction(e -> updateFan());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(15));
        pane.setHgap(10);
        pane.setVgap(10);

        pane.add(idLabel, 0, 0);
        pane.add(idField, 1, 0);
        pane.add(displayButton, 2, 0);

        pane.add(firstNameLabel, 0, 1);
        pane.add(firstNameField, 1, 1);

        pane.add(lastNameLabel, 0, 2);
        pane.add(lastNameField, 1, 2);

        pane.add(favoriteTeamLabel, 0, 3);
        pane.add(favoriteTeamField, 1, 3);

        pane.add(updateButton, 1, 4);

        Scene scene = new Scene(pane, 500, 220);
        stage.setTitle("Fan Database Viewer");
        stage.setScene(scene);
        stage.show();
    }

    private void displayFan() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Fan fan = db.getFanById(id);

            if (fan == null) {
                showMessage("Fan not found.");
                firstNameField.clear();
                lastNameField.clear();
                favoriteTeamField.clear();
                return;
            }

            firstNameField.setText(fan.getFirstName());
            lastNameField.setText(fan.getLastName());
            favoriteTeamField.setText(fan.getFavoriteTeam());
        } catch (NumberFormatException e) {
            showMessage("Enter a valid numeric ID.");
        }
    }

    private void updateFan() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            Fan fan = new Fan(
                    id,
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    favoriteTeamField.getText().trim()
            );

            boolean updated = db.updateFan(fan);

            if (updated) {
                showMessage("Record updated successfully.");
            } else {
                showMessage("Update failed. Check the ID.");
            }
        } catch (NumberFormatException e) {
            showMessage("Enter a valid numeric ID.");
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}