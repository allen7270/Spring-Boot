package com.springboot.project.user.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // pram constructor
@NoArgsConstructor // no pram constructor
public class UserData {
    private String username;
    private String password;
}
