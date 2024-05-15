package com.mil0812.domain.service.impl;

import com.mil0812.domain.dto.TestStoreDTO;
import com.mil0812.domain.dto.TestUpdateDTO;
import com.mil0812.domain.exception.NoAccess;
import com.mil0812.domain.exception.ValidationException;
import com.mil0812.domain.service.impl.AccessValidationService.DtoTypes;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.unitOfWork.PersistenceContext;
import com.mil0812.persistence.unitOfWork.impl.TestUnitOfWork;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  private final TestUnitOfWork testUnitOfWork;
  private final TestRepository testRepository;
  private final AccessValidationService accessValidationService;
  private final Validator validator;

  public TestService(PersistenceContext persistenceContext,
      AccessValidationService accessValidationService, Validator validator) {
    this.testUnitOfWork = persistenceContext.tests;
    this.testRepository = persistenceContext.tests.testRepository;
    this.accessValidationService = accessValidationService;
    this.validator = validator;
  }


  public Test findById(UUID id) {
    return testRepository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Не вдалося знайти тест..."));
  }

  public Test findByTestType(UUID testTypeId) {
    return testRepository.findByTestType(testTypeId)
        .orElseThrow(() -> new EntityNotFound("Не вдалося знайти тест..."));
  }

  public Test findByAuthor(UUID userId) {
    return testRepository.findByAuthor(userId)
        .orElseThrow(() -> new EntityNotFound("Не вдалося знайти тест..."));
  }

  public Set<Test> findAll() {
    return new TreeSet<>(testRepository.findAll());
  }

  public Set<Test> findAllByAuthor(UUID userId) {
    return new TreeSet<>(testRepository.findAllByAuthorId(userId));
  }

  public long count() {
    return testRepository.count();
  }

  public Test create(TestStoreDTO testStoreDTO) {
    var violations = validator.validate(testStoreDTO);
    if (!violations.isEmpty()) {
      throw ValidationException.create("збереженні тесту", violations);
    } else if (!accessValidationService.canCreate(testStoreDTO.userId(), DtoTypes.TEST)) {
      throw new NoAccess("На жаль, Ви не маєте дооступу до створення тестів");
    }

    Test test = new Test(
        null,
        null,
        testStoreDTO.userId(),
        testStoreDTO.typeId(),
        testStoreDTO.sectionId(),
        testStoreDTO.title(),
        null,
        0,
        null
    );

    testUnitOfWork.registerNew(test);
    testUnitOfWork.commit();
    return testUnitOfWork.getEntity();
  }

  public Test update(TestUpdateDTO testUpdateDTO) {
    var violations = validator.validate(testUpdateDTO);
    if (!violations.isEmpty()) {
      throw ValidationException.create("оновленні тесту", violations);
    }

    Test oldTest = findById(testUpdateDTO.id());

    if (!accessValidationService.canUpdate(oldTest, testUpdateDTO.authorId())) {
      throw new NoAccess("В Вас нема доступу до зміни даного тесту");
    }
    Test test = new Test(
        testUpdateDTO.id(),
        null,
        testUpdateDTO.authorId(),
        testUpdateDTO.typeId(),
        testUpdateDTO.sectionId(),
        testUpdateDTO.title(),
        testUpdateDTO.image(),
        testUpdateDTO.questionCount(),
        null
    );

    testUnitOfWork.registerModified(test);
    testUnitOfWork.commit();
    return testUnitOfWork.getEntity();
  }

  public boolean delete(UUID id, UUID userId) {
    Test test = findById(id);
    if (!accessValidationService.canDelete(test, userId)) {
      throw new NoAccess("В Вас нема доступу до видалення даного тесту");
    }

    return testUnitOfWork.testRepository.delete(test.id());
  }

}
