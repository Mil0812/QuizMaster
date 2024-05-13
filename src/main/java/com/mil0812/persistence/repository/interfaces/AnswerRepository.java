package com.mil0812.persistence.repository.interfaces;

import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.repository.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AnswerRepository extends Repository<Answer> {

  Optional<Answer> findByQuestionId(UUID questionId);

  Set<Answer> findAllByQuestionId(UUID questionId);
}
