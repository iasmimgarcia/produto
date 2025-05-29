package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.projections.ProductProjection;
import br.edu.ifmg.produto.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true,
    value = """
        SELECT * from
        (
            SELECT DISTINCT 
                p.id, p.name, p.image_url, p.price
            FROM 
                tb_product p
            INNER JOIN  
                tb_product_category pc ON pc.product_id = p.id
            WHERE 
                (:categories_ID IS NULL || pc.category_id in :categoriesID) 
                AND LOWER(p.name) like LOWER(CONCAT('%',:name,'%'))
        ) as tb_result
    """,
    countQuery = """
        SELECT count(*) from
            (
            SELECT DISTINCT 
                p.id, p.name, p.image_url, p.price
            FROM 
                tb_product p
            INNER JOIN  
                tb_product_category pc ON pc.product_id = p.id
            WHERE 
                (:categories_ID IS NULL || pc.category_id in :categoriesID) 
                AND LOWER(p.name) like LOWER(CONCAT('%',:name,'%'))
            ) as tb_result
        """
    )

    public Page<ProductProjection> searchProducts(List<Long> categoriesID, String name, Pageable pageable);


}
