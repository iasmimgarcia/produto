package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.ProductDTO;
import br.edu.ifmg.produto.services.ProductService;
import br.edu.ifmg.produto.util.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(value = ProductResource.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class ProductResourceTest {

    @Autowired
    //a qual quero testar
    private MockMvc mockMvc; //responsável pelas requisições


    //camada que quero mocar
    @MockitoBean
    private ProductService productService;

    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() {

        existingId = 1L;
        nonExistingId = 2000L;
        productDTO = Factory.createProductDTO();
        productDTO.setId(1L);
        page = new PageImpl<>(List.of(productDTO));
    }

    @Test
    void findAllShouldReturnAllPages() throws Exception {

        //criar o método mocado
        when(productService.findAll(any())).thenReturn(page);

        //teste a requisição
        ResultActions resultActions =
                mockMvc.perform(
                        get("/product")
                                .accept("application/json"));

        //Analisa o resultado
        resultActions.andExpect(status().isOk());
    }

    @Test
    void findByIdShouldReturnProductsWhenIdExists() throws Exception {

        //criar o método mocado
        when(productService.findById(any())).thenReturn(productDTO);

        //teste a requisição
        ResultActions resultActions =
                mockMvc.perform(
                        get("/product/{id}", existingId)
                                .accept("application/json"));

        //Analisa o resultado
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").value(existingId));
        resultActions.andExpect(jsonPath("$.name").value(productDTO.getName()));
        resultActions.andExpect(jsonPath("$.description").value(productDTO.getDescription()));
    }
}