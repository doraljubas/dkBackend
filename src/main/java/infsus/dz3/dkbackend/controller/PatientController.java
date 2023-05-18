package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.dto.PatientDto;
import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.service.PatientService;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {

    PatientService patientService;

    @PostMapping(value="/getPatients")
    public List<PatientDto> getPatients(@RequestParam("doctorId") long doctorId, @RequestBody List<Filter> filters){
        return patientService.getPatients(doctorId, filters);
    }

    @GetMapping(value="/getPatient")
    public PatientDto getPatient(@RequestParam("patientId") long patientId){
        return patientService.getPatient(patientId);
    }
}
