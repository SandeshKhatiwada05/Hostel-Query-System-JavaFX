package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.javafx.hostelmanagementfx.Main;

public class EmailSimulatorView {
    private final VBox root;

    public EmailSimulatorView(Main app, String toEmail) {
        root = new VBox(25);
        root.getStyleClass().add("page-root");
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);

        // Title
        Text title = new Text("📧 Compose Email");
        title.getStyleClass().add("page-title");

        // Recipient section
        Label toLabel = new Label("To:");
        toLabel.getStyleClass().add("field-label");

        TextField to = new TextField(toEmail);
        to.setEditable(false);
        to.getStyleClass().add("tagged-input");

        // Message area
        Label msgLabel = new Label("Message:");
        msgLabel.getStyleClass().add("field-label");

        TextArea message = new TextArea();
        message.setPromptText("Type your message here...");
        message.setPrefRowCount(8);
        message.setWrapText(true);
        message.setStyle("-fx-text-fill: #222; -fx-prompt-text-fill: #888;"); // visible text

        // Buttons
        Button send = new Button("✉ Send");
        send.getStyleClass().addAll("accent-btn", "glow-btn");
        send.setOnAction(e -> showCoolPopup());

        Button back = new Button("⬅ Back to Details");
        back.getStyleClass().addAll("accent-btn", "tiny", "outline-btn");
        back.setOnAction(e -> app.backFromEmail());

        HBox buttonBox = new HBox(15, send, back);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // Card container
        VBox card = new VBox(18,
                toLabel, to,
                msgLabel, message,
                buttonBox
        );
        card.setAlignment(Pos.CENTER_LEFT);
        card.getStyleClass().addAll("card", "glass", "email-card");
        card.setPadding(new Insets(25));

        root.getChildren().addAll(title, card);
    }

    // Custom "cool" popup instead of boring Alert
    private void showCoolPopup() {
        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.initModality(Modality.APPLICATION_MODAL);

        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.setBackground(new Background(new BackgroundFill(Color.web("#ffffffcc"), new CornerRadii(15), Insets.EMPTY)));
        box.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0.3, 0, 5);");

        Label icon = new Label("✅");
        icon.setStyle("-fx-font-size: 32px;");

        Label msg = new Label("Your email has been sent!");
        msg.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

        Button ok = new Button("OK");
        ok.getStyleClass().add("accent-btn");
        ok.setOnAction(e -> popup.close());

        box.getChildren().addAll(icon, msg, ok);

        Scene scene = new Scene(box);
        scene.setFill(Color.TRANSPARENT);
        popup.setScene(scene);
        popup.showAndWait();
    }

    public VBox getRoot() { return root; }
}
