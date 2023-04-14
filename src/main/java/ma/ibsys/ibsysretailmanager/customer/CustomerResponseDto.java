package ma.ibsys.ibsysretailmanager.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {
  private Long id;

  private String name;

  private String email;

  private String phone;
  private String address;
}
