package Model;

import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
    private Stack<Double> stack;
    private String accu;

    public CalculatorModel() {
        this.stack = new Stack<>();
        this.accu = "";
    }
    @Override
    public Stack<Double> getStack() {
        return this.stack;
    }

    public void setStack(Stack<Double> stack) {
        this.stack = stack;
    }


    public String getAccu() {
        return accu;
    }

    public void setAccu(String accu) {
        this.accu = accu;
    }

    // Méthode pour effectuer une addition
    public void add() {
        if (this.stack.size() < 2) {
            // Exception levée si la pile contient moins de 2 opérandes pour l'addition
            throw new IllegalArgumentException();
        }

        Double op1,op2;
        op1 = this.pop();
        op2 = this.pop();

        this.stack.push(op1+op2);

    }

    // Méthode pour effectuer une soustraction
    public void substract() {
        if (this.stack.size() < 2) {
            // Exception levée si la pile contient moins de 2 opérandes pour la soustraction
            throw new IllegalArgumentException();
        }

        Double op1,op2;
        op2 = this.pop();
        op1 = this.pop();

        this.stack.push(op1-op2);
    }

    // Méthode pour effectuer une multiplication
    public void multiply() {
        if (this.stack.size() < 2) {
            // Exception levée si la pile contient moins de 2 opérandes pour la multiplication
            throw new IllegalArgumentException();
        }

        Double op1,op2;
        op1 = this.pop();
        op2 = this.pop();

        this.stack.push(op1*op2);

    }


    // Méthode pour effectuer une division
    public void divide() {
        if (this.stack.size() < 2) {
            // Exception levée si la pile contient moins de 2 opérandes pour la division
            throw new IllegalArgumentException();
        }

        Double op1,op2;
        op2 = this.pop();
        if (op2 == 0) {
            // Exception levée si l'opérande diviseur est zéro
            throw new ArithmeticException();
        }
        op1 = this.pop();
        this.stack.push(op1/op2);

    }


    // Méthode pour changer le signe d'une valeur
    public void opposite() {
        if(this.stack.size()>0) {
            Double op1;
            op1 = this.pop();

            this.stack.push(-1*op1);
        }
    }


    // Méthode pour pousser la valeur de l'accumulateur sur la pile
    public void pushAccu() {
        if(this.accu !="") {
            this.stack.push(Double.parseDouble(this.accu));
        }
    }


    // Méthode pour dépiler une valeur de la pile
    public Double pop() {
        return this.stack.pop();
    }


    // Méthode pour supprimer la valeur supérieure de la pile
    public void drop() {
        if(this.stack.size()>0) {
            this.stack.pop();
        }
    }


    // Méthode pour échanger les deux valeurs supérieures de la pile
    public void swap() {
        if (this.stack.size() < 2) {
            // Exception levée si la pile contient moins de 2 opérandes pour la swap
            throw new IllegalArgumentException();
        }

        Double op1,op2;
        op1 = this.pop();
        op2 = this.pop();

        this.stack.push(op1);
        this.stack.push(op2);
    }


    // Méthode pour vider la pile
    public void clear() {
        this.stack.clear();
    }



}
