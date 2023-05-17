package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.dto.ReportDto;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MedicationController {

    MedicationService medicationService;

    @PostMapping(value="/insertMedication")
    public void insertMedication(@RequestBody Medication medication){
        medicationService.insertMedication(medication);
    }

    @GetMapping(value="/deleteMedication")
    public void deleteMedication(@RequestParam("medicationId") int medicationId){
        medicationService.deleteMedication(medicationId);
    }


}
