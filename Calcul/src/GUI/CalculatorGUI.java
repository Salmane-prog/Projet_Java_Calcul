package GUI;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import Controller.CalculatorController;

import java.util.List;

import static java.awt.SystemColor.control;

import Controller.CalculatorControllerInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Stack;

public class CalculatorGUI extends VBox implements CalculatorGUIInterface {

    private CalculatorControllerInterface controller;
    private TextField displayAccu;
    private Label stackDisplay;

    public CalculatorGUI(CalculatorControllerInterface controller) {
        this.controller = controller;

        // Accumulator display
        displayAccu = new TextField();
        displayAccu.setEditable(false);
        displayAccu.setPrefHeight(30);
        displayAccu.setStyle("-fx-font-size: 18px; -fx-alignment: center-right;");

        // Stack display
        stackDisplay = new Label("Stack: []");
        stackDisplay.setStyle("-fx-font-size: 16px;");

        // Buttons grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);

        // Define buttons
        String[] buttons = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", ".", "<>", "/",
                "C", "CL", "+/-", "swap",
                "←", "="
        };

        int row = 0;
        int col = 0;

        // Add buttons to grid
        for (String text : buttons) {
            Button button = new Button(text);
            button.setPrefSize(50, 50);
            button.setOnAction(e -> handleButton(text));
            grid.add(button, col, row);

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        // Add components to VBox layout
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(displayAccu, stackDisplay, grid);
    }

    // Set the controller
    public void setController(CalculatorControllerInterface controller) {
        this.controller = controller;
    }

    // Method to handle button clicks
    private void handleButton(String text) {
        if (text.equals("=")) {
            controller.change(displayAccu.getText());
        } else {
            controller.change(text);
        }
    }

    // Display the current accumulator value
    @Override
    public void affiche(String accu) {
        displayAccu.setText(accu);
    }

    // Update the stack display
    @Override
    public void change(Stack<Double> stackData) {
        StringBuilder stackText = new StringBuilder("Stack: ");
        for (int i = stackData.size() - 1; i >= 0; i--) {
            stackText.append(stackData.get(i)).append(" ");
        }
        stackDisplay.setText(stackText.toString());
    }

    // Main entry point to set up the GUI
    public static void launchCalculator(Stage primaryStage, CalculatorControllerInterface controller) {
        CalculatorGUI gui = new CalculatorGUI(controller);
        Scene scene = new Scene(gui, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPN Calculator");
        primaryStage.show();
    }
}