package org.javafx.hostelmanagementfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.javafx.hostelmanagementfx.Main;

public class LoginView {
    private final VBox root;

    public LoginView(Main app) {
        root = new VBox(20);
        root.getStyleClass().add("page-root");
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        Text title = new Text("Hostel Booking System");
        title.getStyleClass().add("app-title");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.getStyleClass().add("rounded-input");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.getStyleClass().add("rounded-input");

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

        root.getChildren().addAll(title, username, password, loginBtn);
    }

    public VBox getRoot() { return root; }
}
