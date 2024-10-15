package be.vinci.gateway;

import be.vinci.gateway.products.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "products")
public interface ProductsProxy {
  @GetMapping("/products")
  Iterable<Product> readAll();

  @GetMapping("/products/{id}")
  Product readOne(@PathVariable int id);

  @GetMapping("/products/category/{category}")
  Iterable<Product> readByCategory(@PathVariable String category);

  @PostMapping("/products")
  Product createOne(@RequestBody Product product);

  @PutMapping("/products/{id}")
  Product updateOne(@PathVariable int id, @RequestBody Product product);

  @DeleteMapping("/products/{id}")
  void deleteOne(@PathVariable int id);
}
