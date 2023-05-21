package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerscriptionService {
    PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPrescriptions(int reportId){
        return prescriptionRepository.getPrescriptions(reportId);
    }

}
