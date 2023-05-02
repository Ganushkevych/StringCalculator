package org.example;

public class StringCalculator {
    public static int add(String text){
        if(text.isEmpty()) return 0;
        else {
            int sumOfNumbers = 0;
            String[] numbers = text.split(",");
            for(String number:numbers){
                sumOfNumbers += Integer.parseInt(number);
            }
            return sumOfNumbers;
        }
    }
}
