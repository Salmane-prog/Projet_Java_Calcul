package Controller;

import GUI.CalculatorGUIInterface;
import Model.CalculatorModelInterface;
import java.util.Stack;
import Model.CalculatorModel;
import GUI.CalculatorGUI;

public class CalculatorController implements CalculatorControllerInterface {
    private CalculatorGUIInterface calculatorView;
    private CalculatorModelInterface calculatorModel;

    public CalculatorController(CalculatorModelInterface model, CalculatorGUIInterface view) {
        this.calculatorModel = model;
        this.calculatorView = view;

        if (view instanceof CalculatorGUI) {
            ((CalculatorGUI) this.calculatorView).setController(this);
        }
    }

    // Method to set the view after controller initialization
    public void setView(CalculatorGUIInterface view) {
        this.calculatorView = view;
    }


    public void change(String value) {
        switch (value) {
            case "<>": {
                this.calculatorModel.pushAccu();
                ((CalculatorModel) this.calculatorModel).setAccu("");
                this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;
            }
            case "+":{
                try {
                    this.calculatorModel.add();
                } catch (IllegalArgumentException e) {
                    System.out.println("La pile doit contenir au moins 2 éléments pour effectuer l'addition.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;
            }
            case "-":{
                try {
                    this.calculatorModel.substract();
                } catch (IllegalArgumentException e) {
                    System.out.println("La pile doit contenir au moins 2 éléments pour effectuer la soustraction.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());

                break;
            }
            case "*":{
                try {
                    this.calculatorModel.multiply();
                } catch (IllegalArgumentException e) {
                    System.out.println("La pile doit contenir au moins 2 éléments pour effectuer la multiplication.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());

                break;
            }
            case "/": {
                try {
                    this.calculatorModel.divide();
                } catch (IllegalArgumentException e) {
                    System.out.println("La pile doit contenir au moins 2 éléments pour effectuer la division.");
                } catch (ArithmeticException e) {
                    System.out.println("Division par zéro n'est pas autorisée.");
                }

                this.change(((CalculatorModel) this.calculatorModel).getStack());

                break;
            }
            case "C":{
                this.calculatorModel.drop();
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;
            }
            case "←":{
                String prevValue = ((CalculatorModel) this.calculatorModel).getAccu();
                if (prevValue.length() > 0) {
                    String newString = prevValue.substring(0, prevValue.length() - 1);
                    ((CalculatorModel) this.calculatorModel).setAccu(newString);
                    this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                }
                break;
            }case "CL":{
                this.calculatorModel.clear();
                ((CalculatorModel) this.calculatorModel).setAccu("");
                this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;
            }
            case "swap":{
                try {
                    this.calculatorModel.swap();
                } catch (IllegalArgumentException e) {
                    System.out.println("La pile doit contenir au moins 2 éléments pour effectuer le swap.");
                }
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;
            }
            case "+/-":{
                this.calculatorModel.opposite();
                this.change(((CalculatorModel) this.calculatorModel).getStack());
                break;
            }
            default:
                String prevValue = ((CalculatorModel) this.calculatorModel).getAccu();
                if(!(value=="." && prevValue.contains("."))) {
                    ((CalculatorModel) this.calculatorModel).setAccu(prevValue+value);
                    this.calculatorView.affiche(((CalculatorModel) this.calculatorModel).getAccu());
                }
        }

    };


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
