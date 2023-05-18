package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import infsus.dz3.dkbackend.utils.filters.enums.FilterType;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationService {

    MedicationRepository medicationRepository;
    ModelMapper modelMapper;

    public void insertMedication(Medication medication){
        medicationRepository.insertMedication(medication);
    }

    public void deleteMedication(int medicationId){
        medicationRepository.deleteMedication(medicationId);
    }

    public List<MedicationDto> getMedications(List<Filter> filters) {
        filters.add(new Filter<>(FilterType.BOOLEAN, "inUseFlag",true, null, null, null));
        List<Medication> medications = medicationRepository.getMedications(filters);
        List<MedicationDto> medicationsDto = medications.stream().map(med->modelMapper.map(med, MedicationDto.class)).collect(Collectors.toList());
        //TODO get company
        return medicationsDto;
    }
}
