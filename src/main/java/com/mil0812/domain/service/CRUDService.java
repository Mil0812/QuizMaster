package com.mil0812.domain.service;

import java.util.Collection;
import java.util.UUID;
import java.util.Set;

public interface CRUDService<T> {

  T findById(UUID id);

  T findBy(String column, Object value);

  T findAll();

  T findAllWhere(String whereQuery);

  T save(T entity);

  Set<T> save(Collection<T> entities);

  boolean delete(UUID id);

  boolean delete(Collection<UUID> ids);
}
