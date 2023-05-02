package org.example;

public class StringCalculator {
    public static int add(String text){
        if(text.isEmpty()) return 0;
        else if(text.charAt(0) == ',' || text.charAt(0) == '\n' || text.endsWith(",") || text.endsWith("\n")){
            throw new WrongDelimiterException("Delimiters can't be first or last character");
        }
        else if(text.contains(",\n")||text.contains("\n,")){
            throw new WrongDelimiterException("Two different delimiters in a row are not allowed");
        }
        else {
            int sumOfNumbers = 0;
            text = text.replace("\n",",");
            String[] numbers = text.split(",");
            for(String number:numbers){
                if(!isNumeric(number)) throw new WrongDelimiterException("Wrong delimiter");
                sumOfNumbers += Integer.parseInt(number);
            }
            return sumOfNumbers;
        }
    }
    private static boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
