package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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

        // Email input (non-editable)
        Label toLabel = new Label("To:");
        toLabel.getStyleClass().add("field-label");

        TextField to = new TextField(toEmail);
        to.setEditable(false);
        to.getStyleClass().add("rounded-input");

        // Message area
        Label msgLabel = new Label("Message:");
        msgLabel.getStyleClass().add("field-label");

        TextArea message = new TextArea();
        message.setPromptText("Type your message here...");
        message.setPrefRowCount(8);
        message.getStyleClass().add("rounded-area");

        // Buttons
        Button send = new Button("Send ✉");
        send.getStyleClass().addAll("accent-btn", "glow-btn");
        send.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "✅ Email Sent!", ButtonType.OK);
            a.setHeaderText(null);
            a.setTitle("Email");
            a.showAndWait();
        });

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

    public VBox getRoot() { return root; }
}
