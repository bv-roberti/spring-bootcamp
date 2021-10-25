package com.superdev.dscatalog.repositories;

import com.superdev.dscatalog.entities.Product;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTests {

  @Autowired ProductRepository repo;

  @Test
  public void findByIdShouldReturnValidObject() {
    Long productId = 1L;

    Optional<Product> product = repo.findById(productId);

    Assertions.assertTrue(product.isPresent());
  }

  @Test
  public void findByIdShouldReturnEmptyWhenIdNotValid() {

    Long productId = 100L;

    Optional<Product> product = repo.findById(productId);

    Assertions.assertFalse(product.isPresent());
  }
}
