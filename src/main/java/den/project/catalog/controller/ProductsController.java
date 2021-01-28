package den.project.catalog.controller;

import den.project.catalog.model.Products;
import den.project.catalog.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    @Autowired
    private ProductsRepository repository;

    @GetMapping
    // @Produces("application/json")//сериализация, для формата XML - "application/xml"
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> productsList;
        productsList = repository.getAll();
        return ResponseEntity.ok().body(productsList);
    }

    @GetMapping("/{id}")
    // @Produces("application/json")//сериализация, для формата XML - "application/xml"
    public Products getById(@PathVariable int id) {
        Products products = repository.get(id);
        if (products == null) {
            ///Response.status(404).build();
            return null;
        } else {
            //Response.status(200).entity(products);
            return products;
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducts(@PathVariable int id) {
        Products products = repository.get(id);
        if (products != null) {
            repository.delete(id);
            //      Response.status(200).build();
        }
        //   Response.status(404).build();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    //  @Consumes("application/json")//ДЕсериализация, для формата XML - "application/xml"
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Products> create(@RequestBody Products p) {
        Products products = repository.save(p);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/")
                .buildAndExpand(p.getId()).toUri();
        //   Response.status(201).entity(products).build();
        return ResponseEntity.created(uriOfNewResource).body(products);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Products> update(@RequestBody Products products,
                                           @PathVariable("id") int id) throws EntityNotFoundException {
        Products newProducts = repository.get(id);
        if (newProducts == null) {
            throw new EntityNotFoundException("id-" + id);
        }
        newProducts.setName(products.getName());
        newProducts.setDescription(products.getDescription());
        return ResponseEntity.ok().body(repository.update(newProducts));

    }
}