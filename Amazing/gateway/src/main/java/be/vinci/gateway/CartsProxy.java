package be.vinci.gateway;

import be.vinci.gateway.carts.CartItem;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "carts")
public interface CartsProxy {
  @GetMapping("/carts/users/{pseudo}")
  List<CartItem> getCart(@PathVariable("pseudo") String pseudo);

  @PostMapping("/carts/users/{pseudo}/products/{productId}")
  void addProductToCart(@PathVariable("pseudo") String pseudo, @PathVariable("productId") int productId);

  @DeleteMapping("/carts/users/{pseudo}/products/{productId}")
  void removeProductFromCart(@PathVariable("pseudo") String pseudo, @PathVariable("productId") int productId);

  @DeleteMapping("/carts/users/{pseudo}")
  void clearCart(@PathVariable("pseudo") String pseudo);

  @DeleteMapping("/carts/products/{productId}")
  void removeProductFromAllCarts(@PathVariable("productId") int productId);
}
