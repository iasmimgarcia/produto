package br.edu.ifmg.produto.dtos;

import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;

    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO() {

    }

    public ProductDTO(Long id, String name, String description, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();

        entity.getCategories().stream().forEach(c -> this.categories.add(new CategoryDTO(c)));
    }

    public ProductDTO(Product product, Set<Category> categories) {
        this(product);
        categories.forEach(c -> this.categories.add(new CategoryDTO(c)));
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductDTO product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
