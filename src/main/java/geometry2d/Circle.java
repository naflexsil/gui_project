package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Figure {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "circle with radius: " + radius;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gc.fillOval(x, y, radius * 2, radius * 2);
    }

    public double getRadius() {
        return radius;
    }
}