package infsus.dz3.dkbackend.dto;

import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.model.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {

    private long idPrescription;
    private java.sql.Date dateOfExpiry;
    private String instructions;
    private boolean renewing;
    private MedicationDto medication;
    private Report report;
}
