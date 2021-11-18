package by.mycom.ita.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{
    private static final Logger logger= LoggerFactory.getLogger(DataIsIncorrectException.class);

    public DataNotFoundException (){
        super(DescribeForDataNotFoundException.NOT_FOUND_EXCEPTION.getMessage());
        logger.error(DescribeForDataNotFoundException.NOT_FOUND_EXCEPTION.getMessage());
    }
}
