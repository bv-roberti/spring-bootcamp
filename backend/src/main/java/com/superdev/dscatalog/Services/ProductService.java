package com.superdev.dscatalog.Services;

import com.superdev.dscatalog.dto.ProductDTO;
import com.superdev.dscatalog.entities.Product;
import com.superdev.dscatalog.exceptions.DatabaseException;
import com.superdev.dscatalog.exceptions.EntityNotFoundException;
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
    Product _Product =
        new Product(
            prod.getImg_url(),
            prod.getName(),
            prod.getDescription(),
            prod.getPrice(),
            prod.getDate());

    _Product = ProductRepository.save(_Product);

    return new ProductDTO(_Product);
  }

  @Transactional()
  public ProductDTO update(Long id, ProductDTO cat) {

    try {
      Product _Product = ProductRepository.getOne(id);
      _Product.setName(cat.getName());
      _Product.setDescription(cat.getDescription());
      _Product.setPrice(cat.getPrice());

      _Product = ProductRepository.save(_Product);

      return new ProductDTO(_Product);

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
}
