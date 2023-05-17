package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class MedicationService {

    MedicationRepository medicationRepository;

    public void insertMedication(Medication medication){
        medicationRepository.insertMedication(medication);
    }

    public void deleteMedication(int medicationId){
        medicationRepository.deleteMedication(medicationId);
    }
}
