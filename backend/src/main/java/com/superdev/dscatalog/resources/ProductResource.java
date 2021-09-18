package com.superdev.dscatalog.resources;

import com.superdev.dscatalog.Services.ProductService;
import com.superdev.dscatalog.dto.ProductDTO;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "api/product")
public class ProductResource {

  @Autowired ProductService ProductService;

  @GetMapping
  public ResponseEntity<Page<ProductDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

    PageRequest _page = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

    return ResponseEntity.ok().body(ProductService.findAllPaged(_page));
  }

  @GetMapping(value = "/{Id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long Id) {

    ProductDTO cat = ProductService.findbyid(Id);

    return ResponseEntity.ok().body(cat);
  }

  @PostMapping
  public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO cat) {

    cat = ProductService.insert(cat);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id")
            .buildAndExpand(cat.getId())
            .toUri();

    return ResponseEntity.created(uri).body(cat);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO cat) {

    cat = ProductService.update(id, cat);

    return ResponseEntity.ok().body(cat);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id) {

    ProductService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
