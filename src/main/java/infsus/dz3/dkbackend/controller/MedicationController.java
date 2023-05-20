package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.service.MedicationService;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MedicationController {

    MedicationService medicationService;

    @PostMapping(value="/insertMedication")
    public int insertMedication(@RequestBody MedicationDto medication){
        return medicationService.insertMedication(medication);
    }

    @GetMapping(value="/deleteMedication")
    public void deleteMedication(@RequestParam("medicationId") long medicationId){
        medicationService.deleteMedication(medicationId);
    }
    @PostMapping(value="/editMedication")
    public int updateMedication(@RequestBody MedicationDto medication){
        return medicationService.updateMedication(medication);
    }
    @PostMapping(value="/getMedications")
    public List<MedicationDto> getMedications(@RequestBody List<Filter> filters){
        return medicationService.getMedications(filters);
    }
}
