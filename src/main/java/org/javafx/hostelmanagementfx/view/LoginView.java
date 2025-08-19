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

        // Overlay panel for input fields (semi-transparent)
        VBox overlay = new VBox(20);
        overlay.setPadding(new Insets(40));
        overlay.setAlignment(Pos.CENTER);
        overlay.setMaxWidth(400); // restrict overlay width
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
        loginBtn.setOnAction(e -> {
            if ("user".equals(username.getText()) && "user".equals(password.getText())) {
                app.showMainPage();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Invalid credentials!", ButtonType.OK);
                a.setHeaderText(null);
                a.setTitle("Login Failed");
                a.showAndWait();
            }
        });
        loginBtn.setMaxWidth(150);

        overlay.getChildren().addAll(title, username, password, loginBtn);

        // Add overlay to root
        root.getChildren().add(overlay);
    }

    public StackPane getRoot() {
        return root;
    }
}
    