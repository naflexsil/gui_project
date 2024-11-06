package geometry2d;

import geometry2d.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableFigure {
    private final Figure figure;
    private double x;
    private double y;
    private Color color;

    public DrawableFigure(Figure figure, double x, double y, Color color) {
        this.figure = figure;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        figure.draw(gc, x, y);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean contains(double px, double py) {
        if (figure instanceof geometry2d.Circle) {
            double radius = ((geometry2d.Circle) figure).getRadius();
            return Math.pow(px - (x + radius), 2) + Math.pow(py - (y + radius), 2) <= Math.pow(radius, 2);
        } else if (figure instanceof geometry2d.Rectangle) {
            double width = ((geometry2d.Rectangle) figure).getWidth();
            double height = ((geometry2d.Rectangle) figure).getHeight();
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }
        return false;
    }
}
