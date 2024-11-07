package org.example.gui_project;

import geometry2d.Circle;
import geometry2d.DrawableFigure;
import geometry2d.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class GeometryController {

    private static final double CANVAS_WIDTH = 500;
    private static final double CANVAS_HEIGHT = 500;

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX, offsetY;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                selectedFigure = null;
                for (int i = figures.size() - 1; i >= 0; i--) {
                    DrawableFigure figure = figures.get(i);
                    if (figure.contains(e.getX(), e.getY())) {
                        selectedFigure = figure;
                        offsetX = e.getX() - figure.getX();
                        offsetY = e.getY() - figure.getY();
                        figures.remove(i);
                        figures.add(figure);
                        break;
                    }
                }
            }
        });

        canvas.setOnMouseDragged(e -> {
            if (selectedFigure != null && e.getButton() == MouseButton.PRIMARY) {
                selectedFigure.setPosition(e.getX() - offsetX, e.getY() - offsetY);
                redraw();
            }
        });

        canvas.setOnMouseReleased(e -> selectedFigure = null);

        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                for (int i = figures.size() - 1; i >= 0; i--) {
                    DrawableFigure figure = figures.get(i);
                    if (figure.contains(e.getX(), e.getY())) {
                        figure.setColor(Color.color(Math.random(), Math.random(), Math.random()));
                        redraw();
                        break;
                    }
                }
            }
        });
    }

    @FXML
    private void handleDrawCircle() {
        double radius = 10 + Math.random() * 40;
        Circle circle = new Circle(radius);
        double x = Math.random() * (CANVAS_WIDTH - radius * 2);
        double y = Math.random() * (CANVAS_HEIGHT - radius * 2);
        DrawableFigure drawableCircle = new DrawableFigure(circle, x, y, Color.color(Math.random(), Math.random(), Math.random()));
        figures.add(drawableCircle);
        drawableCircle.draw(gc);
    }

    @FXML
    private void handleDrawRectangle() {
        double width = 20 + Math.random() * 80;
        double height = 20 + Math.random() * 80;
        try {
            Rectangle rectangle = new Rectangle(width, height);
            double x = Math.random() * (CANVAS_WIDTH - width);
            double y = Math.random() * (CANVAS_HEIGHT - height);
            DrawableFigure drawableRectangle = new DrawableFigure(rectangle, x, y, Color.color(Math.random(), Math.random(), Math.random()));
            figures.add(drawableRectangle);
            drawableRectangle.draw(gc);
        } catch (Exception ex) {
            System.out.println("недопустимые размеры для прямоугольника");
        }
    }

    private void redraw() {
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        for (DrawableFigure figure : figures) {
            figure.draw(gc);
        }
    }
}