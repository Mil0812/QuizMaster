package com.mil0812.domain.dto;

import com.mil0812.persistence.entity.impl.TestType;
import java.awt.Image;
import java.util.UUID;

public class QuestionStoreDto {
  @NotNull //Hibernate validator
   UUID typeId,
  TestType type,
  Image image,
  UUID testId,
  String questionText

}
