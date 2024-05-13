package com.mil0812.persistence.repository.interfaces;

import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface TestRepository extends Repository<Test>, ManyToMany {

  Optional<Test> findByAuthor(UUID userId);

  Optional<Test> findByTestType(UUID testTypeId);

  Set<Test> findAllByAuthorId(UUID userId);

  Set<Section> getSections(UUID testId);

}
