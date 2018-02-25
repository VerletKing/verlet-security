package org.verlet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author verlet
 * @date 2018/2/14
 */
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String,String> handler(UserException ue){
        Map<String,String> map = new HashMap<>(2);
        map.put("message",ue.getMessage());
        map.put("id",String.valueOf(ue.getId()));
        return map;
    }
}
