package com.superdev.dscatalog;

import com.superdev.dscatalog.Services.CategoryService;
import com.superdev.dscatalog.dto.CategoryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
public class CategoryServiceTest {

  @Autowired CategoryService service;

  @Test
  public void findByIdShouldReturnValidObject() {

    CategoryDTO catDTO = new CategoryDTO();
    catDTO.setName("Category test unit");

    Assertions.assertNotNull(service.insert(catDTO).getId());
  }
}
