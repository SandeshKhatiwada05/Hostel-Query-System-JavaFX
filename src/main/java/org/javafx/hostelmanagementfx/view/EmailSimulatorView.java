package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;

import java.net.URL;

public class EmailSimulatorView {
    private final StackPane root;

    public EmailSimulatorView(Main app, String toEmail) {
        root = new StackPane();
        root.getStyleClass().add("page-root");

        // ===== Safe Background =====
        String bgPath = "/org/javafx/hostelmanagementfx/image/EmailBG.png";
        try {
            URL url = getClass().getResource(bgPath);
            if (url != null) {
                Image bg = new Image(url.toExternalForm());
                BackgroundImage bgImage = new BackgroundImage(
                        bg,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)
                );
                root.setBackground(new Background(bgImage));
            } else {
                System.err.println("Email background not found at: " + bgPath);
                root.setStyle("-fx-background-color: #0c0d0f;");
            }
        } catch (Exception ex) {
            System.err.println("Failed to load EmailBG.png: " + ex);
            root.setStyle("-fx-background-color: #0c0d0f;");
        }

        // ===== Title =====
        Text title = new Text("Compose Email");
        title.getStyleClass().add("page-title");

        // ===== Fields =====
        Label toLbl = new Label("To:");
        toLbl.getStyleClass().add("field-label");

        TextField to = new TextField(toEmail);
        to.setEditable(false);
        to.getStyleClass().add("rounded-input");

        Label msgLbl = new Label("Message:");
        msgLbl.getStyleClass().add("field-label");

        TextArea message = new TextArea();
        message.setPromptText("Type your message here...");
        message.setPrefRowCount(8);
        message.getStyleClass().addAll("rounded-area");
        // Ensure readable text + a dark inner background so white text doesn't disappear
        message.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-control-inner-background: rgba(255,255,255,0.06); " +
                        "-fx-background-insets: 0; " +
                        "-fx-highlight-fill: derive(#ffd27a, -20%); " +
                        "-fx-highlight-text-fill: black;"
        );

        // ===== Buttons =====
        Button send = new Button("Send");
        send.getStyleClass().addAll("accent-btn", "glow-btn");
        send.setOnAction(e -> {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Email Sent");
            dialog.setHeaderText(null);

            DialogPane pane = dialog.getDialogPane();
            pane.setStyle(
                    "-fx-background-color: rgba(12,13,15,0.92); " +
                            "-fx-border-color: linear-gradient(to right, #00c6ff, #0072ff); " +
                            "-fx-border-width: 2; -fx-border-radius: 12; -fx-background-radius: 12;"
            );
            Label content = new Label("✅ Your email was sent successfully!");
            content.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: 600;");
            pane.setContent(content);

            pane.getButtonTypes().add(ButtonType.OK);
            Button ok = (Button) pane.lookupButton(ButtonType.OK);
            ok.setStyle(
                    "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);" +
                            "-fx-text-fill: black; -fx-background-radius: 10; -fx-font-weight: bold;"
            );
            dialog.showAndWait();
        });

        Button back = new Button("Back to Details");
        back.getStyleClass().addAll("accent-btn", "tiny", "outline-btn");
        back.setOnAction(e -> app.backFromEmail());

        HBox buttons = new HBox(12, send, back);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        // ===== Card (Overlay) =====
        VBox card = new VBox(15,
                title,
                toLbl, to,
                msgLbl, message,
                buttons
        );
        card.setAlignment(Pos.CENTER_LEFT);
        card.getStyleClass().addAll("card", "glass", "email-card");
        card.setPadding(new Insets(25));
        card.setMaxWidth(520);
        card.setMinWidth(360);

        VBox centerBox = new VBox(card);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(30));
        root.getChildren().add(centerBox);
    }

    public StackPane getRoot() { return root; }
}
