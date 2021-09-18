package com.superdev.dscatalog.resources;

import com.superdev.dscatalog.Services.CategoryService;
import com.superdev.dscatalog.dto.CategoryDTO;
import com.superdev.dscatalog.entities.Category;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "api/category")
public class CategoryResource {

  @Autowired CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {

    return ResponseEntity.ok().body(categoryService.findAll());
  }

  @GetMapping(value = "/{Id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long Id) {

    CategoryDTO cat = categoryService.findbyid(Id);

    return ResponseEntity.ok().body(cat);
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO cat) {

    cat = categoryService.insert(cat);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(cat.getId())
            .toUri();

    return ResponseEntity.created(uri).body(cat);
  }
}
