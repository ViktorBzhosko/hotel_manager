package by.mycom.ita.exception;

import lombok.Data;

@Data
public class ExceptionInformation {
    private int errorCode;
    private String exceptionType;
    private String message;
}
