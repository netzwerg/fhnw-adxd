package ch.fhnw.adxd.toggle;

import ch.fhnw.adxd.custom.CustomComponent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;

/**
 * A custom component with two different states, selected or unselected. Its state can be toggled via mouse click
 * or programmatically, via {@link #selectedProperty()}. The "selected" CSS pseudo class can be used to provide a
 * selection-specific style.
 *
 * @author Rahel Lüthy
 */
public class ToggleComponent extends CustomComponent {

    private static final PseudoClass PSEUDO_CLASS_SELECTED = PseudoClass.getPseudoClass("selected");

    /**
     * Creates a new instance which is unselected by default.
     */
    public ToggleComponent() {
        selected = new BooleanPropertyBase() {
            @Override
            protected void invalidated() {
                pseudoClassStateChanged(PSEUDO_CLASS_SELECTED, get());
            }

            @Override
            public Object getBean() {
                return ToggleComponent.this;
            }

            @Override
            public String getName() {
                return PSEUDO_CLASS_SELECTED.getPseudoClassName();
            }
        };

        setOnMouseClicked(e -> setSelected(!isSelected()));
    }


    /**
     * Indicates whether this toggle component is selected. This can be manipulated
     * programmatically.
     */
    private final BooleanProperty selected;
    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    @Override
    public String getUserAgentStylesheet() {
        return ToggleComponent.class.getResource("toggle-component.css").toExternalForm();
    }

}