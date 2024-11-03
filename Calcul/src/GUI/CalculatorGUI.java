package GUI;

import java.util.Stack;
import Controller.CalculatorControllerInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CalculatorGUI implements CalculatorGUIInterface {
    private StackPane root;
    private CalculatorControllerInterface controller;
    private StringProperty accuField;  // For displaying result
    private TextArea historyArea;  // New area for history

    public CalculatorGUI() {
        VBox listValues = new VBox(5);

        // Initialize accumulator field
        accuField = new SimpleStringProperty("");

        // History Area (replaces the four individual fields)
        historyArea = new TextArea();
        historyArea.setEditable(false);
        historyArea.setPrefHeight(100);  // Set a preferred height for history area
        historyArea.setStyle("-fx-font-size: 14px;");
        historyArea.setPromptText("Operation History");


        // Result Field (single TextField to show the result)
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.textProperty().bindBidirectional(accuField);
        resultField.getStyleClass().add("result-field");
        resultField.setPrefHeight(70);

        // Add the history and result fields to the layout
        listValues.getChildren().addAll(historyArea, resultField);

        // Create button grid for calculator operations
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels for buttons
        String[] buttonLabels = {"CL", "C", "+/-", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "←", "0", ".", "<>"};

        int col = 0;
        int row = 0;

        // Create and style buttons
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefSize(50, 50);
            button.getStyleClass().add("squircle-button");  // Apply squircle style

            button.setOnAction(event -> change(label));
            gridPane.add(button, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        // Add "swap" and "isEmpty" buttons in the last row, each spanning two columns
        Button swapButton = new Button("swap");
        swapButton.setPrefSize(100, 50);
        swapButton.getStyleClass().add("squircle-button");
        swapButton.setOnAction(event -> change("swap"));
        gridPane.add(swapButton, 0, row, 2, 1); // Span 2 columns

        Button isEmptyButton = new Button("isEmpty");
        isEmptyButton.setPrefSize(100, 50);
        isEmptyButton.getStyleClass().add("squircle-button");
        isEmptyButton.setOnAction(event -> handleIsEmpty());
        gridPane.add(isEmptyButton, 2, row, 2, 1); // Span 2 columns

        // Set up the root StackPane with padding
        root = new StackPane();
        root.setPadding(new Insets(20));
        Insets margin = new Insets(220, 0, 0, 0);
        root.setMargin(gridPane, margin);
        root.getStyleClass().add("background");
        root.getChildren().addAll(listValues, gridPane);
    }

    public StackPane getViewRoot() {
        return this.root;
    }

    public void setController(CalculatorControllerInterface controller) {
        this.controller = controller;
    }

    // Method to display the result in the accumulator
    @Override
    public void affiche(String accu) {
        this.accuField.setValue(accu);
    }

    // Method to handle operations and button clicks
    @Override
    public void change(String value) {
        controller.change(value);
    }

    // Method to update history and result fields
    @Override
    public void change(Stack<Double> stack) {
        // Display the latest result at the top of the stack if it is not null
        if (!stack.isEmpty() && stack.peek() != null) {
            double result = stack.peek();
            accuField.set(String.valueOf(result));
        } else {
            accuField.set(""); // Clear result field if stack is empty or top value is null
        }

        // Update history display with all stack values, checking for null values
        StringBuilder historyText = new StringBuilder();
        for (int i = stack.size() - 1; i >= 0; i--) {
            Double value = stack.get(i);
            historyText.append(value != null ? value.toString() : "").append("\n");
        }
        historyArea.setText(historyText.toString());
    }

    // Method to handle the "isEmpty" button
    private void handleIsEmpty() {
        if (controller != null) {
            boolean isEmpty = controller.isStackEmpty();
            String message = isEmpty ? "Stack is empty" : "Stack is not empty";
            historyArea.appendText(message + "\n");
        }
    }
}
