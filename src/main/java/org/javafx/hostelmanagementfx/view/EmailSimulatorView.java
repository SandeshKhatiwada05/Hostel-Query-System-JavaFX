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
        String bgPath = "/org/javafx/hostelmanagementfx/image/EmailBG.jpg"; // <-- Leading slash!
        try {
            Image bg = new Image(getClass().getResource(bgPath).toExternalForm());
            BackgroundImage bgImage = new BackgroundImage(
                    bg,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1.0, 1.0, true, true, false, false)
            );
            root.setBackground(new Background(bgImage));
        } catch (Exception ex) {
            System.err.println("Failed to load EmailBG.jpg: " + ex);
            root.setStyle("-fx-background-color: #0c0d0f;"); // fallback
        }

        // ===== Title =====
        Text title = new Text("📧 Compose Email");
        title.setStyle("-fx-fill: white; -fx-font-weight: bold; -fx-font-size: 24px;");

        // ===== Labels and Fields =====
        Label toLbl = new Label("To:");
        toLbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextField to = new TextField(toEmail);
        to.setEditable(false);
        to.setStyle(
                "-fx-background-radius: 10; " +
                        "-fx-background-color: rgba(255,255,255,0.1); " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; -fx-border-radius: 10;"
        );

        Label msgLbl = new Label("Message:");
        msgLbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextArea message = new TextArea();
        message.setPromptText("Type your message here...");
        message.setPrefRowCount(8);
        message.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-control-inner-background: rgba(0,0,0,0.25); " +
                        "-fx-background-insets: 0; " +
                        "-fx-highlight-fill: derive(#ffd27a, -20%); " +
                        "-fx-highlight-text-fill: black; " +
                        "-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: white;"
        );

        // ===== Buttons =====
        Button send = new Button("Send ✉");
        send.setStyle(
                "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);" +
                        "-fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 15; " +
                        "-fx-padding: 8 20;"
        );
        send.setOnAction(e -> {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Email Sent");
            dialog.setHeaderText(null);

            DialogPane pane = dialog.getDialogPane();
            pane.setStyle(
                    "-fx-background-color: rgba(12,13,15,0.9); " +
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

        Button back = new Button("⬅ Back to Details");
        back.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: white; -fx-border-radius: 10; " +
                        "-fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-padding: 6 15;"
        );
        back.setOnAction(e -> app.backFromEmail());

        HBox buttons = new HBox(12, send, back);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        // ===== Card Overlay =====
        VBox card = new VBox(15,
                title,
                toLbl, to,
                msgLbl, message,
                buttons
        );
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: rgba(0,0,0,0.35); " +
                        "-fx-background-radius: 20; -fx-padding: 25;"
        );
        card.setMaxWidth(520);
        card.setMinWidth(360);

        VBox centerBox = new VBox(card);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(30));

        root.getChildren().add(centerBox);
    }

    public StackPane getRoot() {
        return root;
    }
}
