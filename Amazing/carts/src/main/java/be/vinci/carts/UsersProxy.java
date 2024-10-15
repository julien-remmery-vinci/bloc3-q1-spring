package be.vinci.carts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "users")
public interface UsersProxy {
  @GetMapping("/users/{pseudo}")
  User readOne(String pseudo);
}
