package be.vinci.ipl.amazingproductservice;

import org.springframework.stereotype.Service;

@Service
public class ProductsService {
  private final ProductsRepository productsRepository;

  public ProductsService(ProductsRepository productsRepository) {
    this.productsRepository = productsRepository;
  }

  public Iterable<Product> readAll() {
    return productsRepository.findAll();
  }

  public Product readOne(int id) {
    return productsRepository.findById(id).orElse(null);
  }

  public Iterable<Product> readByCategory(String category) {
    return productsRepository.findByCategory(category);
  }

  public Product createOne(Product product) {
    return productsRepository.save(product);
  }

  public Product updateOne(int id, Product product) {
    product.setId(id);
    return productsRepository.save(product);
  }

  public void deleteOne(int id) {
    productsRepository.deleteById(id);
  }
}
