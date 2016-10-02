package ch.fhnw.adxd.spider;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static ch.fhnw.adxd.spider.SpiderComponent.DEFAULT_LEG_RANGE;

/**
 * Demonstrates how the custom {@link SpiderComponent} can be configured via a standard {@link Slider}.
 *
 * @author Rahel Lüthy
 */
public final class ConfigurableSpiderApp extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        Slider slider = createSlider();

        SpiderComponent spiderComponent = new SpiderComponent();
        spiderComponent.legRangeProperty().bind(slider.valueProperty());

        HBox labeledSlider = combineWithLabel(slider);

        BorderPane rootPane = new BorderPane();
        rootPane.setTop(labeledSlider);
        rootPane.setCenter(spiderComponent);

        Scene scene = new Scene(rootPane);

        stage.setScene(scene);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setTitle("JavaFX Spider Demo");
        stage.show();
    }

    private static Slider createSlider() {
        Slider slider = new Slider(0, 300, DEFAULT_LEG_RANGE);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(100);
        return slider;
    }

    private static HBox combineWithLabel(Slider slider) {
        HBox top = new HBox(new Label("Leg Range: "), slider);
        top.setPadding(new Insets(10));
        top.setAlignment(Pos.TOP_LEFT);
        return top;
    }

    public static void main(String... args) {
        launch(args);
    }

}
