package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;

public class MainCategoryView {

    private final StackPane root;

    public MainCategoryView(Main app) {
        root = new StackPane();

        // Background image from resources
        Image bg = new Image(getClass().getResource("/org/javafx/hostelmanagementfx/image/OptionPage.jpg").toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        root.setBackground(new Background(bgImage));

        // Semi-transparent overlay for contrast
        VBox overlay = new VBox(40);
        overlay.setAlignment(Pos.CENTER);
        overlay.setPadding(new Insets(50));
        overlay.setMaxWidth(500);
        overlay.setStyle("-fx-background-color: rgba(12,13,15,0.75); -fx-background-radius: 20;");

        // Title
        Text title = new Text("Select Hostel Category");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-fill: #FFD700;"); // gold

        // Boys Hostel Button
        Button boysBtn = new Button("Boys Hostel");
        styleCategoryButton(boysBtn);
        boysBtn.setTooltip(new Tooltip("View Boys Hostels"));
        boysBtn.setOnAction(e -> app.showHostelList("Boys"));

        // Girls Hostel Button
        Button girlsBtn = new Button("Girls Hostel");
        styleCategoryButton(girlsBtn);
        girlsBtn.setTooltip(new Tooltip("View Girls Hostels"));
        girlsBtn.setOnAction(e -> app.showHostelList("Girls"));

        overlay.getChildren().addAll(title, boysBtn, girlsBtn);
        root.getChildren().add(overlay);
    }

    private void styleCategoryButton(Button btn) {
        btn.setStyle(
                "-fx-background-radius: 20;" +
                        "-fx-background-color: linear-gradient(to right, #FF8C00, #FFA500);" + // orange-gold gradient
                        "-fx-text-fill: black;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 10, 0.2, 0, 4);"
        );
        btn.setPrefWidth(220);
        btn.setPrefHeight(60);

        // Hover effect
        btn.setOnMouseEntered(e -> btn.setScaleX(1.05));
        btn.setOnMouseEntered(e -> btn.setScaleY(1.05));
        btn.setOnMouseExited(e -> btn.setScaleX(1.0));
        btn.setOnMouseExited(e -> btn.setScaleY(1.0));
    }

    public StackPane getRoot() {
        return root;
    }
}
