package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.CategoryDTO;
import br.edu.ifmg.produto.entities.Category;
import br.edu.ifmg.produto.repository.CategoryRepository;
import br.edu.ifmg.produto.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryResource {

    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
       CategoryDTO category = service.findById(id);


        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        dto = categoryService.insert(dto);
        return ResponseEntity.created(uri).body(dto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto){

        dto = categoryService.update(id, dto);
        return ResponseEntity.ok().body(dto);

    }
}
