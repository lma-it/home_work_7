package home_work.exceptions;

public class InvalidOperandException extends Exception{
    String error;
    public InvalidOperandException(String msg){
        error = msg;
    }

    public void getError() {
        System.out.println(error);
    }
}
