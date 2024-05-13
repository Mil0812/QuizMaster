package com.mil0812.persistence.repository.interfaces;

import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface QuestionRepository extends Repository<Question> {

  Optional<Question> findByTestId(UUID testId);

  Set<Question> findAllByTestId(UUID testId);
}
