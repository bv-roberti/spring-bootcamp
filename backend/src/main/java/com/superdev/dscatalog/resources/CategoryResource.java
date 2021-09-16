package com.superdev.dscatalog.resources;

import com.superdev.dscatalog.entities.Category;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/category")
public class CategoryResource {

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> lst = new ArrayList<>();

    lst.add(new Category(1L, "cat1"));

    return ResponseEntity.ok().body(lst);
  }
}
