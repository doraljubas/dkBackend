package infsus.dz3.dkbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Report {

  private long idReport;
  private java.sql.Timestamp dateReport;
  private String diagnosis;
  private String recomendation;
  private String anamnesis;
  private long idDoctor;
  private long idPatient;

}
