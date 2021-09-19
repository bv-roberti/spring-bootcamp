package com.superdev.dscatalog.dto;

import com.superdev.dscatalog.entities.Category;
import com.superdev.dscatalog.entities.Product;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO implements Serializable {

  private List<CategoryDTO> categories = new ArrayList<>();

  private Long id;
  private String img_url;
  private String name;
  private String description;
  private Double price;
  private Instant date;

  public ProductDTO(
      Long id, String img_url, String name, String description, Double price, Instant date) {
    this.id = id;
    this.img_url = img_url;
    this.name = name;
    this.description = description;
    this.price = price;
    this.date = date;
  }

  public ProductDTO(Product entity) {

    this.id = entity.getId();
    this.img_url = entity.getImg_url();
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.date = entity.getDate();
  }

  public ProductDTO() {}

  public ProductDTO(Product entity, Set<Category> categories) {
    this(entity);

    categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
  }

  public List<CategoryDTO> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryDTO> categories) {
    this.categories = categories;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImg_url() {
    return img_url;
  }

  public void setImg_url(String img_url) {
    this.img_url = img_url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }
}
