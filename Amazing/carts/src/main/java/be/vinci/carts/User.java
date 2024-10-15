package be.vinci.carts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
  private String pseudo;
  private String firstname;
  private String lastname;

  public boolean invalid() {
    return pseudo == null || pseudo.isBlank() ||
        firstname == null || firstname.isBlank() ||
        lastname == null || lastname.isBlank();
  }
}