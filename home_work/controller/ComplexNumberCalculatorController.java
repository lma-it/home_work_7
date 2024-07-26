package home_work.controller;

import home_work.entity.ComplexNumbersCalculator;

public class ComplexNumberCalculatorController {
    
    private final ComplexNumbersCalculator calculator;

    public ComplexNumberCalculatorController() {
        this.calculator = new ComplexNumbersCalculator();
    }

    public ComplexNumbersCalculator getCalculator() {
        return calculator;
    }
}
