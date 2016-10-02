package ch.fhnw.adxd.custom;

import javafx.scene.layout.Region;

// tag::custom-component[]
public class CustomComponent extends Region {

    public CustomComponent() {
        getStyleClass().setAll("custom-component");
    }

    @Override
    public String getUserAgentStylesheet() {
        return CustomComponent.class.getResource("custom-component.css").toExternalForm();
    }

}
// end::custom-component[]