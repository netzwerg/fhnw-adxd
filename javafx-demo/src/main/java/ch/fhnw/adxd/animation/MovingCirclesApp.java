package ch.fhnw.adxd.animation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Demonstrates usage of the custom {@link MovingCirclesComponent}.
 *
 * @author Rahel Lüthy
 */
public class MovingCirclesApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        MovingCirclesComponent circlesComponent = new MovingCirclesComponent(10, 500);
        Scene scene = new Scene(new BorderPane(circlesComponent));
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
