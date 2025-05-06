package br.edu.ifmg.produto.util;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.entities.Product;

public class Factory {
    public static Product createProduct() {
        Product p = new Product();
        p.setName("Iphone XXX");
        p.setPrice(5000);
        p.setImageUrl("https://www.google.com/imgres?q=iphone%20X&imgurl=https%3A%2F%2Fi.zst.com.br%2Fthumbs%2F12%2Fc%2F12%2F-13310084.jpg");
        p.getCategories().add(new Category(60L, "News"));
        return p;
    }
    public static ProductDTO createProductDTO() {
        Product p = createProduct();
        return new ProductDTO();
    }
}
