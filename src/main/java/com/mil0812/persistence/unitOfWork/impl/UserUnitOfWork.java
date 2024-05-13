package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.Repository;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class UserUnitOfWork extends GeneralUnitOfWork<User> {

  public final UserRepository repository;

  protected UserUnitOfWork(UserRepository userRepository) {
    super(userRepository);
    this.repository = userRepository;
  }
}
