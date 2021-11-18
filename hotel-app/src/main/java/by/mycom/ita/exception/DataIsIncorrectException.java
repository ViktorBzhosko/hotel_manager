package by.mycom.ita.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataIsIncorrectException extends RuntimeException{
    private static final Logger logger= LoggerFactory.getLogger(DataIsIncorrectException.class);

    public DataIsIncorrectException (){
        super(DescribeExceptionForIncorrectData.INCORRECT_DATA.getMessage());
        logger.error(DescribeExceptionForIncorrectData.INCORRECT_DATA.getMessage());
    }
}
