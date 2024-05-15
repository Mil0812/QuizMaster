package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.Repository;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class SectionUnitOfWork extends GeneralUnitOfWork<Section> {

  private final SectionRepository sectionRepository;

  protected SectionUnitOfWork(SectionRepository sectionRepository) {
    super(sectionRepository);
    this.sectionRepository = sectionRepository;
  }
}
