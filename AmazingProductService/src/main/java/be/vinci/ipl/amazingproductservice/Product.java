package be.vinci.ipl.amazingproductservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {
  private int id;
  private String name;
  private String category;
  private double price;

  public boolean invalid() {
    return name == null || name.isEmpty() || category == null || category.isEmpty() || price <= 0;
  }
}
