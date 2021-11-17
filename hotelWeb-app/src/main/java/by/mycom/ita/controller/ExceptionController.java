package by.mycom.ita.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exception(Model model, Exception e) {
        int status = Integer.parseInt(e.getMessage().substring(0, 3));
        String messageException;
        if (status == 404) {
            messageException = "Не найдены данные согласно запросу!";
        } else if (status >= 500) {
            messageException = "Сервер не может обработать запрос!";
        } else messageException = "Некорректный запрос!";
        model.addAttribute("status", status);
        model.addAttribute("message", messageException);
        return "Exception";
    }
}
