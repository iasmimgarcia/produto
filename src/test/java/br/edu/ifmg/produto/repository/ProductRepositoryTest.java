package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.util.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Long existingId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
    }

    @Test
    @DisplayName("Verificando se o objeto não existe no bd depois de deletado.")
    public void deleteShouldDeleteObjectWhenIdExists() {

        productRepository.deleteById(existingId);
        Optional<Product> obj = productRepository.findById(existingId);

        Assertions.assertFalse(obj.isPresent());
    }

    @Test
    @DisplayName("Verificando o autoincremento da chave primária.")
    public void insertShouldPersistWithAutoincrementIdWhenId0(){
        Product product = Factory.createProduct();
        product.setId(null);

        Product p = productRepository.save(product);

        Optional<Product> obj = productRepository.findById(p.getId());
        Assertions.assertTrue(obj.isPresent());
        Assertions.assertNotEquals(0, obj.get().getId());
        Assertions.assertEquals(26, obj.get().getId());
    }
}
