package com.onebeartoe.imaging.software.box;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SoftwareBox extends Group {

    private final DoubleProperty boxWidth = new SimpleDoubleProperty(180);
    private final DoubleProperty boxHeight = new SimpleDoubleProperty(40);

    private final DoubleProperty frontRightX = new SimpleDoubleProperty(186.6);
    private final DoubleProperty frontLeftX = new SimpleDoubleProperty(13.4);
    private final DoubleProperty frontUpperRightY = new SimpleDoubleProperty(10.0);
    private final DoubleProperty frontUpperLeftY = new SimpleDoubleProperty(10.0);

    private final DoubleProperty sideLeftX = new SimpleDoubleProperty(-13.4);
    private final DoubleProperty sideUpperLeftY = new SimpleDoubleProperty(10.0);

    private final ObjectProperty<Image> frontImage = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> sideImage = new SimpleObjectProperty<>();

    public SoftwareBox() {
        initUI();
        initDefaultImages();
    }

    private void initUI() {
        // 1. Top Lid Rectangle
        Rectangle topRectangle = new Rectangle(140, 140, Color.LIGHTGRAY);
        PerspectiveTransform topTransform = new PerspectiveTransform();

        // Point d: x = c.centerX - (b.layoutX - a.layoutX)
        // Point d: y = c.centerY - (b.layoutY - a.layoutY)
        topTransform.ulxProperty().bind(frontRightX.subtract(frontLeftX.subtract(sideLeftX)));
        topTransform.ulyProperty().bind(frontUpperRightY.subtract(frontUpperLeftY.subtract(sideUpperLeftY)));
        topTransform.urxProperty().bind(frontRightX);
        topTransform.uryProperty().bind(frontUpperRightY);
        topTransform.llxProperty().bind(sideLeftX);
        topTransform.llyProperty().bind(sideUpperLeftY);
        topTransform.lrxProperty().bind(frontLeftX);
        topTransform.lryProperty().bind(frontUpperLeftY);
        topRectangle.setEffect(topTransform);

        // 2. Side Panel ImageView
        ImageView sideView = new ImageView();
        sideView.imageProperty().bind(sideImage);
        PerspectiveTransform sideTransform = new PerspectiveTransform();
        sideTransform.ulxProperty().bind(sideLeftX);
        sideTransform.ulyProperty().bind(sideUpperLeftY);
        sideTransform.setUrx(15.0);
        sideTransform.uryProperty().bind(frontUpperLeftY);
        sideTransform.llxProperty().bind(sideLeftX);
        sideTransform.llyProperty().bind(sideUpperLeftY.add(200.0));
        sideTransform.setLrx(15.0);
        sideTransform.setLry(210.0);
        sideView.setEffect(sideTransform);

        // 3. Front Panel ImageView
        ImageView frontView = new ImageView();
        frontView.imageProperty().bind(frontImage);
        PerspectiveTransform frontTransform = new PerspectiveTransform();
        frontTransform.ulxProperty().bind(frontLeftX);
        frontTransform.ulyProperty().bind(frontUpperLeftY);
        frontTransform.urxProperty().bind(frontRightX);
        frontTransform.uryProperty().bind(frontUpperRightY);
        frontTransform.llxProperty().bind(frontLeftX);
        frontTransform.setLly(210.0);
        frontTransform.lrxProperty().bind(frontRightX);
        frontTransform.lryProperty().bind(frontUpperRightY.add(180.0));
        frontView.setEffect(frontTransform);

        // Add components to group
        getChildren().addAll(topRectangle, sideView, frontView);

        // Mouse interactions matching JavaFX Script version
        setOnMousePressed(event -> {
            setScaleX(0.8);
            setScaleY(0.8);
        });

        setOnMouseReleased(event -> {
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }

    private void initDefaultImages() {
        try {
            var frontStream = getClass().getResourceAsStream("front.jpg");
            if (frontStream != null) {
                setFrontImage(new Image(frontStream));
            }
            var sideStream = getClass().getResourceAsStream("side.jpg");
            if (sideStream != null) {
                setSideImage(new Image(sideStream));
            }
        } catch (Exception e) {
            System.err.println("Could not load default box images: " + e.getMessage());
        }
    }

    // Properties and Accessors

    public DoubleProperty boxWidthProperty() {
        return boxWidth;
    }

    public double getBoxWidth() {
        return boxWidth.get();
    }

    public void setBoxWidth(double value) {
        boxWidth.set(value);
    }

    public DoubleProperty boxHeightProperty() {
        return boxHeight;
    }

    public double getBoxHeight() {
        return boxHeight.get();
    }

    public void setBoxHeight(double value) {
        boxHeight.set(value);
    }

    public DoubleProperty frontRightXProperty() {
        return frontRightX;
    }

    public double getFrontRightX() {
        return frontRightX.get();
    }

    public void setFrontRightX(double value) {
        frontRightX.set(value);
    }

    public DoubleProperty frontLeftXProperty() {
        return frontLeftX;
    }

    public double getFrontLeftX() {
        return frontLeftX.get();
    }

    public void setFrontLeftX(double value) {
        frontLeftX.set(value);
    }

    public DoubleProperty frontUpperRightYProperty() {
        return frontUpperRightY;
    }

    public double getFrontUpperRightY() {
        return frontUpperRightY.get();
    }

    public void setFrontUpperRightY(double value) {
        frontUpperRightY.set(value);
    }

    public DoubleProperty frontUpperLeftYProperty() {
        return frontUpperLeftY;
    }

    public double getFrontUpperLeftY() {
        return frontUpperLeftY.get();
    }

    public void setFrontUpperLeftY(double value) {
        frontUpperLeftY.set(value);
    }

    public DoubleProperty sideLeftXProperty() {
        return sideLeftX;
    }

    public double getSideLeftX() {
        return sideLeftX.get();
    }

    public void setSideLeftX(double value) {
        sideLeftX.set(value);
    }

    public DoubleProperty sideUpperLeftYProperty() {
        return sideUpperLeftY;
    }

    public double getSideUpperLeftY() {
        return sideUpperLeftY.get();
    }

    public void setSideUpperLeftY(double value) {
        sideUpperLeftY.set(value);
    }

    public ObjectProperty<Image> frontImageProperty() {
        return frontImage;
    }

    public Image getFrontImage() {
        return frontImage.get();
    }

    public void setFrontImage(Image image) {
        frontImage.set(image);
    }

    public ObjectProperty<Image> sideImageProperty() {
        return sideImage;
    }

    public Image getSideImage() {
        return sideImage.get();
    }

    public void setSideImage(Image image) {
        sideImage.set(image);
    }
}
