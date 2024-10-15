package be.vinci.ipl.catflix.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentification")
public interface AuthenticationProxy {

  @PostMapping("/authentication/{pseudo}")
  ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials);

  @PutMapping("/authentication/{pseudo}")
  void updateOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials);

  @DeleteMapping("/authentication/{pseudo}")
  void deleteCredentials(@PathVariable String pseudo);

}
