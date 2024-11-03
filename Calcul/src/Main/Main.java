package Main;

import Controller.CalculatorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Model.*;
import GUI.*;
;

public class Main extends Application {
    public static void main(String[] args) {


        Application.launch(args);
    }

    public void start(Stage primaryStage) {

        CalculatorModelInterface model = new CalculatorModel();
        CalculatorGUIInterface view = new CalculatorGUI();

        new CalculatorController(view, model);
        primaryStage.setTitle("Ma calculette");
        //Image icon = new Image("./view/icon.png");
        // primaryStage.getIcons().add(icon);
        Scene scene = new Scene(((CalculatorGUI) view).getVeiwRoot(), 300, 600);
        primaryStage.setScene(scene);
        //scene.getStylesheets().add("./view/styles.css");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
