package be.vinci.ipl.amazingproductservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

  @Id
  private int id;
  private String name;
  private String category;
  private double price;

  public boolean invalid() {
    return name == null || name.isEmpty() || category == null || category.isEmpty() || price <= 0;
  }
}
