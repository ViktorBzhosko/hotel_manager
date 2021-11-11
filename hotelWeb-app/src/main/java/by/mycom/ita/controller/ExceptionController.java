package by.mycom.ita.controller;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.exception.ExceptionInformation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIsIncorrectException.class)
    public String handlerNotCorrectData(Model model, DataIsIncorrectException exception) {
        ExceptionInformation exceptionInformation = new ExceptionInformation();
        exceptionInformation.setMessage("Вы ввели не корректные данные. Проверьте вводимые даныне");
        exceptionInformation.setErrorCode(403);
        exceptionInformation.setExceptionType("DataIsIncorrect");
        model.addAttribute("exception", exceptionInformation);
        return "Exception";
    }

    @ExceptionHandler(DataNotFoundException.class)
    public String handlerDataNotFound(Model model, DataNotFoundException exception) {
        ExceptionInformation exceptionInformation = new ExceptionInformation();
        exceptionInformation.setMessage("Не найдены данные по запросу");
        exceptionInformation.setErrorCode(403);
        exceptionInformation.setExceptionType("DataNotFound");
        model.addAttribute("exception", exceptionInformation);
        return "Exception";
    }


    @ExceptionHandler(Exception.class)
    public String exception(Model model, Exception e) {

        ExceptionInformation exceptionInformation = new ExceptionInformation();
        int numberStatus = Integer.parseInt(e.getMessage().substring(0, 3).strip());
        if (numberStatus==200) {
            System.out.println("Success");
        }
        if (numberStatus == 404) {
            exceptionInformation.setMessage("Server can't process query");
            exceptionInformation.setExceptionType("DataNotFound");
        } else if (numberStatus >= 500) {
            exceptionInformation.setMessage("Server exception");
        } else {
            exceptionInformation.setMessage("Not correct query");
        }
        exceptionInformation.setErrorCode(numberStatus);
        model.addAttribute("exception", exceptionInformation);
        return "Exception";
    }
}
