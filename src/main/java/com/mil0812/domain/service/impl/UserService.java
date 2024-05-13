package com.mil0812.domain.service.impl;

import com.mil0812.domain.dto.UserStoreDTO;
import com.mil0812.domain.dto.UserUpdateDTO;
import com.mil0812.domain.exception.NoAccess;
import com.mil0812.domain.exception.ValidationException;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.entity.impl.User.Status;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.unitOfWork.PersistenceContext;
import com.mil0812.persistence.unitOfWork.impl.UserUnitOfWork;
import jakarta.validation.Validator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserUnitOfWork userUnitOfWork;
  private final UserRepository userRepository;
  private final AccessValidationService accessValidationService;
  private final Validator validator;

  public UserService(PersistenceContext persistenceContext,
      AccessValidationService accessValidationService,
      Validator validator) {
    this.userUnitOfWork = persistenceContext.users;
    this.userRepository = persistenceContext.users.repository;
    this.accessValidationService = accessValidationService;
    this.validator = validator;
  }

  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Не вдалося знайти користувача..."));
  }

  public User findByLogin(String login) {
    return userRepository.findByLogin(login)
        .orElseThrow(() -> new EntityNotFound("Не вдалося знайти користувача..."));
  }

  public User findByEmail(String email) {
    return userRepository.findByLogin(email)
        .orElseThrow(() -> new EntityNotFound("Не вдалося знайти користувача..."));
  }

  public Set<User> findAll() {
    return new TreeSet<>(userRepository.findAll());
  }

  /**
   * Метод для отримання списку користувачів з БД
   *
   * @param offset     - вказує, з якого рядка починати вибірку
   * @param limit      - максимальна кількість результатів, які хочемо отримати
   * @param sortColumn - поле, за яким потрібно сортувати результати
   * @param ascending  - булевий параметр, що вказує тип сортування (ASK, DESK)
   * @return - список користувачів
   */
  public Set<User> findAll(int offset,
      int limit,
      String sortColumn,
      boolean ascending) {
    return new TreeSet<>(userRepository.findAll(
        offset,
        limit,
        sortColumn,
        ascending));
  }

  public long count() {
    return userRepository.count();
  }

  public User create(UserStoreDTO userStoreDTO) {
    var violations = validator.validate(userStoreDTO);
    if (!violations.isEmpty()) {
      throw ValidationException.create("збереженні користувача", violations);
    }

    User user = new User(
        null,
        userStoreDTO.login(),
        userStoreDTO.password(),
        userStoreDTO.firstName(),
        userStoreDTO.lastName(),
        userStoreDTO.email(),
        Objects.nonNull(userStoreDTO.status()) ? userStoreDTO.status() : Status.STUDENT
    );

    userUnitOfWork.registerNew(user);
    userUnitOfWork.commit();
    return userUnitOfWork.getEntity();
  }

  public User update(UserUpdateDTO userUpdateDTO) {
    var violations = validator.validate(userUpdateDTO);
    if (!violations.isEmpty()) {
      throw ValidationException.create("оновленні користувача", violations);
    }

    User oldUser = findById(userUpdateDTO.id());

    if (!accessValidationService.canUpdate(oldUser)) {
      throw new NoAccess("В Вас нема доступу до зміни даного користувача");
    }
    User user = new User(
        userUpdateDTO.id(),
        userUpdateDTO.login(),
        userUpdateDTO.password(),
        userUpdateDTO.firstName(),
        userUpdateDTO.lastName(),
        userUpdateDTO.email(),
        userUpdateDTO.status()
    );

    userUnitOfWork.registerModified(user);
    userUnitOfWork.commit();
    return userUnitOfWork.getEntity();
  }

}
