package infsus.dz3.dkbackend.dto;


import infsus.dz3.dkbackend.model.Healthcareuser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto extends Healthcareuser {

  private long idPatient;
  private DoctorDto familyDoctor;

}
