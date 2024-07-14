package com.rocketseat.planner.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private String description;
    public ExceptionResponse(String message,String description){
        this.message = message;
        this.description = description;
    }


}
