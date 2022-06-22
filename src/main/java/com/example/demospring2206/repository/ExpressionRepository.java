package com.example.demospring2206.repository;

import com.example.demospring2206.entities.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Integer> {

    List<Expression> findByExpr(String expr);

    List<Expression> findByResult(double result);
}