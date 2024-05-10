package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.UserRowMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericJdbcRepository<User>
    implements UserRepository {

  public UserRepositoryImpl(ConnectionManager connectionManager, UserRowMapper userRowMapper) {
    super(connectionManager, userRowMapper, TableTitles.USER.getName());
  }

  @Override
  protected List<String> tableAttributes() {
    return List.of("login", "password", "firstName", "lastName", "email", "status");
  }

  @Override
  protected List<Object> tableValues(User user) {
    ArrayList<Object> values = new ArrayList<>();
    values.add(user.login());
    values.add(user.password());
    values.add(user.firstName());
    values.add(user.lastName());
    values.add(user.email());
    values.add(user.status());
    return values;
  }

  @Override
  public Optional<User> findByLogin(String login) {
    return findBy("login", login);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return findBy("email", email);
  }
}
