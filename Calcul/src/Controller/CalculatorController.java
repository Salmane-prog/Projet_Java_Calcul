package Controller;

import java.util.Stack;

import Model.CalculatorModel;
import Model.CalculatorModelInterface;
import GUI.CalculatorGUIInterface;
import GUI.CalculatorGUI;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class CalculatorController implements CalculatorControllerInterface {
    private CalculatorGUIInterface calculatorView;
    private CalculatorModelInterface calculatorModel;

    public CalculatorController(CalculatorGUIInterface view,CalculatorModelInterface model) {
        this.calculatorView = view;
        this.calculatorModel = model;

        ((CalculatorGUI) this.calculatorView).setController(this);


    }

    public boolean isStackEmpty() {
        return calculatorModel.getStack().isEmpty();
    }

    public void change(String value) {
        switch (value) {
            case "<>":
                this.calculatorModel.pushAccu();
                ((CalculatorModel) this.calculatorModel).setAccu("");
                this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "+":
                try {
                    this.calculatorModel.add();
                } catch (IllegalArgumentException e) {
                    showErrorAlert("La pile doit contenir au moins 2 éléments pour effectuer l'addition.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "-":
                try {
                    this.calculatorModel.substract();
                } catch (IllegalArgumentException e) {
                    showErrorAlert("La pile doit contenir au moins 2 éléments pour effectuer la soustraction.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "*":
                try {
                    this.calculatorModel.multiply();
                } catch (IllegalArgumentException e) {
                    showErrorAlert("La pile doit contenir au moins 2 éléments pour effectuer la multiplication.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "/":
                try {
                    this.calculatorModel.divide();
                } catch (IllegalArgumentException e) {
                    showErrorAlert("La pile doit contenir au moins 2 éléments pour effectuer la division.");
                } catch (ArithmeticException e) {
                    showErrorAlert("Division par zéro n'est pas autorisée.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "C":
                this.calculatorModel.drop();
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "←":
                String prevValue = ((CalculatorModel) this.calculatorModel).getAccu();
                if (prevValue.length() > 0) {
                    String newString = prevValue.substring(0, prevValue.length() - 1);
                    ((CalculatorModel) this.calculatorModel).setAccu(newString);
                    this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                }
                break;

            case "CL":
                this.calculatorModel.clear();
                ((CalculatorModel) this.calculatorModel).setAccu("");
                this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "swap":
                try {
                    this.calculatorModel.swap();
                } catch (IllegalArgumentException e) {
                    showErrorAlert("La pile doit contenir au moins 2 éléments pour effectuer le swap.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            case "+/-":
                this.calculatorModel.opposite();
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;

            default:
                String prevValueDefault = ((CalculatorModel) this.calculatorModel).getAccu();
                if (!(value.equals(".") && prevValueDefault.contains("."))) {
                    ((CalculatorModel) this.calculatorModel).setAccu(prevValueDefault + value);
                    this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                }
        }
    }

    // Method to show error alerts with custom styling
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Set the icon for the alert window
        try {
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(getClass().getResourceAsStream("F:/IMT/Projet_Java_calcul/error.png")));
        } catch (NullPointerException e) {
            System.out.println("Error icon not found at /icons/error_icon.png");
        }

        // Apply custom CSS styling if desired
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/GUI/styles.css").toExternalForm());
        dialogPane.getStyleClass().add("error-alert"); // Custom style for the alert

        alert.showAndWait();
    }



    public void change(Stack<Double> stack) {
        Stack<Double> copieStack = (Stack<Double>) stack.clone();
        Stack<Double> newStack = new Stack<>();
        for (int i = 0; i < 4; i++) {
            if(!copieStack.empty()) {
                newStack.push(copieStack.pop());
            }else {
                newStack.push(null);
            }
        }
        this.calculatorView.change(newStack);

    }


}
