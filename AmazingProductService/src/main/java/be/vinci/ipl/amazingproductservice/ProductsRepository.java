package be.vinci.ipl.amazingproductservice;

import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Integer>{
  Iterable<Product> findByCategory(String category);
}
