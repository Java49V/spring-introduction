package telran.spring.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CalculatorExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return "Неверное значение: '" + e.getValue() + "' для параметра '" + e.getName() + "'. Требуется числовое значение.";
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleArithmeticException(ArithmeticException e) {
        return "Арифметическая ошибка: " + e.getMessage();
    }

}
