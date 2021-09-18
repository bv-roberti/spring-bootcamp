package com.superdev.dscatalog.dto;

import com.superdev.dscatalog.entities.Category;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

  private Long id;

  private String name;

  public CategoryDTO(Category cat) {
    this.id = cat.getId();
    this.name = cat.getName();
  }

  public CategoryDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
