package com.example.demospring2206.service;


import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExpressionService {
    public double calculate(String expressionString) {
        return new ExpressionBuilder(expressionString).build().evaluate();
    }
}
