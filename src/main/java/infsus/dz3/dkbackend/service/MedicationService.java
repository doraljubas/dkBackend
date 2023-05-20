package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.HealthcareCompanyRepository;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import infsus.dz3.dkbackend.utils.filters.enums.FilterType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationService {

    MedicationRepository medicationRepository;
    HealthcareCompanyRepository healthcareCompanyRepository;
    ModelMapper modelMapper;

    public boolean updateMedication(MedicationDto medicationDto){
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        medication.setIdCompany(medicationDto.getCompany().getIdCompany());
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter<>(FilterType.EXACT, "inUseFlag",true, null, null, null));
        for(Medication med : medicationRepository.getMedications(filters)) {
            if(med.getNameMedication().equals(medication.getNameMedication())
                    && med.getTypeMedication().equals(medication.getTypeMedication())
                    && med.getIdCompany()==medication.getIdCompany()){
                return false;
            }
        }
        medicationRepository.updateMedication(medication);
        return true;

    }
    public boolean insertMedication(MedicationDto medicationDto){
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        medication.setIdCompany(medicationDto.getCompany().getIdCompany());
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter<>(FilterType.EXACT, "inUseFlag",true, null, null, null));
        for(Medication med : medicationRepository.getMedications(filters)) {
            if((med.getNameMedication().equals(medication.getNameMedication())
                && !med.getTypeMedication().equals(medication.getTypeMedication()))
                || (med.getNameMedication().equals(medication.getNameMedication())
                    && med.getTypeMedication().equals(medication.getTypeMedication())
                    && med.getIdCompany()==medication.getIdCompany())){
                return false;
            }
        }
        medicationRepository.insertMedication(medication);
        return true;
    }

    public void deleteMedication(long medicationId){
        medicationRepository.deleteMedication(medicationId);
    }

    public List<MedicationDto> getMedications(List<Filter> filters) {
        filters.add(new Filter<>(FilterType.EXACT, "inUseFlag",true, null, null, null));
        List<Medication> medications = medicationRepository.getMedications(filters);
        List<MedicationDto> medicationsDto = new ArrayList<>();//medications.stream().map(med->modelMapper.map(med, MedicationDto.class)).collect(Collectors.toList());
        for(Medication medication : medications){
            MedicationDto medicationDto = modelMapper.map(medication, MedicationDto.class);
            medicationDto.setCompany(healthcareCompanyRepository.getHealhcareCompany(medication.getIdCompany()));
            medicationsDto.add(medicationDto);
        }
        return medicationsDto;
    }
}
