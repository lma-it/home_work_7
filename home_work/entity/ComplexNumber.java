package home_work.entity;

public class ComplexNumber{

    private double real;
    private double imaginary;


    /**
     * Конструкторы
     */
    public ComplexNumber() {
    }

    /*                                                     ГЕТТЕРЫ И СЕТТЕРЫ                                          */

    public double getImaginary() {
        return imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }


    @Override
    public String toString() {
        return String.format("Complex number is: (%s %s %si)", this.real, (this.imaginary >= 0) ? "+" : "-",  (this.imaginary == 1) ? "" : (this.imaginary > 0) ? this.imaginary : Math.abs(this.imaginary));
    }

}
