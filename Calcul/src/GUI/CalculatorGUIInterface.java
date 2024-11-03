package GUI;

import java.util.List;
import java.util.Stack;

public interface CalculatorGUIInterface {
    void affiche(String accu);  // Method to display the accumulator
    void change(Stack<Double> stackData);
}
