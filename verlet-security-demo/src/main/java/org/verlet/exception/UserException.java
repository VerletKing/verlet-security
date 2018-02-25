package org.verlet.exception;

import lombok.Data;

/**
 * @author verlet
 * @date 2018/2/14
 */

@Data
public class UserException extends RuntimeException {

    private int id;
    public UserException(int id){
        super("UserException");
        this.id = id;
    }
}
