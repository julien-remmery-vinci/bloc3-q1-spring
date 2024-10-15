package be.vinci.ipl.amazingproductservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Integer>{
  Iterable<Product> findByCategory(String category);
}
