package org.surojit.tool.calculator;

import dev.langchain4j.agent.tool.Tool;

public class CalculatorTool {

    @Tool("Add two numbers")
    public int add(int a, int b) {
        System.out.println("addition called");
        return a + b;
    }

    @Tool("Multiply two numbers")
    public int multiply(int a, int b) {
        System.out.println("multiplication called");
        return a * b;
    }

    @Tool("Subtract two numbers")
    public int subtract(int a, int b) {
        System.out.println("subtraction called");
         return a - b;
    }
}
