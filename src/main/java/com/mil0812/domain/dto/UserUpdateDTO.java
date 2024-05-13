package com.mil0812.domain.dto;

import com.mil0812.persistence.entity.impl.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UserUpdateDTO(
    @NotBlank(message = "Відсутній іденитфікатор користувача")
    UUID id,
    @Size(min = 5, max = 20, message = "Розмір логіну має бути від 5 до 20 символів...")
    String login,
    @Size(min = 5, max = 20, message = "Розмір паролю має бути від 5 до 20 символів...")
    String password,
    @Size(max = 30)
    String firstName,
    String lastName,
    @Pattern(regexp = ".+@.+\\..+", message = "Емейл має відповідати формату")
    String email,
    @Pattern(regexp = "teacher|student", message = "Оберіть статус з-поміж цих двох: student або teacher")
    User.Status status
) {

}