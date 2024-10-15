package be.vinci.gateway;

import be.vinci.gateway.models.UnsafeCredentials;
import be.vinci.gateway.products.Product;
import be.vinci.gateway.users.User;
import feign.FeignException;
import feign.FeignException.Unauthorized;
import java.util.Objects;
import org.apache.coyote.BadRequestException;
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
public class GatewayController {
  private final GatewayService gatewayService;

  public GatewayController(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }

  @PostMapping("/authentication/connect")
  public void connect(@RequestBody UnsafeCredentials credentials) throws BadRequestException {
    try {
      gatewayService.connect(credentials);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new BadRequestException();
      } else if(e.status() == 401) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
      }
    }
  }
  
  @PostMapping("/authentication/verify")
  public void verify(@RequestBody String token) {
    try {
      gatewayService.verify(token);
    } catch (Unauthorized e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
  }
  
  @PostMapping("/authentication/{pseudo}")
  public void createOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials)
      throws BadRequestException {
    try {
      gatewayService.createOne(pseudo, credentials);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new BadRequestException();
      } else if(e.status() == 409) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
      }
    }
  }
  
  @PutMapping("/authentication/{pseudo}")
  public void updateOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials)
      throws BadRequestException {
    try {
      gatewayService.updateOne(pseudo, credentials);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new BadRequestException();
      } else if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
  }
  
  @DeleteMapping("/authentication/{pseudo}")
  public void deleteCredentials(@PathVariable String pseudo) {
    try {
      gatewayService.deleteCredentials(pseudo);
    } catch (FeignException e) {
      if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
  }
  
  @GetMapping("/carts/{pseudo}")
  public void getCart(@PathVariable String pseudo) {
    try {
      gatewayService.getCart(pseudo);
    } catch (FeignException e) {
      if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
  }

  @PostMapping("/carts/users/{pseudo}/products/{productId}")
  public void addProductToCart(@PathVariable String pseudo, @PathVariable int productId) {
    gatewayService.addProductToCart(pseudo, productId);
  }

  @DeleteMapping("/carts/users/{pseudo}/products/{productId}")
  public void removeProductFromCart(@PathVariable("pseudo") String pseudo, @PathVariable("productId") int productId) {
    gatewayService.removeProductFromCart(pseudo, productId);
  }

  @DeleteMapping("/carts/users/{pseudo}")
  public void clearCart(@PathVariable("pseudo") String pseudo) {
    gatewayService.clearCart(pseudo);
  }

  @DeleteMapping("/carts/products/{productId}")
  public void removeProductFromAllCarts(@PathVariable("productId") int productId) {
    gatewayService.removeProductFromAllCarts(productId);
  }

  @GetMapping("/products")
  public Iterable<Product> readAll() {
    return gatewayService.readAllProducts();
  }

  @GetMapping("/products/{id}")
  public Product readOne(@PathVariable int id) {
    try {
      return gatewayService.readOneProduct(id);
    } catch (FeignException e) {
      if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    return null;
  }

  @GetMapping("/products/category/{category}")
  public Iterable<Product> readByCategory(@PathVariable String category) {
    return gatewayService.readByCategory(category);
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createOne(@RequestBody Product product) {
    try {
      Product created = gatewayService.createOneProduct(product);
      return new ResponseEntity<>(created, HttpStatus.CREATED);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
    }
    return null;
  }

  @PutMapping("/products/{id}")
  public Product updateOne(@PathVariable int id, @RequestBody Product product) {
    try {
      return gatewayService.updateOneProduct(id, product);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      } else if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    return null;
  }

  @DeleteMapping("/products/{id}")
  public void deleteOne(@PathVariable int id) {
    try {
      gatewayService.deleteOneProduct(id);
    } catch (FeignException e) {
      if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
  }

  @PostMapping("/users/{pseudo}")
  public ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody User user) {
    try {
      gatewayService.createOneUser(pseudo, user);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      } else if(e.status() == 409) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
      }
    }
    return null;
  }

  @GetMapping("/users/{pseudo}")
  public User readOne(@PathVariable String pseudo) {
    try {
      return gatewayService.readOneUser(pseudo);
    } catch (FeignException e) {
      if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    return null;
  }

  @PutMapping("/users/{pseudo}")
  public void updateOne(@PathVariable String pseudo, @RequestBody User user) {
    try {
      gatewayService.updateOneUser(pseudo, user);
    } catch (FeignException e) {
      if(e.status() == 400) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      } else if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
  }

  @DeleteMapping("/users/{pseudo}")
  public void deleteOne(@PathVariable String pseudo) {
    try {
      gatewayService.deleteOneUser(pseudo);
    } catch (FeignException e) {
      if(e.status() == 404) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
  }
}
