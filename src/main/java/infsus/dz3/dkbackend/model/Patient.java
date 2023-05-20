package infsus.dz3.dkbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Healthcareuser {

  private long idPatient;
  private long idDoctor;

}
