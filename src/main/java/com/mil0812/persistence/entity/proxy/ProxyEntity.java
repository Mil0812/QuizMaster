package com.mil0812.persistence.entity.proxy;

import java.util.UUID;

@FunctionalInterface
public interface ProxyEntity<T> {

  /**
   * Метод для отримання об’єкта потрібної сутності за його унікальним ідентифікатором (entityId).
   * @param entityId - ідентифікатор
   * @return - об'єкт entity
   */
  T get(UUID entityId);

}