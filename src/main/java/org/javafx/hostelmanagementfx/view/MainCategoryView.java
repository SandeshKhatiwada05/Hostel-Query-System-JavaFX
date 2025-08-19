package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;

public class MainCategoryView {
    private final VBox root;

    public MainCategoryView(Main app) {
        root = new VBox(40);
        root.getStyleClass().add("page-root");
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.CENTER);

        Text title = new Text("Select Hostel Category");
        title.getStyleClass().add("app-title");

        Button boys = new Button("Boys Hostel");
        Button girls = new Button("Girls Hostel");

        boys.getStyleClass().addAll("category-btn", "cyan-gradient");
        girls.getStyleClass().addAll("category-btn", "gold-gradient");

        boys.setTooltip(new Tooltip("Explore boys hostels"));
        girls.setTooltip(new Tooltip("Explore girls hostels"));

        boys.setOnAction(e -> app.showHostelList("Boys"));
        girls.setOnAction(e -> app.showHostelList("Girls"));

        HBox row = new HBox(30, boys, girls);
        row.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, row);
    }

    public VBox getRoot() { return root; }
}
