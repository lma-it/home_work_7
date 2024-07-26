package home_work.service;

import java.util.Scanner;

import home_work.entity.ComplexNumber;

public class ComplexNumberService{

    private final ComplexNumber complexNumber;

    public ComplexNumberService() {
        this.complexNumber = new ComplexNumber();
    }

    public ComplexNumber getComplexNumber() {
        return complexNumber;
    }

    public ComplexNumber createComplexNumber() {
        Scanner scan = new Scanner(System.in);
        ComplexNumber complexNumber = new ComplexNumber();
        System.out.println("Введите вещественную часть комплексного числа: ");
        double real = Double.parseDouble(scan.nextLine());
        complexNumber.setReal(real);
        System.out.println("Введите мнимую часть комплексного числа");
        double imaginary = Double.parseDouble(scan.nextLine());
        complexNumber.setImaginary(imaginary);
        return complexNumber;
    }

}
