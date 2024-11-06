package geometry2d;

import javafx.scene.canvas.GraphicsContext;

public interface Figure {
    double area();
    String toString();
    void draw(GraphicsContext gc, double x, double y);
}
