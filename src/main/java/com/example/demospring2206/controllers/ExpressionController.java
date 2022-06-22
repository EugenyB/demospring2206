package com.example.demospring2206.controllers;

import com.example.demospring2206.entities.Expression;
import com.example.demospring2206.repository.ExpressionRepository;
import com.example.demospring2206.service.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExpressionController {

    private final ExpressionRepository expressionRepository;

    private final ExpressionService expressionService;

    @Autowired
    public ExpressionController(ExpressionRepository expressionRepository, ExpressionService expressionService) {
        this.expressionRepository = expressionRepository;
        this.expressionService = expressionService;
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        model.addAttribute("expressions", expressionRepository.findAll());
        return "result";
    }

    @PostMapping("/add")
    public String addExpression(@RequestParam String expr) {
        try {
            double v = expressionService.calculate(expr);
            Expression expression = new Expression(0, expr, v);
            expressionRepository.save(expression);
        } catch (Exception ignored) {}

        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpression(@PathVariable("id") int id) {
        Expression expression = expressionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid expression id"));
        expressionRepository.delete(expression);

        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdatePage(@PathVariable("id") int id, Model model) {
        Expression expression = expressionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid expression id"));
        model.addAttribute("expression", expression);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateExpression(@PathVariable("id") int id, Expression expression) {
        try {
            double v = expressionService.calculate(expression.getExpr());
            expression.setResult(v);
            expressionRepository.save(expression);
        } catch (Exception ignored){}

        return "redirect:/list";
    }
}
