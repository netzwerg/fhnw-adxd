package ch.fhnw.adxd.rating;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Demonstrates usage of the custom {@link RatingComponent}.
 *
 * @author Rahel Lüthy
 */
public final class RatingApp extends Application {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private static final int INITIAL_RATING = 3;

    @Override
    public void start(Stage stage) {
        Slider slider = createSlider();
        RatingComponent ratingComponent = new RatingComponent(INITIAL_RATING);
        slider.valueProperty().bindBidirectional(ratingComponent.ratingProperty());

        HBox top = new HBox(new Label("Rating: "), slider);
        top.setPadding(new Insets(10));
        top.setAlignment(Pos.TOP_CENTER);

        BorderPane rootPane = new BorderPane();
        rootPane.setTop(top);
        rootPane.setCenter(ratingComponent);

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("JavaFX Rating Demo");
        stage.show();
    }

    private static Slider createSlider() {
        Slider slider = new Slider(1, RatingComponent.MAX_RATING, INITIAL_RATING);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        return slider;
    }

    public static void main(String... args) {
        launch(args);
    }

}
