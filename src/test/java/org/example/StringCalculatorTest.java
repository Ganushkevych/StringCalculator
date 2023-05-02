package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    @Test
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }
    @Test
    public void shouldReturnNumberOnNumber() {
        assertEquals(1, StringCalculator.add("1"));
    }
    @Test
    public void shouldReturnSumOfTwoNumbers() {
        assertEquals(3, StringCalculator.add("1,2"));
    }
    @Test
    public void shouldReturnSumOfNumbers() {
        assertEquals(7, StringCalculator.add("1,2,4"));
        assertEquals(12, StringCalculator.add("1,2,4,5"));
    }
    @Test
    public void shouldReturnSumWithNewLineAsDelimiter() {
        assertEquals(12, StringCalculator.add("1,2\n4,5"));
        assertEquals(12, StringCalculator.add("1\n2\n4\n5"));
    }
    @Test
    public void shouldThrowExceptionOnTwoDelimitersInARow() {
        try{
            StringCalculator.add("1,\n2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnUnknownDelimiter() {
        try{
            StringCalculator.add("1,2$3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheEnd() {
        try{
            StringCalculator.add("1,2,3,");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheBegin() {
        try{
            StringCalculator.add("\n1,2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldReturnSumWithYoursDelimiter() {
        assertEquals(6, StringCalculator.add("//*\n1*2*3"));
        assertEquals(6, StringCalculator.add("//;\n1,2;3"));
        assertEquals(6, StringCalculator.add("//*\n1*2,3"));
    }
    @Test
    public void shouldThrowExceptionOnTwoDelimitersInARow2() {
        try{
            StringCalculator.add("//;\n1,;2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnUnknownDelimiter2() {
        try{
            StringCalculator.add("//%\n1%2$3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheEnd2() {
        try{
            StringCalculator.add("//%\n1,2,3%");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheBegin2() {
        try{
            StringCalculator.add("//^\n^1,2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumbers() {
        try{
            StringCalculator.add("-1,-23,4");
            fail("Should be NegativeNumbersException");
        }catch (NegativeNumbersException e){
            assertEquals(e.getMessage(),"Negative numbers are not allowed\n[-1, -23]");
        }
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumbers2() {
        try{
            StringCalculator.add("//%\n-1%-2,3,4");
            fail("Should be NegativeNumbersException");
        }catch (NegativeNumbersException e){
            assertEquals(e.getMessage(),"Negative numbers are not allowed\n[-1, -2]");
        }
    }
    @Test
    public void shouldReturnSumAvoidingNumberThatMoreThanThousand() {
        assertEquals(1999, StringCalculator.add("1000,999,1001"));
    }
    @Test
    public void shouldReturnSumAvoidingNumberThatMoreThanThousand2() {
        assertEquals(1999, StringCalculator.add("//*\n1000*999,1001"));
    }
    @Test
    public void shouldReturnSumWithMultipliedDelimiter() {
        assertEquals(6, StringCalculator.add("//[*]\n1*2**3"));
        assertEquals(6, StringCalculator.add("//[^]\n1^^^2,,3"));
    }
    @Test
    public void shouldReturnSumWithFewDelimiters() {
        assertEquals(62, StringCalculator.add("//[*][:][?]\n1**1:2::5???53"));
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }
}