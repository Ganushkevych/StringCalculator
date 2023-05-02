package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String text){
        if(text.isEmpty()) return 0;
        else if(Pattern.compile("//\\[(.*)]\n(.*)").matcher(text).matches()){
            Matcher m = Pattern.compile("//\\[(.*)]\n(.*)").matcher(text);
            m.matches();
            String YoursDelimiter = m.group(1);
            String[] YoursDelimiters;
            text = m.group(2);
            if(text.contains("\n")) throw new WrongDelimiterException("New line delimiter are not allowed with yours delimiter");
            if(YoursDelimiter.contains("][")){
                System.out.println("hear");
                YoursDelimiters = YoursDelimiter.split("]\\[");
                StringBuilder Delimiters = new StringBuilder();
                Comparator<String> lengthComparator = (s1, s2) -> {
                    return s2.length()-s1.length(); //спадання кількості знаків роздільників
                };
                Arrays.sort(YoursDelimiters, lengthComparator);
                for (String delimiter:YoursDelimiters) {
                    Delimiters.append("|");
                    for (int i=0;i<delimiter.length();i++){
                        if(!Character.isDigit(delimiter.charAt(i))){
                            Delimiters.append("\\");
                        }
                        Delimiters.append(delimiter.charAt(i));
                    }
                }
                Delimiters = new StringBuilder(Delimiters.substring(1));
                text = text.replaceAll(String.valueOf(Delimiters),"\n");
            }
            else text = text.replace(YoursDelimiter, "\n");
        }
        else if(Pattern.compile("//(.*)\n(.*)").matcher(text).matches()){
            Matcher m = Pattern.compile("//(.*)\n(.*)").matcher(text);
            m.matches();
            String YoursDelimiter = m.group(1);
            text = m.group(2);
            if(text.contains("\n")) throw new WrongDelimiterException("New line delimiter are not allowed with yours delimiter");
            text = text.replace(YoursDelimiter, "\n");
        }
        if(text.charAt(0) == ',' || text.charAt(0) == '\n' || text.endsWith(",") || text.endsWith("\n")){
            throw new WrongDelimiterException("Delimiters can't be first or last character");
        }
        if(text.contains(",\n")||text.contains("\n,")){
            throw new WrongDelimiterException("Two different delimiters in a row are not allowed");
        }
        int sumOfNumbers = 0;
        ArrayList<Integer> negativeNumbersList = new ArrayList<>();
        text = text.replace("\n",",");
        String[] numbers = text.split(",");
        for(String number:numbers){
            if(!number.isEmpty()){
                if(!isNumeric(number)) throw new WrongDelimiterException("Wrong delimiter");
                if(Integer.parseInt(number)<0){
                    negativeNumbersList.add(Integer.parseInt(number));
                }
                else if(Integer.parseInt(number)<=1000) sumOfNumbers += Integer.parseInt(number);
            }
        }
        if(!negativeNumbersList.isEmpty()){
            throw new NegativeNumbersException("Negative numbers are not allowed",negativeNumbersList);
        }
        return sumOfNumbers;
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
