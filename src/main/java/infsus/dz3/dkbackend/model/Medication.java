package infsus.dz3.dkbackend.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

  private long idMedication;
  private String medicationName;
  private String medicationType;


  public long getIdMedication() {
    return idMedication;
  }




  public String getMedicationName() {
    return medicationName;
  }



  public String getMedicationType() {
    return medicationType;
  }


}
