package infsus.dz3.dkbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

  private long idPrescription;
  private java.sql.Date dateOfExpiry;
  private String instructions;
  private boolean renewing;
  private Medication medication;
  private Report report;



}
