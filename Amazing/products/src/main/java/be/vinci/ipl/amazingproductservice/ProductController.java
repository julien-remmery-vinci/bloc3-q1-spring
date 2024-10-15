package be.vinci.ipl.amazingproductservice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductController {

  private final ProductsService productsService;

  public ProductController(ProductsService productsService) {
    this.productsService = productsService;
  }

  @GetMapping("/products")
  public Iterable<Product> readAll() {
    return productsService.readAll();
  }

  @GetMapping("/products/{id}")
  public Product readOne(@PathVariable int id) {
    if(id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    Product found = productsService.readOne(id);
    if(found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return found;
  }

  @GetMapping("/products/category/{category}")
  public Iterable<Product> readByCategory(@PathVariable String category) {
    if(category == null || category.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    return productsService.readByCategory(category);
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createOne(@RequestBody Product product) {
    if(product.invalid()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Product created = productsService.createOne(product);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @PutMapping("/products/{id}")
  public Product updateOne(@PathVariable int id, @RequestBody Product product) {
    if (id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    if(product.invalid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    Product found = productsService.readOne(id);
    if(found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    return productsService.updateOne(id, product);
  }

  @DeleteMapping("/products/{id}")
  public void deleteOne(@PathVariable int id) {
    if(id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    Product found = productsService.readOne(id);
    if(found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    productsService.deleteOne(id);
  }
}
