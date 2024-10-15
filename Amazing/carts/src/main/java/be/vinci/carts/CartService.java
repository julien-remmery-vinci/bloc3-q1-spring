package be.vinci.carts;

import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private final CartRepository cartRepository;
  private final UsersProxy usersProxy;
  private final ProductsProxy productsProxy;

  public CartService(CartRepository cartRepository, UsersProxy usersProxy, ProductsProxy productsProxy) {
    this.cartRepository = cartRepository;
    this.usersProxy = usersProxy;
    this.productsProxy = productsProxy;
  }

  public void addProductToCart(String pseudo, int productId) {
    checkUserExists(pseudo);
    checkProductExists(productId);
    CartItem item = cartRepository.findItemByUserIdAndProductId(pseudo, productId);
    if(item == null) {
      item = new CartItem(new CartItemKey(pseudo, productId), 1);
    } else {
      item.setQuantity(item.getQuantity() + 1);
    }
    cartRepository.save(item);
  }

  public void removeProductFromCart(String pseudo, int productId) {
    checkUserExists(pseudo);
    checkProductExists(productId);
    List<CartItem> items = cartRepository.findItemsByUserId(pseudo);
    if(items == null) {
      items = new ArrayList<>();
    }
    items.removeIf(item -> item.getId().getProductId() == productId);
  }

  public List<CartItem> getCart(String pseudo) {
    checkUserExists(pseudo);
    return cartRepository.findItemsByUserId(pseudo);
  }

  public void clearCart(String pseudo) {
    checkUserExists(pseudo);
    List<CartItem> cart = cartRepository.findItemsByUserId(pseudo);
    if (cart == null) {
      throw new NotFoundException("Cart not found");
    }
    cart.clear();
  }

  public void removeProductFromAllCarts(int productId) {
    checkProductExists(productId);

  }

  private void checkUserExists(String pseudo) {
    if(usersProxy.readOne(pseudo) == null) {
      throw new NotFoundException("User not found");
    }
  }

  private void checkProductExists(int productId) {
    if(productsProxy.readOne(productId) == null) {
      throw new NotFoundException("Product not found");
    }
  }
}
