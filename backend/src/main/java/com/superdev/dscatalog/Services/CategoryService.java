package com.superdev.dscatalog.Services;

import com.superdev.dscatalog.dto.CategoryDTO;
import com.superdev.dscatalog.entities.Category;
import com.superdev.dscatalog.exceptions.DatabaseException;
import com.superdev.dscatalog.exceptions.EntityNotFoundException;
import com.superdev.dscatalog.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

  @Autowired CategoryRepository categoryRepository;

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Transactional(readOnly = true)
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Transactional(readOnly = true)
  public CategoryDTO findbyid(long Id) {
    Optional<Category> cat = categoryRepository.findById(Id);

    return new CategoryDTO(cat.orElseThrow(() -> new EntityNotFoundException("Not found")));
  }

  @Transactional()
  public CategoryDTO insert(CategoryDTO cat) {

    log.info("Creating new entry");
    Category _category = new Category(cat.getName());

    _category = categoryRepository.save(_category);
    log.info("New entry created");

    return new CategoryDTO(_category);
  }

  @Transactional()
  public CategoryDTO update(Long id, CategoryDTO cat) {

    try {
      Category _category = categoryRepository.getOne(id);
      _category.setName(cat.getName());

      _category = categoryRepository.save(_category);

      return new CategoryDTO(_category);

    } catch (javax.persistence.EntityNotFoundException e) {
      throw new EntityNotFoundException("Entity not found");
    }
  }

  @Transactional
  public void delete(Long id) {

    try {
      categoryRepository.deleteById(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new EntityNotFoundException("Entity not found " + id);
    } catch (DataIntegrityViolationException ex) {
      throw new DatabaseException("Integrity violation");
    }
  }

  public Page<CategoryDTO> findAllPaged(Pageable page) {
    return categoryRepository.findAll(page).map(x -> new CategoryDTO(x));
  }
}
