package org.verlet.dto;


import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author verlet
 * @date 2017/12/31
 */
public class UserDTO implements Serializable {

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }


    private String name;
    @NotBlank
    private String password;

    @JsonView(UserSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
