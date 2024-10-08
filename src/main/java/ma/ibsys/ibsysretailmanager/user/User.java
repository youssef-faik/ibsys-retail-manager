package ma.ibsys.ibsysretailmanager.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ibsys.ibsysretailmanager.security.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "User")
@Table(
        name = "_user",
        uniqueConstraints = {@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private int id;

  @Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters.")
  @NotBlank(message = "First name is required.")
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Size(min = 2, max = 50, message = "Last name must be between {min} and {max} characters.")
  @NotBlank(message = "Last name is required.")
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Email(message = "Email must be valid.")
  @NotBlank(message = "Email is required.")
  @Column(name = "email", nullable = false)
  private String email;

  @Size(min = 8, message = "Password must be between {min} and {max} characters.")
  @NotBlank(message = "Password is required.")
  @Column(name = "password", nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "Role is required.")
  @Column(name = "role", nullable = false)
  private Role role;

  @NotNull(message = "Enabled status is required.")
  @Column(name = "is_enabled", nullable = false)
  private boolean isEnabled = true;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}
