package com.mil0812.persistence.entity.proxy.impl;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.entity.proxy.interfaces.UserProxy;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import java.util.UUID;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserProxyImpl implements UserProxy {
  private final ApplicationContext applicationContext;

  public UserProxyImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public User get(UUID entityId) {
    UserProxy proxy = (userId) -> applicationContext.getBean(UserRepository.class)
        .findById(userId)
        .orElseThrow(() -> new EntityNotFound("Не вдалось знайти користувача за id..."));

    return proxy.get(entityId);
  }
}
