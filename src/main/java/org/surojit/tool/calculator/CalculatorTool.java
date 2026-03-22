package org.surojit.tool.calculator;

import dev.langchain4j.agent.tool.Tool;

public class CalculatorTool {

    @Tool("Add two numbers")
    public double add(double a, double b) {
        System.out.println("addition called");
        return a + b;
    }

    @Tool("Multiply two numbers")
    public double multiply(double a, double b) {
        System.out.println("multiplication called");
        return a * b;
    }

    @Tool("Subtract two numbers")
    public double subtract(double a, double b) {
        System.out.println("subtraction called");
         return a - b;
    }
}
