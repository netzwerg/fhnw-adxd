package ch.fhnw.adxd.custom;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Demonstrates usage of {@link CustomComponent}.
 *
 * @author Rahel Lüthy
 */
public final class CustomComponentApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        CustomComponent customComponent = new CustomComponent();

        Pane rootPane = new StackPane(customComponent);
        rootPane.setPadding(new Insets(10));

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("JavaFX Custom Component Demo");
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }

}
