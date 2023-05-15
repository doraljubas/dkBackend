package infsus.dz3.dkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Healthcareuser {
  private long idDoctor;
  private Facility facility;
  private Specialization specialization;

}
