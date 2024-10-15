package be.vinci.carts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "products")
public interface ProductsProxy {

  @GetMapping("/products/{productId}")
  Product readOne(int productId);
}
