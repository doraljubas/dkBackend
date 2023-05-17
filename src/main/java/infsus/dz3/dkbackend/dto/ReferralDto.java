package infsus.dz3.dkbackend.dto;

import infsus.dz3.dkbackend.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReferralDto {

  private long idReferral;
  private java.sql.Date dateIssued;
  private String purpose;
  private boolean used;
  private String reasonOfIssue;
  private Specialization specialization;
  private DoctorDto doctor;
  private PatientDto patient;

}
