package com.mil0812.domain.dto;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.impl.QuestionType;
import java.awt.Image;
import java.util.UUID;

public class QuestionStoreDto {
  @NotNull //Hibernate validator
   UUID typeId,
  QuestionType type,
  Image image,
  UUID testId,
  String questionText

}
