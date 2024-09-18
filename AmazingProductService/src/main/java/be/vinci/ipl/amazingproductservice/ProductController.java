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
  private static final List<Product> products = new ArrayList<>();
  private static int lastId = 0;

  static {
    products.add(new Product(1, "Laptop", "Electronics", 1200));
    products.add(new Product(2, "Mouse", "Electronics", 20));
    lastId = products.size();
  }

  @GetMapping("/products")
  public Iterable<Product> readAll() {
    return products;
  }

  @GetMapping("/products/{id}")
  public Product readOne(@PathVariable int id) {
    if(id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    Product found = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    if(found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return found;
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createOne(@RequestBody Product product) {
    if(product.invalid()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    product.setId(lastId + 1);
    lastId++;

    products.add(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product) {
    if(product.invalid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    Product found = products.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    if(found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    product.setId(found.getId());
    products.remove(found);
    products.add(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public void deleteOne(@PathVariable int id) {
    if(id <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    Product found = products.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    if(found == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    products.remove(found);
  }
}
