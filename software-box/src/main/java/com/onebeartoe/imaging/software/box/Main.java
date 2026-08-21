package com.onebeartoe.imaging.software.box;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage stage) {
        stage.setTitle("onebeartoe.net - Imaging - Software Box Generator");

        // Software Box node
        SoftwareBox softwareBox = new SoftwareBox();

        // 1. Controls
        Slider frontRightXSlider = new Slider(186, 220, 186.6);
        frontRightXSlider.setShowTickLabels(true);

        Slider frontUpperLeftXSlider = new Slider(-20, 20, 10.0);
        frontUpperLeftXSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        frontUpperLeftXSlider.setShowTickLabels(true);

        Slider sideLeftXSlider = new Slider(-15, 15, -13.4);
        sideLeftXSlider.setShowTickLabels(true);

        Slider sideLeftYSlider = new Slider(-20, 20, 10.0);
        sideLeftYSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        sideLeftYSlider.setShowTickLabels(true);

        // Bindings matching JavaFX Script original
        softwareBox.sideUpperLeftYProperty().bind(sideLeftYSlider.valueProperty());
        softwareBox.frontRightXProperty().bind(frontRightXSlider.valueProperty());
        softwareBox.frontUpperRightYProperty().bind(frontUpperLeftXSlider.valueProperty());
        softwareBox.sideLeftXProperty().bind(sideLeftXSlider.valueProperty());

        // Image file selection buttons
        fileChooser.setTitle("Choose Box Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );

        Button frontImageButton = new Button("Choose Front Image");
        frontImageButton.getStyleClass().add("button-primary");
        frontImageButton.setOnAction(event -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("Front image selected: " + selectedFile.getName());
                Image image = new Image(selectedFile.toURI().toString());
                softwareBox.setFrontImage(image);
            }
        });

        Button sideImageButton = new Button("Choose Side Image");
        sideImageButton.getStyleClass().add("button-secondary");
        sideImageButton.setOnAction(event -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("Side image selected: " + selectedFile.getName());
                Image image = new Image(selectedFile.toURI().toString());
                softwareBox.setSideImage(image);
            }
        });

        // Setup Main Layout
        BorderPane root = new BorderPane();

        // Header
        VBox headerPane = new VBox(4);
        headerPane.getStyleClass().add("header-pane");
        Text titleText = new Text("3D Software Box Generator");
        titleText.getStyleClass().add("title-text");
        Text subtitleText = new Text("Converted to modern JavaFX (Java 17+)");
        subtitleText.getStyleClass().add("subtitle-text");
        headerPane.getChildren().addAll(titleText, subtitleText);
        root.setTop(headerPane);

        // Center Area (SoftwareBox + Interactive Overlay Sliders)
        Pane boxCanvas = new Pane();
        boxCanvas.getStyleClass().add("box-container");

        // SoftwareBox position
        softwareBox.setLayoutY(80);
        softwareBox.layoutXProperty().bind(boxCanvas.widthProperty().divide(2).subtract(softwareBox.boxWidthProperty()));

        // Vertical Sliders inside canvas
        sideLeftYSlider.setLayoutX(25);
        sideLeftYSlider.setLayoutY(80);
        sideLeftYSlider.setPrefHeight(200);

        frontUpperLeftXSlider.layoutXProperty().bind(boxCanvas.widthProperty().multiply(0.90));
        frontUpperLeftXSlider.setLayoutY(80);
        frontUpperLeftXSlider.setPrefHeight(200);

        boxCanvas.getChildren().addAll(softwareBox, sideLeftYSlider, frontUpperLeftXSlider);
        root.setCenter(boxCanvas);

        // Bottom Controls Bar
        HBox bottomControls = new HBox(20);
        bottomControls.setAlignment(Pos.CENTER);
        bottomControls.setPadding(new Insets(15));
        bottomControls.setStyle("-fx-background-color: #1a1a24; -fx-border-color: #2c2c3e; -fx-border-width: 1 0 0 0;");

        // Side Left X Control
        VBox sideXBox = new VBox(5, new Label("Side Perspective X"), sideLeftXSlider);
        sideXBox.setAlignment(Pos.CENTER);

        // Front Right X Control
        VBox frontXBox = new VBox(5, new Label("Front Perspective X"), frontRightXSlider);
        frontXBox.setAlignment(Pos.CENTER);

        // Action Buttons
        HBox actionButtons = new HBox(10, sideImageButton, frontImageButton);
        actionButtons.setAlignment(Pos.CENTER);

        bottomControls.getChildren().addAll(actionButtons, sideXBox, frontXBox);
        root.setBottom(bottomControls);

        // Scene setup
        Scene scene = new Scene(root, 780, 560);
        try {
            var cssUrl = getClass().getResource("styles.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }
        } catch (Exception e) {
            System.err.println("Could not load stylesheet: " + e.getMessage());
        }

        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
