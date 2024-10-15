package be.vinci.gateway;

import be.vinci.gateway.models.UnsafeCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication")
public interface AuthenticationProxy {
  @PostMapping("/authentication/connect")
  String connect(@RequestBody UnsafeCredentials credentials);

  @PostMapping("/authentication/verify")
  String verify(@RequestBody String token);

  @PostMapping("/authentication/{pseudo}")
  ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials);

  @PutMapping("/authentication/{pseudo}")
  void updateOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials);

  @DeleteMapping("/authentication/{pseudo}")
  void deleteCredentials(@PathVariable String pseudo);
}
