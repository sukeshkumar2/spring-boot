package com.example.taskproject.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private long id;

    private  String name;

    private  String email;

    private String password;
}
