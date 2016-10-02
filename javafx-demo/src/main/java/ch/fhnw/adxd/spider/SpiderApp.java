package ch.fhnw.adxd.spider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Demonstrates usage of the custom {@link SpiderComponent}.
 *
 * @author Rahel Lüthy
 */
public final class SpiderApp extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        SpiderComponent spiderComponent = new SpiderComponent();
        Scene scene = new Scene(spiderComponent);

        stage.setScene(scene);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setTitle("JavaFX Spider Demo");
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }

}
