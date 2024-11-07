package org.example.gui_project;

import geometry2d.Circle;
import geometry2d.DrawableFigure;
import geometry2d.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Geometry extends Application {
    private static final double CANVAS_WIDTH = 500;
    private static final double CANVAS_HEIGHT = 500;
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX, offsetY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("рисовашка геометри деш");

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // обраб. соб. для перетаскивания фигур
        canvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                // проверка попад.я мыши на фигуру
                selectedFigure = null;
                for (int i = figures.size() - 1; i >= 0; i--) {
                    DrawableFigure figure = figures.get(i);
                    if (figure.contains(e.getX(), e.getY())) {
                        selectedFigure = figure;
                        offsetX = e.getX() - figure.getX();
                        offsetY = e.getY() - figure.getY();
                        figures.remove(i);  // удал. из списка и добавл. обратно, чтобы она оказалась наверху
                        figures.add(figure);
                        break;
                    }
                }
            }
        });

        canvas.setOnMouseDragged(e -> {
            if (selectedFigure != null && e.getButton() == MouseButton.PRIMARY) {
                selectedFigure.setPosition(e.getX() - offsetX, e.getY() - offsetY);
                redraw(gc);
            }
        });

        canvas.setOnMouseReleased(e -> selectedFigure = null);

        // обраб. правой кнопки мыши для измен-я цвета фигуры
        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                for (int i = figures.size() - 1; i >= 0; i--) {
                    DrawableFigure figure = figures.get(i);
                    if (figure.contains(e.getX(), e.getY())) {
                        figure.setColor(Color.color(Math.random(), Math.random(), Math.random()));
                        redraw(gc);  // перерис. только после измен-я цвета
                        break;  // останавл. цикл после изменн-я цвета одного объекта
                    }
                }
            }
        });

        Button drawCircleButton = new Button("круг");
        drawCircleButton.setOnAction(e -> {
            double radius = 10 + Math.random() * 40;
            Circle circle = new Circle(radius);
            double x = Math.random() * (CANVAS_WIDTH - radius * 2);
            double y = Math.random() * (CANVAS_HEIGHT - radius * 2);
            DrawableFigure drawableCircle = new DrawableFigure(circle, x, y, Color.color(Math.random(), Math.random(), Math.random()));
            figures.add(drawableCircle);
            drawableCircle.draw(gc);
        });

        Button drawRectangleButton = new Button("прямоугольник");
        drawRectangleButton.setOnAction(e -> {
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
        });

        VBox root = new VBox();
        root.getChildren().addAll(canvas, drawCircleButton, drawRectangleButton);

        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void redraw(GraphicsContext gc) {
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        for (DrawableFigure figure : figures) {
            figure.draw(gc);
        }
    }
}