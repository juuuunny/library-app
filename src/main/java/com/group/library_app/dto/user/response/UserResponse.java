package com.group.library_app.dto.user.response;

import com.group.library_app.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.age=user.getAge();
    }

}
