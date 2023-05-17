package infsus.dz3.dkbackend.dto;

import infsus.dz3.dkbackend.model.Facility;
import infsus.dz3.dkbackend.model.Healthcareuser;
import infsus.dz3.dkbackend.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto extends Healthcareuser {
  private long idDoctor;
  private Facility facility;
  private Specialization specialization;

}
