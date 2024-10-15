package be.vinci.ipl.catflix.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnsafeCredentials {
    private String pseudo;
    private String password;

    public boolean invalid() {
        return pseudo == null || pseudo.isBlank() ||
                password == null || password.isBlank();
    }
}
