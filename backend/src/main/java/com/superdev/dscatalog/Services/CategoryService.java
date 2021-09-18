package com.superdev.dscatalog.Services;

import com.superdev.dscatalog.entities.Category;
import com.superdev.dscatalog.exceptions.EntityNotFoundException;
import com.superdev.dscatalog.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

  @Autowired CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Category findbyid(long Id) {
    Optional<Category> cat = categoryRepository.findById(Id);

    return cat.orElseThrow(() -> new EntityNotFoundException(""));
  }
}
