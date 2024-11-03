package GUI;

import java.util.Stack;

import Controller.CalculatorControllerInterface;
import GUI.CalculatorGUIInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class CalculatorGUI implements CalculatorGUIInterface {
    private  StackPane root;
    private CalculatorControllerInterface controller;
    Stack<TextField> stackValueTextFields = new Stack<>();
    private StringProperty accuField;


    public CalculatorGUI() {
        VBox listValues = new VBox(5);
        Stack<Double> stackValues = new Stack<>();
        for (int i = 0; i <4; i++) {
            stackValues.push(null);
        }

        accuField = new SimpleStringProperty("");

        // Création des champs de texte pour les valeurs de la pile
        for (Double value : stackValues) {
            String stringValue =value == null ? "" : value.toString();

            TextField stackValue = new TextField();
            stackValue.setEditable(false);
            stackValue.getStyleClass().add("stack-fields");
            stackValue.textProperty().set(stringValue);
            listValues.getChildren().addAll(stackValue);
            stackValueTextFields.push(stackValue);
        }


        // Création d'un champ de texte pour afficher le résultat
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.textProperty().bindBidirectional(accuField);
        resultField.getStyleClass().add("result-field");

        listValues.getChildren().add(resultField);

        // Création d'une grille de boutons pour les opérations
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels des boutons
        String[] buttonLabels = {"CL", "C", "+/-","/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "←", "0", ".", "<>", "swap"};

        int col = 0;
        int row = 0;

        // Création de l'ensemble des bouttons
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefSize(50, 50);
            button.setStyle("-fx-font-size: 17px; -fx-font-weight: 900;");

            // Ajout des styles aux boutons en fonction de leur label
            if(label=="/" || label=="*" || label=="+" || label=="-" || label=="<>") {
                button.getStyleClass().add("orange-button");
            }
            else if(label=="+/-" || label=="CL" || label=="C") {
                button.getStyleClass().add("gray-button");
            }
            else {
                button.getStyleClass().add("black-button");
            }

            if (label.equals("swap")) {
                GridPane.setColumnSpan(button, 4);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
            }

            // Associe une action à chaque bouton
            button.setOnAction(event -> {
                change(label);
            });

            gridPane.add(button, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }


        root = new StackPane();
        Insets padding = new Insets(20);
        root.setPadding(padding);
        Insets margin = new Insets(220,0,0,0);
        root.setMargin(gridPane, margin);
        root.getStyleClass().add("background");
        root.getChildren().addAll(listValues, gridPane);
    };


    public StackPane getVeiwRoot() {
        return this.root;
    }

    public void setController(CalculatorControllerInterface controller) {
        this.controller = controller;
    }


    // Méthode d'interface pour afficher le résultat dans l'accumulateur
    @Override
    public void affiche(String accu) {
        // TODO Auto-generated method stub
        this.accuField.setValue(accu);
    }


    // Méthode d'interface pour gérer le changement d'état de la calculatrice
    @Override
    public void change(String value) {
        // TODO Auto-generated method stub
        this.controller.change(value);
    }

    // Méthode d'interface pour mettre à jour les valeurs de la pile
    @Override
    public void change(Stack<Double> stack) {
        // TODO Auto-generated method stub
        for (int i = 0; i < stackValueTextFields.size(); i++) {
            String stringValue =stack.get(i) == null ? "" : stack.get(i).toString();
            stackValueTextFields.get(stack.size()-1-i).textProperty().set(stringValue);
        }

        return;
    }


}