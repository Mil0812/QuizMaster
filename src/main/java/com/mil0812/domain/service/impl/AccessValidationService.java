package com.mil0812.domain.service.impl;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.entity.impl.User.Status;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.unitOfWork.PersistenceContext;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AccessValidationService {

  private final UserRepository userRepository;
  private final SignInService signInService;

  public AccessValidationService(PersistenceContext persistenceContext,
      SignInService signInService) {
    this.userRepository = persistenceContext.users.repository;
    this.signInService = signInService;
  }

  public boolean canCreate(UUID userId, DtoTypes dtoTypes) {
    User user = getUser(userId);

    return switch (dtoTypes) {
      case SECTION, TEST_TYPE, ANSWER, QUESTION, TEST, RESULT ->
          !user.status().equals(Status.STUDENT);
      case USER -> true;
    };
  }

  public boolean canUpdate(Test test, UUID userId) {
    User user = getUser(userId);
    return test.userId() == userId && !user.status().equals(Status.STUDENT);
  }

  public boolean canDelete(Test test, UUID userId) {
    User user = getUser(userId);
    return test.userId() == userId && !user.status().equals(Status.STUDENT);
  }

  public boolean canUpdate(User user) {
    if (user.status() == Status.TEACHER) {
      return true;
    } else {
      return user.id() == signInService.getUser().id();
    }
  }

  private User getUser(UUID userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFound("На жаль, користувача не знайдено..."));
  }

  public enum DtoTypes {
    TEST,
    TEST_TYPE,
    SECTION,
    QUESTION,
    ANSWER,
    RESULT,
    USER
  }
}
