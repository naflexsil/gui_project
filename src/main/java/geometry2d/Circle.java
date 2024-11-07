package geometry2d;

import javafx.scene.canvas.GraphicsContext;

public record Circle(double radius) implements Figure {

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "круг с радиусом: " + radius;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.fillOval(x, y, radius * 2, radius * 2);
    }
}