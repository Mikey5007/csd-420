import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class CardDisplay extends Application {

    private static final int TOTAL_CARDS = 52;
    private ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {

        HBox cardBox = new HBox(10);
        cardBox.setAlignment(Pos.CENTER);

        // Create 4 card slots
        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitWidth(100);
            cardViews[i].setPreserveRatio(true);
            cardBox.getChildren().add(cardViews[i]);
        }

        Button refreshBtn = new Button("Refresh");

        // Lambda expression
        refreshBtn.setOnAction(e -> displayRandomCards());

        VBox root = new VBox(15, cardBox, refreshBtn);
        root.setAlignment(Pos.CENTER);

        displayRandomCards();

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRandomCards() {
        List<Integer> deck = new ArrayList<>();

        for (int i = 1; i <= TOTAL_CARDS; i++) {
            deck.add(i);
        }

        Collections.shuffle(deck);

        for (int i = 0; i < 4; i++) {
            String path = "/Cards/" + deck.get(i) + ".png";
            Image image = new Image(getClass().getResource(path).toExternalForm());
            cardViews[i].setImage(image);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}