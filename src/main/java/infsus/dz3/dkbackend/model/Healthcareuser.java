package infsus.dz3.dkbackend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Healthcareuser {

  private long idUser;
  private String name;
  private String surname;
  private String oib;
  private java.sql.Date dateOfBirth;
  private String mbo;
  private String sex;
  private String username;

}
