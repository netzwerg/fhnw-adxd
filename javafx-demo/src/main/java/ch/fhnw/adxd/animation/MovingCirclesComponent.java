package ch.fhnw.adxd.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A custom component based on existing JavaFX shapes.
 * <p>
 * Draws circles oscillating on lines. The fact that each trajectory is rotated, and each circle locations is offset
 * systematically, gives the optical illusion that all circles are themselves forming a rotating wheel.
 * </p>
 *
 * @author Rahel Lüthy
 */
final class MovingCirclesComponent extends Pane {

    private static final Color CIRCLE_COLOR = Color.ORANGE;
    private static final double RELATIVE_CIRCLE_SIZE = 0.05;
    private static final double SPEED = 0.5;

    MovingCirclesComponent(int circleCount, int dimension) {
        setMaxSize(dimension, dimension);

        Circle backgroundCircle = new Circle(dimension / 2, dimension / 2, dimension / 2);
        List<MovingCircle> movingCircles = createMovingCircles(circleCount, dimension);

        getChildren().add(backgroundCircle);
        getChildren().addAll(movingCircles);

        final long startTimeNano = System.nanoTime();
        AnimationTimer animationLoop = new AnimationTimer() {

            @Override
            public void handle(long currentTimeNano) {
                double elapsedTimeMillis = (currentTimeNano - startTimeNano) / 1_000_000_000d;
                movingCircles.forEach(pair -> pair.update(elapsedTimeMillis));
            }

        };

        animationLoop.start();
    }

    private static List<MovingCircle> createMovingCircles(int circleCount, double dimension) {
        return IntStream.range(0, circleCount)
                .mapToObj(i -> createMovingCircle(i, circleCount, dimension))
                .collect(Collectors.toList());
    }

    private static MovingCircle createMovingCircle(int index, int circleCount, double dimension) {
        double y = dimension / 2;
        double advance = index * Math.PI / circleCount;
        MovingCircle movingCircle = new MovingCircle(y, dimension, advance);
        double angle = index * (180 / circleCount);
        movingCircle.setRotate(angle);
        return movingCircle;
    }

    /**
     * A circle oscillating on an invisible line.
     */
    private static final class MovingCircle extends Group {

        private final Circle circle;
        private final double width;
        private final double timeAdvance;

        private MovingCircle(double centerY, double width, double timeAdvance) {
            this.width = width;
            this.timeAdvance = timeAdvance;

            Line line = new Line(0, centerY, width, centerY);
            line.setStroke(Color.TRANSPARENT);

            circle = new Circle();
            circle.setFill(CIRCLE_COLOR);
            circle.setRadius(width * RELATIVE_CIRCLE_SIZE);
            circle.setCenterY(centerY);

            getChildren().addAll(line, circle);
        }

        void update(double elapsedTimeMillis) {
            double rangeOfMotion = width / 2 - circle.getRadius();
            double t = elapsedTimeMillis * SPEED * Math.PI + timeAdvance;
            double x = rangeOfMotion * Math.cos(t) + rangeOfMotion + circle.getRadius();
            circle.setCenterX(x);
        }

    }

}