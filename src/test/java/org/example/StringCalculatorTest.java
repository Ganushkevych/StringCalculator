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
            StringCalculator.add("\n1,2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheBegin() {
        try{
            StringCalculator.add("1,2,3,");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
}