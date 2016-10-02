package ch.fhnw.adxd.rating;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.HBox;

public final class RatingComponent extends HBox {

    public static final int MAX_RATING = 5;

    RatingComponent(int initialRating) {

        // TODO: Implementation

    }

    private final IntegerProperty rating = new SimpleIntegerProperty();

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public int getRating() {
        return rating.get();
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

}
