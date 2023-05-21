package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.PrescriptionDto;
import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PrescriptionService {
    PrescriptionRepository prescriptionRepository;
    MedicationService medicationService;

    ModelMapper modelMapper;

    //Dohvat svih recepata nalaza za zadani reportId
    public List<PrescriptionDto> getPrescriptions(int reportId){
        List<Prescription> prescriptions = prescriptionRepository.getPrescriptions(reportId);
        List<PrescriptionDto> prescriptionsDto =new ArrayList<>();
        for(Prescription prescription : prescriptions){
            PrescriptionDto prescriptionDto = modelMapper.map(prescription, PrescriptionDto.class);
            prescriptionDto.setMedication(medicationService.getMedication(prescription.getIdMedication()));
            prescriptionsDto.add(prescriptionDto);
        }
        return prescriptionsDto;
    }

}
