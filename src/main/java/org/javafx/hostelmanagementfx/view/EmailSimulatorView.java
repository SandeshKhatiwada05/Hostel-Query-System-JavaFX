package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;

public class EmailSimulatorView {
    private final StackPane root;

    public EmailSimulatorView(Main app, String toEmail) {
        root = new StackPane();
        root.getStyleClass().add("page-root");

        // ===== Background =====
        Image bg = new Image(
                getClass().getResource("/org/javafx/hostelmanagementfx/image/EmailBG.png").toExternalForm()
        );
        BackgroundImage bgImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        root.setBackground(new Background(bgImage));

        // ===== Title =====
        Text title = new Text("Compose Email");
        title.getStyleClass().add("page-title");

        // ===== Fields =====
        TextField to = new TextField(toEmail);
        to.setEditable(false);
        to.getStyleClass().add("rounded-input");

        TextArea message = new TextArea();
        message.setPromptText("Type your message here...");
        message.setPrefRowCount(8);
        message.getStyleClass().add("rounded-area");

        // ===== Buttons =====
        Button send = new Button("Send");
        send.getStyleClass().addAll("accent-btn", "glow-btn");
        send.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Email Sent!", ButtonType.OK);
            a.setHeaderText(null);
            a.setTitle("Email");
            a.showAndWait();
        });

        Button back = new Button("Back to Details");
        back.getStyleClass().addAll("accent-btn", "tiny", "outline-btn");
        back.setOnAction(e -> app.backFromEmail());

        HBox buttons = new HBox(12, send, back);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        // ===== Card (Overlay) =====
        VBox card = new VBox(15,
                title,
                new Label("To:"), to,
                new Label("Message:"), message,
                buttons
        );
        card.setAlignment(Pos.CENTER_LEFT);
        card.getStyleClass().addAll("card", "glass");
        card.setPadding(new Insets(25));
        card.setMaxWidth(500); // prevent full stretch
        card.setMinWidth(350);

        // ===== Center Layout =====
        VBox centerBox = new VBox(card);
        centerBox.setAlignment(Pos.CENTER);

        root.getChildren().add(centerBox);
    }

    public StackPane getRoot() { return root; }
}
