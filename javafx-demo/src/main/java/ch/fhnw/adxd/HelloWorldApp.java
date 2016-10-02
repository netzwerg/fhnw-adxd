package ch.fhnw.adxd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Demonstrates the basic structure of a JavaFX application.
 *
 * @author Rahel Lüthy
 */
public final class HelloWorldApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    @Override
    // tag::hello-world[]
    public void start(Stage stage) {
        Circle circle = new Circle(100, Color.ORANGE);
        Button button = new Button("Click Me");

        Pane layoutContainer = new StackPane(circle, button);

        Scene scene = new Scene(layoutContainer);
        stage.setScene(scene);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("JavaFX Hello World");
        stage.show();
    }
    // end::hello-world[]

    public static void main(String... args) {
        launch(args);
    }

}
