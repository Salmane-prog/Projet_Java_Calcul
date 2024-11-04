package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import GUI.CalculatorGUI;
import Model.CalculatorModel;
import Controller.CalculatorController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize model and view
        CalculatorModel model = new CalculatorModel();
        CalculatorGUI calculatorGUI = new CalculatorGUI();

        // Initialize controller and link it to the view and model
        CalculatorController controller = new CalculatorController(calculatorGUI, model);
        calculatorGUI.setController(controller); // This is important to prevent the NullPointerException

        // Set up the scene with the loaded CSS
        Scene scene = new Scene(calculatorGUI.getViewRoot(), 300, 500);
        String css = getClass().getResource("/GUI/styles.css") != null ? getClass().getResource("/GUI/styles.css").toExternalForm() : null;
        if (css != null) {
            scene.getStylesheets().add(css);
        } else {
            System.out.println("styles.css not found!");
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ma calculette");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
