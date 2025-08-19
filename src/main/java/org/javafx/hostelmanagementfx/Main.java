package org.javafx.hostelmanagementfx;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.javafx.hostelmanagementfx.model.Hostel;
import org.javafx.hostelmanagementfx.view.*;

public class Main extends Application {

    private Stage primaryStage;
    private String stylesheetPath;
    private Hostel lastHostelForContact; // used to return from Email page

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Hostel Booking System");

        // load CSS from resources
        stylesheetPath = getClass().getResource("style.css").toExternalForm();

        showLogin();
        primaryStage.show();
    }

    public void showLogin() {
        LoginView view = new LoginView(this);
        setScene(view.getRoot(), 800, 600);
    }

    public void showMainPage() {
        MainCategoryView view = new MainCategoryView(this);
        setScene(view.getRoot(), 900, 600);
    }

    public void showHostelList(String category) {
        HostelListView view = new HostelListView(this, category);
        setScene(view.getRoot(), 1000, 700);
    }

    public void showHostelDetails(Hostel hostel) {
        this.lastHostelForContact = hostel;
        HostelDetailsView view = new HostelDetailsView(this, hostel);
        setScene(view.getRoot(), 900, 650);
    }

    public void showEmailSimulator(String toEmail) {
        EmailSimulatorView view = new EmailSimulatorView(this, toEmail);
        setScene(view.getRoot(), 800, 600);
    }

    public void backFromEmail() {
        if (lastHostelForContact != null) {
            showHostelDetails(lastHostelForContact);
        } else {
            showMainPage();
        }
    }

    /* ---------- helpers ---------- */

    private void setScene(Region root, double w, double h) {
        Scene scene = new Scene(root, w, h);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(stylesheetPath);
        primaryStage.setScene(scene);
        fadeIn(root);
    }

    private void fadeIn(Region node) {
        FadeTransition ft = new FadeTransition(Duration.millis(600), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
