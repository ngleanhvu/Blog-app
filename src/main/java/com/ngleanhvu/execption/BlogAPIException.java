package com.ngleanhvu.execption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@NoArgsConstructor
public class BlogAPIException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
    public BlogAPIException(HttpStatus httpStatus, String message){
        this.httpStatus=httpStatus;
        this.message=message;
    }
}
