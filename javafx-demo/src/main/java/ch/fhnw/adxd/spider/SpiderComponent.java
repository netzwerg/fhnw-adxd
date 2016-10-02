package ch.fhnw.adxd.spider;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * A custom component based on existing JavaFX shapes.
 * <p>
 * Draws a spider centered at the current mouse location, with legs connected to surrounding points within a given
 * range.
 * </p>
 *
 * @author Rahel Lüthy
 */
public final class SpiderComponent extends Region {

    public static final int DEFAULT_LEG_RANGE = 50;

    private static final int POINT_COUNT = 500;
    private static final int POINT_RADIUS = 2;
    private static final Color SPIDER_COLOR = Color.RED;

    private final IntegerProperty legRange = new SimpleIntegerProperty(DEFAULT_LEG_RANGE);

    public SpiderComponent() {
        List<Circle> points = createRandomPointCircles(POINT_COUNT);
        Circle spiderCenter = createSpiderCenter();
        Group spiderLegs = new Group();

        getChildren().addAll(points);
        getChildren().add(spiderCenter);
        getChildren().add(spiderLegs);

        setOnMouseMoved(mouseEvent -> {
            Point2D mouseLocation = new Point2D(mouseEvent.getX(), mouseEvent.getY());

            updateSpiderCenter(spiderCenter, mouseLocation);
            updateLegs(points, spiderCenter, spiderLegs);
        });

        this.legRange.addListener(cl -> updateLegs(points, spiderCenter, spiderLegs));

        setClip(clipToDimensions());
        widthProperty().addListener(cl -> spiderLegs.getChildren().clear());
        heightProperty().addListener(cl -> spiderLegs.getChildren().clear());
    }

    public IntegerProperty legRangeProperty() {
        return legRange;
    }

    private void updateSpiderCenter(Circle spiderCircle, Point2D mouseLocation) {
        spiderCircle.setCenterX(mouseLocation.getX());
        spiderCircle.setCenterY(mouseLocation.getY());
    }

    private void updateLegs(List<Circle> points, Circle spiderCircle, Group spiderLegs) {
        List<Line> legs = createLegs(points, spiderCircle);
        spiderLegs.getChildren().setAll(legs);
    }

    private List<Line> createLegs(List<Circle> pointCircles, Circle spiderCircle) {
        Point2D spiderCenter = new Point2D(spiderCircle.getCenterX(), spiderCircle.getCenterY());
        List<Point2D> points = pointCircles.stream().map(c -> new Point2D(c.getCenterX(), c.getCenterY())).collect(toList());
        List<Point2D> pointsWithinRange = findPointsWithinRange(points, spiderCenter, legRange.get());
        return createSpiderLegs(spiderCenter, pointsWithinRange);
    }

    private Node clipToDimensions() {
        Rectangle clipRect = new Rectangle(getWidth(), getHeight());
        clipRect.heightProperty().bind(heightProperty());
        clipRect.widthProperty().bind(widthProperty());
        return clipRect;
    }

    private List<Circle> createRandomPointCircles(int pointCount) {
        return IntStream.range(0, pointCount).mapToObj(i -> createRandomPointCircle()).collect(toList());
    }

    private Circle createRandomPointCircle() {
        double x = Math.random();
        double y = Math.random();
        Circle circle = new Circle(x, y, POINT_RADIUS);
        circle.centerXProperty().bind(Bindings.multiply(x, widthProperty()));
        circle.centerYProperty().bind(Bindings.multiply(y, heightProperty()));
        return circle;
    }

    private static Circle createSpiderCenter() {
        Circle spiderCenter = new Circle(POINT_RADIUS);
        spiderCenter.setFill(SPIDER_COLOR);
        return spiderCenter;
    }

    private static List<Line> createSpiderLegs(Point2D spiderCenter, List<Point2D> legEndPoints) {
        return legEndPoints.stream().map(p -> createSpiderLeg(spiderCenter, p)).collect(toList());
    }

    private static Line createSpiderLeg(Point2D fromPoint, Point2D toPoint) {
        Line leg = new Line();

        leg.setStartX(fromPoint.getX());
        leg.setStartY(fromPoint.getY());

        leg.setEndX(toPoint.getX());
        leg.setEndY(toPoint.getY());

        leg.setStroke(SPIDER_COLOR);
        return leg;
    }

    private static List<Point2D> findPointsWithinRange(List<Point2D> points, Point2D center, double range) {
        Predicate<Point2D> isWithinRange = point -> center.distance(point) < range;
        return points.stream().filter(isWithinRange).collect(toList());
    }

}