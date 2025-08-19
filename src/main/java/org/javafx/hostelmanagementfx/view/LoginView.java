package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;

public class LoginView {
    private final StackPane root;

    public LoginView(Main app) {
        root = new StackPane();

        // Background image from resources
        Image bg = new Image(getClass().getResource("/org/javafx/hostelmanagementfx/image/LockScreen.jpg").toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        root.setBackground(new Background(bgImage));

        // Overlay panel for input fields
        VBox overlay = new VBox(20);
        overlay.setPadding(new Insets(40));
        overlay.setAlignment(Pos.CENTER);
        overlay.setMaxWidth(400);
        overlay.setStyle("-fx-background-color: rgba(12,13,15,0.7); -fx-background-radius: 20;");

        Text title = new Text("Hostel Booking System");
        title.getStyleClass().add("app-title");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.getStyleClass().add("rounded-input");
        username.setMaxWidth(250);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.getStyleClass().add("rounded-input");
        password.setMaxWidth(250);

        Button loginBtn = new Button("Login");
        loginBtn.getStyleClass().addAll("accent-btn", "glow-btn");
        loginBtn.setTooltip(new Tooltip("Login with username/password"));
        loginBtn.setMaxWidth(150);

        loginBtn.setOnAction(e -> {
            if ("user".equals(username.getText()) && "user".equals(password.getText())) {
                app.showMainPage();
            } else {
                // Stylish popup for invalid credentials
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("Login Failed");
                dialog.setHeaderText(null);

                DialogPane pane = dialog.getDialogPane();
                pane.setStyle(
                        "-fx-background-color: #1c1c1c; " +
                                "-fx-border-color: linear-gradient(to right, #ff8c00, #ffae42); " +
                                "-fx-border-width: 3; " +
                                "-fx-border-radius: 12; " +
                                "-fx-background-radius: 12;"
                );

                Label content = new Label("⚠ Invalid username or password!");
                content.setStyle(
                        "-fx-text-fill: #ffae42; " +
                                "-fx-font-size: 16px; " +
                                "-fx-font-weight: bold;"
                );
                content.setWrapText(true);
                pane.setContent(content);

                pane.getButtonTypes().add(ButtonType.OK);
                Button okBtn = (Button) pane.lookupButton(ButtonType.OK);
                okBtn.setStyle(
                        "-fx-background-color: linear-gradient(to right, #ff8c00, #ffae42);" +
                                "-fx-text-fill: black;" +
                                "-fx-background-radius: 10;" +
                                "-fx-font-weight: bold;"
                );

                dialog.showAndWait();
            }
        });

        overlay.getChildren().addAll(title, username, password, loginBtn);
        root.getChildren().add(overlay);
    }

    public StackPane getRoot() {
        return root;
    }
}
