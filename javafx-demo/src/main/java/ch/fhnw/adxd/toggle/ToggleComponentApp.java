package ch.fhnw.adxd.toggle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Demonstrates usage of {@link ToggleComponent}.
 *
 * @author Rahel Lüthy
 */
public final class ToggleComponentApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        ToggleComponent toggleComponent = new ToggleComponent();

        Pane rootPane = new StackPane(toggleComponent);
        rootPane.setPadding(new Insets(60));

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle("JavaFX Toggle Component Demo");
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }

}
