package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.HealthcareCompanyRepository;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import infsus.dz3.dkbackend.utils.filters.enums.FilterType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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


    public int updateMedication(MedicationDto medicationDto){
        Medication medication = modelMapper.map(medicationDto, Medication.class);
        medication.setIdCompany(medicationDto.getCompany().getIdCompany());
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter<>(FilterType.EXACT, "inUseFlag",true, null, null, null));
        for(Medication med : medicationRepository.getMedications(filters)) {
            if(med.getIdMedication() != medication.getIdMedication()){
                if(med.getNameMedication().equalsIgnoreCase(medication.getNameMedication())
                        && !med.getTypeMedication().equalsIgnoreCase(medication.getTypeMedication())){
                    return 1;
                }
                if(med.getNameMedication().equalsIgnoreCase(medication.getNameMedication())
                        && med.getTypeMedication().equalsIgnoreCase(medication.getTypeMedication())
                        && med.getIdCompany()==medication.getIdCompany()){
                    return 2;
                }
            }
        }
        medicationRepository.updateMedication(medication);
        return 0;

    }
    public int insertMedication(MedicationDto medicationDto){

        Medication medication = modelMapper.map(medicationDto, Medication.class);
        medication.setIdCompany(medicationDto.getCompany().getIdCompany());

        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter<>(FilterType.EXACT, "inUseFlag",true, null, null, null));
        for(Medication med : medicationRepository.getMedications(filters)) {
            if(med.getNameMedication().equalsIgnoreCase(medication.getNameMedication())
                && !med.getTypeMedication().equalsIgnoreCase(medication.getTypeMedication())){
                return 1;
            }
            if(med.getNameMedication().equalsIgnoreCase(medication.getNameMedication())
                    && med.getTypeMedication().equalsIgnoreCase(medication.getTypeMedication())
                    && med.getIdCompany()==medication.getIdCompany()){
                return 2;
            }
        }
        medicationRepository.insertMedication(medication);
        return 0;
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

    public MedicationDto getMedication(long medicationId){
        Medication medication = medicationRepository.getMedication(medicationId);
        MedicationDto medicationDto = modelMapper.map(medication, MedicationDto.class);
        medicationDto.setCompany(healthcareCompanyRepository.getHealhcareCompany(medication.getIdCompany()));
        return medicationDto;
    }
}
