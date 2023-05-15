package infsus.dz3.dkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Referral {

  private long idReferral;
  private java.sql.Date dateIssued;
  private String purpose;
  private boolean used;
  private String reasonOfIssue;
  private Specialization specialization;
  private Doctor doctor;
  private Patient patient;






}
