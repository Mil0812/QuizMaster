package com.mil0812.persistence.repository.interfaces;

import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.Repository;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public interface ResultRepository extends Repository<Result> {

  Optional<Result> findByTestId(UUID testId);
  Optional<Result> findBySectionId(UUID sectionId);
  Optional<Result> findByDate(Timestamp date);
}
