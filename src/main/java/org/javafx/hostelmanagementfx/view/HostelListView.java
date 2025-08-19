package org.javafx.hostelmanagementfx.view;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.javafx.hostelmanagementfx.Main;
import org.javafx.hostelmanagementfx.model.Hostel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HostelListView {
    private final BorderPane root;
    private final List<Hostel> allHostels = new ArrayList<>();
    private final FilteredList<Hostel> filtered;

    public HostelListView(Main app, String category) {
        root = new BorderPane();
        root.getStyleClass().add("page-root");
        root.setPadding(new Insets(20));

        // Title & controls
        Text title = new Text(category + " Hostels");
        title.getStyleClass().add("page-title");

        TextField search = new TextField();
        search.setPromptText("Search by name...");
        search.getStyleClass().add("rounded-input");

        ComboBox<String> sortBox = new ComboBox<>();
        sortBox.getItems().addAll("Sort by Price: Low to High", "Sort by Price: High to Low");
        sortBox.setPromptText("Sort");
        sortBox.getStyleClass().add("rounded-combo");

        HBox controls = new HBox(10, search, sortBox);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        VBox top = new VBox(10, title, controls);
        top.setAlignment(Pos.CENTER);
        root.setTop(top);

        // Data
        seedData();
        List<Hostel> categoryHostels = allHostels.stream()
                .filter(h -> h.getCategory().equalsIgnoreCase(category))
                .toList();

        filtered = new FilteredList<>(FXCollections.observableArrayList(categoryHostels), h -> true);

        // Cards area (responsive)
        FlowPane cards = new FlowPane();
        cards.setHgap(20);
        cards.setVgap(20);
        cards.setAlignment(Pos.TOP_CENTER);
        cards.setPadding(new Insets(20));

        // render function
        Runnable render = () -> {
            cards.getChildren().clear();
            for (Hostel h : filtered) {
                VBox card = buildCard(app, h);
                cards.getChildren().add(card);

                FadeTransition ft = new FadeTransition(Duration.millis(450), card);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
            }
        };

        // search & sort behavior
        search.textProperty().addListener((obs, old, q) -> {
            String query = q == null ? "" : q.trim().toLowerCase();
            filtered.setPredicate(h -> h.getName().toLowerCase().contains(query));
            render.run();
        });

        sortBox.setOnAction(e -> {
            String s = sortBox.getValue();
            if (s == null) return;
            if (s.contains("Low to High")) {
                filtered.getSource().sort(Comparator.comparingDouble(Hostel::getPrice1));
            } else {
                filtered.getSource().sort(Comparator.comparingDouble(Hostel::getPrice1).reversed());
            }
            render.run();
        });

        render.run();
        ScrollPane sp = new ScrollPane(cards);
        sp.setFitToWidth(true);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.getStyleClass().add("transparent-scroll");
        root.setCenter(sp);

        // Back
        Button back = new Button("Back");
        back.getStyleClass().addAll("accent-btn", "outline-btn");
        back.setOnAction(e -> app.showMainPage());

        HBox bottom = new HBox(back);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));
        root.setBottom(bottom);
    }

    private VBox buildCard(Main app, Hostel h) {
        VBox card = new VBox(10);
        card.getStyleClass().addAll("card", "glass");
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(12));
        card.setPrefWidth(220);
        card.setCache(true);
        card.setCacheHint(CacheHint.SPEED);

        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(200);
        iv.setFitHeight(130);
        try {
            iv.setImage(new Image(h.getImageUrl(), 200, 130, true, true));
        } catch (Exception ignored) {}

        Label name = new Label(h.getName());
        name.getStyleClass().add("card-title");

        Button viewBtn = new Button("View Details");
        viewBtn.getStyleClass().addAll("accent-btn", "tiny");
        viewBtn.setOnAction(e -> app.showHostelDetails(h));

        card.getChildren().addAll(iv, name, viewBtn);
        card.setOnMouseEntered(e -> card.setScaleX(1.02));
        card.setOnMouseExited(e -> card.setScaleX(1.0));

        return card;
    }

    private void seedData() {
        // Boys (4)
        allHostels.add(new Hostel("Royal Stay", "https://picsum.photos/seed/rs/400/250", 5000, 4000, 3500, 3000, "Boys"));
        allHostels.add(new Hostel("Elite Hostel", "https://picsum.photos/seed/eh/400/250", 6000, 4500, 4000, 3200, "Boys"));
        allHostels.add(new Hostel("Campus Inn", "https://picsum.photos/seed/ci/400/250", 4500, 3800, 3300, 2800, "Boys"));
        allHostels.add(new Hostel("Urban Nest", "https://picsum.photos/seed/un/400/250", 5500, 4200, 3700, 3100, "Boys"));

        // Girls (4)
        allHostels.add(new Hostel("Lily Hostel", "https://picsum.photos/seed/lh/400/250", 5200, 4100, 3600, 3050, "Girls"));
        allHostels.add(new Hostel("Grace Stay", "https://picsum.photos/seed/gs/400/250", 5800, 4400, 3900, 3300, "Girls"));
        allHostels.add(new Hostel("Serenity Home", "https://picsum.photos/seed/sh/400/250", 5400, 4200, 3700, 3150, "Girls"));
        allHostels.add(new Hostel("Paradise Girls", "https://picsum.photos/seed/pg/400/250", 6200, 4800, 4100, 3500, "Girls"));
    }

    public BorderPane getRoot() { return root; }
}
