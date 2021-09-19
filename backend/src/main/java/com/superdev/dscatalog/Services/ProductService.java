package com.superdev.dscatalog.Services;

import com.superdev.dscatalog.dto.ProductDTO;
import com.superdev.dscatalog.entities.Product;
import com.superdev.dscatalog.exceptions.DatabaseException;
import com.superdev.dscatalog.exceptions.EntityNotFoundException;
import com.superdev.dscatalog.repositories.CategoryRepository;
import com.superdev.dscatalog.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

  @Autowired ProductRepository ProductRepository;

  @Autowired CategoryRepository categoryRepo;

  @Transactional(readOnly = true)
  public List<Product> findAll() {
    return ProductRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ProductDTO findbyid(long Id) {
    Optional<Product> cat = ProductRepository.findById(Id);

    return new ProductDTO(
        cat.orElseThrow(() -> new EntityNotFoundException("Not found")), cat.get().getCategories());
  }

  @Transactional()
  public ProductDTO insert(ProductDTO prod) {
    Product _product = new Product();
    copyDtoToEntity(prod, _product);

    _product = ProductRepository.save(_product);

    return new ProductDTO(_product);
  }

  @Transactional()
  public ProductDTO update(Long id, ProductDTO prod) {

    try {
      Product _product = new Product();
      copyDtoToEntity(prod, _product);

      _product = ProductRepository.save(_product);

      return new ProductDTO(_product);

    } catch (javax.persistence.EntityNotFoundException e) {
      throw new EntityNotFoundException("Entity not found");
    }
  }

  @Transactional
  public void delete(Long id) {

    try {
      ProductRepository.deleteById(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new EntityNotFoundException("Entity not found " + id);
    } catch (DataIntegrityViolationException ex) {
      throw new DatabaseException("Integrity violation");
    }
  }

  public Page<ProductDTO> findAllPaged(PageRequest page) {
    return ProductRepository.findAll(page).map(x -> new ProductDTO(x));
  }

  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.getCategories().clear();

    entity.setName(dto.getName());
    entity.setPrice(dto.getPrice());
    entity.setDescription(dto.getDescription());
    entity.setImg_url(dto.getImg_url());
    entity.setDate(dto.getDate());

    dto.getCategories()
        .forEach(each -> entity.getCategories().add(categoryRepo.getOne(each.getId())));
  }
}
