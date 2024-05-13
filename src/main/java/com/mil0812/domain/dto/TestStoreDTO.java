package com.mil0812.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record TestStoreDTO(
    UUID userId,
    @NotNull(message = "Тест повинен мати принаймні один розділ")
    UUID sectionId,
    @NotNull(message = "У теста має бути заданий тип")
    UUID typeId,
    @NotNull(message = "Назва тесту не має бути порожньою")
    @Size(max = 40, message = "Максимальна довжина назви - 40 символів")
    String title
) {

}