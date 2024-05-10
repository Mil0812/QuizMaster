package com.mil0812.persistence.repository.interfaces;

import java.util.UUID;

/**
 * Інтерфейс для зв'язку 'багато до багатьох':
 *  attach - для приєднання елементів однієї сутності до іншої;
 *  detach - для від'єднання
 */
public interface ManyToMany {
  boolean attach(UUID firstId, UUID secondId);
  boolean detach(UUID firstId, UUID secondId);
}
