package com.db.multi.controller.advice;

import com.db.multi.response.MultResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {
    private static  final Logger logger= LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public MultResponse<String> badRequestException(MethodArgumentNotValidException e){
        logger.error("method argument are not valid..");
        String errorMessage=e.getBindingResult().getAllErrors().stream().map(it->it.getDefaultMessage())
                .collect(Collectors.joining(","));
        return new MultResponse(false,null,null,errorMessage,400);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public MultResponse<String> constraintValidator(ConstraintViolationException e){
        logger.error("method argument are not valid");
        String errorMessage="Bad Request Param Missing";
        return new MultResponse(false,null,null,errorMessage,400);
    }
}
