package com.superdev.dscatalog.resources;

import com.superdev.dscatalog.Services.CategoryService;
import com.superdev.dscatalog.entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/category")
public class CategoryResource {

  @Autowired CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {

    return ResponseEntity.ok().body(categoryService.findAll());
  }

  @GetMapping(value = "{Id}")
  public ResponseEntity<Category> findById(@RequestParam Long Id) {

    Category cat = categoryService.findbyid(Id);

    return ResponseEntity.ok().body(cat);
  }
}
