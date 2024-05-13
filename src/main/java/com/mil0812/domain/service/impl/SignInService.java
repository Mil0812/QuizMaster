package com.mil0812.domain.service.impl;

import com.mil0812.domain.exception.SignInException;
import com.mil0812.domain.exception.UserAlreadyExists;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.unitOfWork.PersistenceContext;
import com.password4j.Password;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

  private final UserRepository userRepository;
  private User user;

  public SignInService(PersistenceContext persistenceContext) {
    this.userRepository = persistenceContext.users.repository;
  }

  public boolean authenticated(String login, String password) {
    if (user != null) {
      throw new UserAlreadyExists(
          STR."Ви вже авторизувалися як: \{user.login()}");
    }

    User foundedUser = userRepository.findByLogin(login)
        .orElseThrow(() -> new SignInException("Неправильний логін або пароль..."));

    if (!password.equals(foundedUser.password())) {
      return false;
    }

    user = foundedUser;
    return true;
  }

  public boolean isLoggedIn() {
    return user != null;
  }

  public User getUser() {
    return user;
  }

  public void logOut() {
    if (user == null) {
      throw new UserAlreadyExists("Ви ще зареєстровані");
    }
    user = null;
  }

}
