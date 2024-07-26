package home_work.view;

import home_work.controller.ComplexNumberCalculatorController;
import home_work.entity.ComplexNumber;
import home_work.exceptions.InvalidOperandException;
import home_work.service.ComplexNumberService;

import java.util.Scanner;

public class View {
    private final String ADDICT = "+";
    private final String SUBSTRACT = "-";
    private final String DIVIDE = "/";
    private final String MULTIPLY = "*";

    private final ComplexNumberCalculatorController controller;
    private final ComplexNumberService service;


    public View() {
        this.controller = new ComplexNumberCalculatorController();
        this.service = new ComplexNumberService();
    }

    public void start() {

        boolean isContinue = true;

        while (isContinue) {

            ComplexNumber number1 = service.createComplexNumber();
            ComplexNumber number2 = service.createComplexNumber();

            String operand = consoleInput("Введите операнд (+, -, *, /), чтоб выполнить дальнейшие действия с этими числами:");

            switch (operand) {

                case ADDICT:
                    System.out.println(this.controller.getCalculator().addict(number1, number2));
                    if(isNotExit()){
                        break;
                    }
                    isContinue = false;
                    break;
                case SUBSTRACT:
                    System.out.println(this.controller.getCalculator().substract(number1, number2));
                    if(isNotExit()){
                        break;
                    }
                    isContinue = false;
                    break;
                case DIVIDE:
                    System.out.println(this.controller.getCalculator().divide(number1, number2));
                    if(isNotExit()){
                        break;
                    }
                    isContinue = false;
                    break;
                case MULTIPLY:
                    System.out.println(this.controller.getCalculator().multiply(number1, number2));
                    if(isNotExit()){
                        break;
                    }
                    isContinue = false;
                    break;
                default:
                    new InvalidOperandException("Вы ввели не верный операнд.Необходимо ввести один из следующих: +, -, *, /.").getError();
            }
        }
        
    }

    private String consoleInput(String msg) {
        Scanner scan = new Scanner(System.in);
        System.out.println(msg);
        return scan.nextLine();
    }

    private boolean isNotExit(){
        String stop = consoleInput("Хотите продолжить? y/n");
        return stop.equals("y");
    }

}
