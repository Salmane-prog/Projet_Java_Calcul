package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import GUI.CalculatorGUI;
import Model.CalculatorModel;
import Controller.CalculatorController;
import javafx.scene.image.Image;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Set application icon
        primaryStage.getIcons().add(new Image("file:F:/IMT/Projet_Java_calcul/logo.png"));

        // Existing setup code...
        CalculatorModel model = new CalculatorModel();
        CalculatorGUI calculatorGUI = new CalculatorGUI();
        CalculatorController controller = new CalculatorController(calculatorGUI, model);
        calculatorGUI.setController(controller);

        Scene scene = new Scene(calculatorGUI.getViewRoot(), 400, 700);
        String css = getClass().getResource("/GUI/styles.css") != null ? getClass().getResource("/GUI/styles.css").toExternalForm() : null;
        if (css != null) {
            scene.getStylesheets().add(css);
        } else {
            System.out.println("styles.css not found!");
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ma calculette");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
