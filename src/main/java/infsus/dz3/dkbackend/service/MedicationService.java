package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.HealhcareCompanyRepository;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import infsus.dz3.dkbackend.utils.filters.enums.FilterType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationService {

    MedicationRepository medicationRepository;
    HealhcareCompanyRepository healthcareCompanyRepository;
    ModelMapper modelMapper;

    public void updateMedication(MedicationDto medicationDto){
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        medication.setIdCompany(medicationDto.getCompany().getIdCompany());
        medicationRepository.insertMedication(medication);
    }
    public void insertMedication(MedicationDto medicationDto){
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        medication.setIdCompany(medicationDto.getCompany().getIdCompany());
        medicationRepository.insertMedication(medication);
    }

    public void deleteMedication(long medicationId){
        medicationRepository.deleteMedication(medicationId);
    }

    public List<MedicationDto> getMedications(List<Filter> filters) {
        filters.add(new Filter<>(FilterType.EXACT, "inUseFlag",true, null, null, null));
        List<Medication> medications = medicationRepository.getMedications(filters);
        List<MedicationDto> medicationsDto = medications.stream().map(med->modelMapper.map(med, MedicationDto.class)).collect(Collectors.toList());
        for(MedicationDto medication : medicationsDto){
            medication.setCompany(healthcareCompanyRepository.getHealhcareCompany(medication.getCompany().getIdCompany()));
        }
        return medicationsDto;
    }
}
