package infsus.dz3.dkbackend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

  private long idAppointment;
  private java.sql.Timestamp appointmentStart;
  private String reasonForAppointment;
  private DoctorDto doctor;
  private PatientDto patient;
  private ReferralDto referral;

}
