package infsus.dz3.dkbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private long idReport;
    private java.sql.Timestamp dateReport;
    private String diagnosis;
    private String recomendation;
    private String anamnesis;
    private DoctorDto doctor;
    private PatientDto patient;

}
