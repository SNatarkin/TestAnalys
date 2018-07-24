package com.example.testsystem.transfer;


import com.example.testsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;

    public static UserDto from(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
