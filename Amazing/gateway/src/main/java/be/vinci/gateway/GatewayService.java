package be.vinci.gateway;

import be.vinci.gateway.models.UnsafeCredentials;
import be.vinci.gateway.products.Product;
import be.vinci.gateway.users.User;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {
  private final AuthenticationProxy authenticationProxy;
  private final CartsProxy cartsProxy;
  private final ProductsProxy productsProxy;
  private final UsersProxy usersProxy;

  public GatewayService(AuthenticationProxy authenticationProxy, CartsProxy cartsProxy,
      ProductsProxy productsProxy, UsersProxy usersProxy) {
    this.authenticationProxy = authenticationProxy;
    this.cartsProxy = cartsProxy;
    this.productsProxy = productsProxy;
    this.usersProxy = usersProxy;
  }

  public void connect(UnsafeCredentials credentials) {
    authenticationProxy.connect(credentials);
  }

  public void verify(String token) {
    authenticationProxy.verify(token);
  }

  public void createOne(String pseudo, UnsafeCredentials credentials) {
    authenticationProxy.createOne(pseudo, credentials);
  }

  public void updateOne(String pseudo, UnsafeCredentials credentials) {
    authenticationProxy.updateOne(pseudo, credentials);
  }

  public void deleteCredentials(String pseudo) {
    authenticationProxy.deleteCredentials(pseudo);
  }

  public void getCart(String pseudo) {
    cartsProxy.getCart(pseudo);
  }

  public void addProductToCart(String pseudo, int productId) {
    cartsProxy.addProductToCart(pseudo, productId);
  }

  public void removeProductFromCart(String pseudo, int productId) {
    cartsProxy.removeProductFromCart(pseudo, productId);
  }

  public void clearCart(String pseudo) {
    cartsProxy.clearCart(pseudo);
  }

  public void removeProductFromAllCarts(int productId) {
    cartsProxy.removeProductFromAllCarts(productId);
  }

  public Iterable<Product> readAllProducts() {
    return productsProxy.readAll();
  }

  public Product readOneProduct(int id) {
    return productsProxy.readOne(id);
  }

  public Iterable<Product> readByCategory(String category) {
    return productsProxy.readByCategory(category);
  }

  public Product createOneProduct(Product product) {
    return productsProxy.createOne(product);
  }

  public Product updateOneProduct(int id, Product product) {
    productsProxy.updateOne(id, product);
    return product;
  }

  public void deleteOneProduct(int id) {
    productsProxy.deleteOne(id);
  }

  public void createOneUser(String pseudo, User user) {
    usersProxy.createOne(pseudo, user);
  }

  public User readOneUser(String pseudo) {
    return usersProxy.readOne(pseudo);
  }

  public void updateOneUser(String pseudo, User user) {
    usersProxy.updateOne(pseudo, user);
  }

  public void deleteOneUser(String pseudo) {
    usersProxy.deleteOne(pseudo);
  }
}
