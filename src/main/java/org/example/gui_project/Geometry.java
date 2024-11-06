import geometry2d.Circle;
import geometry2d.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GeometryApp extends Application {
    private static final double CANVAS_WIDTH = 500;
    private static final double CANVAS_HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Geometry Drawing App");

        // Создаем холст и получаем GraphicsContext
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Кнопка для рисования круга
        Button drawCircleButton = new Button("Draw Circle");
        drawCircleButton.setOnAction(e -> {
            double radius = 10 + Math.random() * 40; // Случайный радиус от 10 до 50
            Circle circle = new Circle(radius);
            circle.draw(gc, Math.random() * (CANVAS_WIDTH - radius * 2), Math.random() * (CANVAS_HEIGHT - radius * 2));
        });

        // Кнопка для рисования прямоугольника
        Button drawRectangleButton = new Button("Draw Rectangle");
        drawRectangleButton.setOnAction(e -> {
            double width = 20 + Math.random() * 80; // Случайная ширина от 20 до 100
            double height = 20 + Math.random() * 80; // Случайная высота от 20 до 100
            try {
                Rectangle rectangle = new Rectangle(width, height);
                rectangle.draw(gc, Math.random() * (CANVAS_WIDTH - width), Math.random() * (CANVAS_HEIGHT - height));
            } catch (Exception ex) {
                System.out.println("Invalid dimensions for rectangle");
            }
        });

        // Добавляем элементы в интерфейс
        VBox root = new VBox();
        root.getChildren().addAll(canvas, drawCircleButton, drawRectangleButton);

        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
