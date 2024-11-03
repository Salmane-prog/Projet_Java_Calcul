package GUI;

import java.util.List;
import java.util.Stack;

public interface CalculatorGUIInterface {
    public void affiche(String accu);
    public void change(String value);
    public void change(Stack<Double> accumulator);

}