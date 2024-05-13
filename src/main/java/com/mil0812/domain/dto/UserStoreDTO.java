package com.mil0812.domain.dto;

import com.mil0812.persistence.entity.impl.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Спеціальний DTO, що використовується при створенні і на який навішується валідація
 */
public record UserStoreDTO(
    @NotBlank(message = "Логін не може бути порожнім...")
    @Size(min = 5, max = 20, message = "Розмір логіну має бути від 5 до 20 символів...")
    String login,
    @NotBlank(message = "Пароль не може бути порожнім...")
    @Size(min = 5, max = 20, message = "Розмір паролю має бути від 5 до 20 символів...")
    String password,
    @NotBlank(message = "Поле імені не може бути порожнім...")
    @Size(max = 30)
    String firstName,
    @NotBlank(message = "Поле прізвища не може бути порожнім...")
    String lastName,
    @NotBlank(message = "Поле електронної адреси не може бути порожнім...")
    @Pattern(regexp = ".+@.+\\..+", message = "Емейл має відповідати формату")
    String email,
    @NotNull(message = "Статус користувача не має бути порожнім")
    @Pattern(regexp = "teacher|student", message = "Оберіть статус з-поміж цих двох: student або teacher")
    User.Status status
) {

}
