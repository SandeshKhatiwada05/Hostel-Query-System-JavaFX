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
    private final BorderPane root;

    public HostelDetailsView(Main app, Hostel hostel) {
        root = new BorderPane();
        root.getStyleClass().add("page-root");
        root.setPadding(new Insets(20));

        // Top
        Text title = new Text(hostel.getName());
        title.getStyleClass().add("page-title");
        HBox top = new HBox(title);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10));
        root.setTop(top);

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
        center.getStyleClass().addAll("card", "glass");
        center.setPadding(new Insets(20));
        root.setCenter(center);

        // Right side actions
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
        contact.setOnAction(e -> app.showEmailSimulator("khatiwadasandesh501@gmail.com"));

        VBox right = new VBox(15, book, contact);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(10));
        root.setRight(right);

        // Bottom contact info + back
        Label info = new Label("For enquiry call: 9826834380");
        info.getStyleClass().add("muted");

        Button back = new Button("Back");
        back.getStyleClass().addAll("accent-btn", "tiny");
        back.setOnAction(e -> app.showHostelList(hostel.getCategory()));

        HBox bottom = new HBox(20, back, info);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));
        root.setBottom(bottom);
    }

    public BorderPane getRoot() { return root; }
}
