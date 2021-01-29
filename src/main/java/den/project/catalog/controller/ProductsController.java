package den.project.catalog.controller;

import den.project.catalog.model.Products;
import den.project.catalog.repository.ProductsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Products.",
        tags = {"products"})
public class ProductsController {

    @Autowired
    private ProductsRepository repository;

    @ApiOperation(value = "Find Products by name", notes = "Name search by %name% format", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = List.class)})
    @GetMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> productsList;
        productsList = repository.getAll();
        return ResponseEntity.ok().body(productsList);
    }

    @ApiOperation(value = "finding the product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=Products.class),
            @ApiResponse(code = 404, message = "Product not found") })
    @GetMapping("/{id}")
    public Products getById(@PathVariable int id) {
        Products products = repository.get(id);
        if (products == null) {
            return null;
        } else {
            return products;
        }
    }

    @ApiOperation(value = "Delete a product", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Product not found")})
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducts(@PathVariable int id) {
        Products products = repository.get(id);
        if (products != null) {
            repository.delete(id);
        }
    }

    @ApiOperation(value = "Create the product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product created"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Product already exists") })
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Products> create(@RequestBody Products p) {
        Products products = repository.save(p);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products")
                .buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(products);
    }

    @ApiOperation(value = "Update the product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Product not found") })
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