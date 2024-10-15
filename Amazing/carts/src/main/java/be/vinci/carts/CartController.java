package be.vinci.carts;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
  final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/carts/users/{pseudo}")
  public List<CartItem> getCart(@PathVariable("pseudo") String pseudo) {
    checkPseudo(pseudo);
    List<CartItem> cart = cartService.getCart(pseudo);
    if(cart == null) {
      throw new NotFoundException("Cart not found");
    }
    return cart;
  }

  @PostMapping("/carts/users/{pseudo}/products/{productId}")
  public void addProductToCart(@PathVariable("pseudo") String pseudo, @PathVariable("productId") int productId) {
    checkPseudo(pseudo);
    checkProductId(productId);
    cartService.addProductToCart(pseudo, productId);
  }

  @DeleteMapping("/carts/users/{pseudo}/products/{productId}")
  public void removeProductFromCart(@PathVariable("pseudo") String pseudo, @PathVariable("productId") int productId) {
    checkPseudo(pseudo);
    checkProductId(productId);
    cartService.removeProductFromCart(pseudo, productId);
  }

  @DeleteMapping("/carts/users/{pseudo}")
  public void clearCart(@PathVariable("pseudo") String pseudo) {
    checkPseudo(pseudo);
    cartService.clearCart(pseudo);
  }

  @DeleteMapping("/carts/products/{productId}")
  public void removeProductFromAllCarts(@PathVariable("productId") int productId) {
    checkProductId(productId);
    cartService.removeProductFromAllCarts(productId);
  }

  private void checkPseudo(String pseudo) {
    if(pseudo.isEmpty()) {
      throw new BadRequestException("Pseudo is empty");
    }
  }
  private void checkProductId(int productId) {
    if(productId < 0) {
      throw new BadRequestException("Product id is negative");
    }
  }
}
