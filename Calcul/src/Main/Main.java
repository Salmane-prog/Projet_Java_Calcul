package Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import GUI.CalculatorGUI;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CalculatorGUI calculatorGUI = new CalculatorGUI();
        Scene scene = new Scene(calculatorGUI.getViewRoot(), 300, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Load CSS

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ma calculette");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
