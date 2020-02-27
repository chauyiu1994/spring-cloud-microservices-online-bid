package com.chauyiu1994.onlineBidProductsService.controllers.errorHandlers;

import com.chauyiu1994.onlineBidProductsService.models.SQLErrorResponseModel;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SQLErrorHandler {

    @ExceptionHandler(DataAccessResourceFailureException.class)
    @ResponseBody
    public SQLErrorResponseModel handleSQLError(DataAccessResourceFailureException e) {
        return SQLErrorResponseModel.builder().message(e.getMessage()).build();
    }
}
