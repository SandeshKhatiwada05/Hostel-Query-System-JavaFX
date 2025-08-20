package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;
import org.javafx.hostelmanagementfx.model.Hostel;

public class HostelDetailsView {
    private final StackPane root;

    public HostelDetailsView(Main app, Hostel hostel) {
        root = new StackPane();

        // Background image (dark-themed)
        Image bg = new Image(
                getClass().getResource("/org/javafx/hostelmanagementfx/image/OptionPage.jpg").toExternalForm()
        );
        BackgroundImage bgImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        root.setBackground(new Background(bgImage));

        // Overlay panel for content
        BorderPane overlay = new BorderPane();
        overlay.setPadding(new Insets(20));
        overlay.setStyle("-fx-background-color: rgba(12,13,15,0.85); -fx-background-radius: 20;");

        // Top: Hostel Name
        Text title = new Text(hostel.getName());
        title.getStyleClass().add("page-title");
        HBox top = new HBox(title);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10));
        overlay.setTop(top);

        // Center: Image + Prices
        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitWidth(400);
        iv.setFitHeight(260);
        try {
            iv.setImage(new Image(hostel.getImageUrl(), 400, 260, true, true));
        } catch (Exception ignored) {}

        GridPane prices = new GridPane();
        prices.setHgap(20);
        prices.setVgap(10);

        String[] types = {"1-Seater", "2-Seater", "3-Seater", "4-Seater"};
        double[] vals  = {hostel.getPrice1(), hostel.getPrice2(), hostel.getPrice3(), hostel.getPrice4()};

        for (int i = 0; i < 4; i++) {
            Label t = new Label(types[i] + ":");
            t.getStyleClass().add("muted");
            Label v = new Label("Rs " + (int) vals[i]);
            v.getStyleClass().add("price");
            prices.addRow(i, t, v);
        }

        VBox center = new VBox(20, iv, prices);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20));
        overlay.setCenter(center);

        // Right-side actions
        Button book = new Button("Book Now");
        book.getStyleClass().addAll("accent-btn", "large");
        book.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Booking Successful!", ButtonType.OK);
            a.setHeaderText(null);
            a.setTitle("Booking");
            a.showAndWait();
        });

        Button contact = new Button("Contact Owner");
        contact.getStyleClass().addAll("accent-btn", "large", "outline-btn");
        contact.setOnAction(e -> {
            try {
                app.showEmailSimulator("khatiwadasandesh501@gmail.com");
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Could not open email simulator!").show();
            }
        });

        VBox right = new VBox(15, book, contact);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(10));
        overlay.setRight(right);

        // Bottom: info + back
        Label info = new Label("For enquiry call: 9826834380");
        info.getStyleClass().add("muted");

        Button back = new Button("Back");
        back.getStyleClass().addAll("accent-btn", "tiny");
        back.setOnAction(e -> app.showHostelList(hostel.getCategory()));

        HBox bottom = new HBox(20, back, info);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));
        overlay.setBottom(bottom);

        root.getChildren().add(overlay);
    }

    public StackPane getRoot() {
        return root;
    }
}
