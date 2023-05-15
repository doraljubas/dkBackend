package infsus.dz3.dkbackend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

  private long idAppointment;
  private java.sql.Timestamp appointmentStart;
  private String reasonForAppointment;
  private Doctor doctor;
  private Patient patient;
  private Referral referral;



}
