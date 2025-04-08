package br.edu.ifmg.produto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String description;
    private double price;
    private String imageUrl;

    private Instant createdAt;
    private Instant updatedAt;

    @ManyToMany
    @JoinTable(name = "tb_product_category" ,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Product() {

    }

    public Product(Long id, String nome, String description, double price, String imageUrl) {
        this.id = id;
        this.nome = nome;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    /*
    public Product (Product product) {
        this.id = entity.getId();
    }

    public Product(Product product, Set<Category> categories) {
        this(product);
    }

    */
    
}
