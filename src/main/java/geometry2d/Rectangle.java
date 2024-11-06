package geometry2d;

import Exceptions.InvalidDimensionsException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Figure {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) throws InvalidDimensionsException {
        if (width < 0 || height < 0) {
            throw new InvalidDimensionsException("недопустимый размер");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "прямоугольник шириной: " + width + " и высотой: " + height;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gc.fillRect(x, y, width, height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
