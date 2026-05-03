/*
 * Name: Mirach Erkol
 * Course: CSD420
 * Module: 8
 * Assignment: 8.2
 * Description: This JavaFX program uses three threads to display random
 * letters, digits, and special characters in a TextArea. Each thread
 * outputs 10,000 characters one at a time as they are generated.
 * Test code is included to verify the random character methods work correctly.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

public class MirachThreeThreads extends Application {

    private static final int CHARACTER_COUNT = 10000;
    private static final String SYMBOLS = "!@#$%&*^";

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);

        runTests();

        Thread letterThread = new Thread(() -> outputCharacters(textArea, "LETTER"));
        Thread digitThread = new Thread(() -> outputCharacters(textArea, "DIGIT"));
        Thread symbolThread = new Thread(() -> outputCharacters(textArea, "SYMBOL"));

        letterThread.setDaemon(true);
        digitThread.setDaemon(true);
        symbolThread.setDaemon(true);

        letterThread.start();
        digitThread.start();
        symbolThread.start();

        BorderPane pane = new BorderPane();
        pane.setCenter(textArea);

        Scene scene = new Scene(pane, 700, 450);
        primaryStage.setTitle("Module 8 Assignment 8.2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void outputCharacters(TextArea textArea, String type) {
        for (int i = 0; i < CHARACTER_COUNT; i++) {
            char ch;

            switch (type) {
                case "LETTER":
                    ch = getRandomLetter();
                    break;
                case "DIGIT":
                    ch = getRandomDigit();
                    break;
                case "SYMBOL":
                    ch = getRandomSymbol();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }

            final char outputChar = ch;
            Platform.runLater(() -> textArea.appendText(String.valueOf(outputChar)));

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private char getRandomLetter() {
        return (char) ('a' + ThreadLocalRandom.current().nextInt(26));
    }

    private char getRandomDigit() {
        return (char) ('0' + ThreadLocalRandom.current().nextInt(10));
    }

    private char getRandomSymbol() {
        return SYMBOLS.charAt(ThreadLocalRandom.current().nextInt(SYMBOLS.length()));
    }

    private void runTests() {
        for (int i = 0; i < 1000; i++) {
            char letter = getRandomLetter();
            if (!Character.isLetter(letter)) {
                throw new AssertionError("Letter test failed.");
            }

            char digit = getRandomDigit();
            if (!Character.isDigit(digit)) {
                throw new AssertionError("Digit test failed.");
            }

            char symbol = getRandomSymbol();
            if (SYMBOLS.indexOf(symbol) == -1) {
                throw new AssertionError("Symbol test failed.");
            }
        }

        System.out.println("Test passed: all random character methods work correctly.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}