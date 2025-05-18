package com.projects.shopIt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequest {
    public String name;
    public String email;
}
