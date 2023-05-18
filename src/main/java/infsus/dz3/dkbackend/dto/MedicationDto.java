package infsus.dz3.dkbackend.dto;
import infsus.dz3.dkbackend.model.HealthcareCompany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {

  private long idMedication;
  private String nameMedication;
  private String typeMedication;
  private HealthcareCompany company;
  private boolean inUseFlag;

}
