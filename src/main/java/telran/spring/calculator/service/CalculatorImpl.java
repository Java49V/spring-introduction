package telran.spring.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorImpl implements Calculator {
    
    @Override
    public double multiply(double op1, double op2) {
        return op1 * op2;
    }

    @Override
    public double sum(double op1, double op2) {
        return op1 + op2;
    }

    @Override
    public double subtract(double op1, double op2) {
        return op1 - op2;
    }

    @Override
    public double divide(double op1, double op2) {
        if (op2 == 0) throw new ArithmeticException("Division by zero");
        return op1 / op2;
    }
}
