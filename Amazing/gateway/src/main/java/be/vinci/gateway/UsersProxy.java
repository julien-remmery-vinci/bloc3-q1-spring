package be.vinci.gateway;

import be.vinci.gateway.users.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users")
public interface UsersProxy {
  @PostMapping("/users/{pseudo}")
  ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody User user);

  @GetMapping("/users/{pseudo}")
  User readOne(@PathVariable String pseudo);

  @PutMapping("/users/{pseudo}")
  void updateOne(@PathVariable String pseudo, @RequestBody User user);

  @DeleteMapping("/users/{pseudo}")
  void deleteOne(@PathVariable String pseudo);
}
