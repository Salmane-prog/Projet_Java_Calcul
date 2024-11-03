package Main;
import Controller.CalculatorController;
import GUI.CalculatorGUI;
import Model.CalculatorModel;
import Model.CalculatorModelInterface;
import GUI.CalculatorGUIInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create instances of the model and the view
        CalculatorModelInterface model = new CalculatorModel(); // Model should be of CalculatorModelInterface type
        CalculatorGUI view = new CalculatorGUI(null); // View should be of CalculatorGUIInterface type

        // Create the controller with the correct order of parameters
        CalculatorController controller = new CalculatorController(model, view);

        // Set the view in the controller
        controller.setView(view);

        // Launch the GUI with the controller
        CalculatorGUI.launchCalculator(primaryStage, controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

