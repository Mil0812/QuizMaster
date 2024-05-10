package com.mil0812.persistence.repository.interfaces;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.Repository;
import java.util.Optional;

public interface UserRepository extends Repository<User> {

  Optional<User> findByLogin(String login);
  Optional<User> findByEmail(String email);
}
