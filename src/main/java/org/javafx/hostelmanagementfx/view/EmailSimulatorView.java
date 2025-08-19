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
        root = new VBox(20);
        root.getStyleClass().add("page-root");
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Text title = new Text("Compose Email");
        title.getStyleClass().add("page-title");

        TextField to = new TextField(toEmail);
        to.setEditable(false);
        to.getStyleClass().add("rounded-input");

        TextArea message = new TextArea();
        message.setPromptText("Type your message here...");
        message.setPrefRowCount(8);
        message.getStyleClass().add("rounded-area");

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

        VBox card = new VBox(12,
                new Label("To:"), to,
                new Label("Message:"), message,
                new HBox(10, send, back)
        );
        card.setAlignment(Pos.CENTER_LEFT);
        card.getStyleClass().addAll("card", "glass");
        card.setPadding(new Insets(20));

        root.getChildren().addAll(title, card);
    }

    public VBox getRoot() { return root; }
}
