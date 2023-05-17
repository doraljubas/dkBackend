package infsus.dz3.dkbackend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

  private long idMedication;
  private String medicationName;
  private String medicationType;
  private long idCompany;
  private boolean inUseFlag;

}
