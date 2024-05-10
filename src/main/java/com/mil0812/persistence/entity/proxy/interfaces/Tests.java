package com.mil0812.persistence.entity.proxy.interfaces;

import com.mil0812.persistence.entity.impl.Test;
import java.util.Set;
import java.util.UUID;

@FunctionalInterface
public interface Tests {
  Set<Test> get(UUID sectionId);

}
