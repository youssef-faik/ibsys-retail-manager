package ma.ibsys.ibsysretailmanager.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Product")
@Table(
    name = "product",
    uniqueConstraints = {
      @UniqueConstraint(name = "product_bar_code_unique", columnNames = "bar_code")
    })
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @NotBlank(message = "barCode is mandatory")
  @Column(name = "bar_code", nullable = false)
  private String barCode;
  
  @Size(min = 2, max = 1000, message = "name must be between {min} and {max} characters long")
  @NotBlank(message = "name is mandatory")
  @Column(name = "name", nullable = false)
  private String name;
  
  @NotNull(message = "sellingPriceExcludingTax is mandatory")
  @Positive(message = "sellingPriceExcludingTax must be a positive number")
  @Column(name = "selling_price_excluding_tax", nullable = false)
  private BigDecimal sellingPriceExcludingTax;
  
  @NotNull(message = "purchasePrice is mandatory")
  @Positive(message = "priceExcludingTax must be a positive number")
  @Column(name = "purchase-price", nullable = false)
  private BigDecimal purchasePrice;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "taxRate is mandatory")
  @Column(name = "tax_rate", nullable = false)
  private TaxRate taxRate;
}
