package be.vinci.carts;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartItem, String> {

  List<CartItem> findItemsByUserId(String userId);
  CartItem findItemByUserIdAndProductId(String userId, int productId);

}
