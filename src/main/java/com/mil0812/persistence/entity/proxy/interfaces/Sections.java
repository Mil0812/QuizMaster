package com.mil0812.persistence.entity.proxy.interfaces;

import com.mil0812.persistence.entity.impl.Section;
import java.util.Set;
import java.util.UUID;

@FunctionalInterface
public interface Sections {
  Set<Section> get(UUID testId);

}
