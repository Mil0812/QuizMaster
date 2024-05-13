package com.mil0812.domain.dto;

import com.mil0812.persistence.entity.proxy.interfaces.Sections;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record TestUpdateDTO(
    @NotNull(message = "Відсутній іденитфікатор тесту")
    UUID id,
    UUID sectionId,
    UUID authorId,
    UUID typeId,
    @Size(max = 40, message = "Максимальна довжина назви - 40 символів")
    String title,
    String image,
    int questionCount
) {

}
