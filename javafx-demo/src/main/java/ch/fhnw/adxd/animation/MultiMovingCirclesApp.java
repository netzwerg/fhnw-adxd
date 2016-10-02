package ch.fhnw.adxd.animation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Demonstrates usage of the custom {@link MovingCirclesComponent}.
 * <p>
 * In particular, shows how individual circles move and how the number
 * of moving circles influences the optical illusion.
 * </p>
 *
 * @author Rahel Lüthy
 */
public class MultiMovingCirclesApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.add(new MovingCirclesComponent(1, 190), 0, 0);
        pane.add(new MovingCirclesComponent(2, 190), 1, 0);
        pane.add(new MovingCirclesComponent(5, 190), 0, 1);
        pane.add(new MovingCirclesComponent(10, 190), 1, 1);
        pane.setPadding(new Insets(5));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setTitle("JavaFX Moving Circles Demo");
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }

}
